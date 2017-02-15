package ui;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	JFrame frame = new JFrame("Main");
	JButton loadFileButton;
	JButton saveFileButton;
	JButton synchronizeFileButton;
	JButton showLogsButton;
	
	public MainFrame()
	{
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(false);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
	}
}
