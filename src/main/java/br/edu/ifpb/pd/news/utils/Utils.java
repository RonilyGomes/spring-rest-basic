package br.edu.ifpb.pd.news.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Ronily Gomes e Matheus Augusto
 */
public class Utils {
    public static ResponseEntity<?> response(Object obj, HttpStatus status) {
        return new ResponseEntity<>(obj, status);
    }
}
