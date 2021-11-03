package pb.wi.kck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SerwerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SerwerApplication.class, args);
        System.out.println(String.format("LocalDate size: {0}", getObjectSize(LocalDate))
    }

}