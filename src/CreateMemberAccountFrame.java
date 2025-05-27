import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateMemberAccountFrame extends JFrame {

	private JLabel memberTypeLabel, nameLabel, idLabel;
	private JComboBox<Menu.ACCOUNT_TYPES> memberTypeComboBox;
	private JTextField nameField, idField;
	private JButton submitButton;
	
	private JPanel mainPanel;
	
	public CreateMemberAccountFrame() {
		super(Menu.SUBMENU.CREATE_MEMBER_ACCOUNT.toString());
		
		memberTypeLabel = new JLabel("Select Member Type:");
		memberTypeComboBox = new JComboBox<>(Menu.ACCOUNT_TYPES.values());
		memberTypeComboBox.setSelectedIndex(0);
		
		nameLabel = new JLabel("Name:");
		nameField = new JTextField();
		
		idLabel = new JLabel("ID:");
		idField = new JTextField();
		
		submitButton = new JButton("Submit");
		
		mainPanel = new JPanel(new GridLayout(4, 2, 5, 5));
		
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		mainPanel.add(memberTypeLabel);
		mainPanel.add(memberTypeComboBox);
		mainPanel.add(nameLabel);
		mainPanel.add(nameField);
		mainPanel.add(idLabel);
		mainPanel.add(idField);
		mainPanel.add(new JPanel());
		mainPanel.add(submitButton);
		
		add(mainPanel);
		
		submitButton.addActionListener(new ActionHandler());
		
	}
	
	public static void init() {
		CreateMemberAccountFrame frame = new CreateMemberAccountFrame();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.setSize(300, 180);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (RegularMember.isIdAvailable(Long.parseLong(idField.getText()))) {
				Menu.ACCOUNT_TYPES type = (Menu.ACCOUNT_TYPES) memberTypeComboBox.getSelectedItem();
				RegularMember theMember = null;
				switch (type) {
				case REGULAR:
					theMember = new RegularMember(nameField.getText(), Long.parseLong(idField.getText()));
					break;
				case STUDENT:
					theMember = new Student(nameField.getText(), Long.parseLong(idField.getText()));
					break;
				case ACADEMIC:
					theMember = new Academic(nameField.getText(), Long.parseLong(idField.getText()));
					break;
				}
				Popup.init(CreateMemberAccountFrame.this, Popup.MEMBER_CREATE_SUCCESS, type.toString(), nameField.getText(), idField.getText());
			} else {
				Popup.init(CreateMemberAccountFrame.this, Popup.ID_NOT_AVAILABLE);
			}
		}
		
	}
	
}