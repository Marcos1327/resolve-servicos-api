package com.resolveservicos.emailMessage;

import com.resolveservicos.entities.model.User;

public class EmailMessage {

    public static String createTitle(User user) {
        return user.getName() + "seu cadastro foi realizado com sucesso!";
    }

    public static String messageToNewUser(User user, String password) {
        String base64Image = "https://uploaddeimagens.com.br/images/004/819/894/original/Resolve_20240802_192808_0000-1-fotor-bg-remover-2024080220148.png?1722639811";
        return "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<style>"
                + "body {"
                + "  background-color: #f0f0f0; /* Fundo branco gelo */"
                + "  font-family: Arial, sans-serif;"
                + "  margin: 0;"
                + "  padding: 0;"
                + "  text-align: center;"
                + "}"
                + "header {"
                + "  background-color: #d3d3d3; /* Fundo cinza */"
                + "  padding: 10px;"
                + "}"
                + "header img {"
                + "  max-width: 150px;"
                + "  height: auto;"
                + "}"
                + ".container {"
                + "  background-color: #ffffff; /* Fundo branco para o conteúdo */"
                + "  margin: 20px auto;"
                + "  padding: 20px;"
                + "  border-radius: 8px;"
                + "  max-width: 600px;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<header>"
                + "  <img src='" + base64Image + "' alt='Logo Empresa' />"
                + "</header>"
                + "<div class='container'>"
                + "  <h1>Olá " + user.getName() + ",</h1>"
                + "  <p>Seu cadastro foi realizado com sucesso!</p>"
                + "  <p>Aqui estão suas credenciais de acesso:</p>"
                + "  <p>Usuário: " + user.getEmail() + "</p>"
                + "  <p>Senha: " + password + "</p>"
                + "  <p>Acesse o sistema e aproveite!</p>"
                + "  <p>Atenciosamente,</p>"
                + "  <p>Equipe Resolve Serviços</p>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
