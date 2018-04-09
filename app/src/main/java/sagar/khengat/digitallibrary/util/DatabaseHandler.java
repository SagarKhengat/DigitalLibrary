package sagar.khengat.digitallibrary.util;

import android.content.Context;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sagar.khengat.digitallibrary.R;
import sagar.khengat.digitallibrary.model.Admin;
import sagar.khengat.digitallibrary.model.Book;
import sagar.khengat.digitallibrary.model.Faculty;
import sagar.khengat.digitallibrary.model.Student;


public class DatabaseHandler {
	private static final String TAG = "DatabaseHandler";


	private RuntimeExceptionDao<Admin, Integer> adminDao;
	private RuntimeExceptionDao<Faculty, Integer> facultyDao;
	private RuntimeExceptionDao<Student, Integer> studentDao;
	private RuntimeExceptionDao<Book, Integer> bookDao;


	private DatabaseHelper databaseHelper;

	private Context context;

	public DatabaseHandler() {

	}

	public DatabaseHandler(Context context) {
		this.context = context;
		initElements();
	}

	public void initElements() {


		adminDao = getHelper().getAdminDao();
		facultyDao = getHelper().getFacultyDao();
		studentDao = getHelper().getStudentDao();
		bookDao = getHelper().getBookDao();






	}




