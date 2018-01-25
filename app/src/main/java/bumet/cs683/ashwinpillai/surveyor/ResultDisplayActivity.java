package bumet.cs683.ashwinpillai.surveyor;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.greendao.database.Database;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//import bumet.cs683.ashwinpillai.surveyor.DatabaseHandling.DbHelper;

/**
 * Created by ashwinpillai on 10/25/17.
 **/

public class ResultDisplayActivity extends AppCompatActivity{
    private static final String TAG = "ResultDisplayActivity";
    public static DaoSession daoSession;
    public static final boolean ENCRYPTED = false;
    private ArrayList<Answers> arrayList_answers;
    private List<Answers> answersList;
    private Long timestamp;
    private Answers object_answer;
    public Integer count_1_1=0,count_1_2=0,count_2_1=0,count_2_2=0,count_3_1=0,count_3_2=0,count_4_1=0,count_4_2=0;
    //    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answers_list);

        //initialize dao session for DB handling
        intialize_daosession(this);



            Intent receivedintent = getIntent();
            Bundle data = receivedintent.getExtras();
            if (data!=null) {
                arrayList_answers = new ArrayList<>();

                try {
                    JSONObject answers = new JSONObject(data.getString("answers"));
//            dbHelper = new DbHelper(this);
//                    Log.i(TAG, "onCreate: JSON object/array" + answers);
//                    Log.i(TAG, "onCreate: key values" + answers.keys());
                    Iterator<String> iter = answers.keys();
                    timestamp = System.currentTimeMillis() / 1000;
                    while (iter.hasNext()) {
                        object_answer = new Answers();
                        String key = iter.next();
//                        Log.d(TAG, "onCreate: key : " + key);
//                        Log.i(TAG, "onCreate: value : " + answers.get(String.valueOf(key)));
                        object_answer.setTimestamp(timestamp);
                        object_answer.setQuestion(String.valueOf(key));
                        object_answer.setAnswer("" + String.valueOf(answers.get(String.valueOf(key))));
                        arrayList_answers.add(new Answers(timestamp, object_answer.getQuestion(), object_answer.getAnswer()));
//                dbHelper.readDataToDb(object_answer);
                        Answers.create(daoSession, object_answer.getTimestamp(), object_answer.getQuestion(), object_answer.getAnswer());
//                        Log.i(TAG, "onCreate: inserted values into the database greenDAO rocks");
                        // List view generate
                        AnswerAdapter answerAdapter = new AnswerAdapter(this,  arrayList_answers);
                        // Get a reference to the ListView, and attach the adapter to the listView.
                        ListView listView = findViewById(R.id.listview_answer);
                        listView.setAdapter(answerAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        answersList = object_answer.getAllData(daoSession);

        for (Answers a :answersList){
            String question = a.getQuestion();
//            Log.d(TAG, "onCreate: question "+question);
            String answer = a.getAnswer();
//            Log.d(TAG, "onCreate: answer "+answer);
            if(question.equals("Do you think you were productive today?")){
                if(answer.equals("Yes")){
                    count_1_1++;
//                    Log.d(TAG, "count increased");
                }
                else if(answer.equals("No")){
                    count_1_2++;
//                    Log.d(TAG, "count increased");

                }
            }
            else if(question.equals("Are you a programmer?")){
                if(answer.equals("Yes")){
                    count_2_1++;
//                    Log.d(TAG, "count increased");

                }
                else if(answer.equals("No")){
                    count_2_2++;
//                    Log.d(TAG, "count increased");

                }
            }
            else if(question.equals("Did you sleep well yesterday?")){
                if(answer.equals("Yes")){
                    count_3_1++;
//                    Log.d(TAG, "count increased");

                }
                else if(answer.equals("No")){
                    count_3_2++;
//                    Log.d(TAG, "count increased");

                }
            }
            else if(question.equals("Do you like computer science?")){
                if(answer.equals("Yes")){
                    count_4_1++;
//                    Log.d(TAG, "count increased");

                }
                else if(answer.equals("No")){
                    count_4_2++;
//                    Log.d(TAG, "count increased");

                }
            }
        }
//        Log.i(TAG, "count values"+count_1_1+count_1_2+count_2_1+count_2_2+count_3_1+count_3_2+count_4_1+count_4_2);

        Intent intent = new Intent(this,ReportActivity.class);
        intent.putExtra("1_1", count_1_1.toString() );
        intent.putExtra("1_2",count_1_2.toString() );
        intent.putExtra("2_1", count_2_1.toString() );
        intent.putExtra("2_2",count_2_2.toString() );
        intent.putExtra("3_1",count_3_1.toString() );
        intent.putExtra("3_2",count_3_2.toString() );
        intent.putExtra("4_1",count_4_1.toString() );
        intent.putExtra("4_2",count_4_2.toString() );

//        Log.d(TAG, "onCreate: "+answersList.toString());
        if(data==null) {
            startActivity(intent);
        }
    }

    public void intialize_daosession(Context context){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, ENCRYPTED ? "answers-db-encrypted" : "answers-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
//        Log.i(TAG, "onCreate: -> DAO session created");
    }
}
