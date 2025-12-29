package Pogo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginTestRequestPogo {
    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

}
