package org.airtribe.lld.actor;

import org.airtribe.lld.notification.NotificationManager;

import java.util.*;

public class ReservationSystem {
    private Map<Book, Set<Patron>> reservations;
    public NotificationManager manager;

    public ReservationSystem() {
        this.reservations = new HashMap<>();
        this.manager = new NotificationManager();
    }

    // Reserve Book
    public void reserveBook(Book book, Patron patron){
        Set<Patron> bookQueue = reservations.get(book);
        if(Objects.isNull(bookQueue)){
            bookQueue = new HashSet<>();
        }
        bookQueue.add(patron);
        reservations.put(book,bookQueue);
        // Subscribing to Notification Listener
        manager.subscribe(book, patron);
    }
}
