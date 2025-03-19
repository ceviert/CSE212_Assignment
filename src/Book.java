public class Book {
	
	private String name;
	private String isbn;
	private Date dueDate;
	public static Book[] bookArray;
	public static int bookCount = 0;
	
	public Book(String name, String isbn, Date dueDate) {
		this.name = name;
		this.isbn = isbn;
		this.dueDate = dueDate;
	}
	
	public String[] getBookInfoArray() {
		String[] info = new String[3];
		info[0] = name;
		info[1] = isbn;
		switch (type) {
		case MONTH_NAME:
			info[2] = dueDate.getFormattedText();
			break;
		case MONTH_NUMBER:
			info[2] = dueDate.getFormattedNumbered();
			break;
		default:
			info[2] = "???";
			break;
		}
		return info;
	}
	
	public String getBookName() {
		return name;
	}
	
	public String getBookISBN() {
		return isbn;
	}
}