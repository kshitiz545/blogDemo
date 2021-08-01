package ai.treeleaf.blog.userServices;


import ai.treeleaf.blog.model.Users;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@ToString @NoArgsConstructor
public class MyUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean status;
    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(Users user, Collection<? extends GrantedAuthority> authorities){
        this.username=user.getUsername();
        this.password=user.getPassword();
        this.status=user.isStatus();
        this.authorities= authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status;
    }

    @Override
    public boolean isEnabled() {
        return status;
    }
}

