import java.util.ArrayList;
import java.util.Iterator;

public class Book extends LibraryMaterial {
	
	private String name;
	private String isbn;
	private Date dueDate;
	private int price;
	final private double feeByDay = 50;
	
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
	
	public Book(String name, String isbn, int price) {
		this.name = name;
		this.isbn = isbn;
		dueDate = new Date();
		this.price = price;
	}
	
	public Book(String name, String isbn, Date dueDate, int price) {
		this.name = name;
		this.isbn = isbn;
		this.dueDate = dueDate;
		this.price = price;
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
	
	public int getPrice() {
		return price;
	}
	
	public static void appendToBookArray(Book theBook) {
		bookArray.add(theBook);
		System.out.println("Book added successfully!.");
	}
	
	public static void removeFromBookArray(Book book) {
		bookArray.remove(book);
		System.out.println("remove success");
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

	@Override
	public double calculateCost() {
		int overdue = Date.dayDiff(Date.getLocalDate(), dueDate);
		if (overdue < 0) return 0;
		return overdue * feeByDay;
	}
	
	public String toString() {
		return String.format("Book Name: " + name + "\nISBN: " + isbn + "\nThe cost is: " + price + "\n");
	}
}