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
}