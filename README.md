# Atividade — Caça aos Erros: Sistema Escolar com Spring Boot

Repositório original com erros propositais: [williamfirmino92/sistema_escolar_java](https://github.com/williamfirmino92/sistema_escolar_java)

Este repositório contém o projeto corrigido com todos os erros identificados e documentados.

---

## Autora

Maria Eduarda Ramos — Análise e Desenvolvimento de Sistemas — Senac RJ

---

## Como executar

```bash
git clone https://github.com/mariaeramos18/avalicao-spring-boot.git
cd avalicao-spring-boot
mvn spring-boot:run
```

Acesse: `http://localhost:8080`  
Console H2: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:testdb`, usuário: `sa`, senha: em branco)

---

## Erros encontrados e corrigidos

### Erro 1 — Versão inexistente do Spring Boot
**Arquivo:** `pom.xml`  
**Problema:** A versão `4.0.5` do Spring Boot não existe no Maven Central, impedindo o download das dependências e a inicialização do projeto.  
**Correção:**
```xml
// Antes
<version>4.0.5</version>

// Depois
<version>3.4.5</version>
```

---

### Erro 2 — Artifact da dependência web incorreto
**Arquivo:** `pom.xml`  
**Problema:** O artifact `spring-boot-starter-webmvc` não existe. O correto é `spring-boot-starter-web`.  
**Correção:**
```xml
// Antes
<artifactId>spring-boot-starter-webmvc</artifactId>

// Depois
<artifactId>spring-boot-starter-web</artifactId>
```

---

### Erro 3 — Imports incorretos na classe principal
**Arquivo:** `AppApplication.java`  
**Problema:** A anotação `@SpringBootApplication` estava sendo usada sem o import correto. Os imports presentes (`ComponentScan` e `Configuration`) não são necessários e não substituem o import da anotação principal.  
**Correção:**
```java
// Antes
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Depois
import org.springframework.boot.autoconfigure.SpringBootApplication;
```

---

### Erro 4 — Tabela errada mapeada na entidade Aluno
**Arquivo:** `Aluno.java`  
**Problema:** A anotação `@Table` apontava para a tabela `professores`, fazendo o Hibernate tentar usar a mesma tabela para duas entidades diferentes.  
**Correção:**
```java
// Antes
@Table(name = "professores")

// Depois
@Table(name = "alunos")
```

---

### Erro 5 — Nome e e-mail invertidos no DTO de listagem
**Arquivo:** `DadosListagemAluno.java`  
**Problema:** No construtor do record, os getters `getNome()` e `getEmail()` estavam trocados, fazendo a API retornar o e-mail no campo nome e vice-versa.  
**Correção:**
```java
// Antes
this(aluno.getId(), aluno.getEmail(), aluno.getNome(), aluno.getRa(), aluno.getCurso());

// Depois
this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getRa(), aluno.getCurso());
```

---

### Erro 6 — @PostMapping duplicado no AlunoController
**Arquivo:** `AlunoController.java`  
**Problema:** O método `atualizar()` estava anotado com `@PostMapping`, igual ao método `cadastrar()`. Dois métodos com o mesmo verbo HTTP no mesmo path causam conflito de mapeamento e impedem o Spring de inicializar.  
**Correção:**
```java
// Antes
@PostMapping
public void atualizar(...)

// Depois
@PutMapping
public void atualizar(...)
```

---

### Erro 7 — Campo e-mail sobrescrevendo nome na atualização de Professor
**Arquivo:** `Professor.java`  
**Problema:** No método `atualizarInformacoes()`, ao tentar atualizar o e-mail, o código atribuía o valor ao campo `nome` em vez de `email`, corrompendo o nome do professor.  
**Correção:**
```java
// Antes
this.nome = dados.email();

// Depois
this.email = dados.email();
```

---

### Erro 8 — @PathVariable com nome incompatível no MatriculaController
**Arquivo:** `MatriculaController.java`  
**Problema:** O parâmetro da URL era `{id}`, mas a variável Java estava nomeada como `ids`. O Spring não consegue fazer o bind e lança erro 400 nas requisições DELETE.  
**Correção:**
```java
// Antes
public void excluir(@PathVariable Integer ids)

// Depois
public void excluir(@PathVariable Integer id)
```

---

### Erro bônus — Tipo incorreto no AlunoRepository
**Arquivo:** `AlunoRepository.java`  
**Problema:** O segundo parâmetro do `JpaRepository` define o tipo do ID da entidade. O ID de `Aluno` é `Integer`, mas estava declarado como `String`, causando erros de compilação em todo método que usava `getReferenceById()` ou `deleteById()`.  
**Correção:**
```java
// Antes
public interface AlunoRepository extends JpaRepository<Aluno, String>

// Depois
public interface AlunoRepository extends JpaRepository<Aluno, Integer>
```

---

## Tecnologias utilizadas

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- Banco de dados H2 (em memória)
- Lombok
- Spring Validation
- Maven

