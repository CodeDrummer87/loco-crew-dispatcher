package x.team.loco_crew_dispather.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import x.team.loco_crew_dispather.filter.JwtFilter;
import x.team.loco_crew_dispather.service.CustomUserDetailService;

@Component
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(CustomUserDetailService customUserDetailService, JwtFilter jwtFilter) {
        this.customUserDetailService = customUserDetailService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //.:: Отключаем CSRF для REST API
                //.csrf(AbstractHttpConfigurer::disable) //.:: либо можно написать более лаконично
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //.:: Без сессий
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/**/*.html","/**/*.css","/**/*.js").permitAll()
                        .requestMatchers("/swagger-ui/**",
                                            "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resourses/**",
                                "/webjars/**",
                                "/api/v1/employees/**",
                                "/api/v1/locomotive-crews/**",
                                "/login")
                        .permitAll()//.:: Страница входа доступна всем
                        .requestMatchers("/api/v1/admin",
                                "/api/v1/register").hasRole("ADMIN") //.:: Админка требует наличие роли ADMIN
                        .anyRequest().authenticated() //.:: Всё остальное требует авторизации
                )
                //.authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); //.:: Добавляем JWT фильтр

        return http.build();
    }
}
