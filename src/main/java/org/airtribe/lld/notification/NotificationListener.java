package org.airtribe.lld.notification;

import org.airtribe.lld.actor.Book;

public interface NotificationListener {
    public String receiveNotification(Book book, String message);
    public String getName();
}
