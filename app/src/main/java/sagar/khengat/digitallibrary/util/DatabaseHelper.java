package sagar.khengat.digitallibrary.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Admin;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.model.Faculty;
import sagar.khengat.digitallibrary.model.Student;


/**
 * Database helper class used to manage the creation and upgrading of your
 * database. This class also usually provides the DAOs used by the other
 * classes.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application
	public static final String DATABASE_NAME = "DigitalLibrary.db";
	// any time you make changes to your database objects, you may have to
	// increase the database version

	//increase database version from 18 to 19 due to add formxml field in adminform xml responce
	private static final int DATABASE_VERSION  = 1;

	// the DAO object we use to access the Appointments table
	private RuntimeExceptionDao<Admin, Integer> adminDao = null;
	private RuntimeExceptionDao<Faculty, Integer> facultyDao = null;
	private RuntimeExceptionDao<Student, Integer> studentDao = null;
	private RuntimeExceptionDao<Book, Integer> bookDao = null;



	 Context context1;



	private static final String TABLE_SCANNED = "scanned";
	private static final String COLUMN_SCANNED_ID = "scanned_id";
	private static final String COLUMN_SCANNED_QRCODE = "code";

	private String CREATE_SCANNED_TABLE = "CREATE TABLE " + TABLE_SCANNED + "(" + COLUMN_SCANNED_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ COLUMN_SCANNED_QRCODE + " TEXT)";

	private String DROP_SCANNED_TABLE = "DROP TABLE IF EXISTS" + TABLE_SCANNED;














	public DatabaseHelper(Context context) {
		super( context, DATABASE_NAME, null, DATABASE_VERSION,
				R.raw.ormlite_config );

		context1 = context;
		//getWritableDatabase();
	}

	@Override
	public void setWriteAheadLoggingEnabled(boolean enabled) {
		super.setWriteAheadLoggingEnabled(enabled);
	}

	/**
	 * This is called when the database is first created. Usually you should
	 * call createTable statements here to create the tables that will store
	 * your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
//			db.enableWriteAheadLogging();

			setWriteAheadLoggingEnabled(true);
			TableUtils.createTable(connectionSource, Admin.class);
			TableUtils.createTable(connectionSource, Faculty.class);
			TableUtils.createTable(connectionSource, Student.class);
			TableUtils.createTable(connectionSource, Book.class);

			db.execSQL(CREATE_SCANNED_TABLE);


			//**********@@@***************Log.e("onCreate Database Info. = ", "db="+db.toString()+" Connection String"+connectionSource.toString());
		} catch (SQLException e) {
			//Log.d(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * This is called when your application is upgraded and it has a higher
	 * version number. This allows you to adjust the various data to match the
	 * new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
                          int oldVersion, int newVersion)
	{
		try {
			//Log.i(DatabaseHelper.class.getName(), "onUpgrade");

			TableUtils.dropTable(connectionSource, Admin.class, true);
			TableUtils.dropTable(connectionSource, Faculty.class, true);
			TableUtils.dropTable(connectionSource, Student.class, true);
			TableUtils.dropTable(connectionSource, Book.class, true);


			db.execSQL(DROP_SCANNED_TABLE);

			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);

			//**********@@@***************Log.e("onUpgrade Database Info. = ", "db="+db.toString()+" Connection String = "+connectionSource.toString());

		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao
	 * for our Appointments class. It will create it or just give the cached
	 * value. RuntimeExceptionDao only through RuntimeExceptions.
	 */
	public RuntimeExceptionDao<Admin, Integer> getAdminDao() {
		if (adminDao == null) {
			adminDao = getRuntimeExceptionDao(Admin.class);
		}
		return adminDao;
	}
	public RuntimeExceptionDao<Faculty, Integer> getFacultyDao() {
		if ( facultyDao == null) {
			facultyDao = getRuntimeExceptionDao(Faculty.class);
		}
		return facultyDao;
	}
	public RuntimeExceptionDao<Student, Integer> getStudentDao() {
		if ( studentDao == null) {
			studentDao = getRuntimeExceptionDao(Student.class);
		}
		return studentDao;
	}

	public RuntimeExceptionDao<Book, Integer> getBookDao() {
		if ( bookDao == null) {
			bookDao = getRuntimeExceptionDao(Book.class);
		}
		return bookDao;
	}


	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		adminDao = null;
		facultyDao = null;
		studentDao = null;
		bookDao = null;


	}








	/**
	 * This method adds
	 * @param item = item that will be added to the database
	 * @return a boolean if the code was added to the database or not
	 */
	public boolean addData(String item){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(COLUMN_SCANNED_QRCODE, item);
		//Insert Data into Database with a checking if the insert into the database worked
		long result = db.insert(TABLE_SCANNED, null, values);
		if(result == -1){
			db.close();
			return false;
		} else {
			db.close();
			return true;
		}
	}

	/**
	 * Returns all the data from the database
	 * @return a Cursor pointing on the requested table
	 */
	public Cursor getData(){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM " + TABLE_SCANNED;
		Cursor data = db.rawQuery(query, null);
		return data;
	}

	/**
	 * Returns the ID of the scanned code given as a parameter
	 * @param code
	 * @return a Cursor pointing on the id
	 */
	public Cursor getItemID(String code){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT " + COLUMN_SCANNED_ID + " FROM " + TABLE_SCANNED + " WHERE " + COLUMN_SCANNED_QRCODE + " = '" + code + "'";
		Cursor data = db.rawQuery(query, null);
		return  data;

	}

	/**
	 * Delete a specific Item from database
	 * @param id
	 * @param code
	 */
	public void deleteItem(int id, String code){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "DELETE FROM " + TABLE_SCANNED + " WHERE " + COLUMN_SCANNED_ID + " = '" + id + "'";
		db.execSQL(query);
	}

	public void resetDatabase(){
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "DELETE FROM " + TABLE_SCANNED;
		db.execSQL(query);
	}





}
