package main;

import java.time.Instant;
import java.util.Collection;

public class Email {

    private Instant creationDate;
    private String from;
    private Collection<String> to;
    private Collection<String> cc;
    private Collection<String> bcc;
    private String subject;
    private String message;

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Collection<String> getTo() {
        return to;
    }

    public void setTo(Collection<String> to) {
        this.to = to;
    }

    public Collection<String> getCc() {
        return cc;
    }

    public void setCc(Collection<String> cc) {
        this.cc = cc;
    }

    public Collection<String> getBcc() {
        return bcc;
    }

    public void setBcc(Collection<String> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private Email(EmailBuilder builder) {
        this.creationDate = builder.creationDate;
        this.from = builder.from;
        this.to = builder.to;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.subject = builder.subject;
        this.message = builder.message;
    }

    public static class EmailBuilder {

        private Instant creationDate;
        private String from;
        private Collection<String> to;
        private Collection<String> cc;
        private Collection<String> bcc;
        private String subject;
        private String message;

        public EmailBuilder() {

        }

        public EmailBuilder setCreationDate(Instant creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public EmailBuilder setFrom(String from) {
            this.from = from;
            return this;
        }

        public EmailBuilder setTo(Collection<String> to) {
            this.to = to;
            return this;
        }

        public EmailBuilder setCc(Collection<String> cc) {
            this.cc = cc;
            return this;
        }

        public EmailBuilder setBcc(Collection<String> bcc) {
            this.bcc = bcc;
            return this;
        }

        public EmailBuilder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public EmailBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Email build() {
            return new Email(this);
        }
    }
}
