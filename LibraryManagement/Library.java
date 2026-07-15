public class Library {
    private Book[] books;        // מערך לאחסון כל הספרים בספרייה
    private Member[] members;    // מערך לאחסון כל חברי הספרייה
    private int numberOfBooks;   // כמה ספרים יש כרגע בפועל בספרייה
    private int numberOfMembers; // כמה חברים רשומים כרגע בפועל

    // קונסטרקטור (בנאי)
    public Library() {
        this.books = new Book[100];       // הספרייה יכולה להכיל עד 100 ספרים
        this.members = new Member[50];    // הספרייה יכולה להכיל עד 50 חברים
        this.numberOfBooks = 0;           // בהתחלה אין ספרים
        this.numberOfMembers = 0;         // בהתחלה אין חברים
    }

    // הוספת ספר חדש למערך הספרייה
    public void addBook(Book book) {
        if (this.numberOfBooks < this.books.length) {
            this.books[this.numberOfBooks] = book;
            this.numberOfBooks = this.numberOfBooks + 1;
            System.out.println("הספר '" + book.getTitle() + "' נוסף בהצלחה לספרייה.");
        } else {
            System.out.println("שגיאה: אין מקום לספרים נוספים בספרייה.");
        }
    }

    // רישום חבר חדש במערך חברי הספרייה
    public void registerMember(Member member) {
        if (this.numberOfMembers < this.members.length) {
            this.members[this.numberOfMembers] = member;
            this.numberOfMembers = this.numberOfMembers + 1;
            System.out.println("החבר/ה " + member.getName() + " נרשם/ה בהצלחה לספרייה.");
        } else {
            System.out.println("שגיאה: אין מקום לרשום חברים נוספים.");
        }
    }

    // ביצוע השאלת ספר
    public void lendBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null) {
            System.out.println("שגיאה: הספר עם ה-ISBN " + isbn + " לא נמצא בספרייה.");
            return;
        }
        if (member == null) {
            System.out.println("שגיאה: לא נמצא חבר ספרייה עם המזהה " + memberId + ".");
            return;
        }
        if (book.getIsBorrowed() == true) {
            System.out.println("שגיאה: הספר '" + book.getTitle() + "' כבר מושאל למישהו אחר.");
            return;
        }

        // ניסיון לשייך את הספר לחבר
        boolean isAddedToMember = member.borrowBook(book);
        if (isAddedToMember == true) {
            book.setIsBorrowed(true);
            System.out.println("הצלחה: הספר '" + book.getTitle() + "' הושאל ל" + member.getName() + ".");
        } else {
            System.out.println("שגיאה: " + member.getName() + " הגיע/ה למגבלת ההשאלות המקסימלית (5 ספרים).");
        }
    }

    // ביצוע החזרת ספר
    public void returnBook(String isbn, String memberId) {
        Book book = findBookByIsbn(isbn);
        Member member = findMemberById(memberId);

        if (book == null) {
            System.out.println("שגיאה: הספר לא נמצא בספרייה.");
            return;
        }
        if (member == null) {
            System.out.println("שגיאה: החבר לא נמצא במערכת.");
            return;
        }

        // ניסיון להסיר את הספר מרשימת החבר
        boolean isReturnedFromMember = member.returnBook(book);
        if (isReturnedFromMember == true) {
            book.setIsBorrowed(false);
            System.out.println("הצלחה: הספר '" + book.getTitle() + "' הוחזר בהצלחה על ידי " + member.getName() + ".");
        } else {
            System.out.println("שגיאה: הספר אינו רשום כמושאל אצל חבר זה.");
        }
    }

    // פונקציית עזר פרטית למציאת ספר לפי ISBN (לולאה פשוטה על המערך)
    private Book findBookByIsbn(String isbn) {
        for (int i = 0; i < this.numberOfBooks; i = i + 1) {
            if (this.books[i].getIsbn().equals(isbn)) {
                return this.books[i];
            }
        }
        return null; // לא נמצא ספר
    }

    // פונקציית עזר פרטית למציאת חבר לפי מזהה ייחודי
    private Member findMemberById(String memberId) {
        for (int i = 0; i < this.numberOfMembers; i = i + 1) {
            if (this.members[i].getMemberId().equals(memberId)) {
                return this.members[i];
            }
        }
        return null; // לא נמצא חבר
    }
}