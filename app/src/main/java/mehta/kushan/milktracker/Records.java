package mehta.kushan.milktracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Records extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter mAdapter;
    private EditText mtext;
    private Button mButton;

    Spinner dropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();

        String intent_value = intent.getStringExtra("key"); //if it's a string you stored.


        super.onCreate(savedInstanceState);

        if (intent_value.equalsIgnoreCase("stats")) {
            setContentView(R.layout.activity_records);

            this.setTitle("Statistics");

            dropdown = (Spinner) findViewById(R.id.spinner1);


            dropdown.setOnItemSelectedListener(this);

            String[] items = new String[]{"Daily Statistics", "Monthly Statistics", "Yearly Statistics"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
            dropdown.setAdapter(adapter);


            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

            setData("daily");
        } else { // IF MONEY IS SELECTED
            this.setTitle("Cost Estimator");

            setContentView(R.layout.money_layout);
            recyclerView = (RecyclerView) findViewById(R.id.recycler_view_money);

            mButton = (Button)findViewById(R.id.search_text);

            mButton.setOnClickListener(
                    new View.OnClickListener()
                    {
                        public void onClick(View view)
                        {
                            clear();
                            setMoneyData();
                        }
                    });



            setMoneyData();
        }

        mAdapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(mAdapter);




    }


    public void clear() {
        movieList.clear(); //clear list
        mAdapter.notifyDataSetChanged();
    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {

        if (dropdown.getSelectedItem().equals("Daily Statistics")) {
         //   Toast.makeText(this, "Daily Statistics", Toast.LENGTH_SHORT).show();

            clear();
            setData("daily");

        } else if (dropdown.getSelectedItem().equals("Monthly Statistics")) {
        //    Toast.makeText(this, "Monthly Statistics", Toast.LENGTH_SHORT).show();
            clear();
            setData("monthly");

        } else if (dropdown.getSelectedItem().equals("Yearly Statistics")) {
        //    Toast.makeText(this, "Yearly Statistics", Toast.LENGTH_SHORT).show();
            clear();
            setData("yearly");

        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Strange Error: onNothingSelected", Toast.LENGTH_SHORT).show();
    }


    private void setData(String type) {

        DbHandler dbh = new DbHandler(this, null);
        //    Movie movie = new Movie("Date of entry", "  ", "Quantity");
        //    movieList.add(movie);

        if (type.equalsIgnoreCase("daily")) {

            Movie movie = new Movie("Date of entry", "  ", "Quantity");
            movieList.add(movie);

            dbh.addListViewDaily(movieList, movie);
        } else if (type.equalsIgnoreCase("monthly")) {

            Movie movie = new Movie("Month of entry", "  ", "Quantity");
            movieList.add(movie);

            dbh.addListViewMonthly(movieList, movie);
        } else if (type.equalsIgnoreCase("yearly")) {

            Movie movie = new Movie("Year of entry", "  ", "Quantity");
            movieList.add(movie);

            dbh.addListViewYearly(movieList, movie);
        }

    }

    private void setMoneyData() {
        DbHandler dbh = new DbHandler(this, null);


        mtext   = (EditText)findViewById(R.id.search_box);

        int rate = Integer.parseInt(Records.this.mtext.getText().toString());



        Movie movie = new Movie("Month of entry", "(Rate: \u20B9"+rate+")", "Total Cost");
        movieList.add(movie);

        dbh.addListViewMoney(movieList,movie,rate);

    }


}

