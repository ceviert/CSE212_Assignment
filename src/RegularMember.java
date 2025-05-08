import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class RegularMember implements LibraryData ,Comparable<RegularMember>{

	private String memberName;
	private long id;
	protected int limit = 1;
	private double totalOverdue = 0;
	
	private ArrayList<Book> checkedOutBooks = new ArrayList<Book>();;
	private Iterator<Book> checkedOutBookIterator;
	
	private ArrayList<OnlineArticle> accessedOnlineArticles = new ArrayList<OnlineArticle>();;
	private Iterator<OnlineArticle> accessedOnlineArticleIterator;
	
	private static ArrayList<RegularMember> memberArray = new ArrayList<RegularMember>();
	public static Iterator<RegularMember> memberIterator;
	
	public RegularMember(String memberName, long id) {
		this.memberName = memberName;
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public int getCheckedOutBooksSize() {
		return checkedOutBooks.size();
	}
	
	public int getAccessedOnlineArticlesSize() {
		return accessedOnlineArticles.size();
	}
	
	public static RegularMember getMemberWithTheID(long id) {
		memberIterator = memberArray.iterator();
		while (memberIterator.hasNext()) {
			RegularMember theMember = memberIterator.next();
			if (theMember.id == id) {
				return theMember;
			}
		}
		return null;
	}
	
	public Book getBookWithTheISBN(String ISBN) {
		checkedOutBookIterator = checkedOutBooks.iterator();
		while (checkedOutBookIterator.hasNext()) {
			Book theBook = checkedOutBookIterator.next();
			if (theBook.getBookISBN().equals(ISBN)) {
				return theBook;
			}
		}
		return null;
	}
	
	public OnlineArticle getArticleWithTheDOI(String DOI) {
		accessedOnlineArticleIterator = accessedOnlineArticles.iterator();
		while (accessedOnlineArticleIterator.hasNext()) {
			OnlineArticle theArticle = accessedOnlineArticleIterator.next();
			if (theArticle.getArticleDOI().equals(DOI)) {
				return theArticle;
			}
		}
		return null;
	}
	
	public static void displayInfo() {
		memberIterator = memberArray.iterator();
		while (RegularMember.memberIterator.hasNext()) {
			RegularMember theMember = RegularMember.memberIterator.next();
			System.out.println("Member " + theMember.memberName + "(ID:" + theMember.id + ");");
			if (theMember.getCheckedOutBooksSize() != 0) theMember.printMemberBooks();
			if (theMember.getAccessedOnlineArticlesSize() != 0) theMember.printMemberArticles();
			else if (theMember.getAccessedOnlineArticlesSize() == 0 & theMember.getCheckedOutBooksSize() == 0) {
				System.out.println("(NO DATA)");
			}
		}
	}
	
	private void printMemberBooks() {
		System.out.println("has the following books checked out (" + checkedOutBooks.size()+ "/" + limit + "):");
		for (Book book : checkedOutBooks) { 
			System.out.println("-> Book titled '" + book.getBookName() + "' (ISBN#:" + book.getBookISBN() + ") till " + book.getDueDate() + " with an overdue charge of " + book.calculateCost());
		}
		System.out.println();
	}
	
	private void printMemberArticles() {
		System.out.println("has access to the following online articles (" + accessedOnlineArticles.size() + "/" + limit + "):");
		for (OnlineArticle article : accessedOnlineArticles) { 
			System.out.println("-> Article entitled '" + article.getArticleName() + " with DOI#:" + article.getArticleDOI() + " since " + article.getAccessDate() + " with an overdue charge of " + article.calculateCost());
		}
		System.out.println();
	}
	
	public void appendToCheckedOutBooks(Book theBook) {
		checkedOutBooks.add(theBook);
	}
	
	public void appendToAccessedOnlineArticles(OnlineArticle theArticle) {
		accessedOnlineArticles.add(theArticle);
	}
	
	public static void appendToMemberArray(RegularMember theMember) {
		memberArray.add(theMember);
	}
	
	public boolean hasReachedBookLimit() {
		if (checkedOutBooks.size() == limit) return true;
		return false;
	}
	
	public boolean hasReachedArticleLimit() {
		if (accessedOnlineArticles.size() == limit) return true;
		return false;
	}
	
	public static boolean isIdAvailable(long id) {
		memberIterator = memberArray.iterator();
		while (memberIterator.hasNext()) {
			RegularMember theMember = memberIterator.next();
			if (theMember.id == id) {
				System.out.println("ERR: This id is NOT available, select another.");
				return false;
			}
		}
		return true;
	}
	
	public boolean checkOutBook(String ISBN) {
		Iterator<Book> bookIterator = Book.bookArray.iterator();
		while (bookIterator.hasNext()) {
			Book theBook = bookIterator.next();
			if (theBook.getBookISBN().equals(ISBN)) {
				appendToCheckedOutBooks(theBook);
				return true;
			}
		}
		return false;
	}
	
	public boolean returnBook(String ISBN) {
		checkedOutBookIterator = checkedOutBooks.iterator();
		while (checkedOutBookIterator.hasNext()) {
			Book theBook = checkedOutBookIterator.next();
			if (theBook.getBookISBN().equals(ISBN)) {
				checkedOutBookIterator.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean accessOA(String DOI) {
		Iterator<OnlineArticle> articleIterator = OnlineArticle.articleArray.iterator();
		while (articleIterator.hasNext()) {
			OnlineArticle theArticle = articleIterator.next();
			if (theArticle.getArticleDOI().equals(DOI)) {
				appendToAccessedOnlineArticles(theArticle);
				return true;
			}
		}
		return false;
	}
	
	public boolean returnOA(String DOI) {
		accessedOnlineArticleIterator = accessedOnlineArticles.iterator();
		while (accessedOnlineArticleIterator.hasNext()) {
			OnlineArticle theArticle = accessedOnlineArticleIterator.next();
			if (theArticle.getArticleDOI().equals(DOI)) {
				accessedOnlineArticleIterator.remove();
				return true;
			}
		}
		return false;
	}

	@Override
	public double calculateCost() {
		int total = 0;
		for (Book book : checkedOutBooks) {
			total += book.calculateCost();
		}
		for (OnlineArticle article : accessedOnlineArticles) {
			total += article.calculateCost();
		}
		return total;
	}
	
	private void printTotalOverdue() {
		totalOverdue = calculateCost();
		System.out.println("Member " + memberName + " with user id " + id + " is due to pay " + totalOverdue + " TRL.");
	}

	@Override
	public int compareTo(RegularMember otherMember) {
		double diff = this.totalOverdue - otherMember.totalOverdue;
		if (diff > 0) return 1;
		if (diff < 0) return -1;
		return 0;
	}
	
	public static void printAllTotalOverdue() {
		Collections.sort(memberArray);
		for (RegularMember member : memberArray) {
			member.printTotalOverdue();
		}
	}
}