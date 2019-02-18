package br.edu.ifpb.pd.news.utils;

import br.edu.ifpb.pd.news.models.News;
import java.util.Optional;
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
            case CONFLICT: message = "Já existe uma notícia com esse título em nossa base de dados"; break;
            case CREATED: message = "Notícia criada com sucesso"; break;
            case NOT_FOUND: message = "Notícia não encontrada"; break;
            case OK: message = "Operação concluída sem falhas"; break;
            default: message = "";
        }
        
        return new ResponseHanddler(status, message, data);
    }
    
    public static ResponseEntity<?> responseAsHtml(HttpStatus status, News news) {
        String html = (status == HttpStatus.OK) 
                ? mountModalEdit(news)
                : mountResourceNotFoundAsHtml();
        
        return new ResponseEntity<>(html, status);
    }
    
    public static String mountModalEdit(News news) {
        return "<div class=\"modal-dialog\">" +
"    			<div class=\"modal-content\">" +
"    				<form>" +
"    					<div class=\"modal-header\">" +
"    						<h4 class=\"modal-title\">Editar notícia</h4>" +
"    						<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>" +
"    					</div>" +
"    					<div class=\"modal-body\" id=\"modal-body-edit\">" +
"                                           <div class=\"form-group\">" +
"                                               <label>Título</label>" +
"                                               <input type=\"text\" class=\"form-control\" name=\"nickname\" id=\"news_title_edit\" required value=\"" + news.getTitle() + "\">" +
"                                           </div>" +
"                                           <div class=\"form-group\">" +
"                                               <label>Autor</label>\n" +
"                                               <input type=\"text\" class=\"form-control\" name=\"author\" id=\"news_author_edit\" readonly value=\"" + news.getAuthor()+ "\">" +
"                                           </div>" +
"                                           <div class=\"form-group\">" +
"                                               <label>Conteúdo</label>" +
"                                               <textarea class=\"form-control\" rows=\"5\" iname=\"content\" id=\"news_content_edit\" readonly style=\"resize: none;\">" + news.getContent()+ "</textarea>" +
"                                           </div>" +
"    					</div>\n" +
"    					<div class=\"modal-footer\" id=\"modal-footer-edit\">\n" +
"                                           <input type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"Cancelar\">\n" +
"                                           <input type=\"submit\" class=\"btn btn-success\"\n" +
"                                           onclick=\"event.preventDefault(); editSingleNews(" + news.getId()+ ")\" value=\"Alterar\" id=\"news_edit\">" +
"    					</div>\n" +
"    				</form>\n" +
"    			</div>\n" +
"    		</div>";
    }
    
    public static String mountResourceNotFoundAsHtml() {
        return "<message>Notícia não encontrada</message>";
    }
}
