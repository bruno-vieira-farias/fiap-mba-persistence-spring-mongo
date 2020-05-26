# Java Persistence

 Trabalho final para a disciplina de Persistência em Spring com MongoDb mininstrada pelo Prof. MSc. Rafael Matsuyama. <p>
 
 O projeto foi desenvolvido utilizando algumas técnicas do DDD, em que se busca uma clareza
 nos conceitos do domínio e sua independência entre as demais camadas
 do sistema.<p>
 Os dos pacotes do domínio foram agrupados por entidades, assim, as classes que tentem a 
 serem alteradas com a mesma frequência ficam próximas, facilitando as manutenções.<p>
 Foi utilizado o Spring boot que além de robusto possui uma utilização simples e ágil.<p>
 Foram criadas as entidades Cliente, Produto, Estoque com seus respectivos CRUDs 
 e a entidade Pedido com suas ações especificas.<p>
 Foram disponibilizados endpoints http que podem ser consultados pelo swagger.<p> 

### Pré-Requisitos
- Java 8
- Informar no arquivo `application.yaml` valores válidos para as chaves `uri` do mongo db para 
o funcionamento correto do banco de dados.

### Run
Execute na raiz do projeto a task `spring-boot:run` do maven.

- Windows
  `mvnw spring-boot:run`
- Linux / Mac
  `./mvnw spring-boot:run`
  
### Documentação Swagger
A documentação do swagger pode ser acessada na api `/api/swagger-ui.html`

### Demonstração  
Ao acessar a api `/api/demonstracao` a aplicação ira:
- Cadastrar um cliente,
- Cadastrar um produto,
- Cadastrar um estoque para o produto,
- Emitir um pedido e
- Retornar um objeto JSON demonstrando os objetos produzidos nos passos anteriores.


