package com.revature.erm.services;

import com.revature.erm.daos.ReimbursementDAO;
import com.revature.erm.dtos.requests.NewReimbursementRequest;
import com.revature.erm.dtos.responses.ResourceCreationResponse;
import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import com.revature.erm.models.UserRole;
import com.revature.erm.util.exceptions.InvalidRequestException;
import com.revature.erm.util.exceptions.ResourceConflictException;

import java.util.List;
import java.util.UUID;

public class ReimbursementService {

    private ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {this.reimbursementDAO = reimbursementDAO;}

    public List<Reimbursement> getReimbursementByAuthorId(String authorId){
        return null;
    }

    public List<Reimbursement> getReimbursementByStatusId(String statusId){
        return null;
    }

    public ResourceCreationResponse saveNewReimbursment(NewReimbursementRequest newReimbursementRequest) {
        Reimbursement newReimbursement = newReimbursementRequest.extractReimbursement();

        if (!newReimbursementRequest.isAuthorActive(newReimbursement)) {
            throw new InvalidRequestException("Author is not active");
        }

        /*boolean usernameAvailable = isUsernameAvailable(newUser.getUsername());
        boolean emailAvailable = isEmailAvailable(newUser.getEmail());

        if (!usernameAvailable || !emailAvailable) {
            String msg = "The values provided for the following fields are already taken by other users: ";
            if (!usernameAvailable) msg += "username ";
            if (!emailAvailable) msg += "email";
            throw new ResourceConflictException(msg);
        }*/

        // TODO encrypt provided password before storing in the database

        newReimbursement.setId(UUID.randomUUID().toString());
        //newReimbursement.setAuthor(new User("2", "Employee"));
        //newUser.setIsActive(true);
        reimbursementDAO.save(newReimbursement);

        return null;//newUser;
    }

    public boolean approveReimbursement(String reimbId) {
        return true;
    }

    public boolean denyReimbursement(String reimbId) {
        return false;
    }

    // List<Reimbursement> getReimbursementsByAuthorId(String authorId);
    // List<Reimbursement> getReimbursementsByStatusId(String statusId);
    // ResourceCreationResponse saveNewReimbursement(NewReimbursementRequest req);
    // boolean approveReimbursement(String reimbId);
    // boolean denyReimbursement(String reimbId);

}
