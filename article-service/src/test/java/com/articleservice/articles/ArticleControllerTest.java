package com.articleservice.articles;

import com.articleservice.articles.controller.ArticleController;
import com.articleservice.articles.model.Article;
import com.articleservice.articles.service.ArticleService;
import com.articleservice.articles.dto.ReportResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ArticleController class.
 * Uses Mockito to mock the service layer for isolated controller testing.
 */
class ArticleControllerTest {

    /**
     * Tests the addArticle() method of ArticleController.
     * Ensures the service is called and a success message is returned.
     */
    @Test
    void testAddArticle() {
        // Mock the service layer
        ArticleService service = mock(ArticleService.class);
        ArticleController controller = new ArticleController(service);

        // Create a sample article
        Article article = new Article("http://test.com", 50, "us");

        // Call the controller method
        ResponseEntity<String> response = controller.addArticle(article);

        // Verify response and service call
        assertEquals("Article added successfully!", response.getBody());
        verify(service, times(1)).addArticle(article);
    }

    /**
     * Tests the deleteArticle() method of ArticleController.
     * Ensures that the service's deleteArticle method is called and the correct response is returned.
     */
    @Test
    void testDeleteArticle() {
        // Mock the service layer
        ArticleService service = mock(ArticleService.class);
        ArticleController controller = new ArticleController(service);

        // Call the controller method
        ResponseEntity<String> response = controller.deleteArticle("http://test.com");

        // Verify response and service call
        assertEquals("Article deleted successfully!", response.getBody());
        verify(service, times(1)).deleteArticle("http://test.com");
    }

    /**
     * Tests the getReport() method of ArticleController.
     * Ensures that the correct report is returned from the service.
     */
    @Test
    void testGetReport() {
        // Mock the service layer
        ArticleService service = mock(ArticleService.class);
        ArticleController controller = new ArticleController(service);

        // Create a mock report response
        List<ReportResponse> mockReport = Collections.singletonList(new ReportResponse("test.com", 1, 50));

        // Stub the service method
        when(service.getReportByCountry("us")).thenReturn(mockReport);

        // Call the controller method
        ResponseEntity<List<ReportResponse>> response = controller.getReport("us");

        // Verify response
        assertEquals(1, response.getBody().size());
        assertEquals("test.com", response.getBody().get(0).getDomain());

        // Verify that the service method was called once
        verify(service, times(1)).getReportByCountry("us");
    }
}
