/**
 * Project: DataMiner
 * File: Contact.java
 * Date: Nov 27, 2017
 * Time: 10:37:47 PM
 */
package main.data;

/**
 * @author Jason Keum (QCFB QuanchiFootball)
 *
 */
public class Contact {

	public static final int ATTRIBUTE_COUNT = 12;

	private String salutation;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String company;
	private String workEmail;
	private String companyEmail;
	private String workPhone;
	private String website;
	private String city;
	private String province;
	private String country;

	public static class Builder {
		private String salutation;
		private String firstName;
		private String lastName;
		private String jobTitle;
		private String company;
		private String workEmail;
		private String companyEmail;
		private String workPhone;
		private String website;
		private String city;
		private String province;
		private String country;

		public Builder() {

		}

		public Builder setSalutation(String salutation) {
			this.salutation = salutation;
			return this;
		}

		public Builder setFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder setLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
			return this;
		}

		public Builder setCompany(String company) {
			this.company = company;
			return this;
		}

		public Builder setWorkEmail(String workEmail) {
			this.workEmail = workEmail;
			return this;
		}

		public Builder setCompanyEmail(String companyEmail) {
			this.companyEmail = companyEmail;
			return this;
		}

		public Builder setWorkPhone(String workPhone) {
			this.workPhone = workPhone;
			return this;
		}

		public Builder setWebsite(String website) {
			this.website = website;
			return this;
		}

		public Builder setCity(String city) {
			this.city = city;
			return this;
		}

		public Builder setProvince(String province) {
			this.province = province;
			return this;
		}

		public Builder setCountry(String country) {
			this.country = country;
			return this;
		}

		public Contact build() {
			return new Contact(this);
		}
	}

	private Contact(Builder builder) {
		salutation = builder.salutation;
		firstName = builder.firstName;
		lastName = builder.lastName;
		jobTitle = builder.jobTitle;
		company = builder.company;
		workEmail = builder.workEmail;
		companyEmail = builder.companyEmail;
		workPhone = builder.workPhone;
		website = builder.website;
		city = builder.city;
		province = builder.province;
		country = builder.country;

	}

	/**
	 * @return the attributeCount
	 */
	public static int getAttributeCount() {
		return ATTRIBUTE_COUNT;
	}

	/**
	 * @return the salutation
	 */
	public String getSalutation() {
		return salutation;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @return the workEmail
	 */
	public String getWorkEmail() {
		return workEmail;
	}

	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}

	/**
	 * @return the workPhone
	 */
	public String getWorkPhone() {
		return workPhone;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param salutation
	 *            the salutation to set
	 */
	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param jobTitle
	 *            the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @param company
	 *            the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @param workEmail
	 *            the workEmail to set
	 */
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}

	/**
	 * @param companyEmail
	 *            the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	/**
	 * @param workPhone
	 *            the workPhone to set
	 */
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	/**
	 * @param website
	 *            the website to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @param province
	 *            the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Contact [salutation=" + salutation + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", jobTitle=" + jobTitle + ", company=" + company + ", workEmail=" + workEmail + ", companyEmail="
				+ companyEmail + ", workPhone=" + workPhone + ", website=" + website + ", city=" + city + ", province="
				+ province + ", country=" + country + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equal = true;
		if (obj == null || !obj.getClass().equals(this.getClass())) {
			equal = false;
		}
		Contact contact = (Contact) obj;
		if (!this.toString().equals(contact.toString())) {
			equal = false;
		}
		return equal;
	}
}
