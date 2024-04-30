package com.ecw.backendapi.security;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.ecw.backendapi.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtUtils {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

 // @Value("${epsorder.app.jwtSecret}")
  private String jwtSecret="eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwNzM4NTQ1NiwiaWF0IjoxNzA3Mzg1NDU2fQ.FAEGavoDhAq0nGXdcCP5jcPtVjDu9hcc5wdQjpIvf9g";

  //@Value("${epsorder.app.jwtExpirationMs}")
  private int jwtExpirationMs=37600000;
  /*============= this method is responsible for the generate the Json Web Token [JWT] */
  public String generateJwtToken(Authentication authentication) {
	// log.info("Authentication Generate Tokens  is "+authentication);

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
   // log.info("User Principal  is ::  "+userPrincipal);

  //  log.debug("userPrincipal  is "+userPrincipal.getEmail()+ " "+userPrincipal.getUsername());

    return Jwts.builder()
       // .setSubject((userPrincipal.getUsername()))
        .setSubject((userPrincipal.getEmail()))
        .setIssuedAt(new Date())
        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  /*========GET USER FROM JWT TOKEN=========*/
  public String getUserNameFromJwtToken(String token) {

	  String subject = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	 // log.debug("Jwt Token Subject :: "+subject);
	  return subject;
   // return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
  }
  /*==============VALIDATE TOKENS ==============*/
  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
      return true;
    } catch (SignatureException e) {
      //log.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
     // log.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
    //  log.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
  //    log.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
   //   log.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }

}
