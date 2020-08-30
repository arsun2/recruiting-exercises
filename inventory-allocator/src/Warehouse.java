import java.util.*;

public class Warehouse {

	String name;
	Map<String, Integer> produce;

	public Warehouse(String name, Map<String, Integer> produce){
		this.name = name;
		this.produce = produce;
	}

	public Warehouse(Map<String, Integer> produce){
		this.name = "";
		this.produce = produce;
	}


	public void clearWarehouse(){
		this.produce.clear();
	}

}