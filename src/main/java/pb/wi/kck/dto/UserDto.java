package pb.wi.kck.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private final Integer userId;
    private final String login;
    private final String password;
    private final String email;
}
