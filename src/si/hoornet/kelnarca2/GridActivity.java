package si.hoornet.kelnarca2;

import java.util.ArrayList;
import si.hoornet.database.Grupa;
import si.hoornet.database.ProdList;
import si.hoornet.database.ProdListTable;
import si.hoornet.database.SchemaHelper;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class GridActivity extends Activity {

	Spinner spinnerGrupe, spinnerMize;
	ListView lvProdList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_grid);
		setContentView(R.layout.grid_layout);

		SchemaHelper sh = new SchemaHelper(this);

		fillSpinnerGrupe(sh);

		Cursor c = sh.getProductsForGroup(1);
		while (c.moveToNext()) {
			int colid = c.getColumnIndex(ProdListTable.ID);
			int cName = c.getColumnIndex(ProdListTable.NAME);
			int cGrupaId = c.getColumnIndex(ProdListTable.GRUPA_ID);

			int GrupaId = c.getInt(cGrupaId);
			String name = c.getString(cName);
			int sid = c.getInt(colid);

			System.out.println("Produkt " + name + sid + " v grupi " + GrupaId);
		}

/*		c = sh.getProductsForGroup(2);
		while (c.moveToNext()) {
			int colid = c.getColumnIndex(ProdListTable.ID);
			int cName = c.getColumnIndex(ProdListTable.NAME);
			int cGrupaId = c.getColumnIndex(ProdListTable.GRUPA_ID);

			int GrupaId = c.getInt(cGrupaId);
			String name = c.getString(cName);
			int sid = c.getInt(colid);

			System.out.println("Produkt " + name + sid + " v grupi " + GrupaId);
		}
		*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid, menu);
		return true;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	
	
	public void fillSpinnerGrupe(final SchemaHelper sh) {
		spinnerGrupe = (Spinner) findViewById(R.id.spinnerGrupe);
		Grupa grupa = new Grupa();
		ArrayList<Grupa> groups = new ArrayList<Grupa>();
		groups = grupa.getGroupsArray(sh);

		ArrayAdapter<Grupa> adapter = new ArrayAdapter<Grupa>(this,
				android.R.layout.simple_spinner_item, groups);

		spinnerGrupe.setAdapter(adapter);
		final ArrayList<Grupa> fgroups = groups;
		spinnerGrupe.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				int index = spinnerGrupe.getSelectedItemPosition();
				String izbranaGrupa = fgroups.get(index).toString();
				Grupa g = fgroups.get(index);
				int id = g.getGroup().getId();
				Toast.makeText(getBaseContext(),
						"Izbrana je bila grupa: " + izbranaGrupa,
						Toast.LENGTH_LONG).show();
				
				fillListViewProdList(sh, id);
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
	}
	
	public void fillListViewProdList(SchemaHelper sh, int groupId) {
		lvProdList = (ListView) findViewById(R.id.listViewProdList);
		
		
		ArrayList<ProdList> products = new ArrayList<ProdList>();
		products = getProdListArray(sh, groupId);
		ArrayAdapter<ProdList> adapter = new ArrayAdapter<ProdList>(this,
				android.R.layout.simple_list_item_1, products);
		lvProdList.setAdapter(adapter);
		
/*		Cursor c = sh.getProductsForGroup(groupId);
		while (c.moveToNext()) {
			int colid = c.getColumnIndex(ProdListTable.ID);
			int cName = c.getColumnIndex(ProdListTable.NAME);
			int cGrupaId = c.getColumnIndex(ProdListTable.GRUPA_ID);

			int GrupaId = c.getInt(cGrupaId);
			String name = c.getString(cName);
			int sid = c.getInt(colid);

			String[] columns = new String[] {ProdListTable.ID, ProdListTable.NAME};

			
			
			System.out.println("Produkt " + name + sid + " v grupi " + GrupaId);
		}*/
	}
	
	
	public ArrayList<ProdList> getProdListArray(SchemaHelper sh, int groupId) {
		ArrayList<ProdList> products = new ArrayList<ProdList>();
		Cursor cProdList = sh.getProductsForGroup(groupId);
		while (cProdList.moveToNext()) {
			int colid = cProdList.getColumnIndex(ProdListTable.ID);
			int cName = cProdList.getColumnIndex(ProdListTable.NAME);

			String name = cProdList.getString(cName);
			int sid = cProdList.getInt(colid);

			products.add(new ProdList(colid, name, groupId)); 
			System.out.println("ID " + sid + " v grupi " + name);
		}		
		return products;
	}

}
