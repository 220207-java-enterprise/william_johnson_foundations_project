package com.revature.erm.models;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reimbursement {
    private String id;
    private int amount;
    private Timestamp submitted;
    private Timestamp resolved = null;
    private String description;
    private String payment_id;
    private User author;
    private User resolver = null;
    private ReimbursementStatus status;
    private ReimbursementType type;

    public Reimbursement() { super(); }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Timestamp getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        this.submitted = submitted;
    }

    public Timestamp getResolved() {
        return resolved;
    }

    public void setResolved(Timestamp resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getResolver() {
        return resolver;
    }

    public void setResolver(User resolver) {
        this.resolver = resolver;
    }

    public ReimbursementStatus getStatus() {
        return status;
    }

    public void setStatus(ReimbursementStatus status) {
        this.status = status;
    }

    public ReimbursementType getType() {
        return type;
    }

    public void setType(ReimbursementType type) {
        this.type = type;
    }

    Reimbursement(String id, int amount, Timestamp submitted, Timestamp resolved, String description,
                  String payment_id, User author, User resolver, ReimbursementStatus status, ReimbursementType type) {
        //this.id = id;
        this.amount = amount;
        this.submitted = submitted;
        //this.resolved = resolved;
        this.description = description;
        //this.payment_id = payment_id;
        this.author = author;
        //this.resolver = resolver;
        this.status = status;
        this.type = type;

    }
    public Reimbursement(int amount, String description/*, Timestamp submitted, User author, ReimbursementType type*/) {
        this.amount = amount;
        //this.submitted = submitted;
        this.description = description;
        //this.author = author;
        //this.type = type;
    }

}