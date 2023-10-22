package sptech.safemoney.controle;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import sptech.safemoney.jwt.GerenciadorTokenJwt;
import sptech.safemoney.dominio.UsuarioEntity;
import sptech.safemoney.dto.UsuarioLoginDTO;
import sptech.safemoney.dto.UsuarioTokenDTO;
import sptech.safemoney.repositorio.UsuarioRepository;

@RestController
@RequestMapping("/autenticacao")
public class AutenticacaoController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO> logarUsuario(@RequestBody @Valid UsuarioLoginDTO usuario) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuario.getEmail(), usuario.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        UsuarioEntity usuarioAutenticado =
                usuarioRepository.findByEmail(usuario.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        UsuarioTokenDTO usuarioTokenDto = new UsuarioTokenDTO();

        usuarioTokenDto.setUserId(usuarioAutenticado.getId());
        usuarioTokenDto.setEmail(usuarioAutenticado.getEmail());
        usuarioTokenDto.setNome(usuarioAutenticado.getNome());
        usuarioTokenDto.setToken(token);

        return ResponseEntity.ok(usuarioTokenDto);
    }

}
