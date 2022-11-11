package com.education.platzicurso.web.security;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	private static final String KEY = "pl4tz1";

	/*
	 * Se emcarga de crear el token el cual recibe el usuario y contraseña
	 * ademas este
	 */
	public String generateToken(UserDetails userDetails) {
		return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, KEY).compact();
	}

	/**
	 * Este valida si el usuario existe y lo compara con el que se obtiene del token que se esta utilizando
	 * ademas valida que el token que se esta utilizando este vijente 
	 * @param token
	 * @param userDetails
	 * @return
	 */
	public boolean validateToken(String token, UserDetails userDetails) {
		return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
	}

	/**
	 * Extrae el usuario del token que se esta utilizando por medio de los claims, y valida que se a real,
	 * el subject esta el usuario de la peticion
	 * @param token
	 * @return username que pertenece al token
	 */
	public String extractUsername(String token) {
		return getClaims(token).getSubject();
	}

	/**
	 * Dice si el token ya expiro TRUE si expiro FALSE si no.
	 * Valida si el token ya expiro validando la fecha y hora que expira este se
	 * optiene la fecha de expiracion y se compara con la fecha actual, si expira
	 * antes de la fecha actual.
	 * 
	 * @param token
	 * @return true o false si el token esta activo
	 */
	public boolean isTokenExpired(String token) {
		return getClaims(token).getExpiration().before(new Date());
	}

	/**
	 * Los claims son los objetos(partes) de el JWT, este metodo valida el token que se esta
	 * utilizando el cual lo valida con la KEY que se creo y divide el JWT en partes para
	 * validar que sea correcto. Se obtiene las partes del token para poder ser utilizado
	 * 
	 * @param token
	 * @return
	 */
	public Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
}
