package operaDemo.utils;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author liuhai
 * @date 2019/11/11 11:04
 */
public class Response {
    private final HttpRequest httpRequest;
    private Cookie cookie;
    private int code;
    private String message;
    private String content;

    public Response(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        this.code = httpRequest.code();
        this.message = httpRequest.message();
        String cookieString = httpRequest.header("Set-Cookie");
        if (null != cookieString) {
            this.cookie = new Cookie(cookieString);
        }

    }

    public Cookie getCookie() {
        return this.cookie;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public Response receive(OutputStream output) {
        this.httpRequest.receive(output);
        return this;
    }

    public synchronized String getContent() {
        if (this.content == null) {
            this.content = this.httpRequest.body();
        }

        return this.content;
    }

    public String header(String name) {
        return this.httpRequest.header(name);
    }

    public Map<String, List<String>> headers() {
        return this.httpRequest.headers();
    }

    public long dateHeader(String name) {
        return this.httpRequest.dateHeader(name);
    }

    public long dateHeader(String name, long defaultValue) {
        return this.httpRequest.dateHeader(name, defaultValue);
    }

    public int intHeader(String name) {
        return this.httpRequest.intHeader(name);
    }

    public int intHeader(String name, int defaultValue) {
        return this.httpRequest.intHeader(name, defaultValue);
    }

    public String[] headers(String name) {
        return this.httpRequest.headers(name);
    }

    public String parameter(String headerName, String paramName) {
        return this.httpRequest.parameter(headerName, paramName);
    }

    public Map<String, String> parameters(String headerName) {
        return this.httpRequest.parameters(headerName);
    }

    public Map<String, String> getParams(String header) {
        return this.httpRequest.getParams(header);
    }

    public String getParam(String value, String paramName) {
        return this.httpRequest.getParam(value, paramName);
    }

    public boolean isOK() {
        return this.code >= 200 && this.code < 400;
    }

    public boolean isServerError() {
        return this.code >= 500 && this.code < 600;
    }

    public boolean isClientError() {
        return this.code >= 400 && this.code < 500;
    }
}
