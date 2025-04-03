public class Student extends RegularMember {
	
	final private int limit = 2;

	public Student(String memberName, long id) {
		super(memberName, id);
		
		checkedOutBooks = new Book[2];
		accessedOnlineArticles = new OnlineArticle[2];
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