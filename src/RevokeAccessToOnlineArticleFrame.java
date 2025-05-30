import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RevokeAccessToOnlineArticleFrame extends JFrame {

	private JLabel memberIdLabel, doiLabel;
	private JTextField memberIdField, doiField;
	private JButton submitButton;
	
	private JPanel mainPanel;
	
	public RevokeAccessToOnlineArticleFrame() {
		super(Menu.SUBMENU.REVOKE_ACCESS_TO_ONLINE_ARTICLE.toString());
		
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
		RevokeAccessToOnlineArticleFrame frame = new RevokeAccessToOnlineArticleFrame();
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
				Popup.init(RevokeAccessToOnlineArticleFrame.this, Popup.USER_NOT_FOUND);
			} else {
				if (theMember.getAccessedOnlineArticlesSize() == 0) {
					Popup.init(RevokeAccessToOnlineArticleFrame.this, Popup.DONT_HAVE_THE_ARTICLE);
				} else {
					if (!theMember.returnOA(doiField.getText())) {
						Popup.init(RevokeAccessToOnlineArticleFrame.this, Popup.ARTICLE_NOT_FOUND);
					} else {
						OnlineArticle theArticle = OnlineArticle.getArticleWithTheDOI(doiField.getText());
						Popup.init(RevokeAccessToOnlineArticleFrame.this, Popup.REVOKE_SUCCESS, theArticle.getArticleName(), theArticle.getArticleDOI(), theMember.getMemberName());
						dispose();
					}
				}
			}
			
		}
		
	}
	
}