package system;

import datamodel.Order;

final class OrderProcessor implements Components.OrderProcessor {


	public OrderProcessor(InventoryManager inventoryManager) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue) {
		
		// TODO Auto-generated method stub
		return Math.round(grossValue/1.19*0.19); 
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		long value = 0;
		if(rateIndex==1) {
			value = vat(grossValue);
		}else {
			if(rateIndex == 2) {
				value =Math.round(grossValue/1.07*0.07);
			}	
		}
		return value;
	}

}
