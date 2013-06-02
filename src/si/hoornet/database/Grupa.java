package si.hoornet.database;

import java.util.ArrayList;

import android.database.Cursor;

public class Grupa {

	ArrayList<Grupa> groups = new ArrayList<Grupa>();

	int id;
	String visibleText;

	Grupa(int id, String text) {
		this.id = id;
		this.visibleText = text;
	}

	public Grupa() {
	}
	
	public Grupa(SchemaHelper sh)
	{
	
	}
	void setGroup(int id, String text) {
		this.id = id;
		this.visibleText = text;
	}

	public Grupa getGroup() {
		return this;
	}

	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.visibleText.toString();
	}

	@SuppressWarnings("unused")
	private void createSampleGroups() {
		Grupa group = new Grupa(0, "Hrana");
		groups.add(group);

		groups.add(new Grupa(1, "Pijaèa"));
		groups.add(new Grupa(2, "Cigarete"));
		groups.add(new Grupa(3, "Drugo"));
	}

	public ArrayList<Grupa> getGroupsArray(SchemaHelper sh) {
		Cursor cGroups = sh.getGroups();
		while (cGroups.moveToNext()) {
			int colid = cGroups.getColumnIndex(ProdListTable.ID);
			int cName = cGroups.getColumnIndex(ProdListTable.NAME);

			String name = cGroups.getString(cName);
			int sid = cGroups.getInt(colid);

			groups.add(new Grupa(sid, name)); 
			System.out.println("ID " + sid + " v grupi " + name);
		}
		


		return groups;
	}

}
