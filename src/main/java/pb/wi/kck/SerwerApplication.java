package pb.wi.kck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
public class SerwerApplication {

    public long createTimestamp() {
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.now().atZone(zoneId).toEpochSecond();
    }

    public static void main(String[] args) {
        SpringApplication.run(SerwerApplication.class, args);
        
    }

}
