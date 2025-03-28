public class Student extends RegularMember {

	public Student(String readerName, long id) {
		super(readerName, id);
		
		checkedOutBooks = new Book[2];
		accessedOnlineArticles = new OnlineArticle[2];
	}
}