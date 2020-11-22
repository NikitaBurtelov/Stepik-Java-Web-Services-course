package servlets;

import accounts.AccountService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import sqlconnection.JDBCConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {
    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"})
    private final AccountService accountService;
    public JDBCConnection connection;

    public SignUpServlet(AccountService accountService, JDBCConnection connection) {
        this.accountService = accountService;
        this.connection = connection;
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Configuration cfg = new Configuration();
        Template template = cfg.getTemplate("public_html" + File.separator + "index.html");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        //String email = request.getParameter("email");

        if (login.isEmpty() || password.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        connection.addUser(login, password);

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
