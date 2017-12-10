/**
 * Project: DataMiner
 * File: MainFrame.java
 * Date: Nov 28, 2017
 * Time: 9:09:51 PM
 */
package main.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import main.data.ContactManager;

/**
 * @author Jason Keum (QCFB QuanchiFootball)
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private ContactManager manager;
	private File file;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Data Miner");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(false);
		setBounds(100, 100, 971, 722);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem load = new JMenuItem("Load");
		file.add(load);

		JMenuItem refresh = new JMenuItem("Refresh");
		refresh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
		file.add(refresh);

		JMenuItem saveAs = new JMenuItem("Save As");
		saveAs.setAccelerator(KeyStroke.getKeyStroke("control S"));
		file.add(saveAs);

		JMenuItem size = new JMenuItem("Count");
		file.add(size);

		JMenuItem quit = new JMenuItem("Quit");
		file.add(quit);

		JMenu help = new JMenu("Help");
		menuBar.add(help);

		JMenuItem about = new JMenuItem("About");
		help.add(about);

		JMenuItem instructions = new JMenuItem("Instructions");
		help.add(instructions);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				System.exit(0);
			}
		});

		quit.addActionListener(e -> System.exit(0));

		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Data mining interface tool for GlobalForum\nWritten by Jason Keum\nNovember 28, 2017", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		instructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"This program only accepts *.xlsx files with the following 12 fields in this order:\n\nSalutations | First Name | Last Name | Job Title | Company | Work Email | Company Email | Work Phone | Work Website | City | Province/State | Country\n\nThe first row of the excel file must be the field headers in order to ensure that all of the data is captured correctly.\nInitially contact lists will contain the names, job title, and company of the contact with the rest of the fields being filled out by the user and saved to the file.\nContact data is saved whenever the user clicks Next or Previous.\nThe 'Search' button under each individual contact card will bring up two useful Google Search results on the default browser.\nThe 'Extension' button will perform a similar function once it is filled in with the appropriate email extension (e.g. @bestbuy.com) but this field is not saved.\nOnce the user populates the extension, first name and last name fields, clicking Extension or pressing the Enter key will populate a list of commonly used email formats.\n\n",
						"Instructions", JOptionPane.PLAIN_MESSAGE);
			}
		});

		load.addActionListener(e -> load());

		refresh.addActionListener(e -> refresh());

		saveAs.addActionListener(e -> saveAs());

		size.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (manager != null) {
					String count = String.format("Contacts: %d", manager.getSize());
					JOptionPane.showMessageDialog(null, count, "List Size", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Load a file to view the contact count.", "No File",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private void saveAs() {
		final JFileChooser fc = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("*.xlsx", "xlsx");
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.file = fc.getSelectedFile();
			if (!file.getPath().endsWith(".xlsx")) {
				String newPath = this.file.getPath();
				this.file = new File(newPath + ".xlsx");
			}
			try {
				this.manager.exportContacts(this.file.getPath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			drawTable();
		}
	}

	private void load() {
		System.out.println("Loading");
		FileFilter filter = new FileNameExtensionFilter("*.xlsx", "xlsx");
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(filter);
		fc.setAcceptAllFileFilterUsed(false);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnVal = fc.showDialog(this, "Load");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				this.file = fc.getSelectedFile();
				this.manager = new ContactManager(this.file.getPath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			drawTable();
		}
	}

	private void refresh() {
		if (this.file != null) {
			System.out.println("Refreshing");
			try {
				this.manager = new ContactManager(this.file.getPath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			drawTable();
		} else {
			JOptionPane.showMessageDialog(null, "Load a file first.", "No File", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void drawTable() {
		setTitle(String.format("Data Miner - %s", this.file.getName()));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		ContactList table = new ContactList(this.manager);
		JScrollPane pane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		contentPane.add(pane);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
