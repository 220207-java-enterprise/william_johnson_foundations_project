package com.Revature.Foundations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String URL = "jdbc:postgresql://localhost:5432/postgres?currentSchema=foundations-project";
        String username = "postgres";
        String password = "revature";

        try {
            Connection connection = DriverManager.getConnection(URL, username, password);

            System.out.println("Connection successful");

        } catch (SQLException e) {
            System.out.println("Error in connecting to PostgreSQL");
            e.printStackTrace();
        }


        /*System.out.println("this is my project");
        User testUser = new User("iambob", "bobsemail@gmail.com",
                "asdf1234", "bill", "samolian");*/
    }
}