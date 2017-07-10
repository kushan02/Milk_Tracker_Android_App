package mehta.kushan.milktracker;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import at.markushi.ui.CircleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DbHandler db;

    TextView qty;

    counter obj = new counter(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        qty = (TextView) findViewById(R.id.qty_tv);

        db = new DbHandler(this, null);


  //      Calendar calendar = Calendar.getInstance();
//        Toast.makeText(this, Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)), Toast.LENGTH_SHORT).show();

        init();

        CircleButton add = (CircleButton) findViewById(R.id.addbtn);
        add.setOnClickListener(this);

        CircleButton rm = (CircleButton) findViewById(R.id.rmbtn);
        rm.setOnClickListener(this);

        Button list = (Button) findViewById(R.id.stats);
        list.setOnClickListener(this);
    //    Button graph = (Button) findViewById(R.id.graph);
     //   list.setOnClickListener(this);
        Button money = (Button) findViewById(R.id.money);
        money.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.addbtn:

              //  Toast.makeText(this, "ADD btn!", Toast.LENGTH_SHORT).show();


                db.increment(obj, qty);


                break;

            case R.id.rmbtn:

//                Toast.makeText(this, "Remove btn!", Toast.LENGTH_SHORT).show();

                db.decrement(obj, qty);

                break;

            case R.id.stats:

                Intent myIntent = new Intent(MainActivity.this, Records.class);
                myIntent.putExtra("key", "stats");
                MainActivity.this.startActivity(myIntent);

                break;

            case R.id.money:

        //   Toast.makeText(this, "Money btn!", Toast.LENGTH_SHORT).show();

                Intent myIntentm = new Intent(MainActivity.this, Records.class);
                myIntentm.putExtra("key", "money");
                MainActivity.this.startActivity(myIntentm);

                break;
        }
    }

    public void init() {

    //    Toast.makeText(this, "today: " + db.toDay(obj), Toast.LENGTH_SHORT).show();


        if (!db.checktb() || !db.toDay(obj)) {

            db.addData(obj);

       //     Toast.makeText(this, "FRESH TABLE", Toast.LENGTH_SHORT).show();

            qty.setText("0");



        } else {
            String initqty = db.getValue(obj);

        //    Toast.makeText(this, "getValue(): " + initqty, Toast.LENGTH_SHORT).show();

            qty.setText(initqty);

//            Toast.makeText(this, "parseint : " + Integer.parseInt(initqty), Toast.LENGTH_SHORT).show();

            obj.set_qty(Integer.parseInt(initqty));
        }
    }










    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

}
