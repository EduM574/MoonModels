package model;

public class Validation {
    private boolean status;
    private String text;

    public Validation() {}

    public Validation(boolean status, String text) {
        this.status = status;
        this.text = text;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getText() {
        return this.text;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setText(String text) {
        this.text = text;
    }
}