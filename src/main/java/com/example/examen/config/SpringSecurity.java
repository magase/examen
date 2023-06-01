package com.example.examen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**").permitAll()
        http
                .userDetailsService(userDetailsService)
                .csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()//.hasRole("ADMIN")
                                .requestMatchers("/index/**").authenticated()
                                .requestMatchers("/").authenticated()
                                .requestMatchers("/alumnos/new/**").permitAll()//.authenticated()
                                .requestMatchers("/alumnos/**").authenticated()
                                .requestMatchers("/alumnos/edit/**").authenticated()
                                .requestMatchers("/grupos/**").authenticated()
                                .requestMatchers("/asignaturas/**").authenticated()
                                .requestMatchers("/roles/**").hasRole("ADMIN")//.authenticated()
                                .requestMatchers("/error/**").permitAll()
                                .requestMatchers("/login","/css/**","/js/**","/images/**").permitAll()


                )
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/index")
                                .permitAll()

                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                                .deleteCookies("JSESSIONID")
                )
                //.rememberMe().userDetailsService(this.userDetailsService)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1)

                ;
        return http.build();
    }


    //Configuracion de la autenticacion
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


}
