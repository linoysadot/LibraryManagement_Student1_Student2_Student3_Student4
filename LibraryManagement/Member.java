public class Member {
    private String memberId;
    private String name;
    private Book[] borrowedBooks; // מערך פשוט לאחסון הספרים שהחבר השאיל
    private int numberOfBorrowedBooks; // מונה שעוקב כמה ספרים מושאלים בפועל

    // קונסטרקטור (בנאי)
    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.borrowedBooks = new Book[5]; // חבר יכול להשאיל עד 5 ספרים לכל היותר
        this.numberOfBorrowedBooks = 0;   // בתחילה לא הושאלו ספרים
    }

    // Getters
    public String getMemberId() {
        return this.memberId;
    }

    public String getName() {
        return this.name;
    }

    public Book[] getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public int getNumberOfBorrowedBooks() {
        return this.numberOfBorrowedBooks;
    }

    // הוספת ספר למערך הספרים המושאלים של החבר
    public boolean borrowBook(Book book) {
        // בדיקה אם הגענו למגבלה המקסימלית (5 ספרים)
        if (this.numberOfBorrowedBooks < this.borrowedBooks.length) {
            this.borrowedBooks[this.numberOfBorrowedBooks] = book;
            this.numberOfBorrowedBooks = this.numberOfBorrowedBooks + 1;
            return true; // ההוספה הצליחה
        }
        return false; // המערך מלא, אי אפשר להשאיל עוד ספרים
    }

    // הסרת ספר ממערך הספרים המושאלים (למשל בזמן החזרה)
    public boolean returnBook(Book book) {
        int indexToRemove = -1;

        // חיפוש הספר במערך המושאלים של החבר
        for (int i = 0; i < this.numberOfBorrowedBooks; i = i + 1) {
            if (this.borrowedBooks[i] == book) {
                indexToRemove = i;
            }
        }

        // אם הספר נמצא, נסיר אותו ונזיז את שאר הספרים במערך כדי שלא יישארו "חורים"
        if (indexToRemove != -1) {
            for (int i = indexToRemove; i < this.numberOfBorrowedBooks - 1; i = i + 1) {
                this.borrowedBooks[i] = this.borrowedBooks[i + 1];
            }
            // מאפסים את התא האחרון שהתפנה ומעדכנים את המונה
            this.borrowedBooks[this.numberOfBorrowedBooks - 1] = null;
            this.numberOfBorrowedBooks = this.numberOfBorrowedBooks - 1;
            return true; // ההסרה הצליחה
        }

        return false; // הספר לא נמצא אצל החבר הזה
    }

    public String toString() {
        return "חבר מועדון: " + this.name + " (מזהה: " + this.memberId + ") | מספר ספרים מושאלים כרגע: " + this.numberOfBorrowedBooks;
    }
}