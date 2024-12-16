package Task4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Singleton Logger class
class Logger {
    private static Logger instance;
    private PrintWriter writer;

    private Logger() {
        try {
            writer = new PrintWriter(new FileWriter("log.txt", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println(message);
        writer.println(message);
        writer.flush();
    }
}

// Notification interface
interface Notification {
    void send(String message);
}

// EmailNotification class
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        Logger.getInstance().log("Відправка Email: " + message);
    }
}

// SMSNotification class
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        Logger.getInstance().log("Відправка SMS: " + message);
    }
}

// PushNotification class
class PushNotification implements Notification {
    @Override
    public void send(String message) {
        Logger.getInstance().log("Відправка Push Notification: " + message);
    }
}

// NotificationFactory class
class NotificationFactory {
    public static Notification createNotification(String type) {
        switch (type.toLowerCase()) {
            case "email":
                return new EmailNotification();
            case "sms":
                return new SMSNotification();
            case "push":
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Невідомий тип сповіщення");
        }
    }
}

// Subscriber interface
interface Subscriber {
    void update(String news);
}

// NewsAgency class
class NewsAgency {
    private List<Subscriber> subscribers = new ArrayList<>();

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(String news) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(news);
        }
    }

    public void generateNews(String news) {
        Logger.getInstance().log("Згенеровано новину: " + news);
        notifySubscribers(news);
    }
}

// Concrete Subscriber classes
class EmailSubscriber implements Subscriber {
    @Override
    public void update(String news) {
        Logger.getInstance().log("Email підписник отримав новину: " + news);
    }
}

class SMSSubscriber implements Subscriber {
    @Override
    public void update(String news) {
        Logger.getInstance().log("SMS пiдписник отримав новину: " + news);
    }
}

class PushSubscriber implements Subscriber {
    @Override
    public void update(String news) {
        Logger.getInstance().log("Push пiдписник отримав новину: " + news);
    }
}

// Main application
public class MainApp {
    public static void main(String[] args) {
        System.out.println("Данiїл Iванченко, КIб-1-23-4.0д:");
        Scanner scanner = new Scanner(System.in);
        NewsAgency newsAgency = new NewsAgency();

        while (true) {
            System.out.println("1. Додати пiдписника");
            System.out.println("2. Видалити пiдписника");
            System.out.println("3. Згенерувати новину");
            System.out.println("4. Вiдправити сповiщення");
            System.out.println("5. Вихiд");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Введiть тип пiдписника (email, sms, push):");
                    String subType = scanner.nextLine();
                    switch (subType.toLowerCase()) {
                        case "email":
                            newsAgency.addSubscriber(new EmailSubscriber());
                            break;
                        case "sms":
                            newsAgency.addSubscriber(new SMSSubscriber());
                            break;
                        case "push":
                            newsAgency.addSubscriber(new PushSubscriber());
                            break;
                        default:
                            System.out.println("Невiдомий тип пiдписника.");
                    }
                    break;
                case 2:
                    System.out.println("Введiть тип пiдписника для видалення (email, sms, push):");
                    String removeType = scanner.nextLine();
                    switch (removeType.toLowerCase()) {
                        case "email":
                            newsAgency.removeSubscriber(new EmailSubscriber());
                            break;
                        case "sms":
                            newsAgency.removeSubscriber(new SMSSubscriber());
                            break;
                        case "push":
                            newsAgency.removeSubscriber(new PushSubscriber());
                            break;
                        default:
                            System.out.println("Невiдомий тип пiдписника.");
                    }
                    break;
                case 3:
                    System.out.println("Введiть новину:");
                    String news = scanner.nextLine();
                    newsAgency.generateNews(news);
                    break;
                case 4:
                    System.out.println("Введiть тип сповiщення (email, sms, push):");
                    String notifType = scanner.nextLine();
                    Notification notification = NotificationFactory.createNotification(notifType);
                    System.out.println("Введiть повiдомлення:");
                    String message = scanner.nextLine();
                    notification.send(message);
                    break;
                case 5:
                    System.out.println("Вихiд...");
                    return;
                default:
                    System.out.println("Невiрний вибiр. Спробуйте ще раз.");
            }
        }
    }
}
