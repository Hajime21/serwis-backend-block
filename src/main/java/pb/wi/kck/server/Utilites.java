package pb.wi.kck.server;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utilites {
    public static LocalDateTime createTimestamp() {
        //ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.now();
    }
}
