import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNewOnlineArticleFrame extends JFrame {
	
	private JLabel articleNameLabel, DOILabel, publisherLabel;
	private JTextField articleNameField, DOIField, publisherfField;
	private JButton submitButton;
	
	private JPanel mainPanel;
	
	public AddNewOnlineArticleFrame() {
		super(Menu.SUBMENU.ADD_NEW_ONLINE_ARTICLE.toString());
		
		articleNameLabel = new JLabel("Article Name:");
		articleNameField = new JTextField();
		
		DOILabel = new JLabel("DOI:");
		DOIField = new JTextField();
		
		publisherLabel = new JLabel("Publisher:");
		publisherfField = new JTextField();
		
		submitButton = new JButton("Submit");
		
		mainPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		
		mainPanel.add(articleNameLabel);
		mainPanel.add(articleNameField);
		mainPanel.add(DOILabel);
		mainPanel.add(DOIField);
		mainPanel.add(publisherLabel);
		mainPanel.add(publisherfField);
		mainPanel.add(new JPanel());
		mainPanel.add(submitButton);
		
		add(mainPanel);
		
		articleNameField.addActionListener(new ActionHandler());
		DOIField.addActionListener(new ActionHandler());
		publisherfField.addActionListener(new ActionHandler());
		submitButton.addActionListener(new ActionHandler());

	}

	public static void init() {
		AddNewOnlineArticleFrame frame = new AddNewOnlineArticleFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(300, 180);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (articleNameField.getText().equals("") || DOIField.getText().equals("") || publisherfField.getText().equals("") ) {
				Popup.init(AddNewOnlineArticleFrame.this, Popup.INVALID_INPUT);
			}
			else if (!OnlineArticle.isDOIValid(DOIField.getText())) {
				Popup.init(AddNewOnlineArticleFrame.this, Popup.INVALID_DOI);
			} else {
				OnlineArticle theArticle = new OnlineArticle(articleNameField.getText(), DOIField.getText(), publisherfField.getText());
				OnlineArticle.addToArticleArray(theArticle);
				Popup.init(AddNewOnlineArticleFrame.this, Popup.ARTICLE_ADD_SUCCESS);
				dispose();
			}
		}
		
	}
	
}