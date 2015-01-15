package ralobh.prd_test_1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class PRDactivity extends ActionBarActivity{

    Intent intent;

    public static Button tryprd;
    public static Button restartprd;
    public static Button doneprd;

    public static double K;

    public static int chance;
    public static int isStarted;
    public static int procCounter;
    public static int prdCounter; // P(A) = C*N - prdCounter ini sama dengan N
    public static int tryCounter; // Berapa Kali Coba
    public static int heroSkillImg;

    public static ImageView heroSkill_img;

    private static MediaPlayer skillSound;

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
        prdCounter = 1;
        tryCounter = 0;
        procCounter = 0;

        this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
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
            skillName = "Counter Helix";
            heroSkillImg = getResources().getIdentifier("axe_skill_counterhelix", "drawable", getPackageName());
            skillSound = MediaPlayer.create(this, R.raw.counter_helix);
            K = 0.05570;
        }
        else if(pilihan.equals("brew1"))
        {
            chance = 10;
            skillName = "Drunken Brawler Level 1";
            heroSkillImg = getResources().getIdentifier("brewmaster_drunken_brawler1", "drawable", getPackageName());
            skillSound = MediaPlayer.create(this, R.raw.drunken_brawler);
            K = 0.01475;

        }
        else if(pilihan.equals("coupdegrace"))
        {
            chance = 15;
            skillName = "Coup de Grace";
            heroSkillImg = getResources().getIdentifier("phantom_assassin_coup_de_grace", "drawable", getPackageName());
            skillSound = MediaPlayer.create(this, R.raw.coup_de_grace);
            K = 0.03221;
        }

        skillChance.setText("Chance - "+chance+"%");
        skillNom.setText(skillName);
        heroSkill_img.setImageResource(heroSkillImg);
    }

    public void skillAudio(){
        skillSound.start();
    }

    public void waitInBetween(){
        if(tryprd.getText().equals("Try"))
        {
            tryprd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tryprd.setEnabled(false);
                }
            }, 0);

            tryprd.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tryprd.setEnabled(true);
                }
            }, 1000);
        }
    }


    public void runPRD(View v){
        switch(v.getId()){
            case R.id.tryprd_btn:
                if(isStarted == 0)
                {
                    initPRD();
                    thePRD(K);
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
                doneStats();
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

    public void doneStats()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.showstats, null);

        dialogBuilder.setTitle("Your Statistic");
        dialogBuilder.setView(dialogView)
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        TextView jmlProc = (TextView) dialogView.findViewById(R.id.procC);
        TextView jmlTry = (TextView) dialogView.findViewById(R.id.tryC);

        AlertDialog showStat = dialogBuilder.create();
        jmlProc.setText("Proc - "+procCounter);
        jmlTry.setText("Try - "+tryCounter);
        showStat.show();
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
        procCounter = 0;
        isStarted = 1;
    }

    public void thePRD(double kons) {
        Random rand = new Random();
        boolean proc = false; //Nandain Klo proc atau ga


        if(kons*(prdCounter) >= 1.0)
        {
            proc = true;
            prdCounter = 1;
            tryCounter++;
            procCounter++;

            prdtext.append("\n"+"Proc");
            skillAudio();
            scrollDown();
            waitInBetween();
        }
        else if(rand.nextDouble() <= kons*(prdCounter))
        {
            proc = true;
            prdCounter = 1;
            tryCounter++;
            procCounter++;

            prdtext.append("\n"+"Proc");
            skillAudio();
            scrollDown();
            waitInBetween();

        }
        else
        {
            proc = false;
            prdCounter++;
            tryCounter++;

            prdtext.append("\n"+"NoProc");
            scrollDown();
            waitInBetween();
        }
    }
}
