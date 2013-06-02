package si.hoornet.database;

public class ProdList {
	String name;
	int id;
	int _id;
	int grupa_id;

	public ProdList() {
		// TODO Auto-generated constructor stub
	}
	
	
	public ProdList(int id, String name, int grupa_id) {
		this.id = id;
		this.grupa_id = grupa_id;
		this.name = name;
	}
	
	void setProdList(int id, String name, int grupa_id) {
		this.id = id;
		this.grupa_id = grupa_id;
		this.name = name;
	}
	
	ProdList getProdList() {
		return this;
	}
	
	@Override
	public String toString() {
		
		return this.name.toString();
	}

}
