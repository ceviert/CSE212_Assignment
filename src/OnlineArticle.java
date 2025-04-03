public class OnlineArticle {

	private String nameOfArticle;
	private String DOI;
	public static int articleCount = 0;
	public static OnlineArticle[] articleArray = new OnlineArticle[10];
	
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
		articleArray[articleCount] = theArticle;
		articleCount++;
		System.out.println("Online Article added successfully! (" + articleCount + "/10 articles)");
	}
	
	public static boolean isDOIValid(String doiInput) {
			for (OnlineArticle article : articleArray) {
				if (article == null) break;
				if (article.DOI.equals(doiInput)) {
					System.out.println("ERR: This DOI is NOT av available, select another.");
					return false;
				}
			}
			return true;
	}
}