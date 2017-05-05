package com.example.user.androidtutorial;

/**
 * Created by Aldrich Cheong on 5/6/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class SQLiteHandler extends SQLiteOpenHelper{

    //Used to identify the class in console log for easier debugging
    private static final String TAG = SQLiteHandler.class.getSimpleName();

    //Configuration variables
    //Database version number
    private static final int DATABASE_VERSION = 1;

    //Name of database
    private static final String DATABASE_NAME = "login_db";

    //Name of table
    private static final String LOGIN_TABLE = "login";

    //Column names
    private static final String LOGIN_ID = "login_id";
    private static final String LOGIN_PWD = "login_pwd";

    //Constructor
    public SQLiteHandler(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Triggered when application is installed
    @Override
    public void onCreate(SQLiteDatabase db){
        createStuTable(db);
    }

    //Triggered when database is upgraded
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        dropLoginTable(db);
        createStuTable(db);
    }

    //Create login table using SQL syntax
    public void createStuTable(SQLiteDatabase db){
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + LOGIN_TABLE + "("
                + LOGIN_ID + " TEXT, " + LOGIN_PWD + " TEXT" + " )";
        db.execSQL(CREATE_STUDENT_TABLE);

        Log.d(TAG, "Login Table is created");
    }

    //Drop login table using SQL syntax
    public void dropLoginTable(SQLiteDatabase db){
        String DROP_LOGIN_TABLE = "DROP TABLE IF EXISTS " + LOGIN_TABLE;
        db.execSQL(DROP_LOGIN_TABLE);

        Log.d(TAG, "Login table is dropped");
    }

    //method to add new login details into the login table
    public void addId(String id, String pwd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LOGIN_ID, id);
        values.put(LOGIN_PWD, pwd);

        //insert value into db
        Long message = db.insert(LOGIN_TABLE, null, values);
        //close database connection
        db.close();

        Log.d(TAG, "New id has been added " + message);
    }

    //method to get the userID of the latest user in the login table
    public String getLoginId(){
        String id = "";
        String SELECT_ID = "SELECT " + LOGIN_ID + " FROM " + LOGIN_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ID, null);
        cursor.moveToLast();
        if(cursor.getCount() > 0){
            id = cursor.getString(0);
        }
        cursor.close();
        db.close();
        Log.d(TAG, "Id fetched from " + LOGIN_TABLE + " " + id);
        return id;
    }
}
