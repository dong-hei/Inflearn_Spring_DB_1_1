package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
public class UncheckedAppTest {

    @Test
    void checked(){
        Controller controller = new Controller();
        assertThatThrownBy(() -> controller.request()).isInstanceOf(RuntimeSQLException.class);
    }

    @Test
    void printEx(){
        Controller controller = new Controller();
        try {
            controller.request();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("ex", e);
        }
    }

    static class Controller{
        Service service = new Service();

        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }
    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() throws SQLException, ConnectException {
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient {
        public void call() throws ConnectException {
            throw new RunTimeConnectionException("연결실패");
        }
    }

    static class Repository {
        public void call() throws SQLException {
            try {
                runSQL();
            } catch (SQLException e) {
                throw new RuntimeSQLException(e);
            } //예외 추적을 위해서 꼭 e를 넣어라
        }
        public void runSQL() throws SQLException {
            throw new SQLException("ex");
        }
    }

    static class RunTimeConnectionException extends RuntimeException {
        public RunTimeConnectionException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException(Throwable cause) {
            super(cause);
        }
    } //예외를 전달할때는 꼭 기존 예외를 포함시키자

}
