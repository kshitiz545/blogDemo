package ai.treeleaf.blog.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private String comment;
    private String author;
    private String username;
    private Date createdAt;
}
