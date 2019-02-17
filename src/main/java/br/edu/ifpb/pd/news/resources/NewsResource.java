package br.edu.ifpb.pd.news.resources;

import br.edu.ifpb.pd.news.repository.NewsRepository;
import br.edu.ifpb.pd.news.models.News;

import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author ronily e matheus augusto
 */
@CrossOrigin(origins = "*")
@RequestMapping(value="/api/v1")
@RestController
public class NewsResource {
    
    @Autowired
    NewsRepository newsRepository;
      
    @GetMapping("/noticias")
    public List<News> listarNoticias(){
        return newsRepository.findAll();
    }
    
//    @GetMapping("/noticias/")
//    public Optional<Noticia> pegarNoticia(@RequestParam(value="id", required=true) Long id){
//        return noticiaRepository.findById(id);
//    }
    
    @PostMapping("/noticias")
    public ResponseEntity<?> salvarNoticia(@RequestParam Map<String, String> request) {
        if (newsRepository.findByTitle("teste") instanceof Optional<News>) {
            return new ResponseEntity<>(noticiaRepository.findByTitulo("testand o titulo"), HttpStatus.CREATED);    
        }
        
        Noticia n = null;
        
        n = new Noticia(
                request.get("autor"),
                request.get("titulo"), 
                request.get("conteudo")
        );
        noticiaRepository.save(n);
        
        return new ResponseEntity<>(noticiaRepository.findByTitulo("testand o titulo"), HttpStatus.CREATED);
    }
    
//    @PutMapping("/noticias/{id}")
//    public News atualizaNoticia(@PathVariable(value="id") long id, @RequestBody @Valid New produto) {
////        return noticiaRepository.save(produto);
//    }
//    
//    @DeleteMapping("/noticias/{id}")
//    public void deletaProduto(@PathVariable(value="id") long id, @RequestBody @Valid New produto) {
////        noticiaRepository.delete(produto);
//    }
}
