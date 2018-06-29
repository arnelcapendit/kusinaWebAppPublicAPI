package com.accenture.piwik.security.config;

//import com.accenture.piwik.filter.tokengenerator.TokenGeneratorAuthenticationFilter;
import com.accenture.piwik.security.auth.JwtAuthenticationEntryPoint;
import com.accenture.piwik.security.auth.JwtAuthenticationProvider;
import com.accenture.piwik.security.auth.JwtAuthenticationTokenFilter;
import com.accenture.piwik.security.auth.JwtFailureHandler;
import com.accenture.piwik.security.auth.JwtSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private JwtAuthenticationProvider authenticationProvider;
	
    @Autowired
    private JwtAuthenticationEntryPoint entryPoint;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        filter.setAuthenticationFailureHandler(new JwtFailureHandler());
        return filter;
    }

//    @Bean
//    public FilterRegistrationBean tokenGeneratorFilter() {
//
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new TokenGeneratorAuthenticationFilter());
//        registration.addUrlPatterns("/token/*");
//        registration.setName("TokenGenerator");
//        registration.setOrder(1);
//        return registration;
//    } 


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
//        		.authorizeRequests().antMatchers("/hello").permitAll()
//        			.and()
                .authorizeRequests().antMatchers("/piwik/**").authenticated()
                	.and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                	.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        			.and()
//        		.formLogin()
//        			.successHandler(new JwtSuccessHandler())                  
//                    .failureHandler(new JwtFailureHandler())                 
//                    .loginPage("/token")
//                    .permitAll();
        			
        			

        http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();

    }
}

