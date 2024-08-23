package com.example.phongtro.services;

import com.example.phongtro.entity.RoomRentals;
import com.example.phongtro.entity.PaymentMethod;
import com.example.phongtro.models.RoomModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private RoomModel roomModel;

    public RoomService() throws SQLException {
        this.roomModel = new RoomModel();
    }

    public List<RoomRentals> getAllRooms() throws SQLException {
        List<RoomRentals> rooms = new ArrayList<>();
        ResultSet resultSet = this.roomModel.getRooms();
        while (resultSet.next()) {
            int roomId = resultSet.getInt("roomId");
            String tenantName = resultSet.getString("tenantName");
            String phoneNumber = resultSet.getString("phoneNumber");
            java.sql.Date startDate = resultSet.getDate("startDate");
            int paymentTypeId = resultSet.getInt("paymentType");
            String paymentMethodName = resultSet.getString("payment_method_name");
            String notes = resultSet.getString("notes");

            RoomRentals room = new RoomRentals();
            room.setRoomId(roomId);
            room.setTenantName(tenantName);
            room.setPhoneNumber(phoneNumber);
            room.setStartDate(startDate);
            room.setPaymentType(paymentTypeId);
            room.setNotes(notes);

            PaymentMethod paymentMethod = new PaymentMethod(paymentMethodName);
            paymentMethod.setId(paymentTypeId);
            room.setPaymentMethod(paymentMethod);

            rooms.add(room);
        }
        return rooms;
    }

    public void deleteRooms(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String[] ids = request.getParameterValues("ids");
        if (ids != null) {
            for (String id : ids) {
                this.roomModel.deleteRoom(Integer.parseInt(id));
            }
        }
    }

    public void createRoom(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        String startDate = request.getParameter("startDate");
        String paymentType = request.getParameter("paymentType");
        String notes = request.getParameter("notes");

        RoomRentals room = new RoomRentals();
        room.setTenantName(tenantName);
        room.setPhoneNumber(phoneNumber);
        room.setStartDate(java.sql.Date.valueOf(startDate));
        room.setPaymentType(Integer.parseInt(paymentType));
        room.setNotes(notes);

        this.roomModel.createRoom(room);
    }

    public List<RoomRentals> searchRooms(HttpServletRequest request) throws SQLException {
        String keyword = request.getParameter("keyword");
        ResultSet resultSet = this.roomModel.searchRooms(keyword);
        List<RoomRentals> rooms = new ArrayList<>();
        while (resultSet.next()) {
            int roomId = resultSet.getInt("roomId");
            String tenantName = resultSet.getString("tenantName");
            String phoneNumber = resultSet.getString("phoneNumber");
            java.sql.Date startDate = resultSet.getDate("startDate");
            int paymentTypeId = resultSet.getInt("paymentType");
            String paymentMethodName = resultSet.getString("payment_method_name");
            String notes = resultSet.getString("notes");

            RoomRentals room = new RoomRentals();
            room.setRoomId(roomId);
            room.setTenantName(tenantName);
            room.setPhoneNumber(phoneNumber);
            room.setStartDate(startDate);
            room.setPaymentType(paymentTypeId);
            room.setNotes(notes);

            PaymentMethod paymentMethod = new PaymentMethod(paymentMethodName);
            paymentMethod.setId(paymentTypeId);
            room.setPaymentMethod(paymentMethod);

            rooms.add(room);
        }
        return rooms;
    }

    public RoomRentals findRoomById(HttpServletRequest request) throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        ResultSet resultSet = this.roomModel.getRoomById(id);
        RoomRentals room = null;
        if (resultSet.next()) {
            int roomId = resultSet.getInt("roomId");
            String tenantName = resultSet.getString("tenantName");
            String phoneNumber = resultSet.getString("phoneNumber");
            java.sql.Date startDate = resultSet.getDate("startDate");
            int paymentTypeId = resultSet.getInt("paymentType");
            String paymentMethodName = resultSet.getString("payment_method_name");
            String notes = resultSet.getString("notes");

            room = new RoomRentals();
            room.setRoomId(roomId);
            room.setTenantName(tenantName);
            room.setPhoneNumber(phoneNumber);
            room.setStartDate(startDate);
            room.setPaymentType(paymentTypeId);
            room.setNotes(notes);

            PaymentMethod paymentMethod = new PaymentMethod(paymentMethodName);
            paymentMethod.setId(paymentTypeId);
            room.setPaymentMethod(paymentMethod);
        }

        return room;
    }

    public void updateRoom(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int roomId = Integer.parseInt(request.getParameter("roomId"));
        String tenantName = request.getParameter("tenantName");
        String phoneNumber = request.getParameter("phoneNumber");
        String startDate = request.getParameter("startDate");
        String paymentType = request.getParameter("paymentType");
        String notes = request.getParameter("notes");

        RoomRentals room = new RoomRentals();
        room.setRoomId(roomId);
        room.setTenantName(tenantName);
        room.setPhoneNumber(phoneNumber);
        room.setStartDate(java.sql.Date.valueOf(startDate));
        room.setPaymentType(Integer.parseInt(paymentType));
        room.setNotes(notes);

        this.roomModel.updateRoom(room);
    }
}
