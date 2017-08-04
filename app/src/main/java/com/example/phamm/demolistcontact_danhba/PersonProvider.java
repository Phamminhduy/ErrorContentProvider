package com.example.phamm.demolistcontact_danhba;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by phamm on 8/3/2017.
 */

public class PersonProvider extends ContentProvider {
    static  final String PROVIDER_NAME ="com.example.provider.NumberPhone";
    static final String URL = "content://"+PROVIDER_NAME+"/numbersPhone";
    static final Uri CONTENT_URI = Uri.parse(URL);

    static final String _ID = "_id";
    static final String NAME = "name";
    static final String PHONENUMBER="phone";

    static HashMap<String,String> PHONENUMBER_PROJECT_MAP;

    static final int NUMBER=1;
    static final int NUMBER_ID=2;
    static final UriMatcher uriMatcher;
    static{
        uriMatcher = new UriMatcher((UriMatcher.NO_MATCH));
        uriMatcher.addURI(PROVIDER_NAME,"numbersphone",NUMBER);
        uriMatcher.addURI(PROVIDER_NAME,"numbersphone/#",NUMBER_ID);
    }
    //database specific constant declaration
    private SQLiteDatabase db;
    static final String DATABASE_NAME="NumberPhone";
    static final String NUMBERPHONE_TABLE_NAME="numbers";
    static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE=" CREATE TABLE " +NUMBERPHONE_TABLE_NAME+
            " (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
            " name TEXT NOT NULL, "+" phone TEXT NOT NULL); )";

    private static class DataBaseHelper extends SQLiteOpenHelper{

        DataBaseHelper(Context context){

            super(context,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_DB_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+NUMBERPHONE_TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }
    @Override
    public boolean onCreate() {
        Context context = getContext();
        DataBaseHelper dbhelper = new DataBaseHelper(context);
        db = dbhelper.getWritableDatabase();
        return (db==null)?false:true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        SQLiteQueryBuilder qb =new  SQLiteQueryBuilder();
        qb.setTables(NUMBERPHONE_TABLE_NAME);
        switch (uriMatcher.match(uri)){
            case NUMBER:
                qb.setProjectionMap(PHONENUMBER_PROJECT_MAP);
                break;
            case NUMBER_ID:
                qb.appendWhere(_ID + "= " +uri.getPathSegments().get(1));
                break;
        }
        if (s1 == null || s1 == ""){

            s1=NAME;
        }
        Cursor cs = qb.query(db,strings,s,strings1,null,null,s1);
        cs.setNotificationUri(getContext().getContentResolver(),uri);
        return cs;

    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case NUMBER:return "vnd.android.cursor.dir/vnd.example.numbersphone";
            case NUMBER_ID: return "vnd.android.cursor.item/vnd/example.numbersphone";
            default: throw new IllegalArgumentException("Unsupported URI"+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        long rowID = db.insert(NUMBERPHONE_TABLE_NAME,"",contentValues);
        if(rowID>0){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI,rowID);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }
        throw  new SQLException("KHÔNG THÊM ĐƯỢC DỮ LIỆU:"+uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int count =0;
        switch (uriMatcher.match(uri)){
            case  NUMBER: count = db.delete(NUMBERPHONE_TABLE_NAME,s,strings);
                break;
            case NUMBER_ID: String id = uri.getPathSegments().get(1);
                count = db.delete(NUMBERPHONE_TABLE_NAME,_ID+" = "+ id +(!TextUtils.isEmpty(s)?"AND("+s+')':""),strings);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI"+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

}
