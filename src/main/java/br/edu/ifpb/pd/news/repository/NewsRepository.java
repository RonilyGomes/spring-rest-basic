package br.edu.ifpb.pd.news.repository;

import br.edu.ifpb.pd.news.models.News;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ronily Gomes e Matheus Augusto
 */
public interface NewsRepository extends JpaRepository<News, Long>{
    Optional<News> findById(long id);
    Optional<News> findByTitle(String title);
}
