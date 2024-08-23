package com.example.phongtro.models;

import com.example.phongtro.database.DataBaseConnect;
import com.example.phongtro.entity.PaymentMethod;

import java.sql.*;

public class PaymentMethodModel {
    private Connection conn;

    public PaymentMethodModel() throws SQLException {
        DatabaseMetaData DatabaseConnect = null;
        this.conn = DatabaseConnect.getConnection();
    }


    public ResultSet getPaymentMethods() throws SQLException {
        String sql = "SELECT * FROM PaymentMethods";
        PreparedStatement preparedStatement = this.conn.prepareStatement(sql);
        return preparedStatement.executeQuery();
    }
}
