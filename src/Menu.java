import java.util.Scanner;

public class Menu {
	
	private static enum SUBMENU {
		ZERO,
		ADD_NEW_BOOK,
		ADD_NEW_ONLINE_ARTICLE,
		CREATE_MEMBER_ACCOUNT,
		CHECKOUT_BOOK,
		RETURN_BOOK,
		GIVE_ACCESS_TO_ONLINE_ARTICLE,
		REVOKE_ACCESS_TO_ONLINE_ARTICLE,
		DISPLAY_ALL_ACCOUNTS,
		EXIT;
		
		private static SUBMENU[] values = SUBMENU.values();
	}
	
	private static enum ACCOUNT_TYPES {
		REGULAR,
		STUDENT,
		ACADEMIC;
		
		private static ACCOUNT_TYPES[] values = ACCOUNT_TYPES.values();
	}
	
	static Scanner input = new Scanner(System.in);
	
	public static void start() {
		int choice;
		boolean terminate = false;
		while (true) {
			printMenu();
			choice = input.nextInt();
			if (!(0 < choice && choice <= SUBMENU.values.length - 1)) {
				System.out.println("ERR: You've entered invalid choice, try again. (1-6)");
				continue;
			}
			SUBMENU selection = SUBMENU.values[choice];
			switch (selection) {
			case ADD_NEW_BOOK: 
				addNewBook(); 
				break;
			case ADD_NEW_ONLINE_ARTICLE: 
				addNewArticle();
				break;
			case CREATE_MEMBER_ACCOUNT: 
				createAccount();
				break;
			case CHECKOUT_BOOK: 
				checkOut();
				break;
			case RETURN_BOOK:
				returnBook();
				break;
			case GIVE_ACCESS_TO_ONLINE_ARTICLE:
				giveAccess();
				break;
			case REVOKE_ACCESS_TO_ONLINE_ARTICLE:
				revoke();
				break;
			case DISPLAY_ALL_ACCOUNTS:
				displayAllAccounts();
				break;
			case EXIT:
				System.out.println("Goodbye!");
				terminate = true;
				break;
			case ZERO:
				break;
			}
			if (terminate) break;
		}
	}

	private static void displayAllAccounts() {
		RegularMember.displayInfo();
		waitForKey();
	}
	
	private static void printMenu() {
		System.out.println(" ===================================================");
		System.out.println("|                 WELCOME TO LIBMAN                 |");
		System.out.println(" ===================================================");
		System.out.println("1. Add a new book");
		System.out.println("2. Add a new online article");
		System.out.println("3. Create a member account");
		System.out.println("4. Check out a book");
		System.out.println("5. Return a book");
		System.out.println("6. Give access to an online article");
		System.out.println("7. End an online article access");
		System.out.println("8. Display all accounts");
		System.out.println("9. Exit");
		System.out.print(">");
	}
	
	private static void addNewBook() {
		System.out.print("Enter book name:");
		input.nextLine(); // bugfix
		String name = input.nextLine();
		System.out.print("Enter ISBN number:");
		String isbn = input.nextLine();
		if (!Book.isISBNValid(isbn)) return;
		
		Book theBook = new Book(name, isbn);
		Book.appendToBookArray(theBook);
	}
	
	private static void addNewArticle() {
		System.out.print("Enter article name: ");
		input.nextLine(); // bugfix
		String name = input.nextLine();
		System.out.print("Enter DOI number: ");
		String doi = input.nextLine();
		if (!OnlineArticle.isDOIValid(doi)) return;
		
		OnlineArticle theArticle = new OnlineArticle(name, doi);
		OnlineArticle.addToArticleArray(theArticle);
	}
	
	private static void createAccount() {
		int choice;
		printAccountCreationMenu();
		choice = input.nextInt();
		if (!(0 < choice && choice <= ACCOUNT_TYPES.values.length)) {
			System.out.println("ERR: You've entered invalid choice. (1-3)");
			return;
		}
		ACCOUNT_TYPES type = ACCOUNT_TYPES.values[choice - 1];
		System.out.print("Enter account name: ");
		input.nextLine(); // bugfix
		String name = input.nextLine();
		System.out.print("Enter account id: ");
		long id = input.nextLong();
		if (!RegularMember.isIdAvailable(id)) return;
		RegularMember theMember = null;
		switch (type) {
		case REGULAR:
			theMember = new RegularMember(name, id);
			System.out.println("Regular account " + name + " with the ID#:" + id + " has successfully created!");
			break;
		case STUDENT:
			theMember = new Student(name, id);
			System.out.println("Student account " + name + " with the ID#:" + id + " has successfully created!");
			break;
		case ACADEMIC:
			theMember = new Academic(name, id);
			System.out.println("Academic account " + name + " with the ID#:" + id + " has successfully created!");
			break;
		default:
			System.out.println("ERR: Invalid account type.");
			break;
		}
		RegularMember.appendToMemberArray(theMember);
		System.out.println();
		waitForKey();
	}
	
	private static void printAccountCreationMenu() {
		System.out.println("Please enter the type of member you would like to create:");
		System.out.println("1. Regular Member");
		System.out.println("2. Student Member");
		System.out.println("3. Academic Member");
		System.out.print(">");
	}

