package br.com.fiap.wastemanagementsystem.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private VerifyToken verifyToken;

    @Bean
    public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize

                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()

                                .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole("USER", "COLLECTOR")
                                .requestMatchers(HttpMethod.PATCH, "/users/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/users/{id}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/districts").hasAnyRole("USER", "COLLECTOR")
                                .requestMatchers(HttpMethod.GET, "/districts/{id}").hasAnyRole("USER", "COLLECTOR")
                                .requestMatchers(HttpMethod.POST, "/districts").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/districts").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/districts/{id}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/residents").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/residents/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/residents/district/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/residents").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/residents").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/residents/{id}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/trash-cans").hasAnyRole("USER", "COLLECTOR")
                                .requestMatchers(HttpMethod.GET, "/trash-cans/{id}").hasAnyRole("USER", "COLLECTOR", "SYSTEM")
                                .requestMatchers(HttpMethod.GET, "/trash-cans/district/{id}").hasAnyRole("USER", "COLLECTOR")
                                .requestMatchers(HttpMethod.POST, "/trash-cans").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/trash-cans/{id}").hasRole("SYSTEM")
                                .requestMatchers(HttpMethod.DELETE, "/trash-cans/{id}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/trucks").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/trucks/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/trucks/district/{id}").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/trucks").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/trucks").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/trucks/{id}").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.PATCH, "/schedules/{id}").hasRole("COLLECTOR")
                                .requestMatchers(HttpMethod.GET, "/schedules/{id}").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/schedules/district/{id}").hasAnyRole("USER", "COLLECTOR")
                                .requestMatchers(HttpMethod.GET, "/schedules/district/period").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/schedules/unfinished/district/period").hasRole("USER")
                                .requestMatchers(HttpMethod.GET, "/schedules/delayed/district/period").hasRole("USER")

                                .requestMatchers(HttpMethod.GET, "/reports").hasRole("USER")

                                .anyRequest().authenticated())

                .addFilterBefore(
                        verifyToken,
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();

    }

    @Bean
    public AuthenticationManager getAuthenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
