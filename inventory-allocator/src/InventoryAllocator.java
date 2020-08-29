
import java.util.*;

class InventoryAllocator {

	public List<Warehouse> findCheapestShipments(Map<String, Integer> orderedItems, List<Warehouse> warehouses){
		List<Warehouse> cheapestShipment = new ArrayList<Warehouse>();
		
		//iterate through warehouse invetories starting at cheapest and check for availability for shipments in the order map
		for(int i = 0; i < warehouses.size(); i++){
			boolean canFulfillOrder = false;
			Warehouse curWareHouse = warehouses.get(i);
			Map<String, Integer> curHouseProduce = curWareHouse.produce;

			Warehouse newWarehouse = new Warehouse();
			newWarehouse.name = curWareHouse.name;
			Map<String,Integer> newWarehouseOrders = new HashMap<String, Integer>();
			newWarehouse.produce=newWarehouseOrders;

			Iterator<Map.Entry<String, Integer> > iterator = orderedItems.entrySet().iterator(); 
			while(iterator.hasNext()){
				Map.Entry<String, Integer> curOrder = iterator.next(); 
				String curItem = curOrder.getKey();
				Integer numToShip = curOrder.getValue();

				if(curHouseProduce.containsKey(curItem)){
					canFulfillOrder = true;
					Integer curItemCapacity = curHouseProduce.get(curItem);
					int numToShipLeft = numToShip - curItemCapacity;

					if(numToShipLeft <= 0){
						System.out.println("Num to ship is "+ numToShip);
						newWarehouseOrders.put(curItem, numToShip);
						System.out.println("Removed: " + curItem);
						iterator.remove();
					}  else {
						newWarehouseOrders.put(curItem, numToShipLeft);
						System.out.println("Orders are " + newWarehouseOrders);
						System.out.println("Set: " + curItem + " to " + numToShipLeft);
						curOrder.setValue(numToShipLeft);
					}
				}
			}

			if(canFulfillOrder){
				cheapestShipment.add(newWarehouse);
			}

		}

		return cheapestShipment;
	}
}