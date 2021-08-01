package ai.treeleaf.blog.controller;

import ai.treeleaf.blog.dto.AddBlogDto;
import ai.treeleaf.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseEntity<Object> addBlog(@RequestBody @Valid AddBlogDto addBlogDto){
        return this.blogService.addBlog(addBlogDto,httpServletRequest);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<Object> getAllBlogs(){
        return this.blogService.getAllBlogs();
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseEntity<Object> getBlogDetailsById(@RequestParam("id") Long id){
        return this.blogService.getBlogById(id);
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseEntity<Object> updateBlogById(@RequestBody @Valid AddBlogDto addBlogDto,@RequestParam("id") Long id){
        return this.blogService.updateBlog(addBlogDto,id,httpServletRequest);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public ResponseEntity<Object> deleteBlogById(@RequestParam("id") Long id){
        return this.blogService.deleteBlogById(id,httpServletRequest);
    }

}
