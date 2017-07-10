package mehta.kushan.milktracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Kushan Mehta on 05-07-2017.
 */

public class DbHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 10;
    private static final String DATABSAE_NAME = "milk_qty.db";

    private static final String TABLE_QTY = "milk";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_QTY = "qty";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_MONTH = "month";
    private static final String COLUMN_YEAR = "year";


    public DbHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABSAE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_QTY + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_QTY + " INTEGER, " +
                COLUMN_DATE + " INTEGER, " +
                COLUMN_MONTH + " INTEGER, " +
                COLUMN_YEAR + " INTEGER" +
                ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_QTY + "'");
        onCreate(db);

    }

// Add a new row to database

    public void addData(counter obj) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_QTY, 0);
        values.put(COLUMN_DATE, obj.get_date());
        values.put(COLUMN_MONTH, obj.get_month());
        values.put(COLUMN_YEAR, obj.get_year());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_QTY, null, values);
        db.close();

        obj.set_qty(0);
    }

    public String getValue(counter obj) {
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM milk WHERE year =" + obj.get_year() + " AND month = " + obj.get_month() + " AND date = " + obj.get_date() + " ORDER BY _id desc";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        int co = 0;
        String qty = "";
        while (!c.isAfterLast()) {
            if (co > 0) {
                break;
            }
            co++;
            qty = c.getString(c.getColumnIndex("qty"));

        }


        db.close();


        return qty;
    }


    public Boolean toDay(counter obj) {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM milk WHERE year =" + obj.get_year() + " AND month=" + obj.get_month() + " AND date=" + obj.get_date();

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        int co = 0;

        while (!c.isAfterLast()) {
            if (co > 0) {
                ret = true;
                break;
            }
            co++;
        }

        db.close();

        return ret;
    }


    public boolean checktb() {
        boolean ret = false;

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM milk ORDER BY _id desc";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        int co = 0;

        while (!c.isAfterLast()) {
            if (co > 0) {
                ret = true;
                break;
            }
            co++;
        }
        db.close();

        return ret;

    }


    public void increment(counter obj, TextView tv) {

        if (!toDay(obj)) {
            addData(obj);
        }

        String strSQL = "UPDATE milk SET qty = " + obj.get_qty() + "+1" + " WHERE qty = " + obj.get_qty() + " AND year =" + obj.get_year() + " AND month=" + obj.get_month() + " AND date=" + obj.get_date();

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(strSQL);
        obj.set_qty(obj.get_qty() + 1);

        tv.setText(Integer.toString(obj.get_qty()));

    }

    public void decrement(counter obj, TextView tv) {
        if (obj.get_qty() > 0) {

            String strSQL = "UPDATE milk SET qty = " + obj.get_qty() + "-1" + " WHERE qty = " + obj.get_qty() + " AND year =" + obj.get_year() + " AND month=" + obj.get_month() + " AND date=" + obj.get_date();

            SQLiteDatabase db = getWritableDatabase();
            db.execSQL(strSQL);
            obj.set_qty(obj.get_qty() - 1);

            tv.setText(Integer.toString(obj.get_qty()));
        }
    }

    public void updateQty(counter obj) {
        // ContentValues cv = new ContentValues();
        // cv.put(COLUMN_QTY,obj.get_qty());

        String strSQL = "UPDATE milk SET qty = " + obj.get_qty() + 1 + "WHERE qty = " + obj.get_qty();

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(strSQL);

    }

    public void addListViewDaily(List<Movie> movieList, Movie movie) {

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM milk ORDER BY _id desc";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        String date = "";
        String qty;

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    qty = c.getString(c.getColumnIndex("qty"));
                    date = c.getString(c.getColumnIndex("date"));
                    date = date + "/" + c.getString(c.getColumnIndex("month"));
                    date = date + "/" + c.getString(c.getColumnIndex("year"));

                    movie = new Movie(date, " - ", qty);
                    movieList.add(movie);


                } while (c.moveToNext());
            }
            db.close();


        }
    }


    public void addListViewMonthly(List<Movie> movieList, Movie movie) {

        SQLiteDatabase db = getWritableDatabase();


        String query = "SELECT SUM(qty) as \"sqty\",month,year FROM milk GROUP BY month  ORDER BY _id desc";


        Cursor c = db.rawQuery(query, null);

        c.moveToFirst();

        String date = "";
        String qty;

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    qty = c.getString(c.getColumnIndex("sqty"));
                    //   date = c.getString(c.getColumnIndex("date"));
                    date = c.getString(c.getColumnIndex("month"));
                    date = date + "/" + c.getString(c.getColumnIndex("year"));

                    movie = new Movie(date, " - ", qty);
                    movieList.add(movie);


                } while (c.moveToNext());
            }
            db.close();


        }
    }

    public void addListViewYearly(List<Movie> movieList, Movie movie) {

        SQLiteDatabase db = getWritableDatabase();


        String query = "SELECT SUM(qty) as \"sqty\",year FROM milk GROUP BY year ORDER BY _id desc";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        String date = "";
        String qty;

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    qty = c.getString(c.getColumnIndex("sqty"));
                 //   date = c.getString(c.getColumnIndex("date"));
                  //  date = date + "/" + c.getString(c.getColumnIndex("month"));
                    date = c.getString(c.getColumnIndex("year"));

                    movie = new Movie(date, " - ", qty);
                    movieList.add(movie);


                } while (c.moveToNext());
            }
            db.close();


        }


    }


    public void addListViewMoney(List<Movie> movieList, Movie movie,int rate) {

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT SUM(qty)*" + rate +" as \"cost\",month,year FROM milk GROUP BY month  ORDER BY _id desc";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        String date = "";
        String qty;

        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    qty = "\u20B9";
                    qty = qty + c.getString(c.getColumnIndex("cost"));
                    date = c.getString(c.getColumnIndex("month"));
                    date = date + "/" + c.getString(c.getColumnIndex("year"));

                    movie = new Movie(date, " - ", qty);
                    movieList.add(movie);


                } while (c.moveToNext());
            }
            db.close();


        }
    }


}
