package org.airtribe.lld.recommendation;

import org.airtribe.lld.model.Book;
import org.airtribe.lld.model.Patron;

import java.util.List;

public interface RecommendationStrategy {
    List<Book> getRecommendations(Patron patron);
}
