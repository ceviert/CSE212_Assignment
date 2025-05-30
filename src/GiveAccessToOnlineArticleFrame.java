import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GiveAccessToOnlineArticleFrame extends JFrame {

	private JLabel memberIdLabel, doiLabel;
	private JTextField memberIdField, doiField;
	private JButton submitButton;
	
	private JPanel mainPanel;
	
	public GiveAccessToOnlineArticleFrame() {
		super(Menu.SUBMENU.GIVE_ACCESS_TO_ONLINE_ARTICLE.toString());
		
		memberIdLabel = new JLabel("Member ID:");
		memberIdField = new JTextField();
		
		doiLabel = new JLabel("DOI:");
		doiField = new JTextField();
		
		submitButton = new JButton("Submit");
		
		mainPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mainPanel.add(memberIdLabel);
		mainPanel.add(memberIdField);
		mainPanel.add(doiLabel);
		mainPanel.add(doiField);
		mainPanel.add(new JLabel());
		mainPanel.add(submitButton);
		
		add(mainPanel);
		
		memberIdField.addActionListener(new ActionHandler());
		doiField.addActionListener(new ActionHandler());
		submitButton.addActionListener(new ActionHandler());
	}
	
	public static void init() {
		GiveAccessToOnlineArticleFrame frame = new GiveAccessToOnlineArticleFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(300, 140);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (OnlineArticle.getArticleArraySize() == 0) {
					Popup.init(GiveAccessToOnlineArticleFrame.this, Popup.EMPTY_DATABASE);
					dispose();
				} else {
					RegularMember theMember = RegularMember.getMemberWithTheID(Long.parseLong(memberIdField.getText()));
					if (theMember == null) {
						Popup.init(GiveAccessToOnlineArticleFrame.this, Popup.USER_NOT_FOUND);
					} else {
						if (theMember.hasReachedArticleLimit()) {
							Popup.init(GiveAccessToOnlineArticleFrame.this, Popup.USER_AT_LIMIT);
							dispose();
						} else {
							OnlineArticle theArticle = OnlineArticle.getArticleWithTheDOI(doiField.getText());
							if (theArticle == null) {
								Popup.init(GiveAccessToOnlineArticleFrame.this, Popup.ARTICLE_NOT_FOUND);
							} else {
								theArticle.setAccessDate();
								theMember.appendToAccessedOnlineArticles(theArticle);
								OnlineArticle.removeFromArticleArray(theArticle);
								Popup.init(GiveAccessToOnlineArticleFrame.this, Popup.GIVE_ACCESS_SUCCESS, theArticle.getArticleName(), theArticle.getArticleDOI(), theMember.getMemberName());
								dispose();
							}
						}
					}
				}
			} catch (Exception e1) {
				Popup.init(GiveAccessToOnlineArticleFrame.this, Popup.INVALID_INPUT);
			}
		}
		
	}
	
}