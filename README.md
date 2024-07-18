# Resolve Serviços

## 1. Visão Geral

**Nome do App:** Resolve Serviços  
**Objetivo:** Facilitar o agendamento de serviços de manutenção no lar, permitindo que os usuários gerenciem seus compromissos de forma eficiente.

## 2. Funcionalidades Principais

1. **Login:**
   - Usuário faz login com suas credenciais.
   - Recuperação de senha.

2. **Cadastro de Usuários:**
   - Novo usuário pode se cadastrar fornecendo nome, e-mail e senha.
   - Validação de e-mail e senha.

3. **Cadastro de Clientes:**
   - Cadastro de novos clientes com nome, endereço e detalhes de contato.
   - Listagem e busca de clientes existentes.

4. **Agendamento de Serviços:**
   - Seleção de tipo de serviço (por exemplo, elétrica, encanamento, etc.).
   - Escolha de data e horário.
   - Associação do agendamento a um cliente existente ou novo.
   - Possibilidade de modificar detalhes do agendamento.

5. **Gestão de Serviços:**
   - Visualização de serviços agendados.
   - Edição e cancelamento de agendamentos.
   - Notificações de lembrete para os serviços agendados.

## 3. Plataforma

- **Android:** O app será desenvolvido inicialmente para dispositivos Android.

## 4. Tecnologias Utilizadas

- **Frontend:** React Native
- **Backend:** Spring Framework (Spring Boot)
- **Banco de Dados:** MySQL

## 5. Cliente-Alvo

- **Empresas:** Pequenas e médias empresas que oferecem serviços de manutenção.
- **Indivíduos:** Profissionais autônomos e clientes que necessitam de serviços de manutenção em suas residências.

## 6. Fluxo do Usuário

1. **Login/Cadastro:**
   - O usuário abre o app e vê a tela de login.
   - Caso já tenha uma conta, ele insere suas credenciais e faz login.
   - Caso não tenha uma conta, ele pode se cadastrar fornecendo nome, e-mail e senha.

2. **Cadastro de Cliente:**
   - Após o login, o usuário pode cadastrar um novo cliente fornecendo nome, endereço e tipo de serviço.
   - O usuário pode também buscar e selecionar clientes já cadastrados.

3. **Agendamento de Serviço:**
   - Com o cliente selecionado, o usuário escolhe o tipo de serviço, data e horário.
   - Detalhes do serviço, como endereço e horário, podem ser visualizados e modificados.

4. **Gestão de Agendamentos:**
   - O usuário pode visualizar todos os agendamentos futuros e passados.
   - É possível editar detalhes dos agendamentos ou cancelá-los.
   - O app envia notificações de lembrete antes dos serviços agendados.

## 7. Estrutura do Projeto

1. **Frontend (Android App):**
   - Telas: Login, Cadastro de Usuário, Lista de Clientes, Cadastro de Cliente, Agendamento de Serviço, Detalhes do Serviço.
   - Lógica de Navegação e Gerenciamento de Estados.

2. **Backend (API):**
   - Endpoints para gerenciamento de usuários (login, cadastro).
   - Endpoints para CRUD de clientes.
   - Endpoints para CRUD de agendamentos.
   - Segurança e Autenticação (JWT).

3. **Banco de Dados:**
   - Tabelas para Usuários, Clientes, Serviços, Agendamentos.
   - Relacionamentos e Constraints.

## 8. Roadmap Inicial

1. **Configuração do Ambiente:**
   - Configuração do ambiente de desenvolvimento Android.
   - Configuração do servidor Spring Boot e banco de dados MySQL.

2. **Desenvolvimento do Backend:**
   - Criação das APIs RESTful usando Spring Boot.
   - Configuração do banco de dados MySQL.

3. **Desenvolvimento do Frontend:**
   - Design e implementação das telas principais.
   - Integração com as APIs do backend.-
