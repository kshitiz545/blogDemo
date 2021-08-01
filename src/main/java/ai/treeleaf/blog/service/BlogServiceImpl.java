package ai.treeleaf.blog.service;

import ai.treeleaf.blog.dto.AddBlogDto;
import ai.treeleaf.blog.dto.AddCommentDto;
import ai.treeleaf.blog.dto.ResponseDto;
import ai.treeleaf.blog.jwt.JwtUtil;
import ai.treeleaf.blog.model.Blog;
import ai.treeleaf.blog.model.Comment;
import ai.treeleaf.blog.model.Users;
import ai.treeleaf.blog.userServices.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BlogDao blogDao;

    @Override
    public Users getUserFromHttpRequest(HttpServletRequest request) {
        String jwt = request.getHeader("Authorization").substring(7);
        String username = jwtUtil.extractUsername(jwt);
        return userDao.getUsersByUserName(username);
    }

    @Override
    public ResponseEntity<Object> addBlog(AddBlogDto addBlogDto, HttpServletRequest httpServletRequest) {
        try {
            Users users = getUserFromHttpRequest(httpServletRequest);
            Blog blog = blogDao.addBlog(addBlogDto,users);
            return new ResponseEntity<>(new ResponseDto("0",true, "Blog Added Successfully",blog.getId()), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("201",false, "Something has gone wrong. Please contact us"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getAllBlogs() {
        try {
            List<Blog> blogs = blogDao.findAllBlogs();
            if (blogs.isEmpty()) return new ResponseEntity<>(new ResponseDto("0",true, "No Blogs found."), HttpStatus.OK);
            return new ResponseEntity<>(new ResponseDto(blogs), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("201",false, "Something has gone wrong. Please contact us"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getBlogById(Long id) {
       Optional<Blog> optionalBlog= blogDao.findBlogByID(id);
       if (optionalBlog.isPresent()) return new ResponseEntity<>(new ResponseDto(optionalBlog.get()),HttpStatus.OK);
       else return new ResponseEntity<>(new ResponseDto("202",false, "Blog Not Found"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> updateBlog(AddBlogDto addBlogDto, Long id, HttpServletRequest httpServletRequest) {
        Users users = getUserFromHttpRequest(httpServletRequest);
        Optional<Blog> optionalBlog= blogDao.findBlogByID(id);
        if (optionalBlog.isPresent()){
            if (optionalBlog.get().getUsers().getUsername().equals(users.getUsername())){
                Blog blog = blogDao.updateBlog(addBlogDto,optionalBlog.get());
                return new ResponseEntity<>(new ResponseDto("0",true, "Blog Updated Successfully",blog.getId()), HttpStatus.OK);
            }
            else return new ResponseEntity<>(new ResponseDto("203",false, "Only author can update"), HttpStatus.BAD_REQUEST);

        }
        else return new ResponseEntity<>(new ResponseDto("202",false, "Blog Not Found"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> deleteBlogById(Long id, HttpServletRequest httpServletRequest) {
        Users users = getUserFromHttpRequest(httpServletRequest);
        Optional<Blog> optionalBlog= blogDao.findBlogByID(id);
        if (optionalBlog.isPresent()){
            if (optionalBlog.get().getUsers().getUsername().equals(users.getUsername())){
                blogDao.deleteBlog(optionalBlog.get());
                return new ResponseEntity<>(new ResponseDto("0",true, "Blog deleted Successfully"), HttpStatus.OK);
            }
            else return new ResponseEntity<>(new ResponseDto("204",false, "Only author can delete"), HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(new ResponseDto("202",false, "Blog Not Found"), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> addComment(AddCommentDto addCommentDto, HttpServletRequest httpServletRequest) {
        try {
            Users users = getUserFromHttpRequest(httpServletRequest);
            Optional<Blog> optionalBlog= blogDao.findBlogByID(addCommentDto.getBlog_id());
            if (optionalBlog.isEmpty()) return new ResponseEntity<>(new ResponseDto("202",false, "Blog Not Found"), HttpStatus.BAD_REQUEST);

            Comment comment = blogDao.addComment(addCommentDto,users);
            return new ResponseEntity<>(new ResponseDto("0",true, "Comment Added Successfully",comment.getId()), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ResponseDto("201",false, "Something has gone wrong. Please contact us"), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Object> getAllCommentByBlogId(Long id) {
        Optional<Blog> optionalBlog= blogDao.findBlogByID(id);
        if (optionalBlog.isEmpty()) return new ResponseEntity<>(new ResponseDto("202",false, "Blog Not Found"), HttpStatus.BAD_REQUEST);
        List<Comment> comments = blogDao.findAllCommmentByBlog(optionalBlog.get());
        if (comments.isEmpty()) return new ResponseEntity<>(new ResponseDto("0",true, "No comments found."), HttpStatus.OK);
        return new ResponseEntity<>(new ResponseDto(comments,"comment"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateComment(AddCommentDto addCommentDto, Long id, HttpServletRequest httpServletRequest) {
        Users users = getUserFromHttpRequest(httpServletRequest);
        Optional<Comment> optionalComment = blogDao.findCommentById(id);
        Optional<Blog> optionalBlog= blogDao.findBlogByID(addCommentDto.getBlog_id());
        if (optionalBlog.isPresent() && optionalComment.isPresent()){
            if (optionalBlog.get().getUsers().getUsername().equals(users.getUsername())){
                if (optionalComment.get().getBlog().getId().equals(optionalBlog.get().getId())){
                    Comment comment = blogDao.updateComment(addCommentDto,optionalComment.get());
                    return new ResponseEntity<>(new ResponseDto("0",true, "Comment Updated Successfully",comment.getId()), HttpStatus.OK);
                }
                else return new ResponseEntity<>(new ResponseDto("204",false, "Blog and comment mismatch"), HttpStatus.BAD_REQUEST);
            }
            else return new ResponseEntity<>(new ResponseDto("203",false, "Only author can update"), HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(new ResponseDto("206",false, "Blog and/or comment not found"), HttpStatus.BAD_REQUEST);

    }

    @Override
    public ResponseEntity<Object> deleteCommentById(Long id, HttpServletRequest httpServletRequest) {
        Users users = getUserFromHttpRequest(httpServletRequest);
        Optional<Comment> optionalComment= blogDao.findCommentById(id);
        if (optionalComment.isPresent()){
            if (optionalComment.get().getUsers().getUsername().equals(users.getUsername())){
                blogDao.deleteComment(optionalComment.get());
                return new ResponseEntity<>(new ResponseDto("0",true, "Comment deleted Successfully"), HttpStatus.OK);
            }
            else return new ResponseEntity<>(new ResponseDto("207",false, "Only author can delete"), HttpStatus.BAD_REQUEST);
        }
        else return new ResponseEntity<>(new ResponseDto("205",false, "Comment Not Found"), HttpStatus.BAD_REQUEST);

    }
}
