package ai.treeleaf.blog.controller;

import ai.treeleaf.blog.dto.LoginDto;
import ai.treeleaf.blog.dto.RegisterDto;
import ai.treeleaf.blog.dto.ResponseDto;
import ai.treeleaf.blog.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<Object> signIn(@RequestBody @Valid LoginDto request){
        return this.userService.signIn(request);
    }

    @RequestMapping(value = "/unauthorized",method = RequestMethod.GET)
    public ResponseEntity<Object> unauthorized(){
        return new ResponseEntity<>(new ResponseDto("100",false, "Unauthorized"), HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<Object> signUp(@RequestBody @Valid RegisterDto request){
        return this.userService.signUp(request);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

}
