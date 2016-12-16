package Security;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.inject.Inject;

/**
 * @author Simon Hyben
 *
 */
@Configuration
@EnableWebSecurity
//@ComponentScan(basePackages = {"Config", "Controllers", "Enums","Security"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Inject
    private AuthenticationProviderImpl authProvider; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").anonymous()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("username").passwordParameter("password")
				.defaultSuccessUrl("/home")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/errorpage")
				.and()
			.csrf();
		// @formatter:on
	}

}
