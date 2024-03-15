package zw.co.afrosoft.Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import zw.co.afrosoft.Requests.Security.LoginRequest;
import zw.co.afrosoft.Responses.security.JwtResponse;
import zw.co.afrosoft.utilities.JwtUtil;

@RestController
public class SecurityController {
@PostMapping("/login")
  public ResponseEntity login(@RequestBody LoginRequest request) {                   //TODO add a return statement
  if (request.getUsername().equals("admin") && request.getPassword().equals("1")) {
    String token = JwtUtil.generateToken(request.getUsername());
    return ResponseEntity.ok(new JwtResponse(token));
  }
  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
}
}
