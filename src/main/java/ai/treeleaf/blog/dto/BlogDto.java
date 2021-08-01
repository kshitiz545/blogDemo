package ai.treeleaf.blog.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogDto {

    private Long id;
    private String blogTitle;
    private String description;
    private String author;
    private String username;
}
