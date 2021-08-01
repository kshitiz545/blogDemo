package ai.treeleaf.blog.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddBlogDto {

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 50,message = "Title can be maximum of 50 characters")
    private String blogTitle;
    @NotBlank(message = "Description cannot be blank")
    @Size(max = 100,message = "Description can be maximum of 100 characters")
    private String description;
    @NotBlank(message = "Body cannot be blank")
    private String body;
    @NotBlank(message = "Image cannot be blank")
    private String image;

}
