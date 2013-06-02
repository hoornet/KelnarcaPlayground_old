/**
 * 
 */
package si.hoornet.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Hoornet
 * 
 */
public class SchemaHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "kelnarca.db";

	// TOGGLE THIS NUMBER FOR UPDATING TABLES AND DATABASE
	private static final int DATABASE_VERSION = 1;

	public SchemaHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub

		// CREATE GRUPA TABLE
		db.execSQL("CREATE TABLE " + GrupaTable.TABLE_NAME + " ("
				+ GrupaTable.PKID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ GrupaTable.ID + " INTEGER, "
				+ GrupaTable.NAME + " TEXT);");
		// CREATE COURSES TABLE
		db.execSQL("CREATE TABLE " + ProdListTable.TABLE_NAME + " ("
				+ ProdListTable.PKID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ ProdListTable.ID + " INTEGER, "
				+ ProdListTable.GRUPA_ID + " INTEGER, "
				+ ProdListTable.NAME + " TEXT);");
		
		insertGrupeSampleData(db);
		insertProdListSampleData(db);
		
/*		// CREATE CLASSES MAPPING TABLE
		db.execSQL("CREATE TABLE " + ClassTable.TABLE_NAME + " ("
				+ ClassTable.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ ClassTable.STUDENT_ID + " INTEGER," + ClassTable.COURSE_ID
				+ " INTEGER);");*/
	}

	@Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
    		int newVersion) {
        Log.w("LOG_TAG", "Upgrading database from version " 
        + oldVersion + " to " + newVersion + ", which will destroy all old data");
        // KILL PREVIOUS TABLES IF UPGRADEDfg
        db.execSQL("DROP TABLE IF EXISTS " + GrupaTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProdListTable.TABLE_NAME);
   //     db.execSQL("DROP TABLE IF EXISTS " + ClassTable.TABLE_NAME);
        // CREATE NEW INSTANCE OF SCHEMA
        onCreate(db);
    }

	///
    // WRAPPER METHOD FOR ADDING A STUDENT
    public long addGrupa(String name, int id) {
        // CREATE A CONTENTVALUE OBJECT
        ContentValues cv = new ContentValues();
        cv.put(GrupaTable.NAME, name);
        cv.put(GrupaTable.ID, id);
        // RETRIEVE WRITEABLE DATABASE AND INSERT
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.insert(GrupaTable.TABLE_NAME,  
        		GrupaTable.ID, cv);
        return result;
    }
    // WRAPPER METHOD FOR ADDING A COURSE
    public long addProdList(String name, int id) {
        ContentValues cv = new ContentValues();
        cv.put(ProdListTable.NAME, name);
        cv.put(ProdListTable.ID, id);
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.insert(ProdListTable.TABLE_NAME, 
        		ProdListTable.NAME, cv);
        return result;
    }
    
    // GET ALL Products IN A Group
    public Cursor getProductsForGroup(int groupId) {
        SQLiteDatabase sd = getWritableDatabase();
        // WE ONLY NEED TO RETURN STUDENT IDS
        String[] cols = new String[] { ProdListTable.ID, ProdListTable.GRUPA_ID, ProdListTable.NAME };
        String[] selectionArgs = new String[] { String.valueOf(groupId) };
        // QUERY CLASS MAP FOR STUDENTS IN COURSE
        Cursor c = sd.query(ProdListTable.TABLE_NAME, cols,  
        		ProdListTable.GRUPA_ID + "= ?", selectionArgs, null, 
        null, null);
        return c;
    }
    
    
    
    // GET ALL Groups
    public Cursor getGroups() {
        SQLiteDatabase sd = getWritableDatabase();
        // WE ONLY NEED TO RETURN STUDENT IDS
        String[] cols = new String[] { GrupaTable.ID, GrupaTable.NAME };
        //String[] selectionArgs = new String[] { String.valueOf(groupId) };
        // QUERY CLASS MAP FOR STUDENTS IN COURSE
        Cursor c = sd.query(GrupaTable.TABLE_NAME, cols,  
        		null, null, null, 
        null, null);
        return c;
    }
    
    
    
    
    /*
    // WRAPPER METHOD FOR ENROLLING A STUDENT INTO A COURSE
    public boolean enrollStudentClass(int studentId, int courseId) {
        ContentValues cv = new ContentValues();
        cv.put(ClassTable.STUDENT_ID, studentId);
        cv.put(ClassTable.COURSE_ID, courseId);
        SQLiteDatabase sd = getWritableDatabase();
        long result = sd.insert(ClassTable.TABLE_NAME,  
        ClassTable.STUDENT_ID, cv);
        return (result >= 0);
    }

*/	
	
	
    
	// Sample data for tabel GRUPE
	public void insertGrupeSampleData(SQLiteDatabase db) {
		Log.w("LOG_TAG", "insertGrupeSampleData()");
		
		//SQLiteDatabase db = getWritableDatabase();
		
		// That's how you do it when you receive data from the net
		ContentValues values = new ContentValues();
		values.put(GrupaTable.ID, 1);
		values.put(GrupaTable.NAME, "Pijaèa");
		//db.insert(TABLE, null, values);
		db.insertWithOnConflict(GrupaTable.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		
		// but for the inside only sample we'll just do it the old way:
		db.execSQL("insert into grupa (id, name) values (2, 'Hrana')");
		db.execSQL("insert into grupa (id, name) values (3, 'Cigarete')");
		db.execSQL("insert into grupa (id, name) values (4, 'Neki druzga')");
		db.execSQL("insert into grupa (id, name) values (5, 'Drugo')");
	}	
	// Sample data for tabel PROD_LIST
	public void insertProdListSampleData(SQLiteDatabase db) {
		Log.w("LOG_TAG", "insertProdListSampleData()");
		
	//	SQLiteDatabase db = getWritableDatabase();
		
		// That's how you do it when you receive data from the net
		ContentValues values = new ContentValues();
		values.put(ProdListTable.ID, 1);
		values.put(ProdListTable.GRUPA_ID, 1);
		values.put(ProdListTable.NAME, "Laško Pivo 3dl");
		//db.insert(TABLE, null, values);

		//		db.insertWithOnConflict(ProdListTable.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		
		// but for the inside only sample we'll just do it the old way:
		//Pijaèa sample
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (2, 1, 'Laško pivo 5dm')");
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (3, 1, 'Union pivo 3dl')");
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (4, 1, 'Union pivo 5dl')");
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (5, 1, 'Kratka kava')");
		
		// hrana sample
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (1, 2, 'Prebranec')");
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (2, 2, 'Žlikrofi')");
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (3, 2, 'Vege something')");
		
		// Cigareti sample
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (1, 3, 'Marlboro')");
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (2, 3, '57 stare')");
		db.execSQL("insert into PROD_LIST (id, GRUPA_ID, name) values (3, 3, 'Marlboro light')");
	}	
	
	
	
}
