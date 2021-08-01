package ai.treeleaf.blog.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AddCommentDto {
    @NotNull(message = "Blog Id cannot be null")
    private Long blog_id;
    @NotBlank(message = "Comment cannot be blank")
    @Size(max = 200,message = "Comment can be maximum of 200 characters")
    private String comment;
}
