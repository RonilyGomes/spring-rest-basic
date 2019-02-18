package br.edu.ifpb.pd.news.resources;

import br.edu.ifpb.pd.news.repository.NewsRepository;
import br.edu.ifpb.pd.news.models.News;
import br.edu.ifpb.pd.news.utils.Utils;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ronily Gomes e Matheus Augusto
 */
@CrossOrigin(origins = "*")
@RequestMapping(value="/api/v1")
@RestController
public class NewsResource {
    
    @Autowired
    NewsRepository newsRepository;
      
    @GetMapping(value="/news", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAll(){        
        return Utils.response(HttpStatus.OK, newsRepository.findAll());
    }
    
    @GetMapping(value="/news", params={"id"}, produces=MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<?> getById(@RequestParam(value="id", required=true) Long id){
        Optional<News> news = newsRepository.findById(id);
        
        if (!news.isPresent()) {
            return Utils.responseAsHtml(HttpStatus.NOT_FOUND, null);
        }
       
        return Utils.responseAsHtml(HttpStatus.OK, news.get());
    }
    
    @PostMapping(value="/news", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> store(@RequestParam Map<String, String> request) {
        News news = newsRepository.findByTitle(request.get("title"));
        
        if (news != null) {
            return Utils.response(HttpStatus.CONFLICT, news);
        }
        
        news = new News(
            request.get("author"),
            request.get("title"), 
            request.get("content")
        );
        newsRepository.save(news);
        
        return Utils.response(HttpStatus.CREATED, news);
    }
    
    @PutMapping(value="/news/{id:[0-9]+}/title/{title}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable("id") long id, @PathVariable("title") String title) {
        News news = newsRepository.findByTitle(title);
        
        if (news != null) {
            return Utils.response(HttpStatus.CONFLICT, news);
        }
        
        news = newsRepository.findById(id);
        
        if (news == null) {
            return Utils.response(HttpStatus.NOT_FOUND, null);
        }
         
        news.setTitle(title);
        newsRepository.save(news);
        
        return Utils.response(HttpStatus.OK, news);
    }
    
    @DeleteMapping(value="/news/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> deletaProduto(@PathVariable(value="id") long id) {
        News news = newsRepository.findById(id);
        
        if (news == null) {
            return Utils.response(HttpStatus.NOT_FOUND, null);
        }
        
        newsRepository.delete(news);
        
        return Utils.response(HttpStatus.OK, news);
    }
}
