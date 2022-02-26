package com.revature.erm.daos;

import com.revature.erm.models.User;
import com.sun.xml.internal.bind.v2.TODO;

public class UserDAO implements CrudDAO<User> {

    private final String rootSelect = "SELECT " +
            "eu.id, eu.first_name, eu.last_name, eu.email, eu.username, eu.password, eu.role, eur.role_name " +
            "FROM ers_users eu " +
            "JOIN ers_user_roles eur " +
            "ON eu.role_id = eur.id ";

    public void save(User newObject) {
        // TODO this needs to be built here next, but after the connection factory is up and running
    }

    public User getById(String id) {
        return null;
    }

    public User[] getAll() {
        return new User[0];
    }

    public void update(User updatedObject) {

    }

    public void deleteById(String id) {

    }
}
