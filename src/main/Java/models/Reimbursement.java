package models;
import java.time.LocalDateTime;
import java.util.UUID;

public class Reimbursement {
    private String id;
    public double amount;
    public LocalDateTime submitted;
    public LocalDateTime resolved;
    public String description;
    public String user_id;
    public String author_id;
    public String resolver_id;
    public String status;
    public String type;

    Reimbursement () {

    }

}