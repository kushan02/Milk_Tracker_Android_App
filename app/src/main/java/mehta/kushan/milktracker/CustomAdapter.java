package mehta.kushan.milktracker;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static mehta.kushan.milktracker.R.layout.custom_stats_listview;

/**
 * Created by Kushan Mehta on 05-07-2017.
 */

class CustomAdapter extends BaseAdapter {


    Context context;
    String tv_date[];
    String tv_qty[];

    LayoutInflater inflter;


    public CustomAdapter(Context applicationContext, String[] tv_date, String[] tv_qty) {
        this.context = context;
        this.tv_date = tv_date;
        this.tv_qty = tv_qty;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if(view!=null) {
           view = inflter.inflate(custom_stats_listview, parent, false);
        }
    //    String singleQty = getItem(position);
     //   String singleDate = getItem(position);


        TextView tv_date_tv = (TextView) view.findViewById(R.id.tv_date);

        TextView tv_qty_tv = (TextView) view.findViewById(R.id.tv_qty);


        tv_date_tv.setText(tv_date[i]);
        tv_qty_tv.setText(tv_qty[i]);

        return view;
    }
}
