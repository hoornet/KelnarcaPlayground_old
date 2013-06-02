package si.hoornet.database;



/*
 * CREATE TABLE GRUPA(
      PKID int IDENTITY(1,1) NOT NULL,
      ID int NOT NULL,
      NAME varchar(50) NOT NULL 
 */
public class GrupaTable {

    // EACH grupa HAS autonumber  PID
    public static final String PKID = "_id";
    // EACH grupa HAS UNIQUE ID
    public static final String ID = "id";
    // Ime grupe
    public static final String NAME = "name";

    // NAME OF THE TABLE
    public static final String TABLE_NAME = "grupa";


}


