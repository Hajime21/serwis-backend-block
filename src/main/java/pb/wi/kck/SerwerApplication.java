package pb.wi.kck;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootApplication
public class SerwerApplication {

    public long createTimestamp() {
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.now().atZone(zoneId).toEpochSecond();
    }

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "2115");
    }

    public static void main(String[] args) {
        SpringApplication.run(SerwerApplication.class, args);
        
    }

}
