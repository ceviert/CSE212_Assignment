public class Student extends RegularMember {

	public Student(String memberName, long id) {
		super(memberName, id);
		
		checkedOutBooks = new Book[2];
		accessedOnlineArticles = new OnlineArticle[2];
	}
}