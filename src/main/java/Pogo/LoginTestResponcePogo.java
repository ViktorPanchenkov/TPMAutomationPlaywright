package Pogo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginTestResponcePogo {

  @JsonProperty("message")
  private String message;

  @JsonProperty("authorization")
    private String authorization;
}
