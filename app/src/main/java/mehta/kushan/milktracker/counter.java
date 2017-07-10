package mehta.kushan.milktracker;

import android.content.Context;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Kushan Mehta on 05-07-2017.
 */

public class counter {

    private int _id;
    private int _qty;
    private int _date;
    private int _month;
    private int _year;

    Calendar calendar = Calendar.getInstance();


    public counter(){

   }

    public counter(Context context)
    {
        DbHandler db = new DbHandler(context,null);

    }

    public counter(int _qty) {
        this._qty = _qty;
    }

    public int get_id() {
        return _id;
    }

    public int get_qty() {
        return _qty;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_qty(int _qty) {
        this._qty = _qty;
    }

    public int get_date() {

        _date = calendar.get(Calendar.DAY_OF_MONTH);
        return _date;
    }

    public int get_month() {
        _month = calendar.get(Calendar.MONTH);
        _month++;
        return _month;
    }

    public int get_year() {
        _year = calendar.get(Calendar.YEAR);
        return _year;
    }

    public void set_date(int _date) {
        this._date = _date;
    }

    public void set_month(int _month) {
        this._month = _month;
    }

    public void set_year(int _year) {
        this._year = _year;
    }


}
