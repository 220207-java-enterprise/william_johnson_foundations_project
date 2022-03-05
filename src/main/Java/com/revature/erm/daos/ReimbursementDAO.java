package com.revature.erm.daos;

import com.revature.erm.models.Reimbursement;
import com.revature.erm.models.User;
import com.revature.erm.models.UserRole;
import com.revature.erm.util.ConnectionFactory;
import com.revature.erm.util.exceptions.DataSourceException;
import com.revature.erm.util.exceptions.ResourcePersistenceException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {

    /*private final String rootSelect = "SELECT " +
            "eu.user_id, eu.username, eu.email, eu.password, eu.first_name, eu.last_name, eu.is_active, eu.role_id, eur.role " +
            "FROM ers_users eu " +
            "JOIN ers_user_roles eur " +
            "ON eu.role_id = eur.role_id ";*/
    /*private final String rootSelect = "SELECT " +
            "eu.user_id, eu.username, eu.email, eu.password, eu.first_name, eu.last_name, eu.is_active, eu.role_id, eur.role " +
            "FROM ers_users eu " +
            "JOIN ers_reimbursments er " +
            "ON eu.user_id = eur._id ";*/
    private final String rootSelect = "SELECT " +
            "reimb_id, amount, submitted, resolved, description, payment_id, author_id, resolver_id, status_id, type_id " +
            "FROM ers_reimbursments ";

    public void save(Reimbursement newReimbursement) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ers_reimbursments VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, newReimbursement.getId());
            pstmt.setInt(2, newReimbursement.getAmount());
            pstmt.setTimestamp(3, newReimbursement.getSubmitted());
            pstmt.setTimestamp(4, newReimbursement.getResolved());
            pstmt.setString(5, newReimbursement.getDescription());
            pstmt.setString(6, newReimbursement.getPayment_id());
            pstmt.setString(7, newReimbursement.getAuthor_id());//.getId());
            //if(newReimbursement.getResolver() != null)
                pstmt.setString(8, newReimbursement.getResolver_id());//.getId());
            //else
                //pstmt.setString(8, null);
            pstmt.setString(9, newReimbursement.getStatus().getId());
            pstmt.setString(10, newReimbursement.getType().getId());

            System.out.println(pstmt);

            int rowsInserted = pstmt.executeUpdate();
            System.out.println("pstmt.executeUpdate() ran in ReimbursementDao.java?");
            if (rowsInserted != 1) {
                conn.rollback();
                throw new ResourcePersistenceException("Failed to persist reimbursement to data source");
            }

            conn.commit();

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }
    }

    public Reimbursement getById(String id) {

        Reimbursement matchingReimbursement = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement pstmt = conn.prepareStatement(rootSelect + "WHERE reimb_id = ?");
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                matchingReimbursement= new Reimbursement();
                //authUser.setId(rs.getString("user_id"));
                matchingReimbursement.setId(rs.getString("reimb_id"));
                matchingReimbursement.setAmount(rs.getInt("amount"));
                matchingReimbursement.setSubmitted(rs.getTimestamp("submitted"));
                matchingReimbursement.setResolved(rs.getTimestamp("resolved"));
                matchingReimbursement.setDescription(rs.getString("description"));
                matchingReimbursement.setPayment_id(rs.getString("payment_id"));
                //todo figure out how to pull the ers_users table, ers_user_roles, and ers_reimbursments table in a single select statement
                //matchingReimbursement.setAuthor(new User(rs.getString("author_id"), rs.getString("SELECT username FROM ers_users WHERE user_id = author_id")));

            }

        } catch (SQLException e) {
            throw new DataSourceException(e);
        }

        return null;
    }

    // get reimbs by status id

    public List<Reimbursement> getAll() {
        return null;
    }

    //todo just call the save function above instead of using this function
    public void update(Reimbursement updatedObject) {

    }

    public void deleteById(String id) {

    }
}
