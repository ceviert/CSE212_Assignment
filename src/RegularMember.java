public class RegularMember {

	private String readerName;
	private long id;
	protected Book[] checkedOutBooks;
	protected OnlineArticle[] accessedOnlineArticles;
	private int checkedOutBookCount = 0;
	private int accessibleOnlineArticleCount = 0;
	public static RegularMember[] memberArray = new RegularMember[10];
	public static int memberCount = 0;
	
	public RegularMember(String readerName, long id) {
		this.readerName = readerName;
		this.id = id;
		
		checkedOutBooks = new Book[1];
		accessedOnlineArticles = new OnlineArticle[1];
	}
	
	public long getId() {
		return id;
	}
	
	public String getReaderName() {
		return readerName;
	}
	
	public void displayInfo() {
		
	}
}