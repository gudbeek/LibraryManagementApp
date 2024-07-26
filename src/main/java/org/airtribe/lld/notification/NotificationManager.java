package org.airtribe.lld.notification;

import org.airtribe.lld.actor.Book;

import java.util.*;

public class NotificationManager {
    private Map<Book, List<NotificationListener>> listeners;

    public NotificationManager() {
        this.listeners = new HashMap<>();
    }

    public void subscribe(Book book, NotificationListener listener) {
        List<NotificationListener> users = listeners.get(book);
        if(Objects.isNull(users)){
            users = new ArrayList<>();
        }
        users.add(listener);
        listeners.put(book, users);
    }

    public void unsubscribe(String eventType, NotificationListener listener) {
        List<NotificationListener> users = listeners.get(eventType);
        users.remove(listener);
    }

    public void notify(Book book) {
        List<NotificationListener> patrons = listeners.get(book);
        for (NotificationListener listener : patrons) {
            listener.receiveNotification(book, "Notification! Hi " + listener.getName() + ", the book " + book.getTitle() + " with ISBN "+ book.getIsbn() +" has become available.");
        }
    }
}
