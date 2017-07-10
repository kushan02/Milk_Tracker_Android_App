package mehta.kushan.milktracker;

/**
 * Created by Kushan Mehta on 07-07-2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView date, dash, qty;

        public MyViewHolder(View view) {
            super(view);
            date = (TextView) view.findViewById(R.id.date);
            dash = (TextView) view.findViewById(R.id.tv_dash);
            qty = (TextView) view.findViewById(R.id.milk_qty_tv);
        }
    }


    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.date.setText(movie.getDate());
        holder.dash.setText(movie.getDash());
        holder.qty.setText(movie.getQty());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}