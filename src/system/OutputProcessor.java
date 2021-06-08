package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;

final class OutputProcessor implements Components.OutputProcessor {
	
	private final int printLineWidth = 84;
	private final Components.OrderProcessor orderProcessor;
	
	public OutputProcessor(InventoryManager inventoryManager, OrderProcessor orderProcessor) {
		this.orderProcessor = orderProcessor;
		// TODO Auto-generated constructor stub
	}


	@Override
	public void printOrders(List<Order> orders, boolean printVAT) {
		
		StringBuffer sbAllOrders = new StringBuffer("-------------" + "\n");
		StringBuffer sbLineItem = new StringBuffer();
		long totalPrice = 0;

		for (Order order : orders) {
			StringBuilder orderString = new StringBuilder();// temp Var
			long orderPrice = 0;
			
			orderString.append(order.getId() + ", ");// Order ID
			orderString.append(order.getCustomer().getLastName() + ", " + order.getCustomer().getFirstName() + "' s Bestellung: ");// Customer Name
			int count = order.count();// to find out when to write ","
			int i = 1;// samesame
			long price = 0;// temp Var // OrderString: Order ID, Customer Name's Bestellung:
			for (OrderItem item : order.getItems()) {// each item of the Order
				int unitsOrdered = item.getUnitsOrdered();// number of units
				long unitPrice = item.getArticle().getUnitPrice();// unit Price
				price = unitPrice * unitsOrdered;// calc Price for all ordered Units
				orderPrice += price;
				totalPrice += price;// sum up with total
				orderString.append(unitsOrdered + "x " + item.getDescription());// units x description
				if (i < count) {
					orderString.append(", ");

				}else {
					continue;
				}
			}
			sbLineItem = fmtLine(orderString.toString(), fmtPrice(orderPrice, "EUR", 14), printLineWidth);
			sbAllOrders.append(sbLineItem);
			sbAllOrders.append("\n");
		}
			
		// calculate total price
				String fmtPriceTotal = pad(fmtPrice(totalPrice, " EUR", 14), 14, true);

				// append final line with totals
				sbAllOrders.append("\n").append(fmtLine("-------------", "------------- -------------", printLineWidth))
						.append("\n").append(fmtLine("Gesamtwert aller Bestellungen:", fmtPriceTotal, printLineWidth));
				//enthaltene Steuern ?
				if(printVAT) {
					String vat = pad(fmtPrice(orderProcessor.vat(totalPrice, 1), " EUR", 14), 14, true);
					sbAllOrders.append("\n").append(fmtLine("Darin enthaltene MwSt von (19%): ", vat, printLineWidth));
				}

				// print sbAllOrders StringBuffer with all output to System.out
				System.out.println(sbAllOrders.toString());
		
	}

	@Override
	public void printInventory(boolean sortedByInventoryValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String fmtPrice(long price, String currency) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer(leftStr);
		int shiftable = 0; // leading spaces before first digit
		for (int i = 1; rightStr.charAt(i) == ' ' && i < rightStr.length(); i++) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1; // - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max(0, excess - shiftable);
		if (shift2 > 0) {
			rightStr = rightStr.substring(shift2, rightStr.length());
			excess -= shift2;
		}
		if (excess > 0) {
			switch (excess) {
			case 1:
				sb.delete(sbLen - excess, sbLen);
				break;
			case 2:
				sb.delete(sbLen - excess - 2, sbLen);
				sb.append("..");
				break;
			default:
				sb.delete(sbLen - excess - 3, sbLen);
				sb.append("...");
				break;
			}
		}
		String strLineItem = String.format("%-" + (tab1 - 1) + "s%s", sb.toString(), rightStr);
		sb.setLength(0);
		sb.append(strLineItem);
		return sb;
	}

	@Override
	public String splitName(Customer Customer, String name) {
		String[] teile = name.split(" ");
		String nameRe ="";
		if (teile.length > 2) {
			StringBuilder firstNameString = new StringBuilder();
			for (int i = 0; i < teile.length - 1; i++) {
				firstNameString.append(teile[i] + " ");
			}
			Customer.setFirstName(firstNameString.toString());
			Customer.setLastName(teile[teile.length - 1]);
		} else {
			if(teile[0].contains(",")) {
				teile[0] = teile[0].replaceAll(",","");
				Customer.setFirstName(teile[1]);
				Customer.setLastName(teile[0]);
			}else {
			Customer.setFirstName(teile[0]);
			Customer.setLastName(teile[1]);
			}
		}
		
		return  nameRe;
	}

	@Override
	public String singleName(Customer Customer) {
		return (Customer.getLastName() + ", " + Customer.getFirstName());	 
	}
	private String pad(String str, int width, boolean rightAligned) {
		String fmtter = (rightAligned ? "%" : "%-") + width + "s";
		String padded = String.format(fmtter, str);
		return padded;
	}
	private String fmtPrice(long price, String prefix, String postfix) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if (prefix != null) {
			fmtPriceSB.append(prefix);
		}

		fmtPriceSB = fmtPrice(fmtPriceSB, price);

		if (postfix != null) {
			fmtPriceSB.append(postfix);
		}
		return fmtPriceSB.toString();
	}
	private StringBuffer fmtPrice(StringBuffer sb, long price) {
		if (sb == null) {
			sb = new StringBuffer();
		}
		double dblPrice = (price) / 100.0; // convert cent to Euro
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("de"))); // rounds double
																										// to 2 digits

		String fmtPrice = df.format(dblPrice); // convert result to String in DecimalFormat
		sb.append(fmtPrice);
		return sb;
	}

}

