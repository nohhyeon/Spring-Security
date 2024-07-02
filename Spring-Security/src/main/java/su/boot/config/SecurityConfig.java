package su.boot.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import jakarta.inject.Inject;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import su.boot.begin.jpa.service.CustomUserDetailsService;

@Configuration
// Spring Security의 웹 보안 지원을 활성화한다.
@EnableWebSecurity
public class SecurityConfig {
	private final AccessDeniedHandler accessDeniedHandler;
	private final CustomUserDetailsService customUserDetailsService;

	@Inject
	public SecurityConfig(AccessDeniedHandler accessDeniedHandler, CustomUserDetailsService customUserDetailsService) {
		this.accessDeniedHandler = accessDeniedHandler;
		this.customUserDetailsService = customUserDetailsService;
	}

//SecurityFilterChain 빈을 정의하여 HTTP 보안 설정을 구성한다.
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				.authorizeHttpRequests(auth -> auth.requestMatchers("/**").hasRole("ADMIN")
						.requestMatchers(" /president/**").hasRole("PRESIDENT").requestMatchers("/manager/**")
						.hasRole("MANAGER").requestMatchers("/analyst/**").hasRole("ANALYST")
						.requestMatchers("/clerk/**").hasRole("CLERK").requestMatchers("/salesman /**")
						.hasRole("SALESMAN").requestMatchers("/common/**").hasAnyRole("ANALYST", "CLERK", "SALESMAN")
						.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/", true)
						.failureUrl("/login?error=true"))
				.logout(logout -> logout.permitAll())
				.exceptionHandling(exception -> exception.accessDeniedHandler(accessDeniedHandler));
		return httpSecurity.build();
	}

// PasswordEncoder 빈을 정의하여 비밀번호 인코딩을 처리한다.
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

//AuthenticationManagerBuilder 객체를 통해 사용자 세부 정보 서비스와 비밀번호 인코더를 설정한다.
	@Inject
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
}
