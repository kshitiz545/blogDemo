package ai.treeleaf.blog.userServices;

import ai.treeleaf.blog.model.Users;
import ai.treeleaf.blog.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public MyUserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + userName + " not found"));
        MyUserDetails myUserDetails = new MyUserDetails(user,getAuthorities(user));
        return myUserDetails;
    }

    private static Collection<? extends GrantedAuthority> getAuthorities(Users user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getRole()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}
