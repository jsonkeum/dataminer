/**
 * Project: DataMiner
 * File: ContactDialog.java
 * Date: Nov 28, 2017
 * Time: 11:45:50 PM
 */
package main.ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.data.Contact;
import main.data.ContactManager;
import net.miginfocom.swing.MigLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;

/**
 * @author Jason Keum (QCFB QuanchiFootball)
 *
 */
@SuppressWarnings("serial")
public class ContactDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int index;
	private ContactManager manager;
	private JTextField indexField;
	private JTextField salutation;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField jobTitle;
	private JTextField company;
	private JComboBox<String> workEmail;
	private JTextField companyEmail;
	private JTextField workPhone;
	private JTextField website;
	private JTextField city;
	private JTextField province;
	private JTextField country;
	private JTextField extension;

	/**
	 * Create the dialog.
	 */
	public ContactDialog(ContactManager manager, int index) {
		setMinimumSize(new Dimension(340, 435));
		this.index = index;
		this.manager = manager;

		setModal(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 330, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.NORTH);
		contentPanel.setLayout(new MigLayout("", "[91.00px,right][533px,grow]",
				"[20:n][20px:n][20:n][20:n][20:n][20:n][20:n][20px:n][20:n][20:n][20px:n][20:n][20:n][]"));

		JLabel lblIndex = new JLabel("Index");
		contentPanel.add(lblIndex, "cell 0 0,alignx trailing");

		indexField = new JTextField();
		indexField.setEditable(false);

		contentPanel.add(indexField, "cell 1 0,growx");
		indexField.setColumns(10);

		JLabel lblSal = new JLabel("Sal.");
		contentPanel.add(lblSal, "cell 0 1,alignx trailing");

		salutation = new JTextField();

		contentPanel.add(salutation, "cell 1 1,growx");
		salutation.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name");
		contentPanel.add(lblFirstName, "cell 0 2,alignx trailing");

		firstName = new JTextField();

		contentPanel.add(firstName, "cell 1 2,growx");
		firstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		contentPanel.add(lblLastName, "cell 0 3,alignx trailing");

		lastName = new JTextField();

		contentPanel.add(lastName, "cell 1 3,growx");
		lastName.setColumns(10);

		JLabel lblTitle = new JLabel("Title");
		contentPanel.add(lblTitle, "cell 0 4,alignx trailing");

		jobTitle = new JTextField();

		contentPanel.add(jobTitle, "cell 1 4,growx");
		jobTitle.setColumns(10);

		JLabel lblCompany = new JLabel("Company");
		contentPanel.add(lblCompany, "cell 0 5,alignx trailing");

		company = new JTextField();

		contentPanel.add(company, "cell 1 5,growx");
		company.setColumns(10);

		JLabel lblWorkEmail = new JLabel("Work Email");
		contentPanel.add(lblWorkEmail, "cell 0 6,alignx trailing");

		workEmail = new JComboBox<String>();
		workEmail.setEditable(true);
		contentPanel.add(workEmail, "cell 1 6,growx");

		JLabel lblCompanyEmail = new JLabel("Company Email");
		contentPanel.add(lblCompanyEmail, "cell 0 7,alignx trailing");

		companyEmail = new JTextField();

		contentPanel.add(companyEmail, "cell 1 7,growx");
		companyEmail.setColumns(10);

		JLabel lblWorkPhone = new JLabel("Work Phone");
		contentPanel.add(lblWorkPhone, "cell 0 8,alignx trailing");

		workPhone = new JTextField();

		contentPanel.add(workPhone, "cell 1 8,growx");
		workPhone.setColumns(10);

		JLabel lblWebsite = new JLabel("Website");
		contentPanel.add(lblWebsite, "cell 0 9,alignx trailing");

		website = new JTextField();

		contentPanel.add(website, "cell 1 9,growx");
		website.setColumns(10);

		JLabel lblCity = new JLabel("City");
		contentPanel.add(lblCity, "cell 0 10,alignx trailing");

		city = new JTextField();

		contentPanel.add(city, "cell 1 10,growx");
		city.setColumns(10);

		JLabel lblProvincest = new JLabel("Province/St");
		contentPanel.add(lblProvincest, "cell 0 11,alignx trailing");

		province = new JTextField();

		contentPanel.add(province, "cell 1 11,growx");
		province.setColumns(10);

		JLabel lblCountry = new JLabel("Country");
		contentPanel.add(lblCountry, "cell 0 12,alignx trailing");

		country = new JTextField();

		contentPanel.add(country, "cell 1 12,growx");
		country.setColumns(10);

		JButton btnExtension = new JButton("Extension");
		btnExtension.addActionListener(e -> searchExt());
		contentPanel.add(btnExtension, "cell 0 13");

		extension = new JTextField();
		contentPanel.add(extension, "cell 1 13,growx");
		extension.setColumns(10);
		extension.addActionListener(e -> populateEmailBox());
		setTexts();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton previous = new JButton("Previous");
				previous.setActionCommand("Previous");
				previous.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							previous();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				buttonPane.add(previous);
			}
			{
				JButton close = new JButton("Close");
				close.setActionCommand("Close");
				close.addActionListener(e -> dispose());
				buttonPane.add(close);
			}
			{
				JButton close = new JButton("Search");
				close.setActionCommand("Close");
				close.addActionListener(e -> search());
				buttonPane.add(close);
			}
			{
				JButton next = new JButton("Next");
				next.setActionCommand("Next");
				next.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							next();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				buttonPane.add(next);
			}
		}
	}

	private Contact readFromText() {
		Contact contact = new Contact.Builder().setSalutation(salutation.getText()).setFirstName(firstName.getText())
				.setLastName(lastName.getText()).setJobTitle(jobTitle.getText()).setCompany(company.getText())
				.setWorkEmail((String) workEmail.getSelectedItem()).setCompanyEmail(companyEmail.getText())
				.setWorkPhone(workPhone.getText()).setWebsite(website.getText()).setCity(city.getText())
				.setProvince(province.getText()).setCountry(country.getText()).build();
		return contact;
	}

	private void previous() throws IOException {
		Contact newContact = readFromText();
		if (!newContact.toString().equals(this.manager.getContacts().get(this.index).toString())) {
			System.out.println("Updating contact");
			manager.updateContact(newContact, index);
			manager.exportContacts();
		}
		index--;
		System.out.println(index);
		if (index >= 0) {
			workEmail.removeAllItems();
			setTexts();
			revalidate();
			repaint();
		} else {
			index++;
			JOptionPane.showMessageDialog(null, "You've reached the end of the list", "End",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void next() throws IOException {
		Contact newContact = readFromText();
		if (!newContact.toString().equals(this.manager.getContacts().get(this.index).toString())) {
			System.out.println("Updating contact");
			manager.updateContact(newContact, index);
			manager.exportContacts();
		}
		index++;
		System.out.println(index);
		if (index < manager.getSize()) {
			workEmail.removeAllItems();
			setTexts();
			revalidate();
			repaint();
		} else {
			index--;
			JOptionPane.showMessageDialog(null, "You've reached the end of the list", "End",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	private void search() {
		String urlMain = String.format("http://www.google.com/search?q=%s+%s+%s+%s+email", firstName.getText().trim().replaceAll(" ", "+"),
				lastName.getText().trim().replaceAll(" ", "+"), jobTitle.getText().trim().replaceAll(" ", "+"),
				company.getText().trim().replaceAll(" ", "+"));
		String urlCompany = String.format("http://www.google.com/search?q=%s+email",
				company.getText().trim().replaceAll(" ", "+"));
		try {
			Desktop.getDesktop().browse(URI.create(urlCompany));
			Desktop.getDesktop().browse(URI.create(urlMain));
		} catch (IllegalArgumentException e2) {
			JOptionPane.showMessageDialog(null, "Trim the white spaces at the end of company, job title, first name and last name and try again.", "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void searchExt() {
		if (!extension.getText().isEmpty()) {
			String urlExtension = String.format("http://www.google.com/search?q=%s+email", extension.getText().trim());
			try {
				Desktop.getDesktop().browse(URI.create(urlExtension));
			} catch (IllegalArgumentException e2) {
				JOptionPane.showMessageDialog(null, "Trim the white spaces at the end of company and try again.", "Error", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		populateEmailBox();
	}

	private void populateEmailBox() {
		if (!firstName.getText().isEmpty() && !lastName.getText().isEmpty() && !extension.getText().isEmpty()) {
			String saved = workEmail.getSelectedItem().toString();
			workEmail.removeAllItems();
			workEmail.addItem(saved);
			workEmail.addItem(String.format("%s.%s%s", firstName.getText().trim().toLowerCase(),
					lastName.getText().trim().toLowerCase(), extension.getText().trim().toLowerCase()));
			workEmail.addItem(String.format("%s%s%s", firstName.getText().trim().toLowerCase(),
					lastName.getText().trim().toLowerCase(), extension.getText().trim().toLowerCase()));
			workEmail.addItem(String.format("%s%s%s", firstName.getText().trim().toLowerCase().charAt(0),
					lastName.getText().trim().toLowerCase(), extension.getText().trim().toLowerCase()));
			workEmail.addItem(String.format("%s%s%s", firstName.getText().trim().toLowerCase(),
					lastName.getText().trim().toLowerCase().charAt(0), extension.getText().trim().toLowerCase()));
			workEmail.addItem(String.format("%s.%s%s", firstName.getText().trim().toLowerCase().charAt(0),
					lastName.getText().trim().toLowerCase(), extension.getText().trim().toLowerCase()));
			workEmail.addItem(String.format("%s%s", firstName.getText().trim().toLowerCase(),
					extension.getText().trim().toLowerCase()));
			workEmail.addItem(String.format("%s%s", lastName.getText().trim().toLowerCase(),
					extension.getText().trim().toLowerCase()));
		}
	}

	private void setTexts() {
		Contact contact = this.manager.getContacts().get(this.index);
		setTitle(String.format("%s %s", contact.getFirstName(), contact.getLastName()));
		indexField.setText(String.valueOf(index));
		salutation.setText(contact.getSalutation());
		firstName.setText(contact.getFirstName().trim());
		lastName.setText(contact.getLastName().trim());
		jobTitle.setText(contact.getJobTitle().trim());
		company.setText(contact.getCompany().trim());
		workEmail.addItem(contact.getWorkEmail());
		workEmail.setSelectedItem(contact.getWorkEmail());
		companyEmail.setText(contact.getCompanyEmail());
		workPhone.setText(contact.getWorkPhone());
		website.setText(contact.getWebsite());
		city.setText(contact.getCity());
		province.setText(contact.getProvince());
		country.setText(contact.getCountry());
		if(!website.getText().isEmpty()){
			String ext = website.getText().replaceFirst("[^\\.]+\\.", "@");
			extension.setText(ext);
			populateEmailBox();
		} else {
			extension.setText("");
		}
	}

}
