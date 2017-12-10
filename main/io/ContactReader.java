/**
 * Project: DataMiner
 * File: ContactReader.java
 * Date: Nov 27, 2017
 * Time: 11:21:30 PM
 */
package main.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import main.data.Contact;

/**
 * @author Jason Keum (QCFB QuanchiFootball)
 *
 */
public class ContactReader {
	/**
	 * private constructor to prevent instantiation
	 */
	private ContactReader() {
	}

	public static ArrayList<Contact> readFile(String source) throws IOException {
		ArrayList<Contact> contacts = new ArrayList<>();
		FileInputStream excelFile = new FileInputStream(new File(source));
		Workbook workbook = new XSSFWorkbook(excelFile);
		Sheet datatypeSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = datatypeSheet.iterator();
		iterator.next();
		while (iterator.hasNext()) {
			Row currentRow = iterator.next();
			Contact contact = readRow(currentRow);
			contacts.add(contact);
		}
		excelFile.close();
		workbook.close();
		return contacts;
	}

	public static Contact readRow(Row row) {
		int index = 0;
		String salutation = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String firstName = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String lastName = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String jobTitle = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String company = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String workEmail = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String companyEmail = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String workPhone = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String website = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String city = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String province = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		String country = row.getCell(index++, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
		Contact contact = new Contact.Builder().setSalutation(salutation).setFirstName(firstName).setLastName(lastName)
				.setJobTitle(jobTitle).setCompany(company).setWorkEmail(workEmail).setCompanyEmail(companyEmail)
				.setWorkPhone(workPhone).setWebsite(website).setCity(city).setProvince(province).setCountry(country)
				.build();
		return contact;
	}
}
