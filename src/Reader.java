public class Reader {

	private String readerName;
	private int id;
	private Book checkedOutBook;
	private OnlineArticle accessedArticle;
	public static Reader[] readerArray;
	
	public Reader(String readerName, int id) {
		this.readerName = readerName;
		this.id = id;
	}
	
	public int getId() {
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