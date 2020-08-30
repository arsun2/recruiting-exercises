import java.util.*;

public class Warehouse {

	String name;
	Map<String, Integer> inventory;

	public Warehouse(String name, Map<String, Integer> inventory){
		this.name = name;
		this.inventory = inventory;
	}

	public Warehouse(String name){
		this.name = name;
		this.inventory = new HashMap<String, Integer>();
	}

	public Warehouse(Map<String, Integer> inventory){
		this.name = "";
		this.inventory = inventory;
	}

	public void clearWarehouse(){
		this.inventory.clear();
	}

}