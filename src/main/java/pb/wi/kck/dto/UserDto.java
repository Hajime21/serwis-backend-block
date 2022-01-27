package pb.wi.kck.dto;

import lombok.*;
import pb.wi.kck.model.PermissionLevel;
import pb.wi.kck.model.User;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class UserDto implements Serializable {
    private Integer userId;
    private String login;
    private String password;
    private String email;
    private PermissionLevel permissionLevel;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.permissionLevel = user.getPermissionLevel();
    }
}
