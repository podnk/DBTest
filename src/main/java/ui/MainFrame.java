package ui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import parser.OpenParser;
import parser.SaveParser;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(MainFrame.class);
	
	private static File openedFile;
	private static File savedFile;
	
	JButton saveTableToXmlButton;
	JButton syncTableFromXmlButton;
	JButton showLogsButton;
	
	JFileChooser fileChooser = new JFileChooser();
	Reader r = new Reader();
	
	public MainFrame()
	{
		initFrame();
		log.info("MainFrame initialized");
	}
	
	public void initFrame()
	{
		syncTableFromXmlButton = new JButton("Open file");
		saveTableToXmlButton = new JButton("Save table");
		showLogsButton = new JButton("logs");
		
		add(saveTableToXmlButton);
		add(showLogsButton);
		add(syncTableFromXmlButton);
		
		saveTableToXmlButton.addActionListener(r);
		syncTableFromXmlButton.addActionListener(r);
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
	
	public static File getOpenedFile()
	{
		log.info("File has opened");
		return openedFile;
	}
	
	public static File getSavedFile()
	{
		log.info("File has saved");
		return savedFile;
	}
	
	public class Reader implements ActionListener
	{
		Desktop desktop = null;
		
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if(e.getSource() == syncTableFromXmlButton)
				{
					// synchronizing table from xml file using DOM Parser
					int optionCode = fileChooser.showOpenDialog(null);
					
					if(optionCode == 0/*JFileChooser.APPROVE_OPTION*/)
					{
						openedFile = fileChooser.getSelectedFile();
						
						if(openedFile.exists() && openedFile != null)
						{
							try
							{
								new OpenParser(openedFile);
							}
							catch (Exception e2)
							{
								e2.printStackTrace();
								log.error(e2);
							}
						}
						else
						{
							JOptionPane.showMessageDialog(fileChooser, 
									"some error with opening file");
						}
						System.out.println(openedFile.getName());
					}
				}
				else if (e.getSource() == saveTableToXmlButton) 
				{
					// saving table to xml file using DOM Parser
					int optionCode = fileChooser.showSaveDialog(null);
					
					if(optionCode == 0/*JFileChooser.APPROVE_OPTION*/)
					{
						savedFile = fileChooser.getSelectedFile();
						
						// checking extension
						if (FilenameUtils.getExtension(savedFile.getName()).equalsIgnoreCase("xml")) 
						{
							// if xml - filename is OK
							String fileName = fileChooser.getName(savedFile);
							
							new SaveParser(fileName);
						} 
						else 
						{
							// making xml from anything, or deleting double extension
							savedFile = new File(savedFile.toString() + ".xml");
							savedFile = new File(savedFile.getParentFile(), FilenameUtils.getBaseName(savedFile.getName())+".xml");
							
							String fileName = fileChooser.getName(savedFile);
							
							new SaveParser(fileName);
						}
						log.info("File saved to: " + savedFile);
					}
				}
				else if(e.getSource() == showLogsButton)
				{
					// opening log folder
					File logFolder = new File(System.getenv("appdata")+"\\DB_test");
					
					if (Desktop.isDesktopSupported())
					{
						desktop = Desktop.getDesktop();
					}
					
					try
					{
						desktop.open(logFolder);
					}
					catch (Exception ex2)
					{
						JOptionPane.showMessageDialog(null, "Something wrong, check logs manually:\n%appdata%\\DB_test");
						log.error(ex2);
					}
					log.info("Logs folder has opened");
				}
			}
			catch (Exception ex) 
			{
				log.error(ex);
			}
		}
	}
}
