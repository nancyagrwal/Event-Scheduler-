public class RequestDto {
	
	private String firstName;
	private String lastName;
	private String email;
	private String country;
	private String[] availableDates;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setAvailableDates(String[] availableDates) {
		this.availableDates = availableDates;
	}
	public String[] getAvailableDates() {
		return availableDates;
	}
}
