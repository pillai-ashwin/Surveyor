package bumet.cs683.ashwinpillai.surveyor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.PieChart;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    private static final String TAG = "ReportActivity";
    PieChart pieChart;
    private Integer count_1_1 = 0, count_1_2 = 0, count_2_1 = 0, count_2_2 = 0, count_3_1 = 0, count_3_2 = 0, count_4_1 = 0, count_4_2 = 0;
    private ArrayList<String> spinner_strings;
    String data = "'Yes','No'";
    String values;
    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);
        Intent intent = getIntent();

        count_1_1 = Integer.parseInt(intent.getStringExtra("1_1"));
        count_1_2 = Integer.parseInt(intent.getStringExtra("1_2"));
        count_2_1 = Integer.parseInt(intent.getStringExtra("2_1"));
        count_2_2 = Integer.parseInt(intent.getStringExtra("2_2"));
        count_3_1 = Integer.parseInt(intent.getStringExtra("3_1"));
        count_3_2 = Integer.parseInt(intent.getStringExtra("3_2"));
        count_4_1 = Integer.parseInt(intent.getStringExtra("4_1"));
        count_4_2 = Integer.parseInt(intent.getStringExtra("4_2"));
        Log.i(TAG, "onCreate: count values"+count_1_1+count_1_2+count_2_1+count_2_2+count_3_1+count_3_2+count_4_1+count_4_2);

        graphdisplay();

    }

    private String loadSurveyHtml(String filename) {
        try {
            InputStream is = this.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        if(null!=mWebview) {
            setContentView(R.layout.activity_reporting);
            graphdisplay();
            mWebview = null;
        }
        else{
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();

        }
    }

    private void show_bargraph(){
        String html_code = loadSurveyHtml("bargraph.html");
//        Log.d(TAG, "onCreate: loadSurveyHtml has read bargraph.html" +html_code);

        html_code = html_code.replace("#Values#",values);
        html_code = html_code.replace("#Data#",data);
        Log.d(TAG, "show_bargraph: HTML CODE AFTER REPLACE"+html_code);
        mWebview = new WebView(this);
        mWebview.setWebChromeClient(new WebChromeClient());
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.getSettings().setJavaScriptEnabled (true);
        mWebview.loadDataWithBaseURL(null, html_code, "text/html", "utf-8", null);
        setContentView(mWebview);

    }
    private void graphdisplay(){
        Spinner spinner =  findViewById(R.id.spinner);
        Button showvisualisation = findViewById(R.id.showvisualization_button);

        spinner_strings = new ArrayList<>();
        spinner_strings.add("Do you think you were productive today?") ;
        spinner_strings.add("Are you a programmer?");
        spinner_strings.add("Did you sleep well yesterday?");
        spinner_strings.add("Do you like computer science?");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,spinner_strings);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("Do you think you were productive today?"))
                {
                    values = ""+count_1_1+", "+count_1_2+"";
                    Log.d(TAG, "onItemSelected: values "+values);
                }
                if(selectedItem.equals("Are you a programmer?"))
                {
                    values = ""+count_2_1+", "+count_2_2+"";
                    Log.d(TAG, "onItemSelected: values "+values);
                }
                if(selectedItem.equals("Did you sleep well yesterday?"))
                {
                    values = ""+count_3_1+", "+count_3_2+"";
                    Log.d(TAG, "onItemSelected: values "+values);
                }
                if(selectedItem.equals("Do you like computer science?"))
                {
                    values = ""+count_4_1+", "+count_4_2+"";
                    Log.d(TAG, "onItemSelected: values "+values);
                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {
                Log.d(TAG, "onNothingSelected: Kuch nai select kiya hai");
            }
        });

        showvisualisation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_bargraph();
            }
        });
    }
}
