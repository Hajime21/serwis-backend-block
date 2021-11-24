package pb.wi.kck.dto;

import lombok.*;

@Data
//@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "userDtoBuilder")
public class UserDTO {
    private Integer userId;
    private String login;
    private String password;
    private String email;
}
