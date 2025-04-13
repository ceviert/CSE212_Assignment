import java.util.ArrayList;
import java.util.Iterator;

public class OnlineArticle {

	private String nameOfArticle;
	private String DOI;
	
	public static ArrayList<OnlineArticle> articleArray = new ArrayList<OnlineArticle>();
	public static Iterator<OnlineArticle> articleIterator;
	
	public static OnlineArticle getArticleWithTheDOI(String DOI) {
		articleIterator = articleArray.iterator();
		while (articleIterator.hasNext()) {
			OnlineArticle theArticle = articleIterator.next();
			if (theArticle.DOI.equals(DOI)) {
				return theArticle;
			}
		}
		return null;
	}
	
	public static int getArticleArraySize() {
		return articleArray.size();
	}
	
	public OnlineArticle(String nameOfArticle, String DOI) {
		this.nameOfArticle = nameOfArticle;
		this.DOI = DOI;
	}
	
	public String getArticleDOI() {
		return DOI;
	}
	
	public String getArticleName() {
		return nameOfArticle;
	}
	
	public static void addToArticleArray(OnlineArticle theArticle) {
		articleArray.add(theArticle);
		System.out.println("Online Article added successfully!.");
	}
	
	public static void removeFromArticleArray(OnlineArticle article) {
		while (articleIterator.hasNext()) {
			OnlineArticle theArticle = articleIterator.next();
			if (theArticle.DOI.equals(article.DOI)) {
				articleIterator.remove();
				System.out.println("article rm success");
			}
		}
	}
	
	public static boolean isDOIValid(String DOI) {
		articleIterator = articleArray.iterator();
		while (articleIterator.hasNext()) {
			OnlineArticle theArticle = articleIterator.next();
			if (theArticle.DOI.equals(DOI)) {
				System.out.println("ERR: This DOI is NOT av available, select another.");
				return false;
			}
		}
		return true;
	}
}