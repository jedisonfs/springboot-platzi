package com.education.platzicurso.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.education.platzicurso.domain.service.SecurityUserDetailsService;
import com.education.platzicurso.web.security.JWTUtil;

/**
 * La clase OncePerRequestFilter se ejecuta cada vez que se haga una peticion
 * para hacer la validacion del token
 * 
 * @author Edison
 *
 */
@Component
public class JwtFilterRequest extends OncePerRequestFilter {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private SecurityUserDetailsService securityUserDetailsService;

	/*
	 * Valida que dentro del header de la peticion viene un token y si este es
	 * valido
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authenticationHeader = request.getHeader("Authorization");

		if (authenticationHeader != null && authenticationHeader.startsWith("Bearer")) {
			String jwt = authenticationHeader.substring(7);
			String username = jwtUtil.extractUsername(jwt);

			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails userDetails = securityUserDetailsService.loadUserByUsername(username);

				if (jwtUtil.validateToken(jwt, userDetails)) {
					/*
					 * 
					 * */
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					
					/*Esto extrae todos los detalles de la conexion que esta recibiendo
					  la conexion, sistema operativo que se conecta, navegador que usa, hora de conexion etc..
					  con el token se valida esto*/
					authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					/**
					 * Se le asigna la autenticacion para que no tenga que recorer todos estos filtros
					 * de autenticacion
					 */
					SecurityContextHolder.getContext().setAuthentication(authToken);
				}
			}
		}

		/*
		 * Evaluea el filtro donde recibe la solicitud y la respuesta
		 */
		filterChain.doFilter(request, response);
	}

}
