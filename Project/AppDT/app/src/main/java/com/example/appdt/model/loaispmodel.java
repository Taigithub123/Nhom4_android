package com.example.appdt.model;

import java.util.List;

public class loaispmodel {
    boolean success;
    String message;
    List<loaisp> result;

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

    public List<loaisp> getResult() {
        return result;
    }

    public void setResult(List<loaisp> result) {
        this.result = result;
    }
}
