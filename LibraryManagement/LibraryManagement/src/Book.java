// File: Book.java

public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    // Constructor to initialize a Book object
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true; // By default, a new book is available
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setters for availability
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Method to display book details (demonstrates Polymorphism if overridden)
    public void displayDetails() {
        System.out.println("Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + (isAvailable ? "Yes" : "No"));
    }

    @Override
    public String toString() {
        return "Book [Title: '" + title + "', Author: '" + author + "', ISBN: '" + isbn + "']";
    }
}