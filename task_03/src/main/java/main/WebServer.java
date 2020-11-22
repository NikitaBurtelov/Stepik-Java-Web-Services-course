package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;
import sqlconnection.JDBCConnection;

public class WebServer {
    static final int port = 8089;
    public static void main(String[] args) {
        try {
            AccountService accountService = new AccountService();
            JDBCConnection connection = new JDBCConnection();
            connection.initDataBase();

            accountService.addNewUser(new UserProfile("root", "root"));

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.addServlet(new ServletHolder(new SignUpServlet(accountService, connection)), "/signup");
            context.addServlet(new ServletHolder(new SignInServlet(accountService, connection)), "/signin");

            ResourceHandler resource_handler = new ResourceHandler();
            resource_handler.setResourceBase("public_html");

            HandlerList handlers = new HandlerList();
            handlers.setHandlers(new Handler[]{resource_handler, context});

            Server server = new Server(port);
            server.setHandler(handlers);

            server.start();
            java.util.logging.Logger.getGlobal().info("Server started");
            server.join();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
