package si.hoornet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GrupaData {
	

	// CREATE TABLE [GRUPA]([PKID] [int] IDENTITY(1,1) NOT NULL,  [ID] [int] NOT NULL, [NAME] [varchar](50) NOT NULL 
	
	// Skrajšan testni SQL:
	// create table grupa  PKID int primary key, int id, text name
			      
		static final String TAG = "GrupaData";
		
		public static final String DB_NAME = "kelnarca.db";
		public static final int DB_VERSION  = 1;
		
		public static final String TABLE = "GRUPA";
		public static final String C_ID = "id";
		public static final String C_PKID = "_ID";
	//	public static final String C_CREATED_AT = "created_at";
		public static final String C_NAME = "NAME";
		public static final String C_GRUPA = "GRUPA";



		
		
		
		Context context;
		DbHelper dbhelper;
		SQLiteDatabase db;
		
		
		public GrupaData(Context context) {
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
						"(%s int primary key, %s int, %s text)",
						TABLE, 
						C_PKID, C_ID, C_NAME);
				
				Log.d(TAG, "onCreate with SQL: " + sql);
				db.execSQL(sql);
				
				insertSampleDate();  // ker se onCreate izvede samo enkrat je to tudi priložnost za vnos enkratnih sample podatkov
							
			}

			
			// if sistem detects difference between oldversion and newVersion it runs this:
			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// Usually ALTER TABLE statement
				
				// poor mans version:
				db.execSQL("drop if exists " + TABLE);
				onCreate(db);
				
			}
			
			// Sample data for tabel GRUPE
			public void insertSampleDate() {
				Log.d(TAG, "insertSampleDate()");
				
				db = dbhelper.getWritableDatabase();
				
				// That's how you do it when you receive data from the net
				ContentValues values = new ContentValues();
				values.put(C_ID, 1);
				values.put(C_NAME, "Pijaèa");
				//db.insert(TABLE, null, values);
				db.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
				
				// but for the inside only sample we'll just do it the old way:
				db.execSQL("insert into grupa (id, name) values (2, 'Hrana')");
				db.execSQL("insert into grupa (id, name) values (3, 'Cigarete')");
				db.execSQL("insert into grupa (id, name) values (4, 'Neki druzga')");
				db.execSQL("insert into grupa (id, name) values (5, 'Drugo')");
			}			
		}		
}
