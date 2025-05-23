package com.apiscrum.apiscrum.Security.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthUserDetailsService extends UserDetailsService {
    AuthUserDetails loadUserByEmail(String email);
}
