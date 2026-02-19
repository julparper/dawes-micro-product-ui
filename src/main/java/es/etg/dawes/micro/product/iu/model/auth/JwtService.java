package es.etg.dawes.micro.product.iu.model.auth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {


    @Value("${spring.security.oauth2.resourceserver.jwt.secret-key}")
    private String secretKey;

    public String generarToken(String username) {
        // La clave DEBE ser la misma que la del Gateway
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // Expira en 1 hora
                .signWith(key) // Firma simétrica HS256 automática
                .compact();
    }
}