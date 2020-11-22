package sqlconnection;

import java.sql.*;

public class JDBCConnection {
    static final String userNameDb = "root";
    static final String passwordDb = "root";
    static final String connectionUrl = "jdbc:mysql://localhost:3306/task3db?useSSL=false";
    public Connection connection;

    public void initDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(connectionUrl, userNameDb, passwordDb);
            Statement statement = connection.createStatement();
        }
        catch (ClassNotFoundException | SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void addUser(String login, String password) {
        try {
            String sql = "INSERT INTO users(login, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    public boolean fiendUser(String userName, String password) {
        String login = "";
        String pass;
        
        try {
            String sql = "SELECT login, password FROM users where login = '" +
                    userName + "'" +
                    "and password = '"
                    + password + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                login = resultSet.getString(1);
                pass = resultSet.getString(2);
            }
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return login.equals("");
    }
}
