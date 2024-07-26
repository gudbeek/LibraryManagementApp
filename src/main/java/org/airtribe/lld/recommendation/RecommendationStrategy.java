package org.airtribe.lld.recommendation;

import org.airtribe.lld.actor.Book;
import org.airtribe.lld.actor.Patron;

import java.util.List;

public interface RecommendationStrategy {
    List<Book> getRecommendations(Patron patron);
}
