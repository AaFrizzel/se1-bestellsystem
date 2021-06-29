package datamodel;

public class Customer {
	private final String id;
	private String firstName;
	private String lastName;
	private String contact;

	protected Customer(String id, String name, String contact) {
		this.id = id;
		this.contact = contact; 
		this.lastName = name;
		this.firstName = "";
	}

	public String getId() {
		return id;
	}

	public String getFirstName() {
		if(firstName == null)
			this.firstName = "";
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		if(lastName == null)
			this.lastName = "";
		return lastName;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public String getContact() {
		if(contact == null)
			this.contact = "";
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
}
