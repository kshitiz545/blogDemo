package ai.treeleaf.blog.dto;

import jdk.jfr.ContentType;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterDto {

    @NotBlank(message = "Full Name cannot be blank")
    private String fullName;
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @Email(message = "Email should be valid")
    private String email;
    private String address;
    @Size(min = 10,max = 10,message = "Mobile number should be of 10 digits")
    private String mobile;
}
