package bumet.cs683.ashwinpillai.surveyor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    private final static String TAG = "bumet.cs683";
    private ImageView surveyor_icon_ImageView;
    private ImageView surveyor_logo_ImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        surveyor_icon_ImageView = (ImageView) findViewById(R.id.surveyor_icon_ImageViewid);
        surveyor_logo_ImageView = (ImageView) findViewById(R.id.surveyor_logo_ImageViewid);
        Animation splash = AnimationUtils.loadAnimation(this,R.anim.splashscreen_transition);
        surveyor_icon_ImageView.startAnimation(splash);
        surveyor_logo_ImageView.startAnimation(splash);
        final Intent intent = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2500);
                }catch (InterruptedException exception){
                    exception.printStackTrace();
                }finally {
                    startActivity(intent);
                    finish();
                    Log.d(TAG,"SplashScreen display successfull");
                }
            }
        };

        timer.start();
    }

}
