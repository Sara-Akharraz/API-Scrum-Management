package com.apiscrum.apiscrum.Security.Service;

import com.apiscrum.apiscrum.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUserDetails implements  UserDetails{
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    public AuthUserDetails(User user){
        this.email=user.getEmail();
        this.password=user.getPasswd();
        this.authorities= List.of(new SimpleGrantedAuthority(user.getRole().name()));
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
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
