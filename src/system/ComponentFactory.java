package system;

import datamodel.RawDataFactory;

public class ComponentFactory {
	private static ComponentFactory instance = null;
	private InventoryManager inventoryManager;
	private OrderProcessor orderProcessor;
	private OutputProcessor outputProcessor;
	private DataFactory dataFactory;
	
	private ComponentFactory() {
		this.inventoryManager = new InventoryManager();
		this.orderProcessor = new OrderProcessor(inventoryManager);
		this.outputProcessor = new OutputProcessor(inventoryManager, orderProcessor);
//		Object objectRawFactory = null;
		RawDataFactory.RawDataFactoryIntf objectRawFactory = RawDataFactory.getInstance(this);
		this.dataFactory = new DataFactory( objectRawFactory,
					inventoryManager, outputProcessor);
	}
	
	public static ComponentFactory getInstance() {
		if(instance == null) {
			instance = new ComponentFactory();
		}
		return instance;
	}
	Components.InventoryManager getInventoryManager(){ 
		return inventoryManager;
	}
	public Components.OrderProcessor getOrderProcessor(){
		return orderProcessor;
	}
	public Components.OutputProcessor getOutputProcessor(){
		return outputProcessor;
	}
	public Components.DataFactory getDataFactory(){
		return dataFactory;
	}

}
