package br.edu.ifpb.pd.news.utils;

import java.io.Serializable;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Ronily Gomes e Matheus Augusto
 */
public class ResponseHanddler implements Serializable{
    private HttpStatus status;
    private String message;
    private Object data;
    
    public ResponseHanddler() {}
    
    public ResponseHanddler(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
