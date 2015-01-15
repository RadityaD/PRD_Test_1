package ralobh.prd_test_1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Main extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void toPRD(View v){
        Intent intent = new Intent(this, PRDactivity.class);
        String pilihan = null;
        switch(v.getId()){
            case R.id.select_btn1:
                pilihan = "axehelix";
                break;
            case R.id.select_btn2:
                pilihan = "brew1";
                break;
            case R.id.select_btn3:
                pilihan = "coupdegrace";
                break;
        }
        intent.putExtra("pil", pilihan);
        startActivity(intent);
    }
}
