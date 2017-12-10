/**
 * Project: DataMiner
 * File: ContactList.java
 * Date: Nov 28, 2017
 * Time: 9:40:17 PM
 */
package main.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.data.Contact;
import main.data.ContactManager;
import main.io.ContactWriter;

/**
 * @author Jason Keum (QCFB QuanchiFootball)
 *
 */
@SuppressWarnings("serial")
public class ContactList extends JTable {

	/**
	 * Create the panel.
	 */
	public ContactList(ContactManager manager) {
		ArrayList<Contact> contacts = manager.getContacts();
		Object[][] data = ContactWriter.dataPrepper(contacts);
		setModel(new DefaultTableModel(data, ContactWriter.headers) {
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
		});
		setRowSelectionAllowed(true);
		setColumnSelectionAllowed(false);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					ContactDialog cdl = new ContactDialog(manager, getSelectedRow());
					cdl.setVisible(true);
				}
			}
		});
	}

}
