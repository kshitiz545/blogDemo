package ai.treeleaf.blog.userServices;


import ai.treeleaf.blog.model.Roles;
import ai.treeleaf.blog.model.Users;
import ai.treeleaf.blog.repository.RolesRepository;
import ai.treeleaf.blog.repository.UsersRepository;
import ai.treeleaf.blog.dto.RegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDao {

    private UsersRepository usersRepository;
    private MyUserDetailService myUserDetailService;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDao(UsersRepository usersRepository, MyUserDetailService myUserDetailService, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.myUserDetailService = myUserDetailService;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean adduser(RegisterDto request) {
        Optional<Users> existingUser = usersRepository.findByUsername(request.getUsername());
        if (!existingUser.isPresent()) {

            Optional<Roles> roles = rolesRepository.findByRole("ROLE_USER");
            Users user = new Users();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail().toLowerCase());
            user.setRoles(roles.stream().collect(Collectors.toList()));
            user.setStatus(true);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setMobile(request.getMobile());
            user.setAddress(request.getAddress());
            user.setFullName(request.getFullName());
            Users users = usersRepository.save(user);
            return true;
        } else return false;
    }

    public Users getUsersByUserName(String username){
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return user;
    }

}
