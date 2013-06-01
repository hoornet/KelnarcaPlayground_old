package si.hoornet.kelnarca2;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class DrinkListActivity extends ListActivity {
	
	
	String[] drinks = {
			"pivo 2dl",
			"pivo 3dl",
			"pivo 5dl",
			"Kava",
			"Kava z mlekom",
			"Kava s smetano"
			};
	
	String[] groups = {
		"Pijaèa",
		"Hrana",
		"Drugo",
		"Favourites"
	};
	
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_drink_list);
		
		// ListView 
		setListAdapter(new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, 
				drinks));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.drink_list, menu);
		return true;
	}

}
