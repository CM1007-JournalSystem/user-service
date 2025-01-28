package kth.userservice.DTO;

import java.util.UUID;
import kth.userservice.models.Gender;
import kth.userservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int id;
    private String username;
    private String social_security;
    private String first_name;
    private String last_name;
    private Gender gender;
    private String email;
    private String phoneNr;
    private UUID keycloak_id;

    public User DTOtoUser(){
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setSocial_security(this.social_security);
        user.setFirst_name(this.first_name);
        user.setLast_name(this.last_name);
        user.setGender(this.gender);
        user.setEmail(this.email);
        user.setPhoneNr(this.phoneNr);
        user.setkeycloak_id(this.keycloak_id);
        return user;
    }
}
