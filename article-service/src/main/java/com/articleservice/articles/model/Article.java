package com.articleservice.articles.model;

import javax.persistence.*;

/**
 * Entity class representing an Article.
 * This class is mapped to the 'articles' table in the database.
 */
@Entity
@Table(name = "articles")
public class Article {

    // Primary key for the Article entity.Uses auto-incrementing ID generation (IDENTITY strategy).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The unique URL of the article.Cannot be null (nullable = false). Must be unique (unique = true) 
    @Column(nullable = false, unique = true)
    private String url;

    // The social score of the article, representing engagement (e.g., likes, shares).Cannot be null (nullable = false)
    @Column(nullable = false)
    private int socialScore;

    // The country code where the article is published. Cannot be null (nullable = false). Typically follows ISO 3166-1 alpha-2 format (e.g., "US", "IN").
    @Column(nullable = false)
    private String countryCode;

    // Default constructor required by JPA.
    public Article() {}

    // Parameterized constructor to initialize an Article object.
    public Article(String url, int socialScore, String countryCode) {
        this.url = url;
        this.socialScore = socialScore;
        this.countryCode = countryCode;
    }

    // @return The unique identifier of the article.
    public Long getId() {
        return id;
    }

    // @return The URL of the article.
    public String getUrl() {
        return url;
    }

    // @return The social score of the article.
    public int getSocialScore() {
        return socialScore;
    }

    // @return The country code where the article is published.
    public String getCountryCode() {
        return countryCode;
    }
}
