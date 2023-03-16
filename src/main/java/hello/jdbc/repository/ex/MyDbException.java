package hello.jdbc.repository.ex;

public class MyDbException extends RuntimeException{
 //런타임 상속받았기때문에 당연히 언체크 예외
    public MyDbException() {
    }

    public MyDbException(String message) {
        super(message);
    }

    public MyDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDbException(Throwable cause) {
        super(cause);
    }
}
