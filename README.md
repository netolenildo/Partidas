#Gerenciador de Partidas

- No counter strike é comum que amigos se reúnam e formem times para disputar partidas entre si. Com esse intuito foi
criado um gerenciador de partidas, para que os jogadores possam definir o mapa, data e os jogadores que iram participar
do jogo. 

- No sistema foram utilizadas as tecnologias Spring Framework Boot e SpringMVC para a construção do backend, no frontend foram utilizados
Bootstrap 4 para criação dos layouts dos templates e thymeleaf para torna-los dinâmicos. Os dados foram salvos em um database
mysql e as consultas, inserções e romoções foram feitas através do hibernate e JPA.

- Para instalar o sistema deve-se seguir os passos abaixo:

    * Configurar um banco de dados mysql local. 
    * Realizar o clone do projeto e importalo no eclipse pelo caminho File -> Import... -> Maven -> Existing Maven Projects
    * Setar a url, username e password do banco de dados no arquivo DataConfiguration.java.
    * Executar o Run as Java Application no arquivo PartidasApplication.java
    * As tabelas serão criadas na primeira execução e o sistema estará pronto para ser utilizado.
 