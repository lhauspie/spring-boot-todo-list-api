package com.example.todo.demo.security;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		return http
				.authorizeExchange(exchanges ->
						exchanges
								.pathMatchers(HttpMethod.GET, "/hello").hasAuthority("resource-api_user")
								.pathMatchers(HttpMethod.GET, "/bye").permitAll()
								.anyExchange().authenticated()
				)
				.oauth2ResourceServer(oauth2ResourceServer ->
						oauth2ResourceServer
								.jwt()
								.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
				)
				.build();
	}

	@Bean
	Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
		GrantedAuthoritiesExtractor extractor = new GrantedAuthoritiesExtractor();
		return new ReactiveJwtAuthenticationConverterAdapter(extractor);
	}

	static class GrantedAuthoritiesExtractor extends JwtAuthenticationConverter {
		@Override
		protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
			// dealing with the claim `realm_access` of type of :
			//  "realm_access": {
			//    "roles": [
			//      "offline_access",
			//      "uma_authorization",
			//      "user"
			//    ]
			//  }
			Stream<String> realmAccessRoles;
			if (jwt.containsClaim("realm_access")) {
				realmAccessRoles = ((List<String>) jwt.getClaimAsMap("realm_access").get("roles")).stream();
			} else {
				realmAccessRoles = Stream.empty();
			}

			// dealing with the claim `resource_access` of type of :
			//  "resource_access": {
			//    "iotflux-service": {
			//      "roles": [
			//        "user"
			//      ]
			//    },
			//    "account": {
			//      "roles": [
			//        "manage-account",
			//        "manage-account-links",
			//        "view-profile"
			//      ]
			//    },
			//    "resource-api": {
			//      "roles": [
			//        "user"
			//      ]
			//    }
			//  }
			Stream<String> resourceAccessRoles;
			if (jwt.containsClaim("resource_access")) {
				resourceAccessRoles = jwt.getClaimAsMap("resource_access").entrySet().stream()
						.flatMap(entry -> {
							JSONObject value = (JSONObject) entry.getValue();
							JSONArray roles = (JSONArray) value.get("roles");
							return roles.stream().map(role -> entry.getKey() + "_" + role);
						});
			} else {
				resourceAccessRoles = Stream.empty();
			}

			Stream<String> roles = Stream.concat(realmAccessRoles, resourceAccessRoles);
			return roles.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		}
	}

}
