package com.smoothy.agendadorTarefas.Infrastructure.Repository;

import com.smoothy.agendadorTarefas.Infrastructure.Entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
