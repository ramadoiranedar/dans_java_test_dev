package com.test.javadev.middlewares;

import com.sun.istack.NotNull;
import com.test.javadev.entities.UserEntity;
import com.test.javadev.services.UserService;

import com.test.javadev.shared.SharedJwt;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class AuthMiddleware extends OncePerRequestFilter  {

    private final SharedJwt sharedJwt;
    private final UserService userService;


    public AuthMiddleware(SharedJwt sharedJwt, UserService userService) {
        this.sharedJwt = sharedJwt;
        this.userService = userService;
    }

    private @NotNull User getUserFromEntity(String username, String password) {
        return new User(username, password, new ArrayList<>());
    }

    @Override
    protected boolean shouldNotFilter(@NotNull HttpServletRequest request) {

        AntPathMatcher pathMatcher = new AntPathMatcher();

        return Arrays.stream(new String[]{"/", "/api/auth/login", "/api/auth/register"})
                .anyMatch(p -> pathMatcher.match(p, request.getServletPath()));
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) {
        String tokenHeader = request.getHeader("Authorization");
        String username;
        String token;

        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            token = tokenHeader.replace("Bearer ", "");

            try {
                username = sharedJwt.getUsername(token);
                if (sharedJwt.validateToken(token, username)) {
                    UserEntity userEntity = userService.findByUsername(username);
                    UserDetails userDetails = getUserFromEntity(username, userEntity.getPassword());
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    try {
                        filterChain.doFilter(request, response);
                    } catch (MissingServletRequestPartException exception) {
                        response.setStatus(HttpStatus.BAD_REQUEST.value());
                    } catch (HttpMediaTypeNotSupportedException exception) {
                        response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
                    } catch (Exception exception) {
                        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                    }
                } else response.setStatus(HttpStatus.UNAUTHORIZED.value());
            } catch (Exception ex) {
                ex.printStackTrace();

                response.setStatus(HttpStatus.FORBIDDEN.value());
            }
        } else response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}
