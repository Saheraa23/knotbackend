package com.example.knot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.knot.service.CustomUserDetailsService;
import com.example.knot.util.jwtFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final jwtFilter jwt;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(jwtFilter jwt,
                          CustomUserDetailsService userDetailsService) {
        this.jwt = jwt;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authenticationProvider(authenticationProvider())

            .authorizeHttpRequests(auth -> auth
                   
                    .requestMatchers("/auth/register","/auth/login").permitAll()
                    .requestMatchers("/auth/get").hasAnyRole("FACILITATOR","CONTRIBUTOR","STAKEHOLDER")
                    .requestMatchers("/auth/get/**").hasAnyRole("FACILITATOR", "CONTRIBUTOR","STAKEHOLDER")
                    .requestMatchers("/auth/put/**").hasAnyRole("FACILITATOR","CONTRIBUTOR")
                    .requestMatchers("/auth/patch/**").hasAnyRole("FACILITATOR", "CONTRIBUTOR")
                    .requestMatchers("/auth/delete/**").hasRole("FACILITATOR")


                    .requestMatchers("/Board/postData").hasAnyRole("FACILITATOR", "CONTRIBUTOR")
                    .requestMatchers("/Board/getData","/Board/getData/**").hasAnyRole("FACILITATOR", "CONTRIBUTOR", "STAKEHOLDER")
                    .requestMatchers("/Board/putData/**","/Board/patchData/**").hasAnyRole("FACILITATOR", "CONTRIBUTOR")
                    .requestMatchers("/Board/deleteData/**").hasAnyRole("FACILITATOR")


                    .requestMatchers("/Note/postNote").hasAnyRole("FACILITATOR", "CONTRIBUTOR")
                    .requestMatchers("/Note/getNote","/Note/getNote/**").hasAnyRole("FACILITATOR", "CONTRIBUTOR", "STAKEHOLDER")
                    .requestMatchers("/Note/putNote/**","/Note/patchNote/**").hasAnyRole("FACILITATOR", "CONTRIBUTOR")
                    .requestMatchers("/Note/deleteNote/**").hasAnyRole("FACILITATOR")


                    .requestMatchers("/member/postmember").hasAnyRole("FACILITATOR")
                    .requestMatchers("/member/getmember") .hasAnyRole("FACILITATOR", "CONTRIBUTOR", "STAKEHOLDER")
                    .requestMatchers("/member/getmember/**").hasAnyRole("FACILITATOR","STAKEHOLDER")
                    .requestMatchers("/member/putmember/**").hasAnyRole("FACILITATOR")
                    .requestMatchers("/member/patchmember/**").hasAnyRole("FACILITATOR")
                    .requestMatchers("/member/deletemember/**").hasRole("FACILITATOR")


                    .requestMatchers("/Activities/Post").hasAnyRole("FACILITATOR","CONTRIBUTOR")
                    .requestMatchers("/Activities/Get").hasAnyRole("FACILITATOR", "CONTRIBUTOR")
                    .requestMatchers("/Activities/Get/**").hasAnyRole("FACILITATOR", "CONTRIBUTOR","STAKEHOLDER")  
                    .requestMatchers("/Activities/Put/**").hasAnyRole("FACILITATOR")
                    .requestMatchers("/Activities/Patch/**").hasAnyRole("FACILITATOR")
                    .requestMatchers("/Activities/Delete/**").hasAnyRole("FACILITATOR")

                    .anyRequest().authenticated())

            .httpBasic(httpBasic -> httpBasic.disable())

            .formLogin(form -> form.disable())

            .addFilterBefore(jwt,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

