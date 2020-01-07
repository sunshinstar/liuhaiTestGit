package operaDemo.utils;

import java.io.IOException;

/**
 * @author liuhai
 * @date 2019/11/11 10:55
 */
public class HttpRequestException extends RuntimeException {
    private static final long serialVersionUID = -1170466989781746231L;

    public HttpRequestException(String message, Throwable e) {
        super(message, e);
    }

    public HttpRequestException(IOException cause) {
        super(cause);
    }

    public IOException getCause() {
        return (IOException)super.getCause();
    }
}
