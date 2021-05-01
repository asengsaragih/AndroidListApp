package com.app.email.model;

import java.io.Serializable;

public class Email implements Serializable {
    private final String to;
    private final String subject;
    private final String content;

    public Email(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
