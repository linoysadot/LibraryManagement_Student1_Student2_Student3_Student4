public class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    // קונסטרקטור (בנאי)
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false; // כברירת מחדל, הספר פנוי להשאלה
    }

    // Getters & Setters
    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public boolean getIsBorrowed() {
        return this.isBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    // ייצוג הספר כמחרוזת טקסט
    public String toString() {
        String status;
        if (this.isBorrowed == true) {
            status = "מושאל";
        } else {
            status = "זמין להשאלה";
        }
        return "'" + this.title + "' מאת " + this.author + " (ISBN: " + this.isbn + ") - " + status;
    }
}