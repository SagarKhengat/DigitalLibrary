package sagar.khengat.digitallibrary.util;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import sagar.khengat.digitallibrary.model.Admin;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.model.Faculty;
import sagar.khengat.digitallibrary.model.Student;


/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil
{
	
	
	@SuppressWarnings("rawtypes")
	static Class[] classes = new Class[]{Admin.class, Faculty.class, Student.class, Book.class};
	
	public static void main(String[] args) throws SQLException, IOException {
		writeConfigFile("ormlite_config.txt",classes);
	}

}