	private static void checkOut() {
		if (Book.getBookArraySize() == 0) {
			System.out.println("ERR: There is no books in the library, add some and try again.");
			waitForKey();
			return;
		}
		System.out.print("Enter your id: ");
		long id = input.nextLong();
		RegularMember theMember = RegularMember.getMemberWithTheID(id);
		if (theMember != null) {
			if (theMember.hasReachedBookLimit()) {
				System.out.println("ERR: You've reached your account limit on books, consider upgrading your membership.");
				waitForKey();
				return;
			}
			System.out.println("Welcome " + theMember.getMemberName());
			System.out.print("Enter the ISBN of of the book you would like to check out: ");
			input.nextLine(); // bugfix
			String isbn = input.nextLine();
			System.out.println("traversing books...");
			Book theBook = Book.getBookWithTheISBN(isbn);
			if (theBook != null) {
				System.out.println("Book found with name: " + theBook.getBookName());
				int day, month, year;
				System.out.print("Enter due year (YYYY): ");
				year = input.nextInt();
				System.out.print("Enter due month (MM): ");
				month = input.nextInt();
				System.out.print("Enter due day (DD): ");
				day = input.nextInt();
				if (Date.isDateValid(day, month, year)) {
					Date dueDate = new Date(day, month, year);
					theBook = new Book(theBook.getBookName(), theBook.getBookISBN(), dueDate);
					theMember.appendToCheckedOutBooks(theBook);
					Book.removeFromBookArray(theBook);
					System.out.println("The book with name '" + theBook.getBookName() + "' (ISBN#:" + theBook.getBookISBN() + ") is checked out by user " + theMember.getMemberName() + ".");
					return;
				}
				System.out.println("ERR: Invalid date.");
				return;
			}
			System.out.println("ERR: There is no book with the ISBN#:" + isbn);
			return;
		}
		System.out.println("ERR: There is no user with the ID:" + id);
		return;
	}
	
	private static void returnBook() {
		System.out.print("Enter your id: ");
		long id = input.nextLong();
		RegularMember theMember = RegularMember.getMemberWithTheID(id);
		if (theMember != null) {
			if (theMember.getCheckedOutBooksSize() == 0) {
				System.out.println("ERR: You don't have any checked book(s).");
				waitForKey();
				return;
			}
			System.out.println("Welcome " + theMember.getMemberName());
			System.out.print("Enter the ISBN of of the book you would like to return: ");
			input.nextLine(); // bugfix
			String isbn = input.nextLine();
			System.out.println("traversing your books...");
			if (theMember.returnBook(isbn)) {
				Book returnedBook = Book.getBookWithTheISBN(isbn);
				System.out.println("The book with the name '" + returnedBook.getBookName() + "' (ISBN#:" + returnedBook.getBookISBN() + ") is returned by user " + theMember.getMemberName() + ".");
				return;
			}
			System.out.println("ERR: There is no book with the ISBN#:" + isbn);
			return;
		}
		System.out.println("ERR: There is no user with the ID:" + id);
		return;
	}

	private static void giveAccess() {
		if (OnlineArticle.getArticleArraySize() == 0) {
			System.out.println("WARN: There is no articles in the library, add some and try again.");
			waitForKey();
			return;
		}
		System.out.print("Enter your id: ");
		long id = input.nextLong();
		RegularMember theMember = RegularMember.getMemberWithTheID(id);
		if (theMember != null) {
			if (theMember.hasReachedArticleLimit()) {
				System.out.println("ERR: You've reached your account limit on online articles, consider upgrading your membership.");
				return;
			}
			System.out.println("Welcome " + theMember.getMemberName());
			System.out.print("Enter the DOI of of the article you would like to access: ");
			input.nextLine(); // bugfix
			String doi = input.nextLine();
			System.out.println("traversing articles...");
			OnlineArticle theArticle = OnlineArticle.getArticleWithTheDOI(doi);
			if (theArticle != null) {
				System.out.println("Article found with name: " + theArticle.getArticleName());
				theMember.appendToAccessedOnlineArticles(theArticle);
				OnlineArticle.removeFromArticleArray(theArticle);
				System.out.println("The article with name '" + theArticle.getArticleName() + "' (DOI#:" + theArticle.getArticleDOI() + ") is accessed by user " + theMember.getMemberName() + ".");
				return;
			}
			System.out.println("ERR: There is no article with the DOI#:" + doi);
			return;
		}
		System.out.println("ERR: There is no user with the ID:" + id);
		return;
	}
	
	private static void revoke() {
		System.out.print("Enter your id: ");
		long id = input.nextLong();
		RegularMember theMember = RegularMember.getMemberWithTheID(id);
		if (theMember != null) {
			if (theMember.getAccessedOnlineArticlesSize() == 0) {
				System.out.println("ERR: You don't have any accessed online article(s).");
				waitForKey();
				return;
			}
			System.out.println("Welcome " + theMember.getMemberName());
			System.out.print("Enter the DOI of the article you would like to remove from your account: ");
			input.nextLine();
			String doi = input.nextLine();
			System.out.println("traversing your articles...");
			if (theMember.returnOA(doi)) {
				OnlineArticle returnedOA = OnlineArticle.getArticleWithTheDOI(doi);
				System.out.println("The article with the name '" + returnedOA.getArticleName() + "' (DOI#:" + returnedOA.getArticleDOI() + ") is removed from user " + theMember.getMemberName() + ".");
				return;
			}
			System.out.println("ERR: There is no article with the DOI#:" + doi);
			return;
		}
		System.out.println("ERR: There is no user with the ID:" + id);
		return;
	}
	
	private static void waitForKey() {
		System.out.println("Hit enter to go back to main menu.");
		input.nextLine(); 
        input.nextLine();
	}
}