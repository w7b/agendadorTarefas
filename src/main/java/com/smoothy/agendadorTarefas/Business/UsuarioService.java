package com.smoothy.agendadorTarefas.Business;

import com.smoothy.agendadorTarefas.Business.converter.UsuarioConverter;
import com.smoothy.agendadorTarefas.Business.dto.UsuarioDTO;
import com.smoothy.agendadorTarefas.Infrastructure.Entity.Usuario;
import com.smoothy.agendadorTarefas.Infrastructure.Repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO
            (usuarioRepository.save(usuario));
    }
}
