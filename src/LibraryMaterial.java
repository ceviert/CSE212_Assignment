public abstract class LibraryMaterial implements LibraryData {

	public static void displayDatabase() {
		for (Book book : Book.bookArray) {
			System.out.println(book);
		}
		for (OnlineArticle article : OnlineArticle.articleArray) {
			System.out.println(article);
		}
	}
	
}