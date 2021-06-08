package datamodel;

public class Article {
	private final String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;

	protected Article(String id, String description, long price, int units) {
		this.id = id;
		this.description = description;
		this.unitPrice = price;
		this.unitsInStore = units;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String des) {
		this.description = des;
	}

	public long getUnitPrice() {
		return unitPrice;
	}

	public int getUnitsInStore() {
		return unitsInStore;
	}

	public void setUnitsInStore(int number) {
		this.unitsInStore = number;
	}

}
