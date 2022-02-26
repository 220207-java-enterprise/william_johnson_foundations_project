package com.revature.erm.models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reimbursement {
    private String id;
    private double amount;
    private LocalDateTime submitted;
    private LocalDateTime resolved;
    private String description;
    private String user_id;
    private String author_id;
    private String resolver_id;
    private String status_id;
    private String type_id;


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getSubmitted() {
        return submitted;
    }

    public void setSubmitted(LocalDateTime submitted) {
        this.submitted = submitted;
    }

    public LocalDateTime getResolved() {
        return resolved;
    }

    public void setResolved(LocalDateTime resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(String resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getStatus() {
        return status_id;
    }

    public void setStatus(String status_id) {
        this.status_id = status_id;
    }

    public String getType() {
        return type_id;
    }

    public void setType(String type_id) {
        this.type_id = type_id;
    }


    Reimbursement () {

    }

}