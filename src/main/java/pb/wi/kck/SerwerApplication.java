package pb.wi.kck;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pb.wi.kck.model.Barcode;
import pb.wi.kck.model.ProductBlueprint;
import pb.wi.kck.shared.LoadDatabase;

import java.sql.SQLException;
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
