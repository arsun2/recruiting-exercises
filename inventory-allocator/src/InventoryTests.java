
import java.util.*;

class InventoryTests {

	public static void main(String[] args){

		testExample1();
	}

	public static void testExample1(){
		InventoryAllocator newInventory = new InventoryAllocator();
		Warehouse warehouseTest1 = new Warehouse();
		Map<String, Integer> warehouseInventory1 = new HashMap<String, Integer>();

		Warehouse warehouseTest2 = new Warehouse();
		Map<String, Integer> warehouseInventory2 = new HashMap<String, Integer>();

		Map<String, Integer> orderTest1 = new HashMap<String, Integer>();

		List<Warehouse> wareList = new ArrayList<Warehouse>();

		orderTest1.put("apple", 5);
		orderTest1.put("banana", 5);
		orderTest1.put("orange", 5);

		warehouseTest1.name = "owd";
		warehouseInventory1.put("apple", 5);
		warehouseInventory1.put("orange", 10);
		warehouseTest1.produce = warehouseInventory1;

		warehouseTest2.name = "dm";
		warehouseInventory2.put("banana", 5);
		warehouseInventory2.put("orange", 10);
		warehouseTest2.produce = warehouseInventory2;


		wareList.add(warehouseTest1);
		wareList.add(warehouseTest2);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		System.out.println("Size is " + shipments.size());
		System.out.println(shipments.get(0).name);
		System.out.println(shipments.get(0).produce);
		System.out.println(shipments.get(1).name);
		System.out.println(shipments.get(1).produce);
	}

	public static void testExample2(){
	}




}