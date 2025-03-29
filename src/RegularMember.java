public class RegularMember {

	private String memberName;
	private long id;
	protected Book[] checkedOutBooks;
	protected OnlineArticle[] accessedOnlineArticles;
	private int checkedOutBookCount = 0;
	private int accessibleOnlineArticleCount = 0;
	public static RegularMember[] memberArray = new RegularMember[10];
	private static int memberCount = 0;
	
	public RegularMember(String memberName, long id) {
		this.memberName = memberName;
		this.id = id;
		
		checkedOutBooks = new Book[1];
		accessedOnlineArticles = new OnlineArticle[1];
	}
	
	public long getId() {
		return id;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public void displayInfo() {
		if (checkedOutBookCount != 0) printMemberBooks();
		if (accessibleOnlineArticleCount != 0) printMemberArticles();
	}
	
	private void printMemberBooks() {
		System.out.println(memberName + " (ID#:" + id + ") has the following books checked out:");
		for (Book book : checkedOutBooks) { 
			if (book == null) continue;
			System.out.println("Book titled '" + book.getBookName() + "' (ISBN#:" + book.getBookISBN() + ") till " + book.getDueDate().getFormattedText() + ".");
		}
	}
	
	private void printMemberArticles() {
		System.out.println(memberName + " (ID#:" + id + ") has access to the following online articles:");
		for (OnlineArticle article : accessedOnlineArticles) { 
			if (article == null) continue;
			System.out.println("Article entitled '" + article.getArticleName() + " with DOI#:" + article.getArticleDOI());
		}
	}
	
	public void appendToCheckedOutBooks(Book theBook) {
		checkedOutBooks[checkedOutBookCount] = theBook;
		checkedOutBookCount++;
	}
	
	public void appendToAccessedOnlineArticles(OnlineArticle theArticle) {
		accessedOnlineArticles[accessibleOnlineArticleCount] = theArticle;
		accessibleOnlineArticleCount++;
	}
	
	public static void appendToMemberArray(RegularMember theMember) {
		memberArray[memberCount] = theMember;
		memberCount++;
	}
}