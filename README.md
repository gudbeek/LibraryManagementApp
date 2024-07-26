# Library Management Application LLD

Project : [https://www.airtribe.live/dashboard/backend-java/courses/A7V434RY9CO2/assignments/MS3JJJYLJIYC?src=projects]

## Actors :
1. **Book** class to represent a book in the library.
2. **Patron** class to represent a library member.
3. **Library** class to represent a library entity amanging the books and patrons.

## SOLID Design Principles and Design Patterns :
  Design Patterns
  
1. **Observer Pattern**
Used for the Reservation System to notify patrons. On the even of "reserving a book", the listner(Pateron) gets subscribed for the Notification for that book.
Once the book is returned to the library, all the subscribed patrons gets notified about it. This is loosely coupled and hence configurable such that it can be extended as per requirement for more listners.

2. **Strategy Pattern**
For different search algorithms or recommendation strategies. Craeted a Strategy interface which has an abstract method "getRecommendations". In this case we created 2 implementation strategies to get recommendations - by Author and by Genre. At runtime depending on which strategy is plugged into the method, expected recommendations will be returned. This can be further extended when more strategies gets introduced without changing the underlying business logic.

<h3>Class Diagram</h3>
<img alt="PNG" src="https://github.com/gudbeek/LibraryManagementApp/blob/main/src/main/resources/LibrarayManageApp.drawio.png" />

