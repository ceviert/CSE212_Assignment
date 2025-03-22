public class Reader {

	private String readerName;
	private long id;
	private Book checkedOutBook;
	private OnlineArticle accessedArticle;
	public static Reader[] readerArray = new Reader[10];
	public static int readerCount = 0;
	
	public Reader(String readerName, long id) {
		this.readerName = readerName;
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public String getReaderName() {
		return readerName;
	}
	
	public void setCheckedOutBook(Book checkedOutBook) {
		this.checkedOutBook = checkedOutBook;
	}
	
	public void setAccessedArticle(OnlineArticle accessedArticle) {
		this.accessedArticle = accessedArticle;
	}
}