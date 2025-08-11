// File: Main.java
// (Only case 7 is changed, but providing the full code for convenience)
import java.io.IOException;
import java.util.Scanner;

public class Main {

    // (displayMenu, clearConsole, pressEnterToContinue methods are unchanged)
    private static void displayMenu() {
        System.out.println("=============================================");
        System.out.println("│          Library Management System        │");
        System.out.println("=============================================");
        System.out.println("│                                           │");
        System.out.println("│   1. Add a new Book                       │");
        System.out.println("│   2. Add a new Member                     │");
        System.out.println("│   3. Display All Books                    │");
        System.out.println("│   4. Display Available Books              │");
        System.out.println("│   5. Borrow a Book                        │");
        System.out.println("│   6. Return a Book                        │");
        System.out.println("│   7. Show Member's Borrowed Books         │");
        System.out.println("│   8. Show All Members                     │");
        System.out.println("│   9. Exit                                 │");
        System.out.println("│                                           │");
        System.out.println("=============================================");
        System.out.print("_> Enter your choice: ");
    }
    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name");
            if (operatingSystem.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error trying to clear the console: " + e.getMessage());
        }
    }
    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        // Sample Data
        Member alice = new Member("Alice", 101);
        Book alchemist = new Book("The Alchemist", "Paulo Coelho", "1234");
        library.addMember(alice);
        library.addBook(alchemist);
        alice.borrowBook(alchemist);
        library.addBook(new Book("Java: The Complete Reference", "Herbert Schildt", "5678"));
        library.addMember(new Member("Bob", 102));

        while (true) {
            clearConsole();
            displayMenu();

            int choice = 0;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a number.");
                pressEnterToContinue(scanner);
                continue;
            }

            if (choice != 3 && choice != 4 && choice != 7 && choice != 8 && choice != 9) {
                clearConsole();
            }

            switch (choice) {
                // Cases 1-6 are unchanged
                case 1:
                    System.out.println("+------------------------------------------+");
                    System.out.println("|               Add a New Book             |");
                    System.out.println("+------------------------------------------+");
                    System.out.printf("%-20s: ", "Enter book title");
                    String title = scanner.nextLine();
                    System.out.printf("%-20s: ", "Enter book author");
                    String author = scanner.nextLine();
                    System.out.printf("%-20s: ", "Enter book ISBN");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    System.out.println("\n Book added successfully!");
                    break;
                case 2:
                    System.out.println("+------------------------------------------+");
                    System.out.println("|              Add a New Member            |");
                    System.out.println("+------------------------------------------+");
                    System.out.printf("%-20s: ", "Enter member name");
                    String name = scanner.nextLine();
                    System.out.printf("%-20s: ", "Enter member ID");
                    int id = Integer.parseInt(scanner.nextLine());
                    library.addMember(new Member(name, id));
                    System.out.println("\n Member added successfully!");
                    break;
                case 3:
                    library.displayAllBooks();
                    break;
                case 4:
                    library.displayAvailableBooks();
                    break;
                case 5:
                    System.out.println("+------------------------------------------+");
                    System.out.println("|                Borrow a Book             |");
                    System.out.println("+------------------------------------------+");
                    System.out.printf("%-20s: ", "Enter Member ID");
                    int memberIdBorrow = Integer.parseInt(scanner.nextLine());
                    Member borrower = library.findMemberById(memberIdBorrow);
                    if (borrower == null) {
                        System.out.println(" Member not found.");
                        break;
                    }
                    System.out.printf("%-20s: ", "Enter book ISBN");
                    String isbnBorrow = scanner.nextLine();
                    Book bookToBorrow = library.findBookByIsbn(isbnBorrow);
                    if (bookToBorrow == null) {
                        System.out.println(" Book not found.");
                        break;
                    }
                    borrower.borrowBook(bookToBorrow);
                    break;
                case 6:
                    System.out.println("+------------------------------------------+");
                    System.out.println("|                Return a Book             |");
                    System.out.println("+------------------------------------------+");
                    System.out.printf("%-20s: ", "Enter Member ID");
                    int memberIdReturn = Integer.parseInt(scanner.nextLine());
                    Member returner = library.findMemberById(memberIdReturn);
                    if (returner == null) {
                        System.out.println(" Member not found.");
                        break;
                    }
                    System.out.printf("%-20s: ", "Enter book ISBN");
                    String isbnReturn = scanner.nextLine();
                    Book bookToReturn = library.findBookByIsbn(isbnReturn);
                    if (bookToReturn == null) {
                        System.out.println(" Book not found.");
                        break;
                    }
                    returner.returnBook(bookToReturn);
                    break;

                // ** THIS IS THE CORRECTED SECTION **
                case 7:
                    System.out.println("+------------------------------------------+");
                    System.out.println("|         Show Member's Borrowed Books     |");
                    System.out.println("+------------------------------------------+");
                    System.out.printf("%-20s: ", "Enter Member ID");
                    int memberIdShow = Integer.parseInt(scanner.nextLine());
                    Member memberToShow = library.findMemberById(memberIdShow);
                    if (memberToShow != null) {
                        memberToShow.displayBorrowedBooks();
                    } else {
                        System.out.println(" Member not found.");
                    }
                    break;

                case 8:
                    library.displayAllMembers();
                    break;

                case 9:
                    System.out.println("Thank you for using the Library Management System. Exiting... ");
                    scanner.close();
                    return;

                default:
                    System.out.println(" Invalid choice. Please select a number between 1 and 9.");
            }

            if (choice != 9) {
                pressEnterToContinue(scanner);
            }
        }
    }
}