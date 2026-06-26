package com.github.app.model.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

// O segundo parâmetro do JpaRepository define o tipo do ID da entidade. 
// O ID de Aluno é Integer, mas estava declarado como String, causando erros de compilação em todo método que usava getReferenceById() ou deleteById().
public interface AlunoRepository extends JpaRepository<Aluno, Integer>{
    
}  // era String
