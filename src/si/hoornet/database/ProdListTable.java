package si.hoornet.database;

/*
 * 
 * CREATE TABLE PROD_LIST(
      PKID int IDENTITY(1,1) NOT NULL,
      ID int NOT NULL,
      
      CODE varchar(20) NULL,
      NAME varchar(50) NOT NULL,
      LINK varchar(30) NOT NULL,
      PRICE money NULL,
      EAN varchar(30) NULL,
      PICT image NULL,
      GRUPA int NULL 

 * public class CourseTable {

}
// THIS ESSENTIALLY REPRESENTS A MAPPING FROM STUDENTS TO COURSES
public class ClassTable {
    // UNIQUE ID OF EACH ROW - NO REAL MEANING HERE
    public static final String ID = "_id";
    // THE ID OF THE STUDENT
    public static final String STUDENT_ID = "student_id";
    // THE ID OF ASSOCIATED COURSE
    public static final String COURSE_ID = "course_id";
    // THE NAME OF THE TABLE
    public static final String TABLE_NAME = "classes";
}
 */
public class ProdListTable {
    // NAME OF THE TABLE
    public static final String TABLE_NAME = "prod_list";
	
	// EACH grupa HAS autonumber  PID
    public static final String PKID = "_id";
    // EACH grupa HAS UNIQUE ID
    public static final String ID = "id";
    // Ime Prod liste
    public static final String NAME = "name";

	public static final String CODE = "code";
	public static final String LINK = "link";
	public static final String PRICE = "price";	
	public static final String EAN = "ean";
	public static final String PICT = "pict";
	public static final String GRUPA_ID = "GRUPA_ID";	
}
