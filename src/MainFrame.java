import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class MainFrame extends JFrame {

	private JList<Menu.SUBMENU> submenuList;
	
	public MainFrame() {
		super("LibMan");
		
		submenuList = new JList<>(Menu.SUBMENU.values());
		submenuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
	}
	
	
}