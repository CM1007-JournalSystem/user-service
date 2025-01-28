package kth.userservice.models;

import jakarta.persistence.*;
import kth.userservice.DTO.UserDTO;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
@Table(name ="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String social_security;
    private String first_name;
    private String last_name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private String phoneNr;
    private UUID keycloak_id;

    public UserDTO UserToDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setUsername(this.username);
        userDTO.setSocial_security(this.social_security);
        userDTO.setFirst_name(this.first_name);
        userDTO.setLast_name(this.last_name);
        userDTO.setGender(this.gender);
        userDTO.setEmail(this.email);
        userDTO.setPhoneNr(this.phoneNr);
        userDTO.setKeycloak_id(this.keycloak_id);

        return userDTO;
    }

}
