import java.awt.Component;

import javax.swing.JOptionPane;

public abstract class Popup extends JOptionPane{

	public static final int NULL_SELECTION = 0;
	public static final int ISBN_MISMATCH = 1;
	public static final int BOOK_ADD_SUCCESS = 2;
	public static final int INVALID_DOI = 3;
	public static final int ID_NOT_AVAILABLE = 4;
	public static final int ARTICLE_ADD_SUCCESS = 5;
	public static final int MEMBER_CREATE_SUCCESS = 6;
	public static final int INVALID_INPUT = 7;
	public static final int EMPTY_DATABASE = 8;
	public static final int USER_NOT_FOUND = 9;
	public static final int USER_AT_LIMIT = 10;
	public static final int BOOK_NOT_FOUND = 11;
	public static final int CHECK_OUT_SUCCESS = 12;
	public static final int ARTICLE_NOT_FOUND = 13;
	public static final int GIVE_ACCESS_SUCCESS = 14;
	public static final int DONT_HAVE_THE_BOOK = 15;
	public static final int RETURN_SUCCESS = 16;
	public static final int DONT_HAVE_THE_ARTICLE = 17;
	public static final int REVOKE_SUCCESS = 18;
	
	public static void init(Component parent, int type) {
		switch (type) {
		case NULL_SELECTION:
			JOptionPane.showMessageDialog(parent, "Please select an option.", null, JOptionPane.WARNING_MESSAGE);
			break;
		case ISBN_MISMATCH:
			JOptionPane.showMessageDialog(parent, "Invalid ISBN.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case BOOK_ADD_SUCCESS:
			JOptionPane.showMessageDialog(parent, "Book added successfully!", null, JOptionPane.PLAIN_MESSAGE);
			break;
		case INVALID_DOI:
			JOptionPane.showMessageDialog(parent, "This DOI is NOT av available, select another.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case ID_NOT_AVAILABLE:
			JOptionPane.showMessageDialog(parent, "This id is NOT available, select another.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case ARTICLE_ADD_SUCCESS:
			JOptionPane.showMessageDialog(parent, "Article added successfully!", null, JOptionPane.PLAIN_MESSAGE);
			break;
		case INVALID_INPUT:
			JOptionPane.showMessageDialog(parent, "Invalid input!", null, JOptionPane.ERROR_MESSAGE);
			break;
		case EMPTY_DATABASE:
			JOptionPane.showMessageDialog(parent, "Database is empty! Add some and try again.", null, JOptionPane.WARNING_MESSAGE);
			break;
		case USER_NOT_FOUND:
			JOptionPane.showMessageDialog(parent, "There isn't any user with this ID.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case USER_AT_LIMIT:
			JOptionPane.showMessageDialog(parent, "You have reached your limit on books/articles. Consider upgrading your account.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case BOOK_NOT_FOUND:
			JOptionPane.showMessageDialog(parent, "There isn't any book with this ISBN.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case ARTICLE_NOT_FOUND:
			JOptionPane.showMessageDialog(parent, "There isn't any article with this DOI.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case DONT_HAVE_THE_BOOK:
			JOptionPane.showMessageDialog(parent, "You don't have any books checked out.", null, JOptionPane.ERROR_MESSAGE);
			break;
		case DONT_HAVE_THE_ARTICLE:
			JOptionPane.showMessageDialog(parent, "You don't have any online articles accessed.", null, JOptionPane.ERROR_MESSAGE);
			break;
		}
		
	}
	
	public static void init(Component parent, int type, String arg1, String arg2, String arg3) {
		switch (type) {
		case MEMBER_CREATE_SUCCESS:
			JOptionPane.showMessageDialog(parent, arg1 + " account " + arg2 + " with the ID#:" + arg3 + " has successfully created!", null, JOptionPane.PLAIN_MESSAGE);
			break;
		case CHECK_OUT_SUCCESS:
			JOptionPane.showMessageDialog(parent, "The book with the name " + arg1 + "' (ISBN#:" + arg2 + ") is checked out by user " + arg3 + ".", null, JOptionPane.PLAIN_MESSAGE);
			break;
		case GIVE_ACCESS_SUCCESS:
			JOptionPane.showMessageDialog(parent, "The article with name '" + arg1 + "' (DOI#:" + arg2 + ") is accessed by user " + arg3 + ".", null, JOptionPane.PLAIN_MESSAGE);
			break;
		case RETURN_SUCCESS:
			JOptionPane.showMessageDialog(parent, "The book with the name '" + arg1 + "' (ISBN#:" + arg2 + ") is returned by user " + arg3 + ".", null, JOptionPane.PLAIN_MESSAGE);
			break;
		case REVOKE_SUCCESS:
			JOptionPane.showMessageDialog(parent, "The article with the name '" + arg1 + "' (DOI#:" + arg2 + ") is removed from user " + arg3 + ".", null, JOptionPane.PLAIN_MESSAGE);
			break;
		}
		
	}
	
}