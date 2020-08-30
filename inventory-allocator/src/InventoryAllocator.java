
import java.util.*;

class InventoryAllocator {

	public List<Warehouse> findCheapestShipments(Map<String, Integer> orderedItems, List<Warehouse> warehouses){
		List<Warehouse> cheapestShipment = new ArrayList<Warehouse>();

		//iterate through warehouse inventories starting at cheapest and check for availability for shipments in the order map
		for(int i = 0; i < warehouses.size(); i++){
			boolean canFulfillOrder = false;
			Warehouse curWareHouse = warehouses.get(i);
			Map<String, Integer> curHouseProduce = curWareHouse.produce;

			Map<String,Integer> newWarehouseOrders = new HashMap<String, Integer>();
			Warehouse newWarehouse = new Warehouse(curWareHouse.name, newWarehouseOrders);

			Iterator<Map.Entry<String, Integer> > iterator = orderedItems.entrySet().iterator(); 
			while(iterator.hasNext()){
				Map.Entry<String, Integer> curOrder = iterator.next(); 
				String curItem = curOrder.getKey();
				Integer numToShip = curOrder.getValue();

				//if the current warehouse has capacity for an order item we make note that the current warehouse can take at least one order to add to the result
				if(curHouseProduce.containsKey(curItem) && curHouseProduce.get(curItem) != 0){
					canFulfillOrder = true;
					Integer curItemCapacity = curHouseProduce.get(curItem);
					int numToShipLeft = numToShip - curItemCapacity;

					//update the amount still needed to ship - if current warehouse can take entire capacity of an object remove it from the 'orders still to ship' map
					if(numToShipLeft <= 0){
						newWarehouseOrders.put(curItem, numToShip);
						curHouseProduce.remove(curItem);
						iterator.remove();
					}  else {
						newWarehouseOrders.put(curItem, curItemCapacity);
						curHouseProduce.remove(curItem);
						curOrder.setValue(numToShipLeft);
					}
				}
			}

			//only add new warehouse to the shipment list if it was able to handle at least 1 order
			if(canFulfillOrder){
				cheapestShipment.add(newWarehouse);
			}

		}

		return cheapestShipment;
	}
}