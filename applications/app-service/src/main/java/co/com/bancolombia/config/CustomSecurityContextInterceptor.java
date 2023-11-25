package co.com.bancolombia.config;

import co.com.bancolombia.authorization.model.TokenData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

public class CustomSecurityContextInterceptor implements HandlerInterceptor {


    private final ObjectMapper mapper = new ObjectMapper();

    public static final String AUTHORIZATION = "Authorization";

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = servletRequest.getHeader(AUTHORIZATION);
        TokenData data;
        try {
            var decodedJWT = SignedJWT.parse(accessToken.split(" ")[1]);
            data =  mapper.readValue(decodedJWT.getPayload().toString(), TokenData.class);
        } catch (ParseException e) {
            throw new Exception("Invalid token!");
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(data.getCognitoUsername(),
                "", List.of(new SimpleGrantedAuthority(data.getCustomRole())));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        SecurityContextHolder.clearContext();
    }

}
