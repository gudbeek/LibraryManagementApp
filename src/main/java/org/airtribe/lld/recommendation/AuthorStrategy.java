package org.airtribe.lld.recommendation;

import org.airtribe.lld.actor.Book;
import org.airtribe.lld.actor.Patron;

import java.util.ArrayList;
import java.util.List;

public class AuthorStrategy implements RecommendationStrategy{
    private List<Book> recommendations = new ArrayList<>();
    @Override
    public List<Book> getRecommendations(Patron patron) {

        for (Book book : patron.getBorrowedBooks()) {
            // Logic to get Books by same Authors
            // recommendations.add ...
            System.out.println("Book recommendations for "+ patron.getName()+" based on Authors");
        }
        return recommendations;
    }
}
