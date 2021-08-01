package ai.treeleaf.blog.service;

import ai.treeleaf.blog.dto.AddBlogDto;
import ai.treeleaf.blog.dto.AddCommentDto;
import ai.treeleaf.blog.model.Users;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface BlogService {

    public Users getUserFromHttpRequest(HttpServletRequest request);

    public ResponseEntity<Object> addBlog(AddBlogDto addBlogDto, HttpServletRequest httpServletRequest);

    public ResponseEntity<Object> getAllBlogs();

    public ResponseEntity<Object> getBlogById(Long id);

    public ResponseEntity<Object> updateBlog(AddBlogDto addBlogDto, Long id,HttpServletRequest httpServletRequest);

    public ResponseEntity<Object> deleteBlogById(Long id,HttpServletRequest httpServletRequest);

    public ResponseEntity<Object> addComment(AddCommentDto addCommentDto, HttpServletRequest httpServletRequest);

    public ResponseEntity<Object> getAllCommentByBlogId(Long id);

    public ResponseEntity<Object> updateComment(AddCommentDto addCommentDto, Long id,HttpServletRequest httpServletRequest);

    public ResponseEntity<Object> deleteCommentById(Long id,HttpServletRequest httpServletRequest);




}
