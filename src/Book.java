public class Book {
	
	private String name;
	private String isbn;
	private Date date;
	public static String[][] bookArray = new String[5][3];
	public static int bookCount = 0;
	
	// decide whether to print month as string or numbered
	public static enum DATE_PRINT_TYPE {MONTH_NAME, MONTH_NUMBER};
		
	// numbered as default, can be changed in runtime through menu
	public static DATE_PRINT_TYPE type = DATE_PRINT_TYPE.MONTH_NUMBER;
	
	public Book(String name, String isbn, Date date) {
		this.name = name;
		this.isbn = isbn;
		this.date = date;
	}
	
	public String[] getBookInfoArray() {
		String[] info = new String[3];
		info[0] = name;
		info[1] = isbn;
		switch (type) {
		case MONTH_NAME:
			info[2] = date.getFormattedText();
			break;
		case MONTH_NUMBER:
			info[2] = date.getFormattedNumbered();
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