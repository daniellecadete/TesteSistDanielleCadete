package school.school.cesar.unit.mocking;

public class MyApplication {

    private MessageService service;

    public void setService(MessageService service) {
        this.service = service;
    }

    public boolean processMessage(String msg, String rec) {
        //some magic like validation, logging etc
        return this.service.sendMessage(msg, rec);
    }
}

