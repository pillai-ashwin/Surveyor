package bumet.cs683.ashwinpillai.surveyor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidadvance.androidsurvey.SurveyActivity;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//  GoogleApiClient mGoogleApiClient;
    private static final int SURVEY_REQUEST = 1337;
    Button surveyagain_button;
    Button reports_button;
    Button quit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        surveyagain_button = findViewById(R.id.surveyagain_button);
        surveyagain_button.setOnClickListener(this);
        reports_button = findViewById(R.id.reports_button);
        reports_button.setOnClickListener(this);
        quit = findViewById(R.id.quit_button);
        quit.setOnClickListener(this);

//        // Configure sign-in to request the user's ID, email address, and basic
//        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        // Build a GoogleApiClient with access to the Google Sign-In API and the
//        // options specified by gso.
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
//                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                .build();
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.surveyagain_button:
                Intent i_survey = new Intent(this, SurveyActivity.class);
                //you have to pass as an extra the json string.
                i_survey.putExtra("json_survey", loadSurveyJson("questions.json"));
                startActivityForResult(i_survey, SURVEY_REQUEST);
                break;
            case R.id.reports_button:
                Intent i_report = new Intent(this,ResultDisplayActivity.class);
                startActivity(i_report);
                break;
            case R.id.quit_button:
                this.finish();
                System.exit(0);
                break;

        }
    }
//    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

private String loadSurveyJson(String filename) {
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SURVEY_REQUEST) {
            if (resultCode == RESULT_OK) {

                String answers_json = data.getExtras().getString("answers");
                Log.d("****", "****************** WE HAVE ANSWERS ******************");
                Log.v("ANSWERS JSON", answers_json);
                Log.d("****", "*****************************************************");
                Intent intent = new Intent(this,ResultDisplayActivity.class);
                intent.putExtra("answers",answers_json);
                startActivity(intent);
            }
        }
    }
}
