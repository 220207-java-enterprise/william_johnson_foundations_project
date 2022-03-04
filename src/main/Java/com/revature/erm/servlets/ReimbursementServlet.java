package com.revature.erm.servlets;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import com.revature.erm.services.ReimbursementService;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReimbursementServlet extends HttpServlet{

    private final ReimbursementService reimbursementService;
    private final ObjectMapper mapper;
    // doGet: get reimbs by id, by status, by authorid
    // doPost: saving a new reimb
    // doPut: updating a reimb (approve/deny)

    public ReimbursementServlet(ReimbursementService reimbursementService, ObjectMapper mapper) {
        this.reimbursementService = reimbursementService;
        this.mapper = mapper;
    }

    /*@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }*/

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter respWriter = resp.getWriter();

        try {

            NewReimbursementRequest newReimbursementRequest = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
            System.out.println("about to launch submitNewReimbursement in ReimbursementServlet.java");
            Reimbursement newReimbursement = reimbursementService.submitNewReimbursment(newReimbursementRequest);
            resp.setStatus(201); // CREATED
            resp.setContentType("application/json");
            String payload = mapper.writeValueAsString(new ResourceCreationResponse(newReimbursement.getId()));
            respWriter.write(payload);

        } catch (InvalidRequestException | DatabindException e) {
            e.printStackTrace();
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }

    }

}
