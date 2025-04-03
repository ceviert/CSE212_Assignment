public class Academic extends RegularMember{

	final private int limit = 3;
	
	public Academic(String memberName, long id) {
		super(memberName, id);
		
		checkedOutBooks = new Book[3];
		accessedOnlineArticles = new OnlineArticle[3];
	}
	
	@Override
	public boolean hasReachedBookLimit() {
		if (checkedOutBookCount == limit) return true;
		return false;
	}
	
	@Override
	public boolean hasReachedArticleLimit() {
		if (accessibleOnlineArticleCount == limit) return true;
		return false;
	}
}