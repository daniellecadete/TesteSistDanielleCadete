package test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collection;

import main.*;
import org.junit.*;

import static org.junit.Assert.*;


public class EmailClientTest {

    public EmailClientTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    public class TestEmailService implements EmailService {

        private Collection<Email> emails;

        public TestEmailService() {
            Collection<String> emailsString = new ArrayList();
            emailsString.add("danielle.cadete@teste.com.br");
            emailsString.add("danielle.cadete2@teste.com.br");
            emailsString.add("danielle.cadete3@teste.com.br");

            Email email = new Email.EmailBuilder()
                    .setFrom("danielle.cadete@teste.com.br")
                    .setMessage("Teste unitário")
                    .setSubject("Assunto")
                    .setCreationDate(Instant.now())
                    .setTo(emailsString)
                    .build();

            this.emails = new ArrayList();
            this.emails.add(email);
        }

        @Override
        public boolean sendEmail(Email email) {
            return true;
        }

        @Override
        public Collection<Email> emailList(EmailAccount account) {
            return getEmails();
        }

        public Collection<Email> getEmails() {
            return this.emails;
        }

    }

    public class TestEmailServiceFail implements EmailService {

        private Collection<Email> emails;

        public TestEmailServiceFail() {
            Collection<String> emailsString = new ArrayList();
            emailsString.add("danielle.cadete@teste.com.br");
            emailsString.add("danielle.cadete2@teste.com.br");
            emailsString.add("danielle.cadete3@teste.com.br");

            Email email = new Email.EmailBuilder()
                    .setFrom("danielle.cadete@teste.com.br")
                    .setMessage("Teste unitário")
                    .setSubject("Assunto")
                    .setCreationDate(Instant.now())
                    .setTo(emailsString)
                    .build();

            this.emails = new ArrayList();
            this.emails.add(email);
        }

        @Override
        public boolean sendEmail(Email email) {
            return false;
        }

        @Override
        public Collection<Email> emailList(EmailAccount account) {
            return getEmails();
        }

        public Collection<Email> getEmails() {
            return this.emails;
        }

    }

    /**
     * Test of getAccounts method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetAccounts() throws Exception {
        System.out.println("getAccounts");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        instance.createAccount(account);
        Collection<EmailAccount> expResult = new ArrayList();
        expResult.add(account);
        Collection<EmailAccount> result = instance.getAccounts();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAccounts method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSetAccounts() throws Exception {
        System.out.println("setAccounts");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .build();
        Collection<EmailAccount> accounts = new ArrayList();
        accounts.add(account);
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        instance.setAccounts(accounts);
    }

    /**
     * Test of setEmailService method, of class EmailClient.
     */
    @Test
    public void testSetEmailService() {
        System.out.println("setEmailService");
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        EmailService emailService = new TestEmailService();
        instance.setEmailService(emailService);
    }

