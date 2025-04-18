import java.util.ArrayList;
import java.util.Iterator;

public class RegularMember {

	private String memberName;
	private long id;
	protected int limit = 1;
	
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
			if (theMember.getCheckedOutBooksSize() != 0) theMember.printMemberBooks();
			if (theMember.getAccessedOnlineArticlesSize() != 0) theMember.printMemberArticles();
		}
	}
	
	private void printMemberBooks() {
		System.out.println(memberName + " (ID#:" + id + ") has the following books checked out (" + checkedOutBooks.size()+ "/" + limit + "):");
		for (Book book : checkedOutBooks) { 
			if (book == null) continue;
			System.out.println("-> Book titled '" + book.getBookName() + "' (ISBN#:" + book.getBookISBN() + ") till " + book.getDueDate().getFormattedText() + ".");
		}
		System.out.println();
	}
	
	private void printMemberArticles() {
		System.out.println(memberName + " (ID#:" + id + ") has access to the following online articles (" + accessedOnlineArticles.size() + "/" + limit + "):");
		for (OnlineArticle article : accessedOnlineArticles) { 
			if (article == null) continue;
			System.out.println("-> Article entitled '" + article.getArticleName() + " with DOI#:" + article.getArticleDOI());
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
}