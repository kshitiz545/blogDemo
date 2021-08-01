package ai.treeleaf.blog.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BlogDetailDto {

    private Long id;
    private String blogTitle;
    private String description;
    private String body;
    private String image;
    private String author;
    private String username;
    private Date createdAt;
    private Date updatedAt;

}
