public class Student extends RegularMember {

	public Student(String memberName, long id) {
		super(memberName, id);
		super.limit = 2;
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