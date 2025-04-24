import java.util.ArrayList;
import java.util.Iterator;

public class OnlineArticle extends LibraryMaterial {

	private String nameOfArticle;
	private String DOI;
	private int price;
	
	private String publisher;
	private Date accessDate;
	
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
	
	public OnlineArticle(String nameOfArticle, String DOI, String publisher) {
		this.nameOfArticle = nameOfArticle;
		this.DOI = DOI;
		this.publisher = publisher;
		if (publisher.equals("ACM")) price = 150;
		else if (publisher.equals("IEEE")) price = 200;
		else price = 100;
	}
	
	public String getArticleDOI() {
		return DOI;
	}
	
	public String getArticleName() {
		return nameOfArticle;
	}
	
	public Date getAccessDate() {
		return accessDate;
	}
	
	public int getArticlePrice() {
		return price;
	}
	
	public void setAccessDate() {
		accessDate = Date.getLocalDate();
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

	@Override
	public double calculateCost() {
		int overdue = Date.dayDiff(accessDate, Date.getLocalDate());
		if  (overdue >= 30) return overdue * 10;
		return 0;
	}
	
	public String toString() {
		return String.format("Article Name: " + nameOfArticle + "\nDOI: " + DOI + "\nThe cost is: " + price + "\n");
	}
}