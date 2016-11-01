package com.jovicazoric.entity;

public class MyPayload {
    String filename;
    byte[] payload;

    public MyPayload(String filename, byte[] payload) {
        this.filename = filename;
        this.payload = payload;
    }

    public String getFilename() {
        return filename;
    }

    public byte[] getPayload() {
        return payload;
    }

}