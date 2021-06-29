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
		if(description == null)
			this.description = "";
		return description;
	}

	public void setDescription(String des) {
		this.description = des;
	}

	public long getUnitPrice() {
		if(unitPrice < 0)
			this.unitPrice = 0;
		return unitPrice;
	}

	public int getUnitsInStore() {
		if(unitsInStore < 0)
			this.unitsInStore = 0;
		return unitsInStore;
	}

	public void setUnitsInStore(int number) {
		this.unitsInStore = number;
	}

	public void setUnitPrice(long price) {
		// TODO Auto-generated method stub
		this.unitPrice = price;
		
	}

}
