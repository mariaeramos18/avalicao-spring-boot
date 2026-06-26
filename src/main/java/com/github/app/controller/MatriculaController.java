package com.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.app.model.aluno.Aluno;
import com.github.app.model.aluno.AlunoRepository;
import com.github.app.model.matricula.DadosCadastroMatricula;
import com.github.app.model.matricula.Matricula;
import com.github.app.model.matricula.MatriculaRepository;
import com.github.app.model.professor.Professor;
import com.github.app.model.professor.ProfessorRepository;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody DadosCadastroMatricula dados) {
        Aluno aluno = alunoRepository.getReferenceById(dados.alunoId());  // remove o .toString()
        Professor professor = professorRepository.getReferenceById(dados.professorId());
        Matricula matricula = new Matricula(dados);
        matricula.setAluno(aluno);
        matricula.setProfessor(professor);
        repository.save(matricula);
    }

    @GetMapping
    public List<Matricula> listar() {
        return repository.findAll();
    }

    //O parâmetro da URL era {id}, mas a variável Java estava nomeada como ids. O Spring não consegue fazer o bind e lança erro 400 nas requisições DELETE.
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Integer id) {
        repository.deleteById(id);
    }
}
