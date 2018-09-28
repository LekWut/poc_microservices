package base.spring.security;


//import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
	
	@Autowired
	@Qualifier("commonDaoLdapAuthenticationProvider")
	AuthenticationProvider commonDaoLdapAuthenticationProvider;
	
	@Autowired
	@Qualifier("commonAuthenticationSuccessHandler")
	AuthenticationSuccessHandler commonAuthenticationSuccessHandler;

	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("sysadmin").password("password").roles("SYSADMIN")
//		.and()
//		.withUser("user").password("password").roles("USER");
//		auth.userDetailsService(commonUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired
	@Qualifier("commonUserDetailsService")
	UserDetailsService commonUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/help/**", "/mobilePages/public/**", "/pages/public/**", "/resources/**", "index.xhtml*").permitAll() 
//		.antMatchers("/pages/secured/xxx/**").access("hasRole('ROLE_SYSADMIN')")
//		.antMatchers("/pages/secured/yyy/**").authenticated()
//		.antMatchers("/pages/public/zzz/**").permitAll()
		.antMatchers("/mobilePages/secured/**").authenticated() 
		.antMatchers("/pages/secured/**").authenticated() 
//		.antMatchers("/servlet/secured/**").authenticated() 
		.anyRequest().permitAll()
		.and()
		.formLogin().loginPage("/pages/public/login.xhtml")
		.usernameParameter("loginForm:j_username").passwordParameter("loginForm:j_password")
		.failureUrl("/pages/public/login.xhtml?error=true")
		.successHandler(commonAuthenticationSuccessHandler).permitAll()
		.and()
		.logout().logoutUrl("/logout").permitAll().deleteCookies("JSESSIONID").invalidateHttpSession(true)
		.and()
//		.rememberMe().userDetailsService(commonUserDetailsService).rememberMeParameter("loginForm:_spring_security_remember_me_input").tokenRepository(persistentTokenRepository())
//		.tokenValiditySeconds(60*60*24*30)
//		.and()
		.headers().cacheControl().disable()
		.and()
		.csrf().disable();
		http.authenticationProvider(commonDaoLdapAuthenticationProvider);
	}
	
//	@Autowired
//	@Qualifier("appDataSource")
//	DataSource dataSource;
	
//	@Bean
//	public PersistentTokenRepository persistentTokenRepository() {
//		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//		db.setDataSource(dataSource);
//		return db;
//	}
	
}