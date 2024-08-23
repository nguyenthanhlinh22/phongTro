package com.example.phongtro.models;

import com.example.phongtro.entity.RoomRentals;
import com.example.phongtro.database.DataBaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomModel {
    private Connection conn;

    public RoomModel() throws SQLException {
        this.conn = DataBaseConnect.getConnection();
    }

    public ResultSet getRooms() throws SQLException {
        String sql = "SELECT room_rentals.*, payment_methods.methodName as payment_method_name " +
                "FROM room_rentals " +
                "JOIN payment_methods ON room_rentals.paymentType = payment_methods.id";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }

    public void deleteRoom(int id) throws SQLException {
        String sql = "DELETE FROM room_rentals WHERE roomId = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public void createRoom(RoomRentals room) throws SQLException {
        String sql = "INSERT INTO room_rentals (tenantName, phoneNumber, startDate, paymentType, notes) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, room.getTenantName());
        preparedStatement.setString(2, room.getPhoneNumber());
        preparedStatement.setDate(3, room.getStartDate());
        preparedStatement.setInt(4, room.getPaymentType());
        preparedStatement.setString(5, room.getNotes());
        preparedStatement.execute();
    }

    public ResultSet searchRooms(String query) throws SQLException {
        String sql = "SELECT room_rentals.*, payment_methods.methodName as payment_method_name " +
                "FROM room_rentals " +
                "JOIN payment_methods ON room_rentals.paymentType = payment_methods.id " +
                "WHERE room_rentals.roomId LIKE ? OR room_rentals.tenantName LIKE ? OR room_rentals.phoneNumber LIKE ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, "%" + query + "%");
        preparedStatement.setString(2, "%" + query + "%");
        preparedStatement.setString(3, "%" + query + "%");
        return preparedStatement.executeQuery();
    }

    public ResultSet getRoomById(int id) throws SQLException {
        String sql = "SELECT room_rentals.*, payment_methods.methodName as payment_method_name " +
                "FROM room_rentals " +
                "JOIN payment_methods ON room_rentals.paymentType = payment_methods.id " +
                "WHERE room_rentals.roomId = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement.executeQuery();
    }

    public void updateRoom(RoomRentals room) throws SQLException {
        String sql = "UPDATE room_rentals SET tenantName = ?, phoneNumber = ?, startDate = ?, paymentType = ?, notes = ? " +
                "WHERE roomId = ?";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        preparedStatement.setString(1, room.getTenantName());
        preparedStatement.setString(2, room.getPhoneNumber());
        preparedStatement.setDate(3, room.getStartDate());
        preparedStatement.setInt(4, room.getPaymentType());
        preparedStatement.setString(5, room.getNotes());
        preparedStatement.setInt(6, room.getRoomId());
        preparedStatement.execute();
    }
}
