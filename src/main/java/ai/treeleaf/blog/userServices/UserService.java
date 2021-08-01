package ai.treeleaf.blog.userServices;

import ai.treeleaf.blog.jwt.JwtUtil;
import ai.treeleaf.blog.dto.LoginDto;
import ai.treeleaf.blog.dto.RegisterDto;
import ai.treeleaf.blog.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private MyUserDetailService myUserDetailService;
    private JwtUtil jwtUtil;
    private UserDao userDao;
    private AuthenticationManager authenticationManager;

    @Autowired
    public UserService(MyUserDetailService myUserDetailService, JwtUtil jwtUtil, UserDao userDao, AuthenticationManager authenticationManager) {
        this.myUserDetailService = myUserDetailService;
        this.jwtUtil = jwtUtil;
        this.userDao = userDao;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<Object> signIn(LoginDto request){
        MyUserDetails myUserDetails = null;
        try {
            myUserDetails = myUserDetailService.loadUserByUsername(request.getUsername());
        }
        catch (UsernameNotFoundException e){
            return new ResponseEntity<>(new ResponseDto("101",false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
        try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(request.getUsername(),
                           request.getPassword()));
       }
       catch (BadCredentialsException e){
           return new ResponseEntity<>(new ResponseDto("102",false, e.getMessage()), HttpStatus.BAD_REQUEST);
       }

        String jwtToken = jwtUtil.generateToken(myUserDetails);

        return new ResponseEntity<>(new ResponseDto(jwtToken,jwtUtil.extractExpiration(jwtToken)),HttpStatus.OK);
    }

    public ResponseEntity<Object> signUp(RegisterDto request) {
        if (userDao.adduser(request)) return new ResponseEntity<>(new ResponseDto("0",true, "User added successfully"),HttpStatus.BAD_REQUEST);
        else return new ResponseEntity<>(new ResponseDto("103",false, "Username is already taken"),HttpStatus.BAD_REQUEST);

    }
}
