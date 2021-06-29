package datamodel;

public class OrderItem {
	private String description;
	private final Article article;
	private int unitsOrdered;

	protected OrderItem(String description, Article article, int unitsOrdered) {
		this.description = description;
		this.article = article;
		this.unitsOrdered = unitsOrdered;

	}

	public String getDescription() {
		if(description == null)
			this.description = "";
		return description;
	}

	public void setDescription(String des) {
		this.description = des;
	}

	public Article getArticle() {
		return article;
	}

	public int getUnitsOrdered() {
		if(unitsOrdered < 0)
			this.unitsOrdered = 0;
		return unitsOrdered;
	}

	public void setUnitsOrdered(int number) {
		this.unitsOrdered = number;
	}
}
