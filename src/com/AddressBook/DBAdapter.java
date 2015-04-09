package com.AddressBook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{
	public static final String KEY_ROWID = "_id";
    public static final String KEY_FNAME = "fname";
    public static final String KEY_LNAME = "lname";
    public static final String KEY_PHONE = "phone";    
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ADDRESS = "address";
        
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "DBAddressBook";
    private static final String DATABASE_TABLE = "contacts";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table contacts (_id integer primary key autoincrement, "
        + "fname text not null, lname text not null, " 
        + "phone text not null,email text not null,address text not null);";
    
    private final Context context; 
    
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;
    
    public DBAdapter(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
        int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                    + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }    
    
    //---opens the database---
    public DBAdapter open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a contact into the database---
    public long insertContact(String fname, String lname, String phone, String email,String address) 
    {
        ContentValues initialValues = new ContentValues();
        
        initialValues.put(KEY_FNAME, fname);
        initialValues.put(KEY_LNAME, lname);
        initialValues.put(KEY_PHONE, phone);
        initialValues.put(KEY_EMAIL, email);
        initialValues.put(KEY_ADDRESS, address);
        
        return db.insert(DATABASE_TABLE, null, initialValues);
        
    }

  //---deletes a particular contact---
    public boolean deleteContact(int id) 
    {
        //return db.delete(DATABASE_TABLE, KEY_FNAME LIKE+ id, null) > 0;
    	//db.execSQL("DELETE FROM CONTACTS WHERE FNAME="+id+";");
    	db.execSQL("DELETE FROM CONTACTS WHERE _ID="+id+";");
    	return true;
    }
    
    //---retrieves all the contacts---
    public Cursor getAllContacts() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ROWID, 
        		KEY_FNAME,
        		KEY_LNAME,
                KEY_PHONE,
                KEY_EMAIL,
                KEY_ADDRESS}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    
    //---retrieves a particular contact---
  public Cursor getContact(String rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ROWID,
                		KEY_FNAME, 
                		KEY_LNAME,
                		KEY_PHONE,
                		KEY_EMAIL,KEY_ADDRESS
                		}, 
                		KEY_FNAME + "='" + rowId+"'", 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
  public Cursor getContactLastName(String rowId) throws SQLException 
  {
      Cursor mCursor =
              db.query(true, DATABASE_TABLE, new String[] {
              		KEY_ROWID,
              		KEY_FNAME, 
              		KEY_LNAME,
              		KEY_PHONE,
              		KEY_EMAIL,KEY_ADDRESS
              		}, 
              		KEY_LNAME + "='" + rowId+"'", 
              		null,
              		null, 
              		null, 
              		null, 
              		null);
      if (mCursor != null) {
          mCursor.moveToFirst();
      }
      return mCursor;
  }    
}
