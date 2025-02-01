package com.smoothy.agendadorTarefas.Controller;

import com.smoothy.agendadorTarefas.Business.UsuarioService;
import com.smoothy.agendadorTarefas.Business.dto.EnderecoDTO;
import com.smoothy.agendadorTarefas.Business.dto.TelefoneDTO;
import com.smoothy.agendadorTarefas.Business.dto.UsuarioDTO;
import com.smoothy.agendadorTarefas.Infrastructure.Entity.Usuario;
import com.smoothy.agendadorTarefas.Infrastructure.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping
    public ResponseEntity<UsuarioDTO> salvaUsuario(@RequestBody UsuarioDTO usuarioDTO){
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UsuarioDTO usuarioDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        usuarioDTO.getEmail(),
                        usuarioDTO.getSenha())
        );
        return "Bearer " +jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<Usuario> buscaUsuarioPorEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(usuarioService.buscaUsuarioPorEmail(email));
    }
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email){
        usuarioService.deletaUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioDTO> attUser(@RequestBody UsuarioDTO dto,
                                              @RequestHeader("Authorization") String token) {
            return ResponseEntity.ok(usuarioService.atualizaUsuarios(token, dto));
    }

    @PutMapping("/endereco")
    public ResponseEntity<EnderecoDTO> attEndereco(@RequestBody EnderecoDTO dto,
                                                   @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto));
    }

    @PutMapping("/telefone")
    public ResponseEntity<TelefoneDTO> attTelefone(@RequestBody TelefoneDTO dto,
                                                   @RequestParam("id") Long id){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto));
    }

}

