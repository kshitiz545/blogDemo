package ai.treeleaf.blog.service;

import ai.treeleaf.blog.dto.AddBlogDto;
import ai.treeleaf.blog.dto.AddCommentDto;
import ai.treeleaf.blog.model.Blog;
import ai.treeleaf.blog.model.Comment;
import ai.treeleaf.blog.model.Users;
import ai.treeleaf.blog.repository.BlogRepository;
import ai.treeleaf.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogDaoImpl implements BlogDao{

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Blog addBlog(AddBlogDto addBlogDto, Users users) {
        Blog blog = new Blog();
        blog.setBlogTitle(addBlogDto.getBlogTitle());
        blog.setBody(addBlogDto.getBody());
        blog.setDescription(addBlogDto.getDescription());
        blog.setImage(addBlogDto.getImage());
        blog.setUsers(users);
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> findAllBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public Optional<Blog> findBlogByID(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog;
    }

    @Override
    public Blog updateBlog(AddBlogDto addBlogDto, Blog blog) {
        blog.setBlogTitle(addBlogDto.getBlogTitle());
        blog.setBody(addBlogDto.getBody());
        blog.setDescription(addBlogDto.getDescription());
        blog.setImage(addBlogDto.getImage());
        return blogRepository.save(blog);
    }

    @Override
    public void deleteBlog(Blog blog) {
        List<Comment> comments = findAllCommmentByBlog(blog);
        commentRepository.deleteAll(comments);
        blogRepository.delete(blog);
    }

    @Override
    public Comment addComment(AddCommentDto addCommentDto, Users users) {
        Comment comment = new Comment();
        comment.setComment(addCommentDto.getComment());
        comment.setUsers(users);
        comment.setBlog(findBlogByID(addCommentDto.getBlog_id()).get());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllCommmentByBlog(Blog blog) {
        return commentRepository.findByBlog(blog);
    }

    @Override
    public Optional<Comment> findCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment updateComment(AddCommentDto addCommentDto, Comment comment) {
        comment.setComment(addCommentDto.getComment());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
