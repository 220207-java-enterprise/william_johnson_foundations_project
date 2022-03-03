package com.revature.erm.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.services.ReimbursementService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReimbursementServlet {

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

    }

}
