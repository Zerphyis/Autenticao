# Sistema de Autenticação e Gerenciamento de Usuários

Este projeto é uma aplicação de autenticação e gerenciamento de usuários, desenvolvida com Spring Boot. Ele permite o cadastro de novos usuários, login, e a geração e validação de tokens JWT para garantir a segurança das requisições. A aplicação foi estruturada para fornecer endpoints de login e registro de usuários, onde um token JWT é gerado após a autenticação, permitindo acesso autorizado às funcionalidades da aplicação.

## Funcionalidades

- Cadastro de novos usuários.
- Autenticação de usuários com email e senha.
- Geração de tokens JWT para autenticação segura.
- Validação do token JWT em endpoints protegidos.
- Expiração de token configurada para 2 horas.

## Tecnologias Utilizadas

- **Spring Boot**: Framework utilizado para a construção da aplicação.
- **JWT (JSON Web Token)**: Usado para autenticação e segurança das requisições.
- **Spring Security**: Para autenticação e autorização de usuários.
- **HMAC256**: Algoritmo de assinatura utilizado para gerar e validar os tokens JWT.
- **JPA (Java Persistence API)**: Para interação com o banco de dados.
- **PostgreSQL**: Banco de dados utilizado para armazenamento de informações dos usuários.

## Estrutura do Projeto

- **ControllerUsuarios**: Controlador responsável por gerenciar os endpoints de login e cadastro.
- **ServiceUsuarios**: Serviço que contém a lógica de negócios para manipulação de usuários.
- **ServiceToken**: Serviço responsável pela geração e validação de tokens JWT.
- **EntityUsuarios**: Entidade que representa os dados do usuário no banco de dados.
- **SecurityConfig**: Classe de configuração de segurança, incluindo autenticação via JWT.
- **DadosUsuarios**: Classe DTO para transferência de dados do usuário.
- **LoginResposta**: Classe DTO para resposta contendo o token gerado após login.

## Endpoints

### Cadastro de Usuário
<br>

![Image](https://github.com/user-attachments/assets/deb2166d-8423-48b7-b9e1-1243a2a06cd2)
<br>

### Login de Usuário
<br>

![Image](https://github.com/user-attachments/assets/205c2224-407b-4810-8e08-9f0ab238f429)
<br>

### Atualizar Cadastro
<br>

![Image](https://github.com/user-attachments/assets/0ce153b7-11e4-4549-9858-0a88b8ae0a60)
<br>

### Mudar senha
<br>

![Image](https://github.com/user-attachments/assets/5cd22bbc-1605-4288-afe5-e6bf594ed59c)
<br>

### Desátivar Perfil
<br>

![Image](https://github.com/user-attachments/assets/825fdf84-1cbc-4851-8da5-67fef1cd0937)
<br>

### Gerar Token de Válidação
<br>

![Image](https://github.com/user-attachments/assets/80a36559-5486-4a8c-b920-5f65d7873548)
<br>

### Válidar Token
<br>

![Image](https://github.com/user-attachments/assets/b4b49fc7-8368-43b6-99ca-c039302ed373)
<br>

### Mudar senha pelo Token
<br>

![Image](https://github.com/user-attachments/assets/1bb8d670-1797-46b3-a2d3-f2929fc20b2d)
<br>

### Adicionando novas Informações
<br>

![Image](https://github.com/user-attachments/assets/5329177c-3f56-4ac2-8351-17ed044585f7)
<br>

### Atualizando foto
<br>

![Image](https://github.com/user-attachments/assets/d5dd24c3-2d15-41df-937d-64dd25b50e43)
<br>

### Historico do Perfil
<br>

![Image](https://github.com/user-attachments/assets/4aa0c64c-4b00-4d05-9059-2cecdf59c32e)
<br>

## Origem do Projeto

Este projeto foi criado com base em um template disponível no Notion, que foi adaptado para fornecer uma estrutura inicial para o desenvolvimento de uma aplicação de autenticação e gerenciamento de usuários com Spring Boot. O template forneceu uma base sólida para o gerenciamento de usuários, incluindo funcionalidades de login e cadastro, e a integração com JWT para segurança.
O template pode ser acessado [aqui](https://www.notion.so/Projeto-Sistema-de-Autentica-o-17de3853724481cbba7ae2844dc0b318?pvs=4).

