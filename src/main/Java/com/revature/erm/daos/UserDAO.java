package com.revature.erm.daos;

import com.revature.erm.models.User;
import com.revature.erm.models.UserRole;
import com.revature.erm.util.ConnectionFactory;
import com.revature.erm.util.exceptions.DataSourceException;
import com.revature.erm.util.exceptions.ResourcePersistenceException;
//import com.sun.xml.internal.bind.v2.TODO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements CrudDAO<User> {

    private final String rootSelect = "SELECT " +
            "eu.id, eu.first_name, eu.last_name, eu.email, eu.username, eu.password, eu.role, eur.role_name " +
            "FROM ers_users eu " +
            "JOIN ers_user_roles eur " +
            "ON eu.role_id = eur.id ";

    public User findUserByUsername(String username) {

        User user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("role"), rs.getString("role_name")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public User findUserByEmail(String email) {

        User user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE email = ?");
            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("role"), rs.getString("role_name")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    public User findUserByUsernameAndPassword(String username, String password) {

        User authUser = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                authUser = new User();
                authUser.setId(rs.getString("id"));
                authUser.setFirstName(rs.getString("first_name"));
                authUser.setLastName(rs.getString("last_name"));
                authUser.setEmail(rs.getString("email"));
                authUser.setUsername(rs.getString("username"));
                authUser.setPassword(rs.getString("password"));
                authUser.setRole(new UserRole(rs.getString("role"), rs.getString("role_name")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return authUser;
    }

    @Override
    public void save(User newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO app_users VALUES (?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newUser.getId());
            pstmt.setString(2, newUser.getFirstName());
            pstmt.setString(3, newUser.getLastName());
            pstmt.setString(4, newUser.getEmail());
            pstmt.setString(5, newUser.getUsername());
            pstmt.setString(6, newUser.getPassword());
            pstmt.setString(7, newUser.getRole().getId());

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist user to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public User getById(String id) {

        User user = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("role"), rs.getString("role_name")));
            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return user;

    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            ResultSet rs = conn.createStatement().executeQuery(rootSelect);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(new UserRole(rs.getString("role"), rs.getString("role_name")));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return users;
    }

    @Override
    public void update(User updatedUser) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("UPDATE app_users " +
                    "SET first_name = ?, " +
                    "last_name = ?, " +
                    "email = ?, " +
                    "username = ?, " +
                    "password = ? " +
                    "WHERE id = ?");
            pstmt.setString(1, updatedUser.getFirstName());
            pstmt.setString(2, updatedUser.getLastName());
            pstmt.setString(3, updatedUser.getEmail());
            pstmt.setString(4, updatedUser.getUsername());
            pstmt.setString(5, updatedUser.getPassword());
            pstmt.setString(6, updatedUser.getId());

            // TODO allow role to be updated as well

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                throw new ResourcePersistenceException("Failed to update user data within datasource.");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM app_users WHERE id = ?");
            pstmt.setString(1, id);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to delete user from data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }
}
