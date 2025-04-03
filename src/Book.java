public class Book {
	
	private String name;
	private String isbn;
	private Date dueDate;
	public static Book[] bookArray = new Book[10];
	public static int bookCount = 0;
	
	public Book(String name, String isbn) {
		this.name = name;
		this.isbn = isbn;
		dueDate = new Date();
	}
	
	public Book(String name, String isbn, Date dueDate) {
		this.name = name;
		this.isbn = isbn;
		this.dueDate = dueDate;
	}
	
	public String getBookName() {
		return name;
	}
	
	public String getBookISBN() {
		return isbn;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public static void appendToBookArray(Book theBook) {
		bookArray[bookCount] = theBook;
		bookCount++;
		System.out.println("Book added successfully! (" + bookCount + "/10 books)");
	}
	
	public static boolean isISBNValid(String isbnInput) {
		String part = isbnInput.substring(0, 4);
		if (part.equals("978-")) {
			for (Book book : bookArray) {
				if (book == null) break;
				if (book.isbn == isbnInput) {
					System.out.println("ERR: This ISBN is NOT av available, select another.");
					return false;
				}
			}
			return true;
		}
		return false;
	}
}