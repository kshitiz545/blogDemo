package ai.treeleaf.blog.controller;

import ai.treeleaf.blog.dto.AddBlogDto;
import ai.treeleaf.blog.dto.AddCommentDto;
import ai.treeleaf.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<Object> addComment(@RequestBody @Valid AddCommentDto addCommentDto){
        return this.blogService.addComment(addCommentDto,httpServletRequest);
    }

    @RequestMapping(value = "/get/all",method = RequestMethod.GET)
    public ResponseEntity<Object> getAllCommentByBlogId(@RequestParam("id") @Valid Long id){
        return this.blogService.getAllCommentByBlogId(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<Object> updateCommentById(@RequestBody @Valid AddCommentDto addCommentDto, @RequestParam("id") Long id){
        return this.blogService.updateComment(addCommentDto,id,httpServletRequest);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseEntity<Object> deleteCommentById(@RequestParam("id") Long id){
        return this.blogService.deleteCommentById(id,httpServletRequest);
    }

}
