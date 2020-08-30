
import java.util.*;

class InventoryAllocator {

	public List<Warehouse> findCheapestShipments(Map<String, Integer> orderedItems, List<Warehouse> warehouses){
		List<Warehouse> cheapestShipments = new ArrayList<Warehouse>();

		// iterate through warehouses starting at the one with the cheapest inventory and check for availability for items in the orders
		for(int i = 0; i < warehouses.size(); i++){
			boolean canWarehouseFulfillOrder = false;
			Warehouse warehouse = warehouses.get(i); 
			Map<String, Integer> inventory = warehouse.inventory;

			// create new warehouse object to add to shipment list
			Warehouse warehouseShipment = new Warehouse(warehouse.name);

			Iterator<Map.Entry<String, Integer>> iterator = orderedItems.entrySet().iterator(); 
			while(iterator.hasNext()){
				Map.Entry<String, Integer> orderedItem = iterator.next(); 
				String item = orderedItem.getKey();
				Integer itemCount = orderedItem.getValue();

				// if the current warehouse has capacity for an order item, 
				// we make note that the current warehouse can fulfill at least one order to add to the result
				if(inventory.containsKey(item) && inventory.get(item) > 0){
					canWarehouseFulfillOrder = true;
					Integer currentItemCapacity = inventory.get(item);
					int itemsLeftToShip = itemCount - currentItemCapacity;

					// update the amount still needed to ship  
					// if current warehouse can take entire capacity of an item remove it from the 'ordered items' map
					if(itemsLeftToShip <= 0){
						warehouseShipment.inventory.put(item, itemCount);
						iterator.remove();
					}  
					else {
						warehouseShipment.inventory.put(item, currentItemCapacity);
						orderedItem.setValue(itemsLeftToShip);
					}

					inventory.remove(item);
				}
			}

			// only add new warehouse to the shipment list if it was able to handle at least 1 order
			if(canWarehouseFulfillOrder){
				cheapestShipments.add(warehouseShipment);
			}

		}

		return cheapestShipments;
	}
}