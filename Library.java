class Library {
    private List<Book> books;
    private List<Member> members;

    // Constructor
    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    // (Other methods like addBook, addMember remain unchanged)
    public void addBook(Book book) {
        books.add(book);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    // (displayAllBooks and displayAvailableBooks remain unchanged)
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("The library has no books.");
            return;
        }
        String border = "+-------------------------------------+---------------------------+-----------------+--------------+";
        String headerFormat = "| %-35s | %-25s | %-15s | %-12s |%n";
        String rowFormat = "| %-35.35s | %-25.25s | %-15s | %-12s |%n";
        System.out.println(border);
        System.out.printf(headerFormat, "TITLE", "AUTHOR", "ISBN", "AVAILABILITY");
        System.out.println(border);
        for (Book book : books) {
            System.out.printf(rowFormat, book.getTitle(), book.getAuthor(), book.getIsbn(), book.isAvailable() ? "Yes" : "No");
        }
        System.out.println(border);
    }

    public void displayAvailableBooks() {
        boolean found = false;
        String border = "+-------------------------------------+---------------------------+-----------------+--------------+";
        String headerFormat = "| %-35s | %-25s | %-15s | %-12s |%n";
        String rowFormat = "| %-35.35s | %-25.25s | %-15s | %-12s |%n";
        System.out.println(border);
        System.out.printf(headerFormat, "TITLE", "AUTHOR", "ISBN", "AVAILABILITY");
        System.out.println(border);
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.printf(rowFormat, book.getTitle(), book.getAuthor(), book.getIsbn(), "Yes");
                found = true;
            }
        }
        if (!found) {
            System.out.printf("| %-94s |%n", "No books are currently available.");
        }
        System.out.println(border);
    }

    /
     * NEW METHOD: Displays all members in a tabular format.
     */
    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("There are no members in the library system.");
            return;
        }

        String border = "+------------+---------------------------+------------------+";
        String headerFormat = "| %-10s | %-25s | %-16s |%n";
        String rowFormat = "| %-10d | %-25s | %-16d |%n";

        System.out.println(border);
        System.out.printf(headerFormat, "MEMBER ID", "NAME", "BOOKS BORROWED");
        System.out.println(border);

        for (Member member : members) {
            System.out.printf(rowFormat,
                    member.getMemberId(),
                    member.getName(),
                    member.getBorrowedBooks().size());
        }
        System.out.println(border);
    }

    // (findBookByIsbn and findMemberById remain unchanged)
    public Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                return book;
            }
        }
        return null;
    }

    public Member findMemberById(int id) {
        for (Member member : members) {
            if (member.getMemberId() == id) {
                return member;
            }
        }
        return null;
    }
}