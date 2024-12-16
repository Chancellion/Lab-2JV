package Task3;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Media {
    private String title;

    public Media(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract String getType();
}

class Book extends Media {
    public Book(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "Book";
    }
}

class Magazine extends Media {
    public Magazine(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "Magazine";
    }
}

class DVD extends Media {
    public DVD(String title) {
        super(title);
    }

    @Override
    public String getType() {
        return "DVD";
    }
}

class Library<T extends Media> {
    private List<T> mediaList = new ArrayList<>();

    public void addMedia(T media) {
        mediaList.add(media);
    }

    public void removeMedia(String title) {
        mediaList.removeIf(media -> media.getTitle().equalsIgnoreCase(title));
    }

    public T searchMedia(String title) {
        for (T media : mediaList) {
            if (media.getTitle().equalsIgnoreCase(title)) {
                return media;
            }
        }
        return null;
    }

    public void displayMedia() {
        for (T media : mediaList) {
            System.out.println(media.getType() + ": " + media.getTitle());
        }
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        System.out.println("Данiїл Iванченко, КIб-1-23-4.0д:");
        Library<Media> library = new Library<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Додати медiа");
            System.out.println("2. Видалити носiй");
            System.out.println("3. Пошук медiа");
            System.out.println("4. Вiдобразити всi медiа");
            System.out.println("5. Вихiд");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Введiть тип носiя (книга, журнал, DVD):");
                    String type = scanner.nextLine();
                    System.out.println("Введiть назву:");
                    String title = scanner.nextLine();
                    switch (type.toLowerCase()) {
                        case "книга":
                            library.addMedia(new Book(title));
                            break;
                        case "журнал":
                            library.addMedia(new Magazine(title));
                            break;
                        case "DVD":
                            library.addMedia(new DVD(title));
                            break;
                        default:
                            System.out.println("Неприпустимий тип носiя.");
                    }
                    break;
                case 2:
                    System.out.println("Введiть назву медiафайлу, який потрiбно видалити:");
                    String removeTitle = scanner.nextLine();
                    library.removeMedia(removeTitle);
                    break;
                case 3:
                    System.out.println("Введiть назву медiа для пошуку:");
                    String searchTitle = scanner.nextLine();
                    Media media = library.searchMedia(searchTitle);
                    if (media != null) {
                        System.out.println("Знайдено: " + media.getType() + " - " + media.getTitle());
                    } else {
                        System.out.println("Медiя не знайдено.");
                    }
                    break;
                case 4:
                    library.displayMedia();
                    break;
                case 5:
                    System.out.println("На все добре...");
                    return;
                default:
                    System.out.println("Невiрний вибiр. Спробуй ще раз.");
            }
        }
    }
}
