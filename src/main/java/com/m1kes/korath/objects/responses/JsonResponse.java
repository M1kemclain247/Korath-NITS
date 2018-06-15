package com.m1kes.korath.objects.responses;

import java.util.List;

public class JsonResponse {

    private String status;
    private String message;
    private List<String> log;

    public JsonResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonResponse(List<String> log) {
        this.log = log;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getLog() {
        return log;
    }

    public void setLog(List<String> log) {
        this.log = log;
    }
}
