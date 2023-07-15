<div align="center">
  <h1>Imagens da aplicação</h1>
  <table style="display: inline-table;">
    <tr>
      <td align="center"><img src="gifs/telas.gif"></td>
      <td align="center"><img src="gifs/tela-departamento.gif">Departamentos</td>
      <td align="center"><img src="gifs/tela-vendedor.gif">Vendedores(as)</td>
    </tr>
  </table>
</div>
<br>
<h4>
    Do ponto de vista do usuário, trate-se de um sistema de gerenciamento que permite visualizar e gerenciar informações detalhadas de funcionários e departamentos. 
    Com acesso a um banco de dados, ela oferece uma maneira eficiente de armazenar e manipular dados relacionados a funcionários e departamentos. 
    Essa aplicação por exemplo, seria ideal para atender às necessidades do setor de Recursos Humanos, a aplicação fornece recursos avançados para lidar com um grande 
    volume de informações, facilitando a manutenção e atualização dos dados.
    <br><br>
    Do ponto de vista técnico, o projeto consiste em um CRUD para o gerenciamento de vendedores e departamentos com acesso ao banco de dados relacional, através de tabelas e colunas. 
    No entanto, além disso, a aplicação também incorpora o conceito de tratamento de eventos. Isso significa que a tabela exibe informações atualizadas em tempo real durante a execução do programa. 
    Os formulários são tratados como eventos e observados pela tabela, tanto para a entidade de departamento quanto para a de vendedores.
    <br><br>
    Todos os eventos são desenvolvidos com regras de validação, como as tabelas de edição e deleção, assim como os formulários de cadastro e edição. 
    Eles seguem as regras de negócio da aplicação, garantindo que todos os campos sejam preenchidos corretamente. 
    Caso algum campo seja deixado em branco, o sistema exibirá mensagens de erro e não permitirá o cadastro ou a edição até que todas as informações necessárias sejam fornecidas. 
    Esse tratamento de exceções assegura a integridade dos dados e evita erros durante o processo de inserção ou atualização.
    <br><br>
    Mas o que significa observar esses eventos? Basicamente, a observação dos eventos garante a atualização de todos os campos, como as tabelas e os formulários de cadastro e edição. 
    Para ilustrar, se um novo departamento é cadastrado, quando você for cadastrar um novo vendedor, esse novo departamento estará disponível para seleção. 
    Esse mecanismo segue o padrão de design conhecido como Observer, em que as alterações em um objeto são notificadas e refletidas em outros objetos que estão observando.
    <br><br>
    Essa abordagem técnica traz uma dinâmica eficiente para a aplicação, garantindo que as informações sejam sincronizadas e atualizadas em tempo real.
</h4>
<br>
<div>
<h2 align="center">Tecnologias Usadas</h2>
  <br>
    <h4 align="center"> 
      <p>
       <img align="center" alt="kruger" height="35" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg"/>
       Java
       <img align="center" alt="kruger" height="35" width="40" src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg"/>
       CSS3
       <img align="center" alt="kruger" height="35" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/mysql/mysql-plain.svg">
       MySQL Database
       </p>
   </h4>
</div>
<br>
<div>
    <h2 align="center">UML</h2>
        <div align="center">
          <img src="gifs/uml-sales-mgmt.png">
        </div>
    <h3> Destrinchando a estrutura: </h3>
</div>

🔹 `Seller`: Entidade que representa um vendedor ou vendedora e armazena informações como nome, e-mail, data de nascimento, salário e o departamento ao qual ele pertence.

🔹 `Department`: Entidade que representa um departamento ou setor da empresa e coleta informações básicas, como um número de identificação único e o nome do departamento.

- `1`: Expressão utilizada para demonstrar uma 'dependência' ou associação entre as entidades, que neste caso trata-se de que um `Seller` deve ter **1** `- department`. 

> Com essa dependência entre as classes, é garantido que não seja possível excluir um departamento que tenha um vendedor associado. No entanto, é possível excluir um vendedor que esteja associado a um departamento, uma vez que a entidade `Department` é independente. Da mesma forma, não é possível cadastrar um vendedor sem um `- department` associado.

<br>
<div>
  <h2 align="center">Instruções de Execução</h2>

  <h4>Requisitos:</h4>
  <p>
    🔹
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="java-logo" height="35" width="40"> Java 8 ou superior instalado no sistema.
  </p>

  <p>
    🔹
    <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" alt="javafx-logo" height="35" width="40">FX instalado e configurado corretamente.
  </p>
  <br>
  <p>
    <strong>Passo 1:</strong> Definir o caminho do módulo do JavaFX SDK.
  </p>

  <pre>
    <code>--module-path /path/to/javafx-sdk-VERSION/lib</code>
  </pre>

  <p>
    <strong>Passo 2:</strong> Adicionar os módulos do JavaFX.
  </p>

  <pre>
    <code>--add-modules javafx.controls,javafx.fxml</code>
  </pre>

  <p>
    <strong>Passo 3:</strong> Executar o arquivo JAR.
  </p>

  <pre>
    <code>-jar sales_mgmt.jar</code>
  </pre>

</div>





