import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CheckOutBookFrame extends JFrame {

	private JLabel memberIdLabel, bookIsbnLabel, dueYearLabel, dueMonthLabel, dueDayLabel;
	private JTextField memberIdField, bookIsbnField, dueYearField, dueMonthField, dueDayField;
	private JButton submitButton;
	
	private JPanel mainPanel;
	
	public CheckOutBookFrame() {
		super(Menu.SUBMENU.CHECKOUT_BOOK.toString());
		
		memberIdLabel = new JLabel("Member ID:");
		memberIdField = new JTextField();
		
		bookIsbnLabel = new JLabel("Book ISBN:");
		bookIsbnField = new JTextField();
		
		dueYearLabel = new JLabel("Due Year (YYYY)");
		dueYearField = new JTextField();
		
		dueMonthLabel = new JLabel("Due Month (MM)");
		dueMonthField = new JTextField();
		
		dueDayLabel = new JLabel("Due Day (DD)");
		dueDayField = new JTextField();
		
		submitButton = new JButton("Submit");
		
		mainPanel = new JPanel(new GridLayout(6, 2, 5, 5));
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mainPanel.add(memberIdLabel);
		mainPanel.add(memberIdField);
		mainPanel.add(bookIsbnLabel);
		mainPanel.add(bookIsbnField);
		mainPanel.add(dueYearLabel);
		mainPanel.add(dueYearField);
		mainPanel.add(dueMonthLabel);
		mainPanel.add(dueMonthField);
		mainPanel.add(dueDayLabel);
		mainPanel.add(dueDayField);
		mainPanel.add(new JLabel()); 
		mainPanel.add(submitButton);
		
		add(mainPanel);
		
		memberIdField.addActionListener(new ActionHandler());
		bookIsbnField.addActionListener(new ActionHandler());
		dueYearField.addActionListener(new ActionHandler());
		dueMonthField.addActionListener(new ActionHandler());
		dueDayField.addActionListener(new ActionHandler());
		submitButton.addActionListener(new ActionHandler());
	}
	
	public static void init() {
		CheckOutBookFrame frame = new CheckOutBookFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(300, 250);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (Book.getBookArraySize() == 0) {
					Popup.init(CheckOutBookFrame.this, Popup.EMPTY_DATABASE);
					dispose();
				} else {
					RegularMember theMember = RegularMember.getMemberWithTheID(Long.parseLong(memberIdField.getText()));
					if (theMember == null) {
						Popup.init(CheckOutBookFrame.this, Popup.USER_NOT_FOUND);
					} else {
						if (theMember.hasReachedBookLimit()) {
							Popup.init(CheckOutBookFrame.this, Popup.USER_AT_LIMIT);
							dispose();
						} else {
							Book theBook = Book.getBookWithTheISBN(bookIsbnField.getText());
							if (theBook == null) {
								Popup.init(CheckOutBookFrame.this, Popup.BOOK_NOT_FOUND);
							} else {
								Date.dateValidator(Integer.parseInt(dueDayField.getText()), Integer.parseInt(dueMonthField.getText()), Integer.parseInt(dueYearField.getText()));
								Date dueDate = new Date(Integer.parseInt(dueDayField.getText()), Integer.parseInt(dueMonthField.getText()), Integer.parseInt(dueYearField.getText()));
								theBook = new Book(theBook.getBookName(), theBook.getBookISBN(), dueDate, theBook.getPrice());
								theMember.appendToCheckedOutBooks(theBook);
								Book.removeFromBookArray(theBook);
								Popup.init(CheckOutBookFrame.this, Popup.CHECK_OUT_SUCCESS, theBook.getBookName(), theBook.getBookISBN(), theMember.getMemberName());
								dispose();
							}
						}
						
					}
				}
			} catch (Exception e1) {
				Popup.init(CheckOutBookFrame.this, Popup.INVALID_INPUT);
			}
		}
	}
}