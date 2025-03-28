public class Academic extends RegularMember{

	public Academic(String readerName, long id) {
		super(readerName, id);
		
		checkedOutBooks = new Book[3];
		accessedOnlineArticles = new OnlineArticle[3];
	}
}