package si.hoornet.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// user table
	public static final String TABLE_USER = "user";
	public static final String COLUMN_USER_ID = "_id";
	public static final String COLUMN_USER_NAME = "username";
	public static final String COLUMN_PASSWORD = "password";

	// project table
	public static final String TABLE_PROJECT = "project";
	public static final String COLUMN_PROJECT_id = "_id";
	public static final String COLUMN_PROJECTNAME = "projectname";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_USER_ID_FK = "user_id"; // this will be as FK


	private static final String DATABASE_NAME = "SOMENAME.db";
	private static final int DATABASE_VERSION = 1;

	// Database creation sql statement
	private static final String DATABASE_USER_CREATE = null /*
	                                                       * CREATE STATEMENT
	                                                     * FOR USER TABLE
	                                                     */;
	private static final String DATABASE_PROJECT_CREATE = null /*
	                                                         * CREATE
	                                                         * STATEMENT FOR
	                                                         * PROJECT TABLE
	                                                         */;

	public MySQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
	// Create both the table user and project initially
	    database.execSQL(DATABASE_USER_CREATE);
	    database.execSQL(DATABASE_PROJECT_CREATE);
	    }

	// Change it as per the logic
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    // Something
	    }
	
}
