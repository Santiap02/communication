package co.com.bancolombia.authorization;

import co.com.bancolombia.authorization.annotations.SecuredTest;
import co.com.bancolombia.authorization.model.TokenData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Aspect
@Component
@RequiredArgsConstructor
public class AuthorizationHelper {
    final ObjectMapper mapper = new ObjectMapper();

    private String parseJWT(String accessToken) throws Exception {
        try {
            var decodedJWT = SignedJWT.parse(accessToken.split(" ")[1]);
            return mapper.readValue(decodedJWT.getPayload().toString(), TokenData.class).getCustomRole();
        } catch (ParseException e) {
            throw new Exception("Invalid token!");
        }
    }
    @Before(value = "@annotation(co.com.bancolombia.authorization.annotations.SecuredTest) && args(token,..)")
    public void validateRole(JoinPoint joinPoint, String token) throws Exception {
        var signature = (MethodSignature) joinPoint.getSignature();
        var role = signature.getMethod().getAnnotation(SecuredTest.class).role();
        if (!this.parseJWT(token).equals(role)){
            throw new Exception("El usuario no tiene las credenciales correctas");
        }
    }

}
