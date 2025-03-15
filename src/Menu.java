import java.util.Scanner;

public class Menu {
	
	static Scanner input = new Scanner(System.in);
	
	private static boolean isDateFormatValid(String dateInput) {
		char[] charArrayOfDateInput = dateInput.toCharArray();
		if (charArrayOfDateInput.length == 10 && charArrayOfDateInput[2] == '/' 
				&& charArrayOfDateInput[5] == '/') {
			return true;
		}
		return false;
	}
	
	private static boolean isISBNValid(String isbnInput) {
		String part = isbnInput.substring(0, 4);
		if (part.equals("978-")) {
			return true;
		}
		return false;
	}
	
	private static Date parseDate(String dateInput) {
		String dayText = dateInput.substring(0, 2);
		int day = Integer.parseInt(dayText);

		String monthText = dateInput.substring(3, 5);
		int month = Integer.parseInt(monthText);

		String yearText = dateInput.substring(6, 10);
		int year = Integer.parseInt(yearText);

		Date theDate = null;
		if (Date.isDateValid(day, month, year)) {
			theDate = new Date(day, month, year);
		}
		return theDate;
	}
	
	private static void printMenu() {
		System.out.println("===================================================");
		System.out.println("                 WELCOME TO LIBMAN                 ");
		System.out.println("===================================================");
		System.out.println("1. Add a new book to the checkout list");
		System.out.println("2. Display all books checked out");
		System.out.println("3. Display the total number of books checked out");
		System.out.println("4. Change date format");
		System.out.println("5. Exit");
		System.out.print(">");
	}
	
	private static void addNewBook() {
		if (Book.bookCount == 5) {
			System.out.println("Library is full! Cannot add more than 5 books.");
			return;
		}
		System.out.print("Enter book name:");
		input.nextLine(); // bugfix
		String name = input.nextLine();
		System.out.print("Enter ISBN number:");
		String isbn = input.nextLine();
		if (!isISBNValid(isbn)) {
			System.out.println("ERR: Invalid ISBN number.");
			return;
		}
		System.out.print("Enter due date (DD/MM/YYYY):");
		String date = input.nextLine();
		if (!isDateFormatValid(date)) {
			System.out.println("ERR: Invalid date format.");
			return;
		}
		Date theDate = parseDate(date);
		if (Date.isDateValid(theDate.getDay(), theDate.getMonth(), theDate.getYear())) {
			Book theBook = new Book(name, isbn, theDate);
			addToArray(theBook);
		}
	}
	
	private static void addToArray(Book book) {
		Book.bookArray[Book.bookCount] = book.getBookInfoArray();
		Book.bookCount++;
		System.out.println("Book checked out successfully! (" + Book.bookCount + "/5 books)");
	}
	
	private static void displayBooks() {
		if (Book.bookCount == 0) {
			System.out.println("ERR: No books!");
			return;
		}
		System.out.println("Checked out book list:");
		for (int i = Book.bookCount, row = 0; i != 0; i--, row++) {
			System.out.println("	Book titled '" + Book.bookArray[row][0] + "' that has ISBN# " + Book.bookArray[row][1] + " is due " + Book.bookArray[row][2]);
		}
	}
	
	private static void totalNumberOfBooksCheckedOut() {
		System.out.println("Total number of books checked out: " + Book.bookCount);
	}
	
	private static void changeDateFormat() {
		System.out.print("Current format: ");
		switch (Book.type) {
		case MONTH_NAME:
			System.out.println("DD <MONTH_NAME> YYYY");
			break;
		case MONTH_NUMBER:
			System.out.println("DD/MM/YYYY");
			break;
		}
		System.out.print("Select the format you want (1 for numbered, 2 for with name):");
		int choice = input.nextInt();
		if (choice == 1) Book.type = Book.DATE_PRINT_TYPE.MONTH_NUMBER;
		else if (choice == 2) Book.type = Book.DATE_PRINT_TYPE.MONTH_NAME;
		else System.out.println("ERR: Invalid selection.");
	}
	
	public static void start() {
		int choice;
		boolean terminate = false;
		while (true) {
			printMenu();
			choice = input.nextInt();
			switch (choice) {
			case 1:
				addNewBook();
				break;
			case 2:
				displayBooks();
				break;
			case 3:
				totalNumberOfBooksCheckedOut();
				break;
			case 4:
				changeDateFormat();
				break;
			case 5:
				System.out.println("Goodbye!");
				terminate = true;
				break;
			}
			if (terminate) break;
		}
	}
}