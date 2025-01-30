package com.smoothy.agendadorTarefas.Business;

import com.smoothy.agendadorTarefas.Business.converter.UsuarioConverter;
import com.smoothy.agendadorTarefas.Business.dto.UsuarioDTO;
import com.smoothy.agendadorTarefas.Infrastructure.Entity.Usuario;
import com.smoothy.agendadorTarefas.Infrastructure.Exceptions.ConflictException;
import com.smoothy.agendadorTarefas.Infrastructure.Exceptions.ResourceNotFoundException;
import com.smoothy.agendadorTarefas.Infrastructure.Repository.UsuarioRepository;
import com.smoothy.agendadorTarefas.Infrastructure.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }


    public void emailExiste(String email) {
        try {
            boolean existe = verifcaEmailExistente(email);
            if (existe) {
                throw new ConflictException("E-Mail ja cadastrado " + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("E-Mail cadastrado com sucesso " +email);
        }
    }

    public boolean verifcaEmailExistente(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscaUsuarioPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email nao encontrado "+email));
    }

    public void deletaUsuarioPorEmail(String email){
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizaUsuarios(String token, UsuarioDTO dto){

        String email = jwtUtil.extractUsername(token.substring(7));

        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email nao encontrado!"));

        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}

