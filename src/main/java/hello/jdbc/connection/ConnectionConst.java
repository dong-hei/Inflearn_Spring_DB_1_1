package hello.jdbc.connection;


import javax.sql.DataSource;

public abstract class ConnectionConst {

    public static final String URL = "jdbc:h2:tcp://localhost/~/test";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

    public static DataSource dataSource;
}
