/*
 */
package com.mamoris.portfolio.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                //.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v1/jwt/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/person/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/about/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/certificate/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/education/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/experience/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/interest/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/socialmedia/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/skill/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/auth/**").permitAll()
                .anyRequest().authenticated();

    }

}
