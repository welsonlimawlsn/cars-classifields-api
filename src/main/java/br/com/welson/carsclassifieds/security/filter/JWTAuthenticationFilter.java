package br.com.welson.carsclassifieds.security.filter;

import br.com.welson.carsclassifieds.persistence.model.user.ApplicationUser;
import br.com.welson.carsclassifieds.security.util.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import static br.com.welson.carsclassifieds.security.filter.SecurityConstants.*;
import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.time.temporal.ChronoUnit.DAYS;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser user = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ZonedDateTime expirationTimeUTC = ZonedDateTime.now(ZoneOffset.UTC).plus(EXPIRATION_TIME_DAYS, DAYS);
        String token = generateToken(((ApplicationUser) authResult.getPrincipal()).getUsername(), Date.from(expirationTimeUTC.toInstant()));
        addTokenToHeader(response, token);
        response.getWriter().write(createJsonForToken(token, expirationTimeUTC.toString()));
    }

    private String generateToken(String username, Date expirationTime) {
        return TOKEN_PREFIX + Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationTime)
                .signWith(HS512, SECRET)
                .compact();
    }

    private void addTokenToHeader(HttpServletResponse response, String token) {
        response.addHeader("Content-Type", APPLICATION_JSON_UTF8_VALUE);
        response.addHeader(HEADER_STRING, token);
    }

    private String createJsonForToken(String token, String expirationTime) {
        return new JsonGenerator().put("token", token).put("exp", expirationTime).toString();
    }
}
