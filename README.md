# 📚 Sistema de Gestão de Tarefas

### 👤 Integrantes
- Ronald de Oliveira Farias - RM 552364
- Gustavo Carvalho Noia - RM 552466
- Vitor Teixeira - RM 552228
- Lucas Serbato - RM 551821

## 📝 Sobre a aplicação

Sistema de Gestão de Tarefas é uma aplicação desenvolvida em Java 21 com Spring Boot 3,
neste sistema após o usuário efetuar o cadastro ou o login, ele poderá realizar a criação e manipulação de tarefas do seu dia a dia, 
para ajudar a manter a rotina organizada e bem planejada.

## 💻 Especificações
O sistema conta com autenticação de usuário, podendo garantir o acesso se o usuário estiver logado ou bloquear o acesso
caso o usuário não estiver logado ou se o Token JWT do mesmo estiver expirado ou inválido, o tempo de acesso após o login
é de 2 horas, tendo passado essas duas horas será necessário realizar o login novamente.

## 📋 Funcionalidades
✔️ Cadastro e Login de Usuário <br>
✔️ CRUD de Tarefas <br>
✔️ Autenticação via Token JWT

## 🔎 Tecnologias

- Java 21
- Spring Boot 3
- Maven
- Spring Data JPA - Hibernate
- Banco de Dados Oracle
- Spring Security
- JWT
