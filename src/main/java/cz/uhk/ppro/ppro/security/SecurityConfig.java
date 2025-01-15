package cz.uhk.ppro.ppro.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{

    private final UserDetailsService userService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/", "/403", "/login", "/register").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Custom login page
                        .loginProcessingUrl("/login")  // The URL that handles the login form submission
                        .successHandler((request, response, authentication) -> {
                            if (authentication != null && authentication.isAuthenticated()) {
                                // Check if user has the "ADMIN" role
                                if (authentication.getAuthorities().stream()
                                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                                    response.sendRedirect("/admin/");  // Redirect to admin page
                                } else {
                                    response.sendRedirect("/");  // Redirect to home page
                                }
                            } else {
                                response.sendRedirect("/login");  // If not authenticated, stay on login page
                            }
                        })
                        .permitAll()  // Allow all users to access the login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")  // Redirect to login after logout
                        .permitAll()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/access-denied")  // Custom access denied page
                );

        return http.build();
    }
}
