// *** BEHAVIORAL DESIGN PATTERN USE CASE 1: Observer Pattern ***
// *** Use Case: Real-time Chat Application Notifications ***
//
// This implementation uses the **Observer Pattern**, where the chat room acts as a "Subject" and
// users are "Observers". The goal of this design pattern is to establish a subscription model in
// which multiple observers receive notifications when the subject's state changes.
//
// 1. The **ChatRoom** class maintains a list of observers (users) and notifies all users
//    whenever a message is sent. The design prevents over-complicated coupling between objects.
// 2. The **User** class implements the observer interface and handles the message reception.
// 3. The design uses **defensive programming** to ensure message validity. If a message is null
//    or empty, an exception is thrown.
// 4. **Logging** is integrated at key points to track when users are added or removed from
//    notifications and when notifications are sent.
//
// This pattern is especially useful in real-time applications where multiple objects need to
// be informed of updates in a scalable and efficient manner.

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

interface Observer {
    void update(String message);
}

interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

class ChatRoom implements Subject {
    private List<Observer> users = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(ChatRoom.class.getName());

    @Override
    public void addObserver(Observer observer) {
        users.add(observer);
        logger.info("User added: " + observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        users.remove(observer);
        logger.info("User removed: " + observer);
    }

    @Override
    public void notifyObservers(String message) {
        if (message == null || message.isEmpty()) {
            throw new IllegalArgumentException("Message cannot be null or empty");
        }

        for (Observer user : users) {
            user.update(message);
        }
        logger.info("All users notified.");
    }
}

class User implements Observer {
    private String name;

    public User(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be null or empty");
        }
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

public class ChatApplication {
    public static void main(String[] args) {
        ChatRoom chatRoom = new ChatRoom();

        User user1 = new User("Alice");
        User user2 = new User("Bob");

        chatRoom.addObserver(user1);
        chatRoom.addObserver(user2);

        chatRoom.notifyObservers("Welcome to the chat!");
    }
}
