package com.revature.erm.servlets;

import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.requests.NewUserRequest;
import com.revature.erm.dtos.requests.UpdateReimbursementRequest;
import com.revature.erm.dtos.responses.Principal;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.dtos.responses.UserResponse;
import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import com.revature.erm.services.ReimbursementService;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] reqFrags = req.getRequestURI().split("/");
        if (reqFrags.length == 4 && reqFrags[3].equals("availability")) {
            //checkAvailability(req, resp);
            return; // necessary, otherwise we end up doing more work than was requested
        }

        // TODO implement some security logic here to protect sensitive operations

        // get users (all, by id, by w/e)
        HttpSession session = req.getSession(false);
        if (session == null) {
            resp.setStatus(401);
            return;
        }

        Principal requester = (Principal) session.getAttribute("authUser");

        if (!requester.getRole().equals("ADMIN")) {
            resp.setStatus(403); // FORBIDDEN
        }

        /*List<UserResponse> users = userService.getAllUsers();
        String payload = mapper.writeValueAsString(users);
        resp.setContentType("application/json");
        resp.getWriter().write(payload);*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter respWriter = resp.getWriter();

        try {

            HttpSession session = req.getSession(false);
            if (session == null) {
                System.out.println("ReimbursementServlet session==null throws 401 error");
                resp.setStatus(401);
                return;
            }
            else {
                System.out.println("session!=null, doesn't throw exception");
                System.out.println(session.getAttribute("authUser"));
                String sessionUserId = parseSessionUserId(session);//pull id from the session so the authorId can match


                NewReimbursementRequest newReimbursementRequest = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
                System.out.println("about to launch submitNewReimbursement in ReimbursementServlet.java");

                newReimbursementRequest.setAuthorId(sessionUserId); //set authorId being pulled from the session
                System.out.println("setAuthorId(sessionUserId) in reimbursementServlet has been called");
                Reimbursement newReimbursement = reimbursementService.submitNewReimbursment(newReimbursementRequest);
                resp.setStatus(201); // CREATED
                resp.setContentType("application/json");
                String payload = mapper.writeValueAsString(new ResourceCreationResponse(newReimbursement.getId()));
                respWriter.write(payload);
            }

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

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter respWriter = resp.getWriter();

        /*try {

            //NewReimbursementRequest newReimbursementRequest = mapper.readValue(req.getInputStream(), NewReimbursementRequest.class);
            //System.out.println("about to launch submitNewReimbursement in ReimbursementServlet.java");
            //Reimbursement newReimbursement = reimbursementService.submitNewReimbursment(newReimbursementRequest);
            //resp.setStatus(201); // CREATED
            //resp.setContentType("application/json");
            //String payload = mapper.writeValueAsString(new ResourceCreationResponse(newReimbursement.getId()));
            //respWriter.write(payload);

            *//*String approveOrNot = mapper.readValue(req.getInputStream(), String.class);
            boolean result = reimbursementService.approveReimbursement(approveOrNot);*//*


        } catch (InvalidRequestException | DatabindException e) {
            e.printStackTrace();
            resp.setStatus(400); // BAD REQUEST
        } catch (ResourceConflictException e) {
            resp.setStatus(409); // CONFLICT
        } catch (Exception e) {
            e.printStackTrace(); // include for debugging purposes; ideally log it to a file
            resp.setStatus(500);
        }*/
    }

    public String parseSessionUserId(HttpSession session){

        Principal parseThis = (Principal) session.getAttribute("authUser");
        System.out.println("printing authUser attribute as a string: " + parseThis);
        System.out.println("printing principal's id: " + parseThis.getId());

        return parseThis.getId();
    }

}
