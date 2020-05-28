package mnstate.example.projectthree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="database";
    static int version=1;

    String createTableUser="CREATE TABLE if not exists \"user\" (\n" +
            "\t\"id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"gender\"\tTEXT,\n" +
            "\t\"status\"\tTEXT,\n" +
            "\t\"dob\"\tTEXT,\n" +
            "\t\"phnnum\"\tTEXT\n" +
            ")";


    public DatabaseHelper( Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
    }

    public void insertUser(ContentValues contentValues){
        getWritableDatabase().insert("user","",contentValues);
    }
    public boolean isLoginValid(String username, String password){
        String sql="Select count(*) from user where username='" +username+"' and password='" +password+"'";
        SQLiteStatement statement =getReadableDatabase().compileStatement(sql);
        long l=statement.simpleQueryForLong();
        statement.close();

        if(l==1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //retrieving data

    public Cursor alldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from user",null);
        return cursor;

    }
}