package com.example.appdt.model;

import java.util.List;

public class sanphammodel {
    boolean success;
    String message;
    List<sanpham> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<sanpham> getResult() {
        return result;
    }

    public void setResult(List<sanpham> result) {
        this.result = result;
    }
}
