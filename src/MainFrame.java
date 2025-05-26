import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;

public class MainFrame extends JFrame {

	private JList<Menu.SUBMENU> submenuList;
	private JButton OKButton;
	private JPanel panel;
	
	public MainFrame() {
		super("LibMan");
		
		submenuList = new JList<>(Menu.SUBMENU.values());
		submenuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		OKButton = new JButton("OK");
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(submenuList, BorderLayout.CENTER);
		panel.add(OKButton, BorderLayout.SOUTH);
		
		add(panel);
		
		OKButton.addActionListener(new ActionHandler());
		
	}
	
	public static void init() {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(350, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Menu.SUBMENU selectedOption = submenuList.getSelectedValue();
			
			if (selectedOption == null) {
				Popup.init(MainFrame.this, Popup.NULL_SELECTION);
			} else {
				switch (selectedOption) {
				case ADD_NEW_BOOK:
					AddNewBookFrame.init();
					break;
				case ADD_NEW_ONLINE_ARTICLE:
					AddNewOnlineArticleFrame.init();
					break;
				}
			}
		}
		
	}
	
}