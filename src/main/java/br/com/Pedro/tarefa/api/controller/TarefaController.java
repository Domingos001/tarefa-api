package br.com.Pedro.tarefa.api.controller;

import br.com.Pedro.tarefa.api.model.Tarefa;
import br.com.Pedro.tarefa.api.repository.TarefaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaRepository repository;

    public TarefaController(TarefaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(tarefa -> ResponseEntity.ok(tarefa))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tarefa> adicionar(@RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = repository.save(tarefa);
        URI location = URI.create("/api/tarefas/" + tarefaSalva.getId());
        return ResponseEntity.created(location).body(tarefaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        return repository.findById(id)
                .map(tarefaExistente -> {
                    tarefaExistente.setNome(tarefaAtualizada.getNome());
                    tarefaExistente.setDataEntrega(tarefaAtualizada.getDataEntrega());
                    tarefaExistente.setResponsavel(tarefaAtualizada.getResponsavel());
                    
                    Tarefa salvo = repository.save(tarefaExistente);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Primeiro, verificamos se a tarefa realmente existe
        if (repository.existsById(id)) {
            // Se existir, nós a deletamos
            repository.deleteById(id);
            // E retornamos o status 204 No Content (sucesso, sem conteúdo)
            return ResponseEntity.noContent().build();
        } else {
            // Se não existir, retornamos o status 404 Not Found
            return ResponseEntity.notFound().build();
        }
    }
}