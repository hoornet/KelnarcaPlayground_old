package si.hoornet.kelnarca2;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	
	// Aktiviraj izbor skupin
	public void onClickGroups(View v) {
		Intent i = new Intent(MainActivity.this, GroupsActivity.class);
		startActivity(i);	
	}
	
	
	// Zgolj demonstarcija za èas developmenta
	public void onClickPijaca(View v) {
		Intent i = new Intent(MainActivity.this, GridFavouritesActivity.class);
		startActivity(i);
	}	
	public void onClickHrana(View v) {
		Intent i = new Intent(MainActivity.this, GridFavouritesActivity.class);
		startActivity(i);
	}
	public void onClickDrugo(View v) {
		Intent i = new Intent(MainActivity.this, GridActivity.class);
		startActivity(i);	
	}
	
	
}
