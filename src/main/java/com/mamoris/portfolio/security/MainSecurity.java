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
                .antMatchers(HttpMethod.GET, "/api/jwt/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/person/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/about/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/certificate/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/education/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/experience/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/interest/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/socialmedia/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/skill/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated();

    }

}
