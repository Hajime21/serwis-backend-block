package pb.wi.kck.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    private @Id @GeneratedValue(strategy = GenerationType.SEQUENCE) Integer userId;
    private String login;
    private String password;
    private String email;

}
