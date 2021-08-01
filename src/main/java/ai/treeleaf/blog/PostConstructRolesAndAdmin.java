package ai.treeleaf.blog;

import ai.treeleaf.blog.configuration.SecurityProperties;
import ai.treeleaf.blog.model.Roles;
import ai.treeleaf.blog.model.Users;
import ai.treeleaf.blog.repository.RolesRepository;
import ai.treeleaf.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PostConstructRolesAndAdmin {


    private UsersRepository usersRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PostConstructRolesAndAdmin(UsersRepository usersRepository, RolesRepository rolesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private final String adminUsername= SecurityProperties.getProps().getProperty("admin.username");
    private final String adminPassword= SecurityProperties.getProps().getProperty("admin.password");
    private final String adminEmail= SecurityProperties.getProps().getProperty("admin.email");

    @PostConstruct
    public void postConstructRolesAndAdmin(){

        if(rolesRepository.findAll().isEmpty()){
            Roles roles = new Roles();
            roles.setRole("ROLE_ADMIN");
            rolesRepository.save(roles);
            roles = new Roles();
            roles.setRole("ROLE_ACTUATOR");
            rolesRepository.save(roles);
            roles = new Roles();
            roles.setRole("ROLE_USER");
            rolesRepository.save(roles);
            List<Roles> rolesList = rolesRepository.findAll();
            Users users = new Users();
            users.setFullName(adminUsername);
            users.setUsername(adminUsername);
            users.setPassword(passwordEncoder.encode(adminPassword));
            users.setEmail(adminEmail);
            users.setStatus(true);
            users.setRoles(rolesList);
            usersRepository.save(users);
        }

    }
}
