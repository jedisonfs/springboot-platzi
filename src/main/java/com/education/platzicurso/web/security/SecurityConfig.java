package com.education.platzicurso.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.education.platzicurso.domain.service.SecurityUserDetailsService;
import com.education.platzicurso.web.security.filter.JwtFilterRequest;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityUserDetailsService detailsService;

	@Autowired
	private JwtFilterRequest jwtRequest;

	/*
	 * Con este metodo se esta definiendo el usuario con el cual se van a logear en
	 * la aplicacion para que esto sea posible el usuario debe ser una clase que
	 * extienda de UserDeatilsService y sobreescribir el metodo loginUserByUsername
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService);
	}

	/*
	 * Con este metodo se dice a spring security permite el acesso a las uris de
	 * swagger
	 * 
	 */
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/v2/api-docs/**");
//		web.ignoring().antMatchers("/swagger.json");
//		web.ignoring().antMatchers("/swagger-ui.html");
//		web.ignoring().antMatchers("/swagger-resources/**");
//		web.ignoring().antMatchers("/webjars/**");
//	}

	/**
	 * Este metodo habilita las rutas que van a estar habilitadas para poser utilizarse sin estar autenticados
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll().anyRequest()
				.authenticated()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		/**
		 * Se dice que antes permitir el ingreso tiene un filtro y el tipo de filtro es un
		 * UsernamePasswordAuthenticationFilter
		 */
		http.addFilterBefore(jwtRequest, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
