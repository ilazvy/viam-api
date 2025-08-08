package com.example.viam.controller;
import com.example.viam.model.Usuario;
import com.example.viam.repository.UsuarioRepository;
import com.example.viam.exception.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public static class LoginRequest {
        public String email;
        public String password;
    }

    public static class UsuarioLoginResponse {
        public Long id;
        public String nombre;
        public String email;
        public String rol;

        public UsuarioLoginResponse(Usuario usuario) {
            this.id = usuario.getId();
            this.nombre = usuario.getNombre();
            this.email = usuario.getEmail();
            this.rol = String.valueOf(usuario.getRol());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        Usuario usuario = usuarioRepository.findByEmail(req.email)
                .orElseThrow(() -> new AuthException("Email o contraseña incorrectos"));
        if (!passwordEncoder.matches(req.password, usuario.getPassword())) {
            throw new AuthException("Email o contraseña incorrectos");
        }
        return ResponseEntity.ok(new UsuarioLoginResponse(usuario));
    }
}