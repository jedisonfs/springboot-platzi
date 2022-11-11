package com.education.platzicurso.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.platzicurso.domain.dto.AuthenticationRequest;
import com.education.platzicurso.domain.dto.AuthenticationResponse;
import com.education.platzicurso.domain.service.SecurityUserDetailsService;
import com.education.platzicurso.web.security.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private SecurityUserDetailsService serviceSecurity;

	@Autowired
	private JWTUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> createToken(@RequestBody AuthenticationRequest auRequest) {

		try {
			/**
			 * Este metodod realiza la autenticacion del usuario, es decir valida que si el tipo de validacon
			 * que se hace cumple con los parametros en este caso es una validacion de usuario y contraseña.
			 */
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(auRequest.getUsername(), auRequest.getPassword()));
			/**
			 * Devuelve un usuario que este registrado de lo contrario debe generar un error 400
			 */
			UserDetails userDetails = serviceSecurity.loadUserByUsername(auRequest.getUsername());
			
			/**
			 * Este metodo genera el token para el usuario que se esta creando.
			 */
			String jwt = jwtUtil.generateToken(userDetails);

			return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
			
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}
}
