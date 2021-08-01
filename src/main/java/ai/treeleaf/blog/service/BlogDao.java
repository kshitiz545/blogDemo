package ai.treeleaf.blog.service;

import ai.treeleaf.blog.dto.AddBlogDto;
import ai.treeleaf.blog.dto.AddCommentDto;
import ai.treeleaf.blog.model.Blog;
import ai.treeleaf.blog.model.Comment;
import ai.treeleaf.blog.model.Users;

import java.util.List;
import java.util.Optional;

public interface BlogDao {
    public Blog addBlog(AddBlogDto addBlogDto, Users users);
    public List<Blog> findAllBlogs();
    public Optional<Blog> findBlogByID(Long id);
    public Blog updateBlog(AddBlogDto addBlogDto, Blog blog);
    public void deleteBlog(Blog blog);

    public Comment addComment(AddCommentDto addCommentDto, Users users);
    List<Comment> findAllCommmentByBlog(Blog blog);
    public Optional<Comment> findCommentById(Long id);
    public Comment updateComment(AddCommentDto addCommentDto, Comment comment);
    public void deleteComment(Comment comment);
}
