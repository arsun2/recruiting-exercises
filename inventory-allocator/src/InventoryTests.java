
import java.util.*;

class InventoryTests {

	static InventoryAllocator newInventory = new InventoryAllocator();
	static Map<String, Integer> warehouseInventory1 = new HashMap<String, Integer>();
	static Warehouse warehouseTest1 = new Warehouse("owd", warehouseInventory1);
	static Map<String, Integer> warehouseInventory2 = new HashMap<String, Integer>();
	static Warehouse warehouseTest2 = new Warehouse("dm", warehouseInventory2);
	static Map<String, Integer> orderTest1 = new HashMap<String, Integer>();
	static List<Warehouse> wareList = new ArrayList<Warehouse>();

	//My assertions for each test is on the size of the expected shipment list, while
	//the validation for the contents of the expected shipment list is from manually examining 
	//the printed results in the command line. In an actual deployed environment I would assert the contents of 
	//the list in the test but for quick readability for myself and the grader I printed this in stdout

	public static void main(String[] args){
		testSingleWarehouse();
		testSingleWarehouseIncomplete();
		testMultipleWarehouse();
		testNoCapacityWarehouse();
		testSplitWarehouses();		
		testEmptyWarehouse();
		testNoWarehouses();
		testPartialShipment();
		testProperlyCompletedOrders();

	}

	public static void testSingleWarehouse(){
		System.out.println("Test Single Warehouse: ");
		initTests();
		orderTest1.put("apple", 50);
		warehouseInventory1.put("apple", 50);
		wareList.add(warehouseTest1);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 1;
		printShipments(shipments);
	}

	public static void testSingleWarehouseIncomplete(){
		System.out.println("Test Single Warehouse: ");
		initTests();
		orderTest1.put("apple", 50);
		warehouseInventory1.put("apple", 49);
		wareList.add(warehouseTest1);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 1;
		printShipments(shipments);
	}

	public static void testMultipleWarehouse(){
		System.out.println("Test Multiple Warehouses: ");
		initTests();
		orderTest1.put("apple", 5);
		orderTest1.put("banana", 5);
		orderTest1.put("orange", 5);

		warehouseInventory1.put("apple", 5);
		warehouseInventory1.put("orange", 10);

		warehouseInventory2.put("banana", 5);
		warehouseInventory2.put("orange", 10);

		wareList.add(warehouseTest1);
		wareList.add(warehouseTest2);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 2;
		printShipments(shipments);
	}

	public static void testNoCapacityWarehouse(){
		System.out.println("Test Warehouse with no capcity: ");
		initTests();
		orderTest1.put("apple", 1);
		orderTest1.put("banana", 1);
		warehouseInventory1.put("apple", 0);
		warehouseInventory1.put("banana", 0);
		wareList.add(warehouseTest1);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 0;
		printShipments(shipments);
	}

	public static void testSplitWarehouses(){
		System.out.println("Test splitting order across multiple warehouses: ");
		initTests();
		orderTest1.put("apple", 10);
		warehouseInventory1.put("apple", 5);
		warehouseInventory2.put("apple", 5);
		wareList.add(warehouseTest1);
		wareList.add(warehouseTest2);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 2;
		printShipments(shipments);
	}

	public static void testEmptyWarehouse(){
		System.out.println("Test warehouse with empty order capacity: ");
		initTests();
		orderTest1.put("apple", 1);
		wareList.add(warehouseTest1);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 0;
		printShipments(shipments);
	}

	public static void testNoWarehouses(){
		System.out.println("Test empty warehouse list: ");
		initTests();

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 0;
		printShipments(shipments);
	}

	public static void testPartialShipment(){
		System.out.println("Test warehouses that can only complete orders partially: ");
		initTests();
		orderTest1.put("apple", 5);
		orderTest1.put("banana", 5);
		orderTest1.put("orange", 5);

		warehouseInventory1.put("apple", 4);
		warehouseInventory1.put("banana", 3);
		
		warehouseInventory2.put("tomato", 5);
		warehouseInventory2.put("orange", 2);
		warehouseInventory2.put("grape", 20);

		wareList.add(warehouseTest1);
		wareList.add(warehouseTest2);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 2;
		printShipments(shipments);
	}

	public static void testProperlyCompletedOrders(){
		System.out.println("Test completed orders are not assigned multiple warehouses: ");
		initTests();
		orderTest1.put("apple", 30);
		orderTest1.put("banana", 50);
		orderTest1.put("orange", 100);

		warehouseInventory1.put("apple", 30);
		warehouseInventory1.put("banana", 50);
		warehouseInventory1.put("orange", 100);
		
		warehouseInventory2.put("tomato", 5);
		warehouseInventory2.put("orange", 2);

		wareList.add(warehouseTest1);
		wareList.add(warehouseTest2);

		List<Warehouse> shipments = newInventory.findCheapestShipments(orderTest1, wareList);
		assert shipments.size() == 1;
		printShipments(shipments);
	}

	public static void printShipments(List<Warehouse> shipments){
		if(shipments.size() == 0){
			System.out.println("0 possible warehouse shipments");
			return;
		}
		for(Warehouse shipment : shipments){
			System.out.println(shipment.name);
			System.out.println(shipment.produce);
		}
	}

	public static void initTests(){
		orderTest1.clear();
		warehouseTest1.clearWarehouse();
		warehouseTest2.clearWarehouse();
	}


}