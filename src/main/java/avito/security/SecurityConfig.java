package avito.security;

import org.aspectj.weaver.ast.And;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/profile/**",
							"/animal_ad/**", "/apartments_ad/**", "/car_ad/**", "/garage_ad/**",
							 "/house_ad/**", "/motorcycle_ad/**", "/service_ad/**",
							 "/truck_and_special_machinery/**")
					.hasRole("USER")
				.antMatchers("/home**", "/**" ).permitAll()
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/home")
			.and()
				.logout()
				.logoutSuccessUrl("/home")
			.and()
				.csrf()
					.ignoringAntMatchers("/h2-console/**");
				
	}
	
}
