package com.bankguru.builder;

public class NewCustomerBuilder {
	public static class Customer {

		private String customerName;
		private String dateOfBirth;
		private String address;
		private String city;
		private String state;
		private String pin;
		private String mobileNumber;
		private String email;

		private String password;

		public Customer withCustomerName(String customerName) {
			this.customerName = customerName;
			return this;
		}

		public Customer withDateOfBirth(String dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			return this;
		}

		public Customer withAddress(String address) {
			this.address = address;
			return this;
		}

		public Customer withCity(String city) {
			this.city = city;
			return this;
		}

		public Customer withState(String state) {
			this.state = state;
			return this;
		}

		public Customer withPin(String pin) {
			this.pin = pin;
			return this;
		}

		public Customer withMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
			return this;
		}

		public Customer withEmail(String email) {
			this.email = email;
			return this;
		}

		public Customer withPassword(String password) {
			this.password = password;
			return this;
		}

		public CustomerAccount build() {
			return new CustomerAccount(customerName, dateOfBirth, address, city, state, pin, mobileNumber, email,
					password);
		}

		// Declare all customer infor to provide Customer class

		public class CustomerAccount {
			private String customerName;
			private String dateOfBirth;
			private String address;
			private String city;
			private String state;
			private String pin;
			private String mobileNumber;
			private String password;
			private String email;

			public String getCustomerName() {
				return customerName;
			}

			public String getDateOfBirth() {
				return dateOfBirth;
			}

			public String getAddress() {
				return address;
			}

			public String getCity() {
				return city;
			}

			public String getState() {
				return state;
			}

			public String getPin() {
				return pin;
			}

			public String getMobileNumber() {
				return mobileNumber;
			}

			public String getPassword() {
				return password;
			}

			public String getEmail() {
				return email;
			}

			public CustomerAccount(String customerName, String dateOfBirth, String address, String city, String state,
					String pin, String mobileNumber, String email, String password) {
				this.customerName = customerName;
				this.dateOfBirth = dateOfBirth;
				this.address = address;
				this.city = city;
				this.state = state;
				this.pin = pin;
				this.mobileNumber = mobileNumber;
				this.email = email;
				this.password = password;
			}

		}

	}
}
