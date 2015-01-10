package ralobh.prd_test_1;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.provider.SyncStateContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class PRDactivity extends ActionBarActivity{

    Intent intent;

    public static Button tryprd;
    public static Button restartprd;
    public static Button doneprd;

    public static double K;

    public static int chance;
    public static int isStarted;
    public static int prdCounter; // P(A) = C*N - prdCounter ini sama dengan N
    public static int tryCounter; // Berapa Kali Coba
    public static int heroSkillImg;

    public static ImageView heroSkill_img;

    public static ScrollView scroll;

    public static String skillName;
    public static String pilihan;

    public static TextView prdtext;
    public static TextView skillChance;
    public static TextView skillNom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prdactivity);
        intent = getIntent();
        pilihan = intent.getExtras().getString("pil");
        scroll = (ScrollView) findViewById(R.id.scrollView);

        isStarted = 0;
        prdCounter = 0;
        tryCounter = 0;

        skillDetail();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_prdactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void skillDetail(){
        skillChance = (TextView) findViewById(R.id.heroskillchance_txt);
        skillNom = (TextView) findViewById(R.id.heroskillname_txt);
        heroSkill_img = (ImageView) findViewById(R.id.heroSkill_img);

        if(pilihan.equals("axehelix"))
        {
            chance = 20;
            skillName = "Axe's Counter Helix";
            heroSkillImg = getResources().getIdentifier("axe_skill_counterhelix", "drawable", getPackageName());
        }

        skillChance.setText("Chance - "+chance+"%");
        skillNom.setText(skillName);
        heroSkill_img.setImageResource(heroSkillImg);
    }

    public void runPRD(View v){
        switch(v.getId()){
            case R.id.tryprd_btn:
                if(pilihan.equals("axehelix") && isStarted == 0)
                {
                    K = 0.05570;
                    initPRD();
                }
                else
                {
                    thePRD(K);
                }
                break;
            case R.id.restartprd_btn:
                initPRD();
                break;
            case R.id.doneprd_btn:
                isStarted = 0;
                tryprd.setText("start");
                restartprd.setEnabled(false);
                doneprd.setEnabled(false);
                break;
        }
    }

    public void scrollDown()
    {
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    public void initPRD(){
        prdtext = (TextView) findViewById(R.id.prdView);
        tryprd = (Button) findViewById(R.id.tryprd_btn);
        restartprd = (Button) findViewById(R.id.restartprd_btn);
        doneprd = (Button) findViewById(R.id.doneprd_btn);

        prdtext.setText("");
        tryprd.setText("Try");
        restartprd.setEnabled(true);
        doneprd.setEnabled(true);

        prdCounter = 1;
        tryCounter = 0;
        isStarted = 1;
        thePRD(K);
    }

    public void thePRD(double kons) {
        Random rand = new Random();
        String currentText;
        boolean proc = false; //Nandain Klo proc atau ga


        if(kons*(prdCounter) >= 1.0)
        {
            proc = true;
            prdCounter = 1;
            tryCounter++;
            /*
            currentText = prdtext.getText().toString();
            currentText += "\n" + "Proc";
            prdtext.setText(currentText);
            prdtext.setSelected(true);*/
            prdtext.append("\n"+"Proc");
            scrollDown();
        }
        else if(rand.nextDouble() <= kons*(prdCounter))
        {
            proc = true;
            prdCounter = 1;
            tryCounter++;
            /*
            currentText = prdtext.getText().toString();
            currentText += "\n" + "Proc";
            prdtext.setText(currentText);
            prdtext.setSelected(true);*/
            prdtext.append("\n"+"Proc");
            scrollDown();
        }
        else
        {
            proc = false;
            prdCounter++;
            tryCounter++;
            /*
            currentText = prdtext.getText().toString();
            currentText += "\n" + "noProc";
            prdtext.setText(currentText);*/
            prdtext.append("\n"+"NoProc");
            scrollDown();
        }
    }
}
