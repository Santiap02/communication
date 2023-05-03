package co.com.bancolombia.usecase.authorization;

import co.com.bancolombia.usecase.helper.TokenData;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;

import java.text.ParseException;


@RequiredArgsConstructor
public class AuthorizationUseCase {

    final ObjectMapper mapper = new ObjectMapper();

    private String parseJWT(String accessToken) throws Exception {
        try {
            var decodedJWT = SignedJWT.parse(accessToken.split(" ")[1]);
            return mapper.readValue(decodedJWT.getPayload().toString(), TokenData.class).getCustomRole();
        } catch (ParseException e) {
            throw new Exception("Invalid token!");
        }
    }

    public void validateRole(String token, String role) throws Exception {
        if (!this.parseJWT(token).equals(role)){
            throw new Exception("El usuario no tiene las credenciales correctas");
        }
    }

}
