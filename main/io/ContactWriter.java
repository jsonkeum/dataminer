/**
 * Project: DataMiner
 * File: ContactWriter.java
 * Date: Nov 28, 2017
 * Time: 8:40:17 PM
 */
package main.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import main.data.Contact;

/**
 * @author Jason Keum (QCFB QuanchiFootball)
 *
 */
public class ContactWriter {
	public static final Object[] headers = { "Sal.", "First Name", "Last Name", "Job Title", "Company", "Work Email",
			"Company Work Email", "Work Phone", "Work Website", "Work City", "Work Prov/St", "Work Country" };

	/**
	 * private constructor to prevent instantiation
	 */
	private ContactWriter() {
	}

	public static void writeFile(ArrayList<Contact> contacts, String path) throws IOException {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Sheet1");
		Object[][] data = dataPrepper(contacts);
		int rowNum = 0;
		Row headerRow = sheet.createRow(rowNum++);
		int headColNum = 0;
		for (Object field : headers) {
			Cell cell = headerRow.createCell(headColNum++);
			cell.setCellValue((String) field);
		}
		for (Object[] dat : data) {
			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			for (Object field : dat) {
				Cell cell = row.createCell(colNum++);
				cell.setCellValue((String) field);
			}
		}

		FileOutputStream outputStream = new FileOutputStream(path);
		workbook.write(outputStream);
		workbook.close();
	}

	public static Object[][] dataPrepper(ArrayList<Contact> contacts) {
		Object[][] data = new Object[contacts.size()][headers.length];
		int index = 0;
		for (Contact contact : contacts) {
			Object[] cont = { contact.getSalutation(), contact.getFirstName(), contact.getLastName(),
					contact.getJobTitle(), contact.getCompany(), contact.getWorkEmail(), contact.getCompanyEmail(),
					contact.getWorkPhone(), contact.getWebsite(), contact.getCity(), contact.getProvince(),
					contact.getCountry() };
			data[index++] = cont;
		}
		return data;
	}
}
