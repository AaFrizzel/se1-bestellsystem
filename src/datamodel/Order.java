package datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	private final long id;
	private final Date date;
	private final Customer Customer;
	private List<OrderItem> items;

	protected Order(long id, Date date, Customer Customer) {
		this.id = id;
		this.date = date;
		this.Customer = Customer;
		this.items = new ArrayList<OrderItem>();
	}

	public long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public Customer getCustomer() {
		return Customer;
	}

	public Iterable<OrderItem> getItems() {
		return items;
	}

	public int count() {
		if (items.isEmpty()) {
			return 0;
		} else {
			return items.size();

		}
	}

	public Order addItem(OrderItem item) {
		items.add(item);
		return this;
	}

	public Order removeItem(OrderItem item) {
		items.remove(item);
		return this;
	}

	public Order clearItems() {
		items.clear();
		return this;
	}

}
