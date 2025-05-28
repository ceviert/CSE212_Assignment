import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewBookFrame extends JFrame{

	private JLabel bookNameLabel, ISBNLabel, priceLabel;
	private JTextField bookNameField, ISBNField, priceField;
	private JButton submitButton;
	
	private JPanel mainPanel;
	
	public AddNewBookFrame() {
		super(Menu.SUBMENU.ADD_NEW_BOOK.toString());
		
		bookNameLabel = new JLabel("Book Name:");
		bookNameField = new JTextField();
		
		ISBNLabel = new JLabel("ISBN:");
		ISBNField = new JTextField();
		
		priceLabel = new JLabel("Price:");
		priceField = new JTextField();
		
		submitButton = new JButton("Submit");
		
		mainPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		
		mainPanel.add(bookNameLabel);
		mainPanel.add(bookNameField);
		mainPanel.add(ISBNLabel);
		mainPanel.add(ISBNField);
		mainPanel.add(priceLabel);
		mainPanel.add(priceField);
		mainPanel.add(new JPanel());
		mainPanel.add(submitButton);
		
		add(mainPanel);
		
		bookNameField.addActionListener(new ActionHandler());
		ISBNField.addActionListener(new ActionHandler());
		priceField.addActionListener(new ActionHandler());
		submitButton.addActionListener(new ActionHandler());
	}
	
	public static void init() {
		AddNewBookFrame frame = new AddNewBookFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(300, 180);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Book.authenticateISBN(ISBNField.getText());
				Book theBook = new Book(bookNameField.getText(), ISBNField.getText(), Integer.parseInt(priceField.getText()));
				Book.appendToBookArray(theBook);
				Popup.init(AddNewBookFrame.this, Popup.BOOK_ADD_SUCCESS);
				dispose();
			} catch (ISBNMismatchException e1) {
				Popup.init(AddNewBookFrame.this, Popup.ISBN_MISMATCH);
			}  catch (NumberFormatException e1) {
				Popup.init(AddNewBookFrame.this, Popup.INVALID_INPUT);
			}
		}
		
	}
	
}