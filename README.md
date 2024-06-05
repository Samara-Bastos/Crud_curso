
- CRUD CURSO
🧑🏻 - Executar
Este projeto utiliza o Docker para gerenciar um banco de dados PostgreSQL. Para iniciar, siga estas instruções:

docker compose up -d # Inicializa o container do PostgreSQL

Após este passo, você pode rodar o projeto normalmente.

Para acessar a documentação das requisições, utilize o Swagger através do link: http://localhost:8080/swagger-ui/index.html
 
## 🗃️ **Dependências:**
- Spring Data JPA
- Spring Web
- PostgreSQL Driver
- H2 Driver
- SpringBoot DevTools
- Docker Compose Support
- Bin Validation
- Swagger
- Mapstruct
- Lombok
   
## 📄 Entidades
    Aluno
    Curso
    Professor

## 📄 Controladores

    Aluno
    Curso
    Professor
 
## Minimum Viable Product
- ✅ Container Docker
- ✅ Conectar Banco
- ✅ Entidade Professor
- ✅ Entidade Aluno
- ✅ Entidade Curso
- ✅ Relacionar Curso e Professor (n:1)
- ✅ Relacionar Curso e Aluno (n:n)
- ✅ Listar Professor, Aluno e Curso
- ✅ Criar Aluno
- ✅ Atualizar Aluno
- ✅ Excluir Aluno
- ✅ Matricular Aluno
- ✅ Desmatricular Aluno
- ✅ Criar Professor
- ✅ Atualizar Professor
- ✅ Excluir Professor
- ✅ Criar Curso
- ✅ Atualizar Curso
- ✅ Excluir Curso
- ✅ Ver Alunos do Curso
- ✅ Ver Cursos
- ✅ Validações
 
-> EXTRAS:
- ✅ Tratamento de Exceções
- ✅ Testes Unitários
- ✅ Testes de Integração
- ✅ Swagger
- ✅ Paginação de Curso
 