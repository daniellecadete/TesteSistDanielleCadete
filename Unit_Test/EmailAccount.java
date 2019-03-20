package main;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static java.time.temporal.ChronoUnit.DAYS;

public class EmailAccount {

    private String user;
    private String domain;
    private String password;
    private Instant lastPasswordUpdate;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Instant getLastPasswordUpdate() {
        return lastPasswordUpdate;
    }

    public void setLastPasswordUpdate(Instant lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    private EmailAccount(@NotNull EmailAccountBuilder builder) {
        this.setUser(builder.user);
        this.setDomain(builder.domain);
        this.setPassword(builder.password);
        this.setLastPasswordUpdate(builder.lastPasswordUpdate);
    }

    public boolean verifyPasswordExpiration() {
        return verifyPasswordExpiration(LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC), LocalDateTime.ofInstant(this.lastPasswordUpdate, ZoneOffset.UTC));
    }

    private boolean verifyPasswordExpiration(LocalDateTime nowDate, LocalDateTime instantDate) {
        long daysBetween = DAYS.between(instantDate, nowDate);
        return daysBetween <= 90;
    }

    public static class EmailAccountBuilder {

        private String user;
        private String domain;
        private String password;
        private Instant lastPasswordUpdate;

        public EmailAccountBuilder() {

        }

        public EmailAccountBuilder setUser(String user) {
            this.user = user;
            return this;
        }

        public EmailAccountBuilder setDomain(String domain) {
            this.domain = domain;
            return this;
        }

        public EmailAccountBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public EmailAccountBuilder setLastPasswordUpdate(Instant lastPasswordUpdate) {
            this.lastPasswordUpdate = lastPasswordUpdate;
            return this;
        }

        public EmailAccount build() {
            return new EmailAccount(this);
        }
    }
}