    /**
     * Test of isValidAddress method, of class EmailClient.
     */
    @Test
    public void testIsValidAddressValidAdress() {
        System.out.println("isValidAddressValidAdress");
        String emailAddress = "danielle.cadete@teste.com.br";
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = true;
        boolean result = instance.isValidAddress(emailAddress);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAddress method, of class EmailClient.
     */
    @Test
    public void testIsValidAddressInvalidDomain() {
        System.out.println("isValidAddressInvalidDomain");
        String emailAddress = "danielle.cadete@teste";
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidAddress(emailAddress);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAddress method, of class EmailClient.
     */
    @Test
    public void testIsValidAddressInvalidUser() {
        System.out.println("isValidAddressInvalidUser");
        String emailAddress = "@teste.com.br";
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidAddress(emailAddress);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidAddress method, of class EmailClient.
     */
    @Test
    public void testIsValidAddressNull() {
        System.out.println("isValidAddressNull");
        String emailAddress = null;
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidAddress(emailAddress);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class EmailClient.
     */
    @Test
    public void testIsValidEmailValidEmails() {
        System.out.println("isValidEmailValidEmails");
        Collection<String> emails = new ArrayList();
        emails.add("danielle.cadete@teste.com");
        emails.add("danielle.cadete2@teste.com");
        emails.add("danielle.cadete3@teste.com.br");
        Email email = new Email.EmailBuilder()
                .setBcc(emails)
                .setCc(emails)
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emails)
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = true;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class EmailClient.
     */
    @Test
    public void testIsValidEmailInvalidEmails() {
        System.out.println("isValidEmailInvalidEmails");
        Collection<String> emails = new ArrayList();
        emails.add("@teste.com");
        emails.add("danielle.cadete2@teste");
        Email email = new Email.EmailBuilder()
                .setBcc(emails)
                .setCc(emails)
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emails)
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class EmailClient.
     */
    @Test
    public void testIsValidEmailInvalidCcEmails() {
        System.out.println("isValidEmailInvalidCcEmails");
        Collection<String> emails = new ArrayList();
        emails.add("danielle.cadete@teste.com.br");
        emails.add("danielle.cadete2@teste.com.br");
        Collection<String> ccEmails = new ArrayList();
        emails.add("@teste.com");
        emails.add("danielle.cadete2@teste.com.br");
        Email email = new Email.EmailBuilder()
                .setCc(ccEmails)
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emails)
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class EmailClient.
     */
    @Test
    public void testIsValidEmailInvalidBccEmails() {
        System.out.println("isValidEmailInvalidBccEmails");
        Collection<String> emails = new ArrayList();
        emails.add("danielle.cadete@teste.com.br");
        emails.add("danielle.cadete2@teste.com.br");
        Collection<String> bccEmails = new ArrayList();
        emails.add("@teste.com");
        emails.add("danielle.cadete2@teste.com.br");
        Email email = new Email.EmailBuilder()
                .setBcc(bccEmails)
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emails)
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class EmailClient.
     */
    @Test
    public void testIsValidEmailValidInvalidEmails() {
        System.out.println("isValidEmailValidInvalidEmails");
        Collection<String> emails = new ArrayList();
        emails.add("danielle.cadete2@teste.com");
        emails.add("danielle.cadete2@com");
        Email email = new Email.EmailBuilder()
                .setBcc(emails)
                .setCc(emails)
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emails)
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class EmailClient.
     */
    @Test
    public void testIsValidEmailNoTo() {
        System.out.println("isValidEmailNoTo");
        Collection<String> emails = new ArrayList();
        emails.add("danielle.cadete@teste.com");
        emails.add("danielle.cadete2@teste.com");
        Email email = new Email.EmailBuilder()
                .setBcc(emails)
                .setCc(emails)
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of isValidEmail method, of class EmailClient.
     */
    @Test
    public void testIsValidEmailInvalidFrom() {
        System.out.println("isValidEmailInvalidFrom");
        Collection<String> emails = new ArrayList();
        emails.add("danielle.cadete@teste.com");
        emails.add("danielle.cadete2@teste.com");
        Email email = new Email.EmailBuilder()
                .setBcc(emails)
                .setCc(emails)
                .setFrom("danielle.cadete@teste")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emails)
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = false;
        boolean result = instance.isValidEmail(email);
        assertEquals(expResult, result);
    }

    /**
     * Test of emailList method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testEmailListValid() throws Exception {
        System.out.println("emailListValid");

        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .setLastPasswordUpdate(Instant.now())
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        EmailService emailService = new TestEmailService();

        instance.setEmailService(emailService);
        Collection<Email> expResult = ((TestEmailService) emailService).getEmails();
        Collection<Email> result = instance.emailList(account);
        assertEquals(expResult, result);
    }

    /**
     * Test of emailList method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = RuntimeException.class)
    public void testEmailListInvalidPassword() throws Exception {
        System.out.println("emailListInvalidPassword");

        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete")
                .setDomain("teste.com.br")
                .setPassword("1234")
                .setLastPasswordUpdate(Instant.now())
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        EmailService emailService = new TestEmailService();

        instance.setEmailService(emailService);
        instance.emailList(account);
    }

    /**
     * Test of emailList method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test(expected = RuntimeException.class)
    public void testEmailListExpiredPassword() throws Exception {
        System.out.println("emailListExpiredPassword");

        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle.cadete")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .setLastPasswordUpdate(Instant.now().minusSeconds(60 * 60 * 24 * 91))
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        EmailService emailService = new TestEmailService();

        instance.setEmailService(emailService);
        instance.emailList(account);
    }

    /**
     * Test of sendEmail method, of class EmailClient.
     */
    @Test
    public void testSendEmailSuccess() {
        System.out.println("sendEmailSuccess");
        Collection<String> emailsString = new ArrayList();
        emailsString.add("danielle.cadete@teste.com.br");
        emailsString.add("danielle.cadete2@teste.com.br");

        Email email = new Email.EmailBuilder()
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emailsString)
                .build();

        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        EmailService emailService = new TestEmailService();
        instance.setEmailService(emailService);
        instance.sendEmail(email);
    }

    /**
     * Test of sendEmail method, of class EmailClient.
     */
    @Test(expected = RuntimeException.class)
    public void testSendEmailFail() {
        System.out.println("sendEmailFail");
        Collection<String> emailsString = new ArrayList();
        emailsString.add("danielle.cadete@teste.com.br");
        emailsString.add("danielle.cadete2@teste.com.br");

        Email email = new Email.EmailBuilder()
                .setFrom("danielle.cadete@teste.com.br")
                .setMessage("Teste unitário")
                .setSubject("Assunto")
                .setCreationDate(Instant.now())
                .setTo(emailsString)
                .build();

        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        EmailService emailService = new TestEmailServiceFail();
        instance.setEmailService(emailService);
        instance.sendEmail(email);
    }

    /**
     * Test of createAccount method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateAccountValid() {
        System.out.println("createAccountValid");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean expResult = true;
        boolean result = instance.createAccount(account);
        assertEquals(expResult, result);
    }

    /**
     * Test of createAccount method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateAccountInvalidUser()  {
        System.out.println("createAccountInvalidUser");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete%")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean result = instance.createAccount(account);
        assertEquals(false, result);
    }

    /**
     * Test of createAccount method, of class EmailClient.
     *
     */
    @Test
    public void testCreateAccountInvalidDomain() {
        System.out.println("createAccountInvalidDomain");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete")
                .setDomain("teste")
                .setPassword("1234567")
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();
        boolean result = instance.createAccount(account);
        assertEquals(false, result);
    }

    /**
     * Test of createAccount method, of class EmailClient.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateAccountInvalidPassword() {
        System.out.println("createAccountInvalidPassword");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle._-cadete")
                .setDomain("teste.com.br")
                .setPassword("12345")
                .build();
        EmailClient instance = new EmailClient.EmailClientBuilder().build();

        boolean result = instance.createAccount(account);
        assertEquals(false, result);
    }

    /**
     * Test of verifyPasswordExpiration method, of class EmailAccount.
     *
     */
    @Test
    public void testVerifyPasswordExpirationValid()  {
        System.out.println("VerifyPasswordExpirationValid");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle.cadete")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .setLastPasswordUpdate(Instant.now().minusSeconds(60 * 60 * 24 * 90)) //-90 days
                .build();

        boolean result = account.verifyPasswordExpiration();
        assertEquals(true, result);
    }

    /**
     * Test of verifyPasswordExpiration method, of class EmailAccount.
     *
     */
    @Test
    public void testVerifyPasswordExpirationExpired() {
        System.out.println("VerifyPasswordExpirationExpired");
        EmailAccount account = new EmailAccount.EmailAccountBuilder()
                .setUser("danielle.cadete")
                .setDomain("teste.com.br")
                .setPassword("1234567")
                .setLastPasswordUpdate(Instant.now().minusSeconds(60 * 60 * 24 * 91)) //-91 days
                .build();

        boolean result = account.verifyPasswordExpiration();
        assertEquals(false, result);
    }
}
