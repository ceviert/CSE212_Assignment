import java.time.LocalDate;

public class Date {

	private int day;   // 1-28 or 29 or 30 or 31 depending on month and year
	private int month; // 1-12
	private int year;  // 1-9999
	
	static enum MONTH {
		ZERO, // wont be used, placed just so january equals index 1, not 0
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
		JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
	}
	
	private MONTH[] months = MONTH.values(); // array to hold months
	
	public Date() {
		this(1, 1, 2000);
	}
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public Date(int day, MONTH month, int year) {
		this.day = day;
		this.month = month.ordinal();
		this.year = year;
	}
	
	public static Date getLocalDate() {
		LocalDate currentDate = LocalDate.now();
		return new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(), currentDate.getYear());
	}
	
	public static void dateValidator(int d, int m, int y) throws NotValidDateException {
		boolean valid = true;
		if (!(1 <= m && m < 13)) {
			valid = false;
		}
		
		if (!(1 <= y && y <= 9999)) {
			valid = false;
		}
					
		if (!(1 <= d && d <= getMaxDays(m, y))) {
			valid = false;
		}
		
		if (!valid) throw new NotValidDateException("ERR: Invalid date.");
		
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
	
	private static int toDays(Date theDate) {
		int days = 365 * theDate.year;
		
		for (int i = 1; i <= theDate.month; i++) {
			days += getMaxDays(i, theDate.year);
		}
        
        return days;
	}
	
	public static int dayDiff(Date date1, Date date2) {
		int days1 = toDays(date1);
		int days2 = toDays(date2);
		return days1 - days2;
	}
	
	// return 02 if 2
	public static String handleNumber(int num) {
		if (num < 10) return "0" + num;
		else return String.valueOf(num);
	}
	
	public void printFormattedText() {
		System.out.println(day + " " + getMonthName(months[month]) + " " + year);
	}
	
	public String toString() {
		return day + " " + getMonthName(months[month]) + " " + year;
	}
	
	public void printFormattedNumbered() {
		System.out.println(handleNumber(day) + "/" + handleNumber(month) + "/" + year);
	}
	
	public String toStringNumbered() {
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