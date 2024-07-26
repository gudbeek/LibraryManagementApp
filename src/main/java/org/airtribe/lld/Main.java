package org.airtribe.lld;

import org.airtribe.lld.actor.Book;
import org.airtribe.lld.actor.Library;
import org.airtribe.lld.actor.Patron;
import org.airtribe.lld.recommendation.AuthorStrategy;
import org.airtribe.lld.recommendation.GenreStrategy;

import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {

        // Create Books
        Book b1 = new Book("Harry Potter", "JKR", "isbnb1", 2002);
        Book b2 = new Book("Push", "A Audrain", "isbnb2", 2009);
        Book b3 = new Book("Emma", "J Austen", "isbnb3", 1969);
        Book b4 = new Book("Educated", "Tara", "isbnb4", 1999);
        Book b5 = new Book("Uprooted", "N Novik", "isbnb5", 2001);

        // Create Patrons
        Patron p1 = new Patron("p1", "Asha");
        Patron p2 = new Patron("p2", "Bhasha");
        Patron p3 = new Patron("p3", "Casa");

        // Create Libraries
        Library lb1 = new Library("br1", "Mumbai");
        Library lb2 = new Library("br2", "Banglore");

        // Add all books to Library lb1
        lb1.addBook(b1);
        lb1.addBook(b2);
        lb1.addBook(b3);
        lb1.addBook(b4);
        lb1.addBook(b5);


        lb1.getBooks();

        // Add all patrons to Library lb1
        lb1.addPatron(p1);
        lb1.addPatron(p2);
        lb1.addPatron(p3);

        lb1.searchBooks("Potter");

        p1.borrowBook(b1);
        p1.borrowBook(b2);

        lb1.getPatrons();

        System.out.println("=============Reservation System=============");
        //ReservationSystem reservationSystem = new ReservationSystem();
        // ReservationSystem reservationSystem = lb1.getReservationSystem();
        //lb1.setReservationSystem(reservationSystem);
        lb1.reserveBook(b1, p2);
        lb1.reserveBook(b1, p3);

        lb1.returnBook(b1, p1);

        lb1.transferBookTo(lb2, b5);
        lb2.getBooks();

        System.out.println("=============Recommendation System=============");
        lb1.generateRecommendations(p1, new AuthorStrategy());
        lb2.generateRecommendations(p1, new GenreStrategy());
    }
}