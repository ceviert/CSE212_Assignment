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
		}
		
	}
	
	public static void init(Component parent, int type, String arg1, String arg2, String arg3) {
		switch (type) {
		case MEMBER_CREATE_SUCCESS:
			JOptionPane.showMessageDialog(parent, arg1 + " account " + arg2 + " with the ID#:" + arg3 + " has successfully created!", null, JOptionPane.PLAIN_MESSAGE);
			break;
		}
		
	}
	
}