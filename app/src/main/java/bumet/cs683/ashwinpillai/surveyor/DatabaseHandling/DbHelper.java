//package bumet.cs683.ashwinpillai.surveyor.DatabaseHandling;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.res.Resources;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//
//import bumet.cs683.ashwinpillai.surveyor.Answers;
//
///**
// * Created by ashwinpillai on 10/25/17.
// */
//
//public class DbHelper extends SQLiteOpenHelper {
//    private static final String TAG = DbHelper.class.getSimpleName();
//
//    private Resources mResources;
//    private static final String DATABASE_NAME = "answers.db";
//    private static final int DATABASE_VERSION = 1;
//    Context context;
//    SQLiteDatabase db;
//
//
//    public DbHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//
//        mResources = context.getResources();
//
//        db = this.getWritableDatabase();
//    }
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//        final String SQL_CREATE_ANSWERS_TABLE = "CREATE TABLE " + DbContract.resultsEntry.TABLE_NAME + " (" +
//                DbContract.resultsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
//                DbContract.resultsEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
//                DbContract.resultsEntry.COLUMN_QUESTION + " TEXT UNIQUE NOT NULL, " +
//                DbContract.resultsEntry.COLUMN_ANSWER + " TEXT NOT NULL " + " );";
//
//
//        db.execSQL(SQL_CREATE_ANSWERS_TABLE);
//        Log.d(TAG, "Database Created Successfully" );
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//
//    }
//
//    public void readDataToDb(Answers answer_object) throws IOException, JSONException {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//            String question = answer_object.getQuestion();
//            String answer = answer_object.getAnswer();
//
//
//                ContentValues resultValues = new ContentValues();
//
//                resultValues.put(DbContract.resultsEntry.COLUMN_QUESTION, question);
//                resultValues.put(DbContract.resultsEntry.COLUMN_ANSWER, answer);
//
//                db.insert(DbContract.resultsEntry.TABLE_NAME, null, resultValues);
//
//
//                Log.d(TAG, "Inserted Successfully " + resultValues );
//
//
//
//
//
//    }
//
//}
