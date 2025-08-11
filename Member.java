// File: Member.java

import java.util.ArrayList;
import java.util.List;

public class Member {
    private String name;
    private int memberId;
    private List<Book> borrowedBooks;

    public Member(String name, int memberId) {
        this.name = name;
        this.memberId = memberId;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    // borrowBook and returnBook methods remain the same
    public void borrowBook(Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            this.borrowedBooks.add(book);
            System.out.println(name + " successfully borrowed '" + book.getTitle() + "'.");
        } else {
            System.out.println(" Sorry, the book '" + book.getTitle() + "' is currently not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.setAvailable(true);
            this.borrowedBooks.remove(book);
            System.out.println(name + " successfully returned '" + book.getTitle() + "'.");
        } else {
            System.out.println("Error: This member did not borrow the book '" + book.getTitle() + "'.");
        }
    }

    /**
     * UPDATED METHOD: Displays borrowed books in a clean, tabular format.
     */
    public void displayBorrowedBooks() {
        System.out.println("\n>> Books borrowed by " + this.name + " (ID: " + this.memberId + ")");

        if (borrowedBooks.isEmpty()) {
            System.out.println("+----------------------------------------------------+");
            System.out.println("| This member has not borrowed any books.            |");
            System.out.println("+----------------------------------------------------+");
            return;
        }

        String border = "+-------------------------------------+---------------------------+-----------------+";
        String headerFormat = "| %-35s | %-25s | %-15s |%n";
        String rowFormat = "| %-35.35s | %-25.25s | %-15s |%n";

        System.out.println(border);
        System.out.printf(headerFormat, "TITLE", "AUTHOR", "ISBN");
        System.out.println(border);

        for (Book book : this.borrowedBooks) {
            System.out.printf(rowFormat, book.getTitle(), book.getAuthor(), book.getIsbn());
        }
        System.out.println(border);
    }
}