package main;

import java.time.Instant;
import java.util.Collection;
import java.util.ArrayList;

public class EmailClient {

    private Collection<EmailAccount> accounts;
    private EmailService emailService;

    public Collection<EmailAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<EmailAccount> accounts) {
        this.accounts = accounts;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public boolean isValidAddress(String emailAddress) {
        if (emailAddress == null)
            return false;
        String userRegex = "^[a-zA-Z0-9\\-\\._]+$";
        String domainRegex = "^[a-zA-Z0-9]+((\\.)[a-zA-Z0-9]+)+$";
        String[] emailParts = emailAddress.split("@");
        if (emailParts.length != 2) {
            return false;
        }

        return emailParts[0].matches(userRegex) && emailParts[1].matches(domainRegex);
    }

    public boolean isValidEmail(Email email) {

        if (email.getCreationDate() == null) {
            return false;
        }

        if (email.getTo() == null || !email.getTo().stream().allMatch((to) -> (isValidAddress(to)))) {
            return false;
        }

        if (isValidAddress(email.getFrom()) == false) {
            return false;
        }

        if (email.getCc() != null && email.getCc().isEmpty() == false && !email.getCc().stream().allMatch((cc) -> (isValidAddress(cc)))) {
            return false;
        }

        if (email.getBcc() != null && email.getBcc().isEmpty() == false && !email.getBcc().stream().allMatch((bcc) -> (isValidAddress(bcc)))) {
            return false;
        }

        return true;
    }

    public Collection<Email> emailList(EmailAccount account) {
        if (account.getPassword().length() < 7) {
            throw new RuntimeException("Password needs to be greater than 6 characters");
        }

        if (account.verifyPasswordExpiration() == false) {
            throw new RuntimeException("LastPasswordUpdate has more than 90 days");
        }

        return emailService.emailList(account);
    }

    public void sendEmail(Email email) {
        if (isValidEmail(email) == false) {
            throw new RuntimeException("Email is invalid");
        }

        if (emailService.sendEmail(email) == false) {
            throw new RuntimeException("Error sending email");
        }
    }

    public boolean createAccount(EmailAccount account) {
        if (isValidAddress(account.getUser() + "@" + account.getDomain()) == false) {
            return false;
        }

        if (account.getPassword().length() < 7) {
            return false;
        }

        account.setLastPasswordUpdate(Instant.now());

        accounts.add(account);

        return true;
    }

    private EmailClient(EmailClientBuilder builder) {
        this.accounts = builder.accounts;
        this.emailService = builder.emailService;
        if (accounts == null)
            accounts = new ArrayList();
    }

    public static class EmailClientBuilder {

        private Collection<EmailAccount> accounts;
        private EmailService emailService;

        public EmailClientBuilder() {

        }

        public void setAccounts(Collection<EmailAccount> accounts) {
            this.accounts = accounts;
        }

        public void setEmailService(EmailService emailService) {
            this.emailService = emailService;
        }

        public EmailClient build() {
            return new EmailClient(this);
        }
    }
}