	private DatabaseHelper getHelper() {
		databaseHelper = null;
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(context,
					DatabaseHelper.class);
		}
		return databaseHelper;
	}

	/**
	 * This method to check user exist or not
	 *
	 * @param email
	 * @return true/false
	 */
	public boolean checkAdmin(String email) {
		boolean b = false;
		List<Admin> mListAllStores = fnGetAllAdmin();
		try {
			QueryBuilder < Admin, Integer> qb = adminDao.queryBuilder();

			for (Admin user:
					mListAllStores) {

				if (user.getName().equals(email))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}


	/**
	 * This method to check user exist or not
	 *
	 * @param email
	 * @return true/false
	 */
//	public boolean checkRetailerWithNumber(String email, String number) {
//		boolean b = false;
//		List<Retailer> mListAllStores = fnGetAllRetailer();
//		try {
//			QueryBuilder < Retailer, Integer> qb = retailerDao.queryBuilder();
//
//			for (Retailer user:
//					mListAllStores) {
//
//				if (user.getName().equals(email) && user.getMobno().equals(number))
//				{
//					b = true;
//				}
//				else
//				{
//
//				}
//
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return b;
//	}

	public boolean checkFaculty(String email) {
		boolean b = false;
		List<Faculty> mListAllStores = fnGetAllFaculty();
		try {
			QueryBuilder < Faculty, Integer> qb = facultyDao.queryBuilder();

			for (Faculty user:
					mListAllStores) {

				if (user.getFacultyId().equals(email))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}
	public boolean checkStudent(String email) {
		boolean b = false;
		List<Student> mListAllStores = fnGetAllStudent();
		try {
			QueryBuilder < Student, Integer> qb = studentDao.queryBuilder();

			for (Student user:
					mListAllStores) {

				if (user.getStudentId().equals(email))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}


//	public boolean checkStore(String email) {
//		boolean b = false;
//		List<Store> mListAllStores = fnGetAllStore();
//		try {
//			QueryBuilder < Store, Integer> qb = storeDao.queryBuilder();
//
//			for (Store user:
//					mListAllStores) {
//
//				if (user.getStoreName().equals(email))
//				{
//					b = true;
//				}
//				else
//				{
//
//				}
//
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return b;
//	}
//
//	public boolean checkArea(Area email) {
//		boolean b = false;
//		List<Area> mListAllStores = fnGetAllArea();
//		try {
//			QueryBuilder < Area, Integer> qb = areaDao.queryBuilder();
//
//			for (Area user:
//					mListAllStores) {
//
//				if (user.getAreaName().equals(email.getAreaName()))
//				{
//					b = true;
//				}
//				else
//				{
//
//				}
//
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return b;
//	}
//
//
//	public boolean checkCategory(Category email) {
//		boolean b = false;
//		List<Category> mListAllStores = fnGetAllCategory();
//		try {
//			QueryBuilder < Category, Integer> qb = categoryDao.queryBuilder();
//
//			for (Category user:
//					mListAllStores) {
//
//				if (user.getCategoryName().equals(email.getCategoryName()))
//				{
//					b = true;
//				}
//				else
//				{
//
//				}
//
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return b;
//	}
//
//
//	public boolean checkSubCategory(SubCategory email) {
//		boolean b = false;
//		List<SubCategory> mListAllStores = fnGetAllSubCategory();
//		try {
//			QueryBuilder < SubCategory, Integer> qb = subCategoryDao.queryBuilder();
//
//			for (SubCategory user:
//					mListAllStores) {
//
//				if (user.getSubCategoryName().equals(email.getSubCategoryName()))
//				{
//					b = true;
//				}
//				else
//				{
//
//				}
//
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return b;
//	}



	/**
	 * This method to check user exist or not
	 *
	 * @param email
	 * @param password
	 * @return true/false
	 */
	public boolean checkFaculty(String email, String password) {

		boolean b = false;
		List<Faculty> mListAllStores = fnGetAllFaculty();
		try {
			QueryBuilder < Faculty, Integer> qb = facultyDao.queryBuilder();

			for (Faculty user:
					mListAllStores) {

				if (user.getFacultyId().equals(email) && user.getPassword().equals(password))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}

	public boolean checkStudent(String email, String password) {

		boolean b = false;
		List<Student> mListAllStores = fnGetAllStudent();
		try {
			QueryBuilder < Student, Integer> qb = studentDao.queryBuilder();

			for (Student user:
					mListAllStores) {

				if (user.getStudentId().equals(email) && user.getPassword().equals(password))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}


	public boolean checkBook(String username) {

		boolean b = false;
		List<Book> mListAllStores = fnGetAllBook();
		try {
			QueryBuilder < Book, Integer> qb = bookDao.queryBuilder();

			for (Book user:
					mListAllStores) {

				if (user.getBookId().equals(username))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}

	/**
	 * This method to check user exist or not
	 *
	 * @param email
	 * @param password
	 * @return true/false
	 */
	public boolean checkAdmin(String email, String password) {

		boolean b = false;
		List<Admin> mListAllStores = fnGetAllAdmin();
		try {
			QueryBuilder < Admin, Integer> qb = adminDao.queryBuilder();

			for (Admin user:
					mListAllStores) {

				if (user.getName().equals(email) && user.getPassword().equals(password))
				{
					b = true;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}


	public void addAdmin(Admin user) {
		try
		{
			adminDao.create( user );
			Toast.makeText(context, context.getString(R.string.success_message), Toast.LENGTH_LONG).show();

		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			Toast.makeText( context, "Problem in adding User. Please try again.", Toast.LENGTH_LONG ).show();

			e.printStackTrace();
		}
	}

	public void addStudent(Student user) {
		try
		{
			studentDao.create( user );
			Toast.makeText(context, context.getString(R.string.success_message), Toast.LENGTH_LONG).show();

		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			Toast.makeText( context, "Problem in adding Student. Please try again.", Toast.LENGTH_LONG ).show();

			e.printStackTrace();
		}
	}

	public void addFaculty(Faculty user) {
		try
		{
			facultyDao.create( user );
			Toast.makeText(context, context.getString(R.string.success_message), Toast.LENGTH_LONG).show();

		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			Toast.makeText( context, "Problem in adding Faculty. Please try again.", Toast.LENGTH_LONG ).show();

			e.printStackTrace();
		}
	}

//	public boolean addRetailer(Retailer user) {
//		boolean b = false;
//		try
//		{
//			retailerDao.create( user );
//			Toast.makeText(context, context.getString(R.string.success_message), Toast.LENGTH_LONG).show();
//			b = true;
//
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
//		} catch(Exception e) {
//			Toast.makeText( context, "Problem in adding User. Please try again.", Toast.LENGTH_LONG ).show();
//
//			e.printStackTrace();
//		}
//		return  b;
//	}
//
//
//	public List<Store> fnGetAllStore() {
//		List< Store > mListIndustry = new ArrayList<>();
//
//		try {
//			QueryBuilder< Store, Integer> queryBuilder = storeDao.queryBuilder();
//			PreparedQuery< Store > preparedQuery = null;
//			preparedQuery = queryBuilder.prepare();
//			mListIndustry = storeDao.query( preparedQuery );
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		return mListIndustry;
//	}



	public List<Admin> fnGetAllAdmin() {
		List< Admin > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Admin, Integer> queryBuilder = adminDao.queryBuilder();
			PreparedQuery< Admin > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = adminDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mListIndustry;
	}


	public List<Faculty> fnGetAllFaculty() {
		List< Faculty > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Faculty, Integer> queryBuilder = facultyDao.queryBuilder();
			PreparedQuery< Faculty > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = facultyDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mListIndustry;
	}

	public List<Student> fnGetAllStudent() {
		List< Student > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Student, Integer> queryBuilder = studentDao.queryBuilder();
			PreparedQuery< Student > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = studentDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mListIndustry;
	}


//	public List<History> fnGetAllHistory() {
//		List< History > mListIndustry = new ArrayList<>();
//
//		try {
//			QueryBuilder< History, Integer> queryBuilder = historyDao.queryBuilder();
//			PreparedQuery< History > preparedQuery = null;
//			preparedQuery = queryBuilder.prepare();
//			mListIndustry = historyDao.query( preparedQuery );
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		return mListIndustry;
//	}

	public List<Book> fnGetAllBook() {
		List< Book > mListIndustry = new ArrayList<>();

		try {
			QueryBuilder< Book, Integer> queryBuilder = bookDao.queryBuilder();
			PreparedQuery< Book > preparedQuery = null;
			preparedQuery = queryBuilder.prepare();
			mListIndustry = bookDao.query( preparedQuery );
		} catch ( SQLException e ) {
			e.printStackTrace();
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}

		return mListIndustry;
	}



//	public List<Cart> fnGetAllCart() {
//		List< Cart > mListIndustry = new ArrayList<>();
//
//		try {
//			QueryBuilder< Cart, Integer> queryBuilder = cartDao.queryBuilder();
//			PreparedQuery< Cart > preparedQuery = null;
//			preparedQuery = queryBuilder.prepare();
//			mListIndustry = cartDao.query( preparedQuery );
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//
//		return mListIndustry;
//	}
//
//
//
//	public List<Area> fnGetAllArea() {
//		List< Area > mListIndustry = new ArrayList<>();
//
//		try {
//			QueryBuilder< Area, Integer> queryBuilder = areaDao.queryBuilder();
//			PreparedQuery< Area > preparedQuery = null;
//			preparedQuery = queryBuilder.prepare();
//			mListIndustry = areaDao.query( preparedQuery );
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return mListIndustry;
//	}
//
//
//
//	public List<Category> fnGetAllCategory() {
//		List< Category > mListIndustry = new ArrayList<>();
//
//		try {
//			QueryBuilder< Category, Integer> queryBuilder = categoryDao.queryBuilder();
//			PreparedQuery< Category > preparedQuery = null;
//			preparedQuery = queryBuilder.prepare();
//			mListIndustry = categoryDao.query( preparedQuery );
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return mListIndustry;
//	}
//
//
//
//	public List<SubCategory> fnGetAllSubCategory() {
//		List< SubCategory > mListIndustry = new ArrayList<>();
//
//		try {
//			QueryBuilder< SubCategory, Integer> queryBuilder = subCategoryDao.queryBuilder();
//			PreparedQuery< SubCategory > preparedQuery = null;
//			preparedQuery = queryBuilder.prepare();
//			mListIndustry = subCategoryDao.query( preparedQuery );
//		} catch ( SQLException e ) {
//			e.printStackTrace();
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		return mListIndustry;
//	}
//
//
//
//	public void addStore(Store store) {
//		try
//		{
//			storeDao.create( store );
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void addCategory(Category store) {
//		try
//		{
//			categoryDao.create( store );
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void addSubCategory(SubCategory store) {
//		try
//		{
//			subCategoryDao.create( store );
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//
//	public void addArea(Area area) {
//		try
//		{
//			areaDao.create( area );
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//
//	public List< Store> fnGetStoreInArea(Area area ) {
//		List<Store> mListStores = new ArrayList<>();
//		List<Store> mListAllStores = fnGetAllStore();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Store store : mListAllStores)
//			{
//				if(store.getArea().getAreaId()==area.getAreaId())
//				{
//					mListStores.add(store);
//				}
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return mListStores;
//	}
//	public List< SubCategory> fnGetSubCategoriesInCategory(Category area ) {
//		List<SubCategory> mListStores = new ArrayList<>();
//		List<SubCategory> mListAllStores = fnGetAllSubCategory();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (SubCategory store : mListAllStores)
//			{
//				if(store.getCategory().getCategoryId()==area.getCategoryId())
//				{
//					mListStores.add(store);
//				}
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return mListStores;
//	}


	public void addBook(Book book) {
		try
		{
			bookDao.create( book );
		} catch(OutOfMemoryError e) {
			e.printStackTrace();
			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

//	public void addToCart(Cart product) {
//		try
//		{
//			cartDao.create( product );
//			Toast.makeText( context, "Product Added in Cart Successfully.", Toast.LENGTH_LONG ).show();
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
//		} catch(Exception e) {
//			Toast.makeText( context, "Something went wrong. Please try again.", Toast.LENGTH_LONG ).show();
//			e.printStackTrace();
//		}
//	}
//
//
//	public int fnGetCartCount(Customer store)
//	{
//		int i = 0;
//
//
//		List<Cart> mListStores = new ArrayList<>();
//		List<Cart> mListAllStores = fnGetAllCart();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Cart cart : mListAllStores)
//			{
//				if(cart.getCustomer().getId()==store.getId())
//				{
//					mListStores.add(cart);
//				}
//			}
//
//			if(mListStores.size()!=0) {
//				i = mListStores.size();
//			}
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//
//		return i;
//	}
//
//
//	public List<Cart> fnGetAllCart(Customer store)
//	{
//
//		List<Cart> mListStores = new ArrayList<>();
//		List<Cart> mListAllStores = fnGetAllCart();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Cart cart : mListAllStores)
//			{
//				if(cart.getCustomer().getId()==store.getId())
//				{
//					mListStores.add(cart);
//				}
//			}
//
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//
//		return mListStores;
//	}
//
//
//
//	public void deleteCartitem(Cart cart) {
//		try
//		{
//			cartDao.delete(cart);
//			Toast.makeText(context, "Product Deleted Successfully", Toast.LENGTH_LONG).show();
//
//		}
//		catch(OutOfMemoryError e)
//		{
//			e.printStackTrace();
//			Toast.makeText(context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG).show();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//	}
//
//
//	public void fnDeleteAllCart(Customer store)
//	{
//
//		List<Cart> mListStores = new ArrayList<>();
//		List<Cart> mListAllStores = fnGetAllCart();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Cart cart : mListAllStores)
//			{
//				if(cart.getCustomer().getId()==store.getId())
//				{
//					cartDao.delete(cart);
//				}
//			}
//
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//
//
//	}
//
//
//
//	public List<History> fnGetAllHistory(Store store)
//	{
//
//		List<History> mListStores = new ArrayList<>();
//		List<History> mListAllStores = fnGetAllHistory();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (History event : mListAllStores) {
//				boolean isFound = false;
//				// check if the event name exists in noRepeat
//				for (History e : mListStores) {
//					if (e.getProductName().equals(event.getProductName()) && e.getProductBrand().equals(event.getProductBrand()))
//					isFound = true;
//					break;
//				}
//				if (!isFound) mListStores.add(event);
//			}
//
//
////			for (History cart : mListAllStores)
////			{
////
////				mListStores.add(cart);
////
////				for(History carta : mListStores)
////				{
////
////					if (  cart.getProductName().equals(carta.getProductName()) && cart.getProductBrand().equals(carta.getProductBrand()))
////					{
////						mListAllStores.remove(carta);
////					}
////				}
////			}
//
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//
//		return mListStores;
//	}
//
//
//
//	public void addProductHistory(History product) {
//		try
//		{
//			historyDao.createIfNotExists( product );
//		} catch(OutOfMemoryError e) {
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in memory allocation. Please free some memory space and try again.", Toast.LENGTH_LONG ).show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public Product fnGetProductFromCart(Cart cart)
//	{
//
//
//
//		List<Product> mListAllStores = fnGetAllProduct();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Product product : mListAllStores)
//			{
////					if(product.getProductId() == cart.getProductCartId())
////					{
////						return product;
////					}
//			}
//
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//
//		return null;
//	}
//
//
////	public Cart fnGetCartFromCartHistory(History cart)
////	{
////
////
////
////		List <Cart> mListAllStores = fnGetAllCart();
////
////		try {
//////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
//////			Where<Store, Integer> where = qb.where();
//////
//////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
//////
//////
//////
//////			// It filters only data present in DB fetched at the time of sync.
//////			PreparedQuery < Store> pq = where.prepare();
//////			mListStores = storeDao.query( pq );
////
////
////			for (Cart product : mListAllStores)
////			{
////				if(product.getProductId().equals(cart.getProductId()))
////				{
////					return product;
////				}
////			}
////
////
////		} catch (Exception e) {
////
////			e.printStackTrace();
////		}
////
////
////		return null;
////	}
//
//
	public void updateAdminPassword(String Email, String Password)
	{
		try
		{
			UpdateBuilder<Admin, Integer> updateBuilder = adminDao.updateBuilder();
			updateBuilder.where().eq("name",Email);
			updateBuilder.updateColumnValue("password",Password);
			updateBuilder.update();
			Toast.makeText( context, "Password changed Successfully...", Toast.LENGTH_LONG ).show();
		} catch(OutOfMemoryError e)
		{
			e.printStackTrace();
			Toast.makeText( context, "Problem in updating password try again.", Toast.LENGTH_LONG ).show();

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Toast.makeText( context, "Problem in updating password try again.", Toast.LENGTH_LONG ).show();

		}
	}
//
//	public void updateRetailerPassword(String Email, String Password)
//	{
//		try
//		{
//			UpdateBuilder<Retailer, Integer> updateBuilder = retailerDao.updateBuilder();
//			updateBuilder.where().eq("name",Email);
//			updateBuilder.updateColumnValue("password",Password);
//			updateBuilder.update();
//			Toast.makeText( context, "Password changed Successfully...", Toast.LENGTH_LONG ).show();
//		} catch(OutOfMemoryError e)
//		{
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in updating password try again.", Toast.LENGTH_LONG ).show();
//
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//			Toast.makeText( context, "Problem in updating password try again.", Toast.LENGTH_LONG ).show();
//
//		}
//	}


	public Faculty getFaculty(String retailerName, String password)
	{
		Faculty b = null;
		List<Faculty> mListAllStores = fnGetAllFaculty();
		try {
			QueryBuilder < Faculty, Integer> qb = facultyDao.queryBuilder();

			for (Faculty user:
					mListAllStores) {

				if (user.getFacultyId().equals(retailerName) && user.getPassword().equals(password))
				{
					b = user;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}


	public Admin getAdmin(String retailerName, String password)
	{
		Admin b = null;
		List<Admin> mListAllStores = fnGetAllAdmin();
		try {
			QueryBuilder < Admin, Integer> qb = adminDao.queryBuilder();

			for (Admin user:
					mListAllStores) {

				if (user.getName().equals(retailerName) && user.getPassword().equals(password))
				{
					b = user;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}


	public List<Book> getAvailableBooks()
	{
		List<Book> bookList = new ArrayList<>();
		List<Book> mListAllStores = fnGetAllBook();
		try {
			QueryBuilder < Book, Integer> qb = bookDao.queryBuilder();

			for (Book user:
					mListAllStores) {

				if (user.isAvailable())
				{
					bookList.add(user);
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bookList;
	}

	public List<Book> fnGetAllBooksForIssue()
	{
		List<Book> bookList = new ArrayList<>();
		List<Book> mListAllStores = fnGetAllBook();
		try {
			QueryBuilder < Book, Integer> qb = bookDao.queryBuilder();

			for (Book user:
					mListAllStores) {

				if (!user.isAvailable())
				{
					bookList.add(user);
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bookList;
	}
	public List<Book> getAvailableBooksofStudent(Student student)
	{
		List<Book> bookList = new ArrayList<>();
		List<Book> mListAllStores = fnGetAllBook();
		try {
			QueryBuilder < Book, Integer> qb = bookDao.queryBuilder();

			for (Book user:
					mListAllStores) {
				if(user.getBookStudent() != null) {
					if (user.getBookStudent().getStudentId().equals(student.getStudentId()) && user.issued()) {
						bookList.add(user);
					} else {

					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bookList;
	}

	public List<Book> getAvailableBooksofFaculty(Faculty faculty)
	{
		List<Book> bookList = new ArrayList<>();
		List<Book> mListAllStores = fnGetAllBook();
		try {
			QueryBuilder < Book, Integer> qb = bookDao.queryBuilder();

			for (Book user:
					mListAllStores) {
				if (user.getBookFaculty() != null) {
					if (user.getBookFaculty().getFacultyId().equals(faculty.getFacultyId())&& user.issued()) {
						bookList.add(user);
					} else {

					}

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bookList;
	}
	public Student getStudent(String retailerName, String password)
	{
		Student b = null;
		List<Student> mListAllStores = fnGetAllStudent();
		try {
			QueryBuilder < Student, Integer> qb = studentDao.queryBuilder();

			for (Student user:
					mListAllStores) {

				if (user.getName().equals(retailerName) && user.getPassword().equals(password))
				{
					b = user;
				}
				else
				{

				}

			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return b;
	}

//	public Store getStore(String storeName)
//	{
//		Store b = null;
//		List<Store> mListAllStores = fnGetAllStore();
//		try {
//			QueryBuilder < Store, Integer> qb = storeDao.queryBuilder();
//
//			for (Store user:
//					mListAllStores) {
//
//				if (user.getStoreName().equals(storeName))
//				{
//					b = user;
//				}
//				else
//				{
//
//				}
//
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return b;
//	}
//
//
//	public List<Product> fnGetAllProductInStore(Store store)
//	{
//		List<Product> mListStores = new ArrayList<>();
//		List<Product> mListAllStores = fnGetAllProduct();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Product product : mListAllStores)
//			{
//				if(product.getStore().getStoreId()==store.getStoreId())
//				{
//					mListStores.add(product);
//				}
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return mListStores;
//	}


	public void updateBook(Book book)
	{

		try
		{
			UpdateBuilder<Book, Integer> deleteBuilder = bookDao.updateBuilder();
			deleteBuilder.updateColumnValue("bookName", book.getBookName());
			deleteBuilder.updateColumnValue("bookCategory", book.getBookCategory());
			deleteBuilder.updateColumnValue("bookPublicationHouse", book.getBookPublicationHouse());
			deleteBuilder.updateColumnValue("bookAuther", book.getBookAuther());
			deleteBuilder.updateColumnValue("bookOriginalPrice", book.getBookOriginalPrice());
			deleteBuilder.updateColumnValue("bookPages", book.getBookPages());

			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.update();


		}
		catch(OutOfMemoryError e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updateBookForIssue(Book book)
	{

		try
		{
			UpdateBuilder<Book, Integer> deleteBuilder = bookDao.updateBuilder();
			deleteBuilder.updateColumnValue("issued", true);

			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.update();


		}
		catch(OutOfMemoryError e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void deleteBook(Book book)
	{
		try {
			DeleteBuilder<Book, Integer> deleteBuilder = bookDao
					.deleteBuilder();
			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.delete();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void addToBorrowListofStudent(Book book, Student student)
	{
		try {
			UpdateBuilder<Book, Integer> deleteBuilder = bookDao.updateBuilder();
			deleteBuilder.updateColumnValue("bookStudent_id", student);
			deleteBuilder.updateColumnValue("bookFaculty_id", null);
			deleteBuilder.updateColumnValue("available", false);
			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void returnBookofStudent(Book book, Student student)
	{
		try {
			UpdateBuilder<Book, Integer> deleteBuilder = bookDao.updateBuilder();
			deleteBuilder.updateColumnValue("bookStudent_id", null);
			deleteBuilder.updateColumnValue("bookFaculty_id", null);
			deleteBuilder.updateColumnValue("available", true);
			deleteBuilder.updateColumnValue("issued", false);
			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void returnBookofFaculty(Book book, Faculty faculty)
	{
		try {
			UpdateBuilder<Book, Integer> deleteBuilder = bookDao.updateBuilder();
			deleteBuilder.updateColumnValue("bookStudent_id", null);
			deleteBuilder.updateColumnValue("bookFaculty_id", null);
			deleteBuilder.updateColumnValue("available", true);
			deleteBuilder.updateColumnValue("issued", false);
			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void rejectBook(Book book)
	{
		try {
			UpdateBuilder<Book, Integer> deleteBuilder = bookDao.updateBuilder();
			deleteBuilder.updateColumnValue("bookStudent_id", null);
			deleteBuilder.updateColumnValue("bookFaculty_id", null);
			deleteBuilder.updateColumnValue("available", true);
			deleteBuilder.updateColumnValue("issued", false);
			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addToBorrowListofFaculty(Book book, Faculty faculty)
	{
		try {
			UpdateBuilder<Book, Integer> deleteBuilder = bookDao.updateBuilder();
			deleteBuilder.updateColumnValue("bookFaculty_id", faculty);
			deleteBuilder.updateColumnValue("bookStudent_id", null);
			deleteBuilder.updateColumnValue("available", false);
			deleteBuilder.where().eq("bookId", book.getBookId());
			deleteBuilder.update();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

//	public List<Product> fnGetAllProductForCustomer(Store store, Area area, Category category, SubCategory subCategory)
//	{
//		List<Product> mListStores = new ArrayList<>();
//		List<Product> mListAllStores = fnGetAllProduct();
//
//		try {
////			QueryBuilder < Store, Integer > qb = storeDao.queryBuilder();
////			Where<Store, Integer> where = qb.where();
////
////			where.like( "areaId", area.getAreaId() );//.or().like("customerPrintAs", "%"+nameToSearch+"%");
////
////
////
////			// It filters only data present in DB fetched at the time of sync.
////			PreparedQuery < Store> pq = where.prepare();
////			mListStores = storeDao.query( pq );
//
//
//			for (Product product : mListAllStores)
//			{
//				if(product.getStore().getStoreId()==store.getStoreId() && product.getProductArea().getAreaId()==area.getAreaId()
//						 && product.getProductCategory().getCategoryId()==category.getCategoryId()
//						&&product.getProductSubCategory().getSubCategoryId()==subCategory.getSubCategoryId())
//				{
//					mListStores.add(product);
//				}
//			}
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return mListStores;
//	}

}