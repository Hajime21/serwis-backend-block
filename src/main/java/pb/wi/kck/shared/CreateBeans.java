package pb.wi.kck.shared;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreateBeans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
