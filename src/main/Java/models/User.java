package models;
import java.util.UUID;

public class User {
    private String id;
    public String username;
    public String email;
    private String password;
    public String firstName;
    public String lastName;
    private boolean isActive = false;
    private int role_id;

    public void requestRole(int requestedRole){
        //1 = admin, 2 = finance manager, 3 = regular employee

    }

    User (String username, String email, String password, String firstName, String lastName) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

        System.out.println(this.id);
    }

}
