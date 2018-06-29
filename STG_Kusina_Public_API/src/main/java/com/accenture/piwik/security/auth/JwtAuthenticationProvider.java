package com.accenture.piwik.security.auth;

import com.accenture.piwik.model.User;
import com.accenture.piwik.security.model.JwtAuthenticationToken;
import com.accenture.piwik.security.model.JwtPiwikUser;
import com.accenture.piwik.security.model.JwtPiwikUserDetails;
import com.accenture.piwik.security.model.JwtUser;
import com.accenture.piwik.security.model.JwtUserDetails;
import com.accenture.piwik.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;
    
    @Autowired
    private UserService userService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        JwtPiwikUser jwtPiwikUser = validator.validate(token);

        if (jwtPiwikUser == null) {
            throw new RuntimeException("JWT Token is incorrect");
        }

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(jwtPiwikUser.getAccess());
        
        return new JwtPiwikUserDetails(
        		jwtPiwikUser.getEid(),
        		jwtPiwikUser.getAirid(),
        		jwtPiwikUser.getId(),
        		jwtPiwikUser.getAccess(),
        		jwtPiwikUser.getExpiryDate(),
        		token,
        		grantedAuthorities
        		);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
