package com.nubi.controlador;

/**
 * Created by Felipe on 18/08/2016.
 */
public class message {
    public static final int HELLO = 0;
    public static final int GOODBYE = 1;

    private String message;

    private int status;

    public message() {
    }

    public message(String message, int status) {
        this.message = message;
        this.status = status;
    }

    public static int getHELLO() {
        return HELLO;
    }

    public static int getGOODBYE() {
        return GOODBYE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
