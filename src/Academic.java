public class Academic extends RegularMember{

	public Academic(String memberName, long id) {
		super(memberName, id);
		
		checkedOutBooks = new Book[3];
		accessedOnlineArticles = new OnlineArticle[3];
	}
}