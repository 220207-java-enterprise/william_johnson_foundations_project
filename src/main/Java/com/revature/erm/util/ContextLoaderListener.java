package com.revature.erm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.erm.daos.UserDAO;
import com.revature.erm.services.UserService;
import com.revature.erm.daos.UserDAO;
import com.revature.erm.services.UserService;
import com.revature.erm.servlets.AuthServlet;
import com.revature.erm.servlets.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Spinning up foundations project web application");

        ObjectMapper mapper = new ObjectMapper();

        UserDAO userDAO = new UserDAO();
        UserService userService = new UserService(userDAO);
        UserServlet userServlet = new UserServlet(userService, mapper);
        AuthServlet authServlet = new AuthServlet(userService, mapper);
        // TODO instantiate the ReimbursementServlet and wire all of its dependencies

        // TODO register the ReimbursementServlet to handle requests to /reimbursements
        // Programmatic Servlet Registration
        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("ReimbursementServlet", userServlet).addMapping("/reimbursements/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Closing down foundations project web application");
    }

}
