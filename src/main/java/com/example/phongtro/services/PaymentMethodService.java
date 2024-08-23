package com.example.phongtro.services;

import com.example.phongtro.entity.PaymentMethod;
import com.example.phongtro.models.PaymentMethodModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodService {
    private PaymentMethodModel paymentMethodModel;

    public PaymentMethodService() throws SQLException {
        this.paymentMethodModel = new PaymentMethodModel();
    }


    public List<PaymentMethod> getAllPaymentMethods() throws SQLException {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        ResultSet resultSet = this.paymentMethodModel.getPaymentMethods();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String methodName = resultSet.getString("methodName");

            PaymentMethod paymentMethod = new PaymentMethod(methodName);
            paymentMethod.setId(id);

            paymentMethods.add(paymentMethod);
        }
        return paymentMethods;
    }
}
