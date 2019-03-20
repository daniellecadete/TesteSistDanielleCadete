package school.school.cesar.unit.mocking;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MyApplicationTest {

    @Mock MessageService service;
    @InjectMocks MyApplication myApplication;

    @Test
    public void processMessage_sendMessageIsTrue_true(){
        when(service.sendMessage(any(String.class), anyString())).thenReturn(true);
        assertTrue(myApplication.processMessage("msg", "rec"));
        verify(service).sendMessage("msg", "rec");
    }

    @Test
    public void processMessage_sendMessageIsFalse_false(){
        when(service.sendMessage(any(String.class), anyString())).thenReturn(false);
        assertFalse(myApplication.processMessage("msg", "rec"));
        verify(service).sendMessage("msg", "rec");
    }
}
