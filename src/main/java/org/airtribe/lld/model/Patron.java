package org.airtribe.lld.model;

import org.airtribe.lld.notification.NotificationListener;

import java.util.ArrayList;
import java.util.List;

public class Patron implements NotificationListener {
    private String patronId;    // Key for Patron, hence no setter for patronId. thus restricting update of patronId once patron created
    private String name;
    private List<Book> borrowedBooks;

    //Constructor
    public Patron(String patronId, String name) {
        this.patronId = patronId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();  //Initially set as empty list
    }

    //Borrow Book
    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    // Return Book
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    //Getters and Setters
    public String getPatronId() {
        return patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String receiveNotification(Book book, String message) {
        System.out.println(message);
        return message;
    }

}
