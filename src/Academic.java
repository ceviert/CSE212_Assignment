public class Academic extends RegularMember {

	public Academic(String memberName, long id) {
		super(memberName, id);
		super.limit = 3;
	}
	
	public boolean hasReachedBookLimit() {
		if (getCheckedOutBooksSize() == limit) return true;
		return false;
	}
	
	public boolean hasReachedArticleLimit() {
		if (getAccessedOnlineArticlesSize() == limit) return true;
		return false;
	}
}