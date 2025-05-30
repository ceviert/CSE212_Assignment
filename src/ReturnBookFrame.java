import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReturnBookFrame extends JFrame {

	private JLabel memberIdLabel, isbnLabel;
	private JTextField memberIdField, isbnField;
	private JButton submitButton;
	
	private JPanel mainPanel;
	
	public ReturnBookFrame() {
		super(Menu.SUBMENU.RETURN_BOOK.toString());
		
		memberIdLabel = new JLabel("Member ID:");
		memberIdField = new JTextField();
		
		isbnLabel = new JLabel("ISBM:");
		isbnField = new JTextField();
		
		submitButton = new JButton("Submit");
		
		mainPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mainPanel.add(memberIdLabel);
		mainPanel.add(memberIdField);
		mainPanel.add(isbnLabel);
		mainPanel.add(isbnField);
		mainPanel.add(new JPanel());
		mainPanel.add(submitButton);
		
		add(mainPanel);
		
		memberIdField.addActionListener(new ActionHandler());
		isbnField.addActionListener(new ActionHandler());
		submitButton.addActionListener(new ActionHandler());
	}
	
	public static void init() {
		ReturnBookFrame frame = new ReturnBookFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(300, 140);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			RegularMember theMember = RegularMember.getMemberWithTheID(Long.parseLong(memberIdField.getText()));
			if (theMember == null) {
				Popup.init(ReturnBookFrame.this, Popup.USER_NOT_FOUND);
			} else {
				if (theMember.getCheckedOutBooksSize() == 0) {
					Popup.init(ReturnBookFrame.this, Popup.DONT_HAVE_THE_BOOK);
				} else {
					if (!theMember.returnBook(isbnField.getText())) {
						Popup.init(ReturnBookFrame.this, Popup.BOOK_NOT_FOUND);
					} else {
						Book theBook = Book.getBookWithTheISBN(isbnField.getText());
						Popup.init(ReturnBookFrame.this, Popup.RETURN_SUCCESS, theBook.getBookName(), theBook.getBookISBN(), theMember.getMemberName());
						dispose();
					}
				}
			}
		}
		
	}
	
}