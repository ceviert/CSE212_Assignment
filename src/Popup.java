import java.awt.Component;

import javax.swing.JOptionPane;

public abstract class Popup extends JOptionPane{

	public static final int NULL_SELECTION = 0;
	public static final int ISBN_MISMATCH = 1;
	public static final int BOOK_ADD_SUCCESS = 2;
	public static final int INVALID_DOI = 3;
	
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
		}
		
	}
	
}