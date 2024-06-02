import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static BookManager bookManager;

    public static void main(String[] args) {
        initialize();

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character after nextInt()

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    removeBook(scanner);
                    break;
                case 3:
                    updateBook(scanner);
                    break;
                case 4:
                    listBooks();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void initialize() {
        List<Book> initialBooks = Arrays.asList(
            new Book("Title1", "Author1", "ISBN1", 2000),
            new Book("Title2", "Author2", "ISBN2", 2005),
            new Book("Title3", "Author3", "ISBN3", 2010),
            new Book("Title4", "Author4", "ISBN4", 2015),
            new Book("Title5", "Author5", "ISBN5", 2020)
        );

        bookManager = new BookManager(initialBooks);
    }

    private static void displayMenu() {
        System.out.println("Menu (in terminal):");
        System.out.println("[1] Add book");
        System.out.println("[2] Remove book");
        System.out.println("[3] Update book");
        System.out.println("[4] List books");
        System.out.println("[5] Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline character after nextInt()

        Book newBook = new Book(title, author, isbn, year);
        bookManager.addBook(newBook);
        System.out.println("Book added successfully.");
    }

    private static void removeBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to remove: ");
        String isbn = scanner.nextLine();

        List<Book> books = bookManager.getBooks();
        Book bookToRemove = null;

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                bookToRemove = book;
                break;
            }
        }

        if (bookToRemove != null) {
            bookManager.removeBook(bookToRemove);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }

    private static void updateBook(Scanner scanner) {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scanner.nextLine();

        List<Book> books = bookManager.getBooks();
        Book oldBook = null;

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                oldBook = book;
                break;
            }
        }

        if (oldBook != null) {
            System.out.print("Enter new title: ");
            String newTitle = scanner.nextLine();
            System.out.print("Enter new author: ");
            String newAuthor = scanner.nextLine();
            System.out.print("Enter new ISBN: ");
            String newIsbn = scanner.nextLine();
            System.out.print("Enter new year: ");
            int newYear = scanner.nextInt();
            scanner.nextLine(); 

            Book newBook = new Book(newTitle, newAuthor, newIsbn, newYear);
            bookManager.updateBook(oldBook, newBook);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }

    private static void listBooks() {
        List<Book> books = bookManager.getBooks();
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("List of books:");
            for (Book book : books) {
                System.out.println(book.getTitle() + " by " + book.getAuthor() + " (" + book.getYear() + ")");
            }
        }
    }
}
