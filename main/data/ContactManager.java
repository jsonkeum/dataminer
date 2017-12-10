/**
 * Project: DataMiner
 * File: ContactManager.java
 * Date: Nov 29, 2017
 * Time: 12:22:38 AM
 */
package main.data;

import java.io.IOException;
import java.util.ArrayList;

import main.io.ContactReader;
import main.io.ContactWriter;

/**
 * @author Jason Keum (QCFB QuanchiFootball)
 *
 */
public class ContactManager {
	private ArrayList<Contact> contacts;
	private String path;

	public ContactManager(String path) throws IOException {
		this.path = path;
		this.contacts = ContactReader.readFile(path);
	}

	public ArrayList<Contact> getContacts() {
		return this.contacts;
	}

	public void updateContact(Contact contact, int index) {
		this.contacts.set(index, contact);
		System.out.println("Updated contact: " + index);
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}

	/**
	 * Overloaded save method 1. Saves to the existing path directory. Regular
	 * save.
	 * 
	 * @throws IOException
	 */
	public void exportContacts() throws IOException {
		if (this.path != null) {
			System.out.println(String.format("Saving to original file: %s", this.path));
			ContactWriter.writeFile(this.contacts, path);
		}
	}

	/**
	 * Overloaded save method 2. Saves to the new directory. Save As.
	 * 
	 * @param path
	 *            new directory path
	 * @throws IOException
	 */
	public void exportContacts(String path) throws IOException {
		this.path = path;
		System.out.println(String.format("Saving to new file: %s", path));
		ContactWriter.writeFile(this.contacts, path);
	}

	public int getSize() {
		return this.contacts.size();
	}
}
