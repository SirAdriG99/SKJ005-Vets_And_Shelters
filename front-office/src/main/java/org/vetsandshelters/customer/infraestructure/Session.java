package org.vetsandshelters.customer.infraestructure;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

public class Session {
    public static final Duration SESSION_LENGTH = Duration.ofHours(12 /* Change this value to change the session's length */);
    public static final Duration MAX_TIME_WITHOUT_ACTION = Duration.ofHours(2 /* Change this value to change the maximum time without action. */ );
    private LocalDateTime dateTimeBeginSession; // This variable is
    private LocalDateTime dateTimeLastAction;   // This one saves the last

    public Session() {
        this.dateTimeBeginSession = LocalDateTime.now();
        this.dateTimeLastAction = LocalDateTime.now();
    }

    public void setDateTimeLastAction() {
        this.dateTimeLastAction = LocalDateTime.now();
    }

    public LocalDateTime getDateTimeBeginSession() {
        return dateTimeBeginSession;
    }

    public LocalDateTime getDateTimeLastAction() {
        return dateTimeLastAction;
    }
    
    public boolean isSessionActive(){

        Duration durationWithoutAction = Duration.between(LocalDateTime.now(), this.dateTimeLastAction);
        Duration sessionDuration = Duration.between(LocalDateTime.now(), this.dateTimeBeginSession);

        // La sesión está activa si ha estado activa menos del tiempo preestablecido y
        //  ha estado inactiva por menos tiempo que el preestablecido.
        return  (sessionDuration.compareTo(Session.SESSION_LENGTH) <= 0) &&
                (durationWithoutAction.compareTo(Session.MAX_TIME_WITHOUT_ACTION) <= 0);
    }
}
