/*
 */
package com.mamoris.portfolio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MainSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }*/

 /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //   auth.userDetailsService(userDetailsService).pa;
        auth.userDetailsService(userDetailsService).passwordEncoder(b);
    }*/
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /* @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //@Override
    /*protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authProvider());
    }*/
 /*@Override
    protected void configure(HttpSecurity http) throws Exception {

        // Set permissions on endpoints
        http.
                // Set session management to stateless
                sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //.antMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/auth/new").permitAll()
                //.antMatchers(HttpMethod.GET, "/api/certificate").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/certificate/create").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/certificate/create").hasAuthority("ADMIN")
                .antMatchers("/auth/login").permitAll()
                //.antMatchers("/certificate/certificates").permitAll()
                .anyRequest().authenticated()
                .and()
                // Set unauthorized requests exception handler
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                // Enable CORS and disable CSRF                
                .cors()
                .and()
                //.httpBasic().realmName("RDF-TRANSFORMER")
                //.and()
                .csrf().disable();
        /*.authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/**").permitAll()
     */
    // Add JWT token filter
    // http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    //}*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //http.cors(withDefaults()).csrf().disable()
        http.cors().disable().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/about/abouts").permitAll()
                .antMatchers("/api/certificate/certificates").permitAll()
                .antMatchers("/api/education/educations").permitAll()
                .antMatchers("/api/experience/**").permitAll()
                .antMatchers("/api/interest/interests").permitAll()
                .antMatchers("/api/person/persons").permitAll()
                .antMatchers("/api/person/**").permitAll()
                .antMatchers("/api/socialmedia/socialmedias").permitAll()
                .antMatchers("/api/skill/skills").permitAll()
                //.anyRequest().authenticated()
                .and()
                //      .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                //.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /*@Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cc = new CorsConfiguration();
        cc.setAllowedHeaders(Arrays.asList("Origin,Accept", "X-Requested-With", "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers", "Authorization"));
        cc.setExposedHeaders(Arrays.asList("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        cc.setAllowedOrigins(Arrays.asList("/*"));
        cc.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "PATCH"));
        cc.addAllowedOrigin("http://localhost:8080");
        cc.setMaxAge(Duration.ZERO);
        cc.setAllowCredentials(Boolean.TRUE);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cc);
        return source;
    }*/
}
