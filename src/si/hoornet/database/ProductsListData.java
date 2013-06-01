package si.hoornet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProductsListData {
	


/*	CREATE TABLE PROD_LIST(
		      PKID int IDENTITY(1,1) NOT NULL,
		      ID int NOT NULL,
		      CODE varchar(20) NULL,
		      NAME varchar(50) NOT NULL,
		      LINK varchar(30) NOT NULL,
		      PRICE money NULL,
		      EAN varchar(30) NULL,
		      PICT image NULL,
		      GRUPA int NULL )
		      */
	
	// Skrajšan testni SQL:
	// create table prod_list PKID int primary key, int id, int grupa, text name
		      
	static final String TAG = "ProductsListData";
	
	public static final String DB_NAME = "kelnarca.db";
	public static final int DB_VERSION  = 1;
	
	public static final String TABLE = "PROD_LIST";
	public static final String C_ID = "id";
	public static final String C_PKID = "_id";   // PKID(v SQL server) -> _id (v Androidu)
	
	public static final String C_CREATED_AT = "created_at";
	public static final String C_NAME = "NAME";
	public static final String C_GRUPA = "GRUPA";
//	public static final String C_USER = "user_name";
//	public static final String C_TEXT = "status_text";
//	public static final String C_CODE = "CODE";


	
	
	
	Context context;
	DbHelper dbhelper;
	SQLiteDatabase db;
	
	
	public ProductsListData(Context context) {
		//super();
		this.context = context;
		dbhelper = new DbHelper();
	}

	class DbHelper extends SQLiteOpenHelper {

		// simplify contructor
		public DbHelper() {
			super(context, DB_NAME, null, DB_VERSION);
		}

		
		// RUN ONCE ONLY !!!
		@Override
		public void onCreate(SQLiteDatabase db) {
			// create table prod_list PKID int primary key, id int , grupa int , name text;
			String sql = String.format("create table %s " + 
					"(%s int primary key, %s int not null, %s int null, %s text not null)",
					TABLE, 
					C_PKID, C_ID, C_GRUPA, C_NAME);
			
			Log.d(TAG, "onCreate with SQL: " + sql);
			db.execSQL(sql);
						
			insertSampleDate();
		}

		
		// if sistem detects difference between oldversion and newVersion it runs this:
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Usually ALTER TABLE statement
			
			// poor mans version:
			db.execSQL("drop if exists " + TABLE);
			onCreate(db);
			
		}
		
		// Sample data for tabel PROD_LIST
		public void insertSampleDate() {
			Log.d(TAG, "insertSampleDate()");
			
			db = dbhelper.getWritableDatabase();
			
			// That's how you do it when you receive data from the net
			ContentValues values = new ContentValues();
			values.put(C_ID, 1);
			values.put(C_GRUPA, 1);
			values.put(C_NAME, "Laško Pivo 3dl");
			//db.insert(TABLE, null, values);
			db.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
			
			// but for the inside only sample we'll just do it the old way:
			//Pijaèa sample
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (2, 1, 'Laško pivo 5dm')");
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (3, 1, 'Union pivo 3dl')");
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (4, 1, 'Union pivo 5dl')");
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (5, 1, 'Kratka kava')");
			
			// hrana sample
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (1, 2, 'Prebranec')");
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (2, 2, 'Žlikrofi')");
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (3, 2, 'Vege something')");
			
			// Cigareti sample
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (1, 3, 'Marlboro')");
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (2, 3, '57 stare')");
			db.execSQL("insert into PROD_LIST (id, grupa, name) values (3, 3, 'Marlboro light')");
		}	
		
	}
	
	
}
