package org.airtribe.lld.model;

import org.airtribe.lld.notification.NotificationManager;
import org.airtribe.lld.recommendation.RecommendationStrategy;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Library {
    private String branchCode;      // Key for Library, hence no setter for isbn. thus restricting update of branchId once library created
    private String address;
    private Map<String,Book> books;  // Key : Book.isbn
    private Map<String, Patron> patrons;    // Key : Patron.patronId

    /*private ReservationSystem reservationSystem;*/
    public NotificationManager manager;
    private Map<Book, Set<Patron>> reservations;

    private static final Logger logger = Logger.getLogger(Library.class.getName());


    public Library(String branchCode, String address) {
        this.branchCode = branchCode;
        this.address = address;
        this.books = new HashMap<>();       // Initially set as empty Map
        this.patrons = new HashMap<>();     // Initially set as empty Map
        /*this.reservationSystem = new ReservationSystem();*/
        //this.recommendationSystem = new RecommendationSystem();
        this.manager = new NotificationManager();
        this.reservations = new HashMap<>();
    }

    // Add Book to library
    public void addBook(Book book){
        books.put(book.getIsbn(), book);
    }

    // Remove Book from library
    public void removeBook(Book book){
        books.remove(book.getIsbn());
    }

    // Update Book from library
    public void updateBook(Book updatedBook){    //Update the book against the ISBN
        books.put(updatedBook.getIsbn(), updatedBook);
    }

    // Search Book in library
    public List<Book> searchBooks(String query){
        return books.values().stream().filter(book -> book.getTitle().contains(query) || book.getAuthor().contains(query) || book.getIsbn().contains(query)).collect(Collectors.toList());
    }

    // Add a new patron
    public void addPatron(Patron patron) {
        patrons.put(patron.getPatronId(), patron);
    }

    // Lend book to patron
    public boolean lendBook(Book book, Patron patron) {
        if(Objects.nonNull(books.get(book.getIsbn())) && Objects.nonNull(patrons.get(patron.getPatronId()))){
            if (book.isAvailable()) {
                book.setAvailable(false);
                patron.borrowBook(book);
                return true;
            }
        }
        return false;
    }

    // Return book from patron
    public boolean returnBook(Book book, Patron patron) {
        if(Objects.nonNull(books.get(book.getIsbn())) && Objects.nonNull(patrons.get(patron.getPatronId()))){
            patron.returnBook(book);
            book.setAvailable(true);
            //reservationSystem.manager.notify(book);
            System.out.println("Book "+book.getIsbn()+" returned by "+patron.getName()+" pId : "+patron.getPatronId());

            manager.notify(book);
            return true;
        }
        return false;
    }

    // Transfer Book
    public void transferBookTo(Library targetLib, Book book) {
        if (Objects.nonNull(books.get(book.getIsbn()))) {
            books.remove(book.getIsbn());
            targetLib.addBook(book);
            System.out.println("Book "+book.getIsbn()+" transferred from library brandId : "+this.getBranchCode()+" to branchId : "+targetLib.getBranchCode());
        }
    }

    // Reserve Book

    // Getters and Setters
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, Book> getBooks() {
        System.out.println("Books in Library "+ this.getBranchCode());
        books.forEach((key, value) -> {
            System.out.println("Book :"+value.getIsbn()+" | "+value.getTitle()+" | "+value.isAvailable());
        });
        return books;
    }

    public void setBooks(Map<String, Book> books) {
        this.books = books;
    }

    public Map<String, Patron> getPatrons() {
        System.out.println("Patrons in Library "+ this.getBranchCode());
        patrons.forEach((key, value) -> {
            System.out.println("Patron : "+value.getPatronId()+" | "+value.getName()+" | Books : "+value.getBorrowedBooks().stream().map(e -> e.getIsbn()+"; ").reduce("", String::concat));
        });
        return patrons;
    }

    public void setPatrons(Map<String, Patron> patrons) {
        this.patrons = patrons;
    }

    public String getBranchCode() {
        return branchCode;
    }

    /*public ReservationSystem getReservationSystem() {
        return reservationSystem;
    }

    public void setReservationSystem(ReservationSystem reservationSystem) {
        this.reservationSystem = reservationSystem;
    }*/
    public List<Book> generateRecommendations(Patron patron, RecommendationStrategy strategy){
        return strategy.getRecommendations(patron);
    }

    public void reserveBook(Book book, Patron patron){
        Set<Patron> bookQueue = reservations.get(book);
        if(Objects.isNull(bookQueue)){
            bookQueue = new HashSet<>();
        }
        bookQueue.add(patron);
        reservations.put(book,bookQueue);
        // Subscribing to Notification Listener
        System.out.println("Book "+book.getIsbn()+" reserved by "+patron.getName()+" pId : "+patron.getPatronId());
        manager.subscribe(book, patron);
    }

}
