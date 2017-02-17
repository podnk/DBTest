package ui;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.apache.commons.io.FilenameUtils;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	private static File openedFile;
	private static File savedFile;
	
	JButton loadFileButton;
	JButton synchronizeFileButton;
	JButton showLogsButton;
	
	JFileChooser fileChooser = new JFileChooser();
	Reader r = new Reader();
	
	public MainFrame()
	{
		loadFileButton = new JButton("Load table from file");
		synchronizeFileButton = new JButton("synch");
		showLogsButton = new JButton("logs");
		
		add(loadFileButton);
		add(showLogsButton);
		add(synchronizeFileButton);
		
		loadFileButton.addActionListener(r);
		synchronizeFileButton.addActionListener(r);
		showLogsButton.addActionListener(r);
		
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);
		setLayout(new GridBagLayout());
		setVisible(true);
	}
	
	public class Reader implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if(e.getSource() == loadFileButton)
				{
					// opening file
					int ret = fileChooser.showOpenDialog(null);
					if(ret == JFileChooser.APPROVE_OPTION)
					{
						openedFile = fileChooser.getSelectedFile();
						System.out.println(openedFile.getName());
					}
				}
				else if (e.getSource() == synchronizeFileButton) 
				{
					// saving file
					int ret = fileChooser.showSaveDialog(null);
					if(ret == JFileChooser.APPROVE_OPTION)
					{
						savedFile = fileChooser.getSelectedFile();
						
						// checking extension
						if (FilenameUtils.getExtension(savedFile.getName()).
								equalsIgnoreCase("xml")) 
						{
						    // if xml - filename is OK
						} 
						else 
						{
							// making xml from anything, or deleting double extension
							savedFile = new File(savedFile.toString() + ".xml");
							savedFile = new File(savedFile.getParentFile(), 
									FilenameUtils.getBaseName(savedFile.getName())+".xml");
						}
						System.out.println(savedFile);
					}
				}
				else if(e.getSource() == showLogsButton)
				{
					// opening log file
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
	}
	
	public static File getOpenedFile()
	{
		return openedFile;
	}
	
	public static File getSavedFile()
	{
		return savedFile;
	}
}
