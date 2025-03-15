public class Date {

	private int day;   // 1-28 or 29 or 30 or 31 depending on month and year
	private int month; // 1-12
	private int year;  // 1-9999
	
	static enum MONTH {
		ZERO, // wont be used, placed just so january equals index 1, not 0
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
		JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}
	
	MONTH[] months = MONTH.values(); // array to hold months
	
	public Date() {
		this(1, 1, 2000);
	}
	
	public Date(int day, int month, int year) {
		setDate(day, month, year);
	}
	
	public Date(int day, MONTH month, int year) {
		setDate(day, month.ordinal(), year);
	}
	
	public static boolean isDateValid(int d, int m, int y) {
		if (!(1 <= m && m < 13)) {
			System.out.println("ERR: Invalid month.");
			return false;
		}
		
		if (!(1 <= d && d < getMaxDays(m, y))) {
			System.out.println("ERR: Invalid day.");
			return false;
		}
		
		if (!(1 <= y && y <= 9999)) {
			System.out.println("ERR: Invalid year.");
			return false;
		}
		return true;
	}
	
	private void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	private static int getMaxDays(int month, int year) {
		if (month < 8) {
			if (month % 2 == 1) return 31;
		    if (month == 2) return (year % 4 == 0) ? 29 : 28; // february
		    return 30;

		}
		else {
			if (month % 2 == 1) return 30;
			return 31;
		}
	}
	
	private String getMonthName(MONTH month) {
		switch (month) {
        case JANUARY:
            return "January";
        case FEBRUARY:
            return "February";
        case MARCH:
            return "March";
        case APRIL:
            return "April";
        case MAY:
            return "May";
        case JUNE:
            return "June";
        case JULY:
            return "July";
        case AUGUST:
            return "August";
        case SEPTEMBER:
            return "September";
        case OCTOBER:
            return "October";
        case NOVEMBER:
            return "November";
        case DECEMBER:
            return "December";
        default:
            return "what??";
		}
	}
	
	public static String handleNumber(int num) {
		if (num < 10) return "0" + num;
		else return String.valueOf(num);
	}
	
	public void printFormattedText() {
		System.out.println(day + " " + getMonthName(months[month]) + " " + year);
	}
	
	public String getFormattedText() {
		return day + " " + getMonthName(months[month]) + " " + year;
	}
	
	public void printFormattedNumbered() {
		System.out.println(handleNumber(day) + "/" + handleNumber(month) + "/" + year);
	}
	
	public String getFormattedNumbered() {
		return handleNumber(day) + "/" + handleNumber(month) + "/" + year;
	}
	
	public int getDay() {
		return day;
	}
	
	public int getMonth() {
		return month;
	}
	
	public int getYear() {
		return year;
	}
}