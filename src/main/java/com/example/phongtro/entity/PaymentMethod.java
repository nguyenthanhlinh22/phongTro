package com.example.phongtro.entity;

public class PaymentMethod {
    private int paymentMethodId;
    private String methodName;

    public PaymentMethod(String methodName) {
        this.methodName = methodName;
    }

    public int getId() {
        return paymentMethodId;
    }

    public void setId(int id) {
        this.paymentMethodId = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
