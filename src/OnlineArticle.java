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
}