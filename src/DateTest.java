
public class DateTest {

	public static void main(String[] args) {
		
		Date mydate = new Date(2, Date.MONTH.DECEMBER, 2024);
		
		mydate.printFormattedText();
		
		Date mydate1 = new Date(2, 5, 2024);
		
		mydate1.printFormattedText();
		
		Date mydate2 = new Date();
		
		mydate2.printFormattedText();
		
		Date mydate3 = new Date(2, 2, 1987);
		
		mydate3.printFormattedNumbered();
		
	}
	
}
