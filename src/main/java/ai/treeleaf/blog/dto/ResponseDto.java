package ai.treeleaf.blog.dto;

import ai.treeleaf.blog.model.Blog;
import ai.treeleaf.blog.model.Comment;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDto {
    private String code;
    private boolean status;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String token;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tokenType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date expires;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BlogDto> blogList;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BlogDetailDto blog;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CommentDto> commentList;

    public ResponseDto(String code, boolean status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ResponseDto(String code, boolean status, String message, Long id) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.id = String.valueOf(id);
    }

    public ResponseDto(String jwtToken, Date expires) {
        this.code = "0";
        this.status = true;
        this.message = "Success";
        this.token = jwtToken;
        this.expires = expires;
    }

    public ResponseDto(List<Blog> blogs){
        this.code = "0";
        this.status = true;
        this.message = "Success";
        List<BlogDto> blogList = new ArrayList<>();
        if (!blogs.isEmpty()){
            for (Blog blog :blogs) {
                BlogDto blogDto = new BlogDto();
                blogDto.setId(blog.getId());
                blogDto.setBlogTitle(blog.getBlogTitle());
                blogDto.setDescription(blog.getDescription());
                blogDto.setAuthor(blog.getUsers().getFullName());
                blogDto.setUsername(blog.getUsers().getUsername());
                blogList.add(blogDto);
            }
        }
        this.blogList=blogList;
    }

    public ResponseDto(Blog blog){
        this.code = "0";
        this.status = true;
        this.message = "Success";
        BlogDetailDto blogDetailDto = new BlogDetailDto();
        blogDetailDto.setId(blog.getId());
        blogDetailDto.setBlogTitle(blog.getBlogTitle());
        blogDetailDto.setDescription(blog.getDescription());
        blogDetailDto.setAuthor(blog.getUsers().getFullName());
        blogDetailDto.setUsername(blog.getUsers().getUsername());
        blogDetailDto.setBody(blog.getBody());
        blogDetailDto.setImage(blog.getImage());
        blogDetailDto.setCreatedAt(blog.getCreatedAt());
        blogDetailDto.setUpdatedAt(blog.getUpdatedAt());
        this.blog = blogDetailDto;
    }

    public ResponseDto(List<Comment> comments,String com){
        this.code = "0";
        this.status = true;
        this.message = "Success";
        List<CommentDto> commentDtos = new ArrayList<>();
        if (!comments.isEmpty()){
            for (Comment comment :comments) {
                CommentDto commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setComment(comment.getComment());
                commentDto.setAuthor(comment.getUsers().getFullName());
                commentDto.setUsername(comment.getUsers().getUsername());
                commentDto.setCreatedAt(comment.getCreatedAt());
                commentDtos.add(commentDto);
            }
        }
        this.commentList=commentDtos;
    }
}
