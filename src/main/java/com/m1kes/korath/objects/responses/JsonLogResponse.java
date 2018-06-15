package com.m1kes.korath.objects.responses;

import java.util.List;

public class JsonLogResponse {

    private String[]lines;

    public JsonLogResponse(List<String> lines){
        this.lines = lines.toArray(new String[lines.size()]);
    }

    public JsonLogResponse(JsonResponse response){
        this.lines = new String[1];
        this.lines[0] = " status: " + response.getStatus()  + ", message: " + response.getMessage();
    }

    public String[] getLines() {
        return lines;
    }

    public void setLines(String[] lines) {
        this.lines = lines;
    }
}
