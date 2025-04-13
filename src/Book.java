import java.util.ArrayList;
import java.util.Iterator;

public class Book {
	
	private String name;
	private String isbn;
	private Date dueDate;
	
	public static ArrayList<Book> bookArray = new ArrayList<Book>();
	public static Iterator<Book> bookIterator;
	
	public static Book getBookWithTheISBN(String ISBN) {
		bookIterator = bookArray.iterator();
		while (bookIterator.hasNext()) {
			Book theBook = bookIterator.next();
			if (theBook.isbn.equals(ISBN)) {
				return theBook;
			}
		}
		return null;
	}
	
	public static int getBookArraySize() {
		return bookArray.size();
	}
	
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
		bookArray.add(theBook);
		System.out.println("Book added successfully!.");
	}
	
	public static void removeFromBookArray(Book book) {
		while (bookIterator.hasNext()) {
			Book theBook = bookIterator.next();
			if (theBook.isbn.equals(book.isbn)) {
				bookIterator.remove();
				System.out.println("book rm success"); // to be deleted
			}
		}
	}
	
	public static boolean isISBNValid(String isbnInput) {
		String part = isbnInput.substring(0, 4);
		if (part.equals("978-")) {
			for (Book book : bookArray) {
				if (book == null) break;
				if (book.isbn.equals(isbnInput)) {
					System.out.println("ERR: This ISBN already exists in database, select another.");
					return false;
				}
			}
			return true;
		}
		return false;
	}
}