package com.smoothy.agendadorTarefas.Infrastructure.Repository;

import com.smoothy.agendadorTarefas.Infrastructure.Entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
