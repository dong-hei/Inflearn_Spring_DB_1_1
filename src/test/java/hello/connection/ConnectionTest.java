package hello.connection;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        Connection c1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection c2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        log.info("connection={}, class={}",c1 ,c1.getClass());
        log.info("connection={}, class={}",c2 ,c2.getClass());
    }

    @Test
    void dateSourceDriverManager() throws SQLException {
        //DriverManagerDataSource - 항상 새로운 Connection을 획득
        // 최상위 계층에 DataSource가 있기때문에 DriverManagerDataSource를 받을수있다.
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException {
        //커넥션 풀링:
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        useDataSource(dataSource);
    }

    private void useDataSource(DataSource dataSource) throws SQLException{
        Connection c1 = dataSource.getConnection();
        Connection c2 = dataSource.getConnection();
        log.info("connection={}, class={}",c1 ,c1.getClass());
        log.info("connection={}, class={}",c2 ,c2.getClass());

    }
}
