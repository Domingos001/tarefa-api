package br.com.Pedro.tarefa.api.repository;

import br.com.Pedro.tarefa.api.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
   
}