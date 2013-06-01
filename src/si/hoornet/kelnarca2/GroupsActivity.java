package si.hoornet.kelnarca2;

import java.util.ArrayList;
import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GroupsActivity  extends ListActivity {

	class Group {
		
		int id;
		String visibleText;
		
		Group(int id, String text) {
			this.id = id;
			this.visibleText = text;
		}
		
		
		void setGroup(int id, String text) {
			this.id = id;
			this.visibleText = text;
		}

		Group getGroup() {
			return this;
		}

		@Override
		public String toString() {
			// TODO Auto-generated method stub
			return this.visibleText.toString();
		}		
	}
	
	ArrayList<Group> groups = new ArrayList<Group>();
	
	private void createSampleGroups() {
		Group group = new Group(0, "Hrana");		
		groups.add(group);
		
		groups.add(new Group(1, "Pijaèa"));
		groups.add(new Group(2, "Cigarete"));
		groups.add(new Group(3, "Drugo"));		
	}
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groups);
		
		// skreiraj nekaj skupin za demo
		createSampleGroups();  


		//ListAdapter adapter = SimpleCursorAdapter...
		
		// ListView 
		setListAdapter(new ArrayAdapter<Group>(this, 
				android.R.layout.simple_list_item_1, 
				groups));		
	}

	/* (non-Javadoc)
	 * @see android.app.ListActivity#getSelectedItemId()
	 */
	@Override
	public long getSelectedItemId() {
		// TODO Auto-generated method stub
		return super.getSelectedItemId();
	}



	/* (non-Javadoc)
	 * @see android.app.ListActivity#getSelectedItemPosition()
	 */
	@Override
	public int getSelectedItemPosition() {
		// TODO Auto-generated method stub
		return super.getSelectedItemPosition();
	}



	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.groups, menu);
		return true;
	}

}
