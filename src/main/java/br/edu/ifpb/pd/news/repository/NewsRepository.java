package br.edu.ifpb.pd.news.repository;

import br.edu.ifpb.pd.news.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Ronily Gomes e Matheus Augusto
 */
public interface NewsRepository extends JpaRepository<News, Long>{
    News findById(long id);
    News findByTitle(String title);
}
