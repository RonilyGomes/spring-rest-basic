package br.edu.ifpb.pd.news.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Ronily Gomes e Matheus Augusto
 */
public class Utils {
    public static ResponseEntity<?> response(HttpStatus status, Object data) {
        return new ResponseEntity<>(mountResponseHanddler(status, data), status);
    }
    
    public static ResponseHanddler mountResponseHanddler(HttpStatus status, Object data) {
        String message;
        
        switch (status) {
            case CONFLICT: message = "news already exists in the system"; break;
            case CREATED: message = "news created successfully"; break;
            case NOT_FOUND: message = "news not found in the system"; break;
            case OK: message = "operation completed without errors"; break;
            default: message = "";
        }
        
        return new ResponseHanddler(status, message, data);
    }
}
