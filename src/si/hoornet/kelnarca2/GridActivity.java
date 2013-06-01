package si.hoornet.kelnarca2;

import si.hoornet.database.ProdListTable;
import si.hoornet.database.SchemaHelper;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;

public class GridActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_grid);
		setContentView(R.layout.grid_layout);
		
		SchemaHelper sh = new SchemaHelper(this);
		
		Cursor c = sh.getProductsForGroup(1);
		while(c.moveToNext())
		{
			int colid = c.getColumnIndex(ProdListTable.ID);
			int sid = c.getInt(colid);
			System.out.println("Produkt "+ sid + " v grupi ");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid, menu);
		return true;
	}

}
