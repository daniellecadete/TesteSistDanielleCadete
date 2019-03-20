package school.cesar.agile.unit.project;

import java.time.LocalDate;

public class EmailAccount {
    private String user;
    private String domain;
    private String password;
    private LocalDate lastPasswordUpdate;

    public void setLastPasswordUpdate(LocalDate lastPasswordUpdate) {
        this.lastPasswordUpdate = lastPasswordUpdate;
    }

    public boolean verifyPasswordExpiration(){
        throw new UnsupportedOperationException();
    }
}
