package com.github.app.model.aluno;

// DTO utilizado para listar alunos com campos resumidos.
public record DadosListagemAluno(
    Integer id,
    String nome,
    String email,
    String ra,
    String curso
) {
    public DadosListagemAluno(Aluno aluno) {
        this(
            aluno.getId(),
            aluno.getNome(), // No construtor do record, os getters getNome() e getEmail() estavam trocados, fazendo a API retornar o e-mail no campo nome e vice-versa.
            aluno.getEmail(),  
            aluno.getRa(),
            aluno.getCurso()
        );
    }
}
