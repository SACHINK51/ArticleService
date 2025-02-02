package com.articleservice.articles.repository;

import com.articleservice.articles.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing Article entities.
 * Extends JpaRepository to inherit built-in CRUD operations.
 */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Finds an article by its unique URL.
    Optional<Article> findByUrl(String url);

    // Retrieves a list of articles based on the country code.
    List<Article> findByCountryCode(String countryCode);
}
