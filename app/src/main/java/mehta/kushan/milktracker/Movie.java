package mehta.kushan.milktracker;

/**
 * Created by Kushan Mehta on 07-07-2017.
 */

public class Movie {
//    private String title, genre, year;

    private String date, dash, qty;

    public Movie()
    {
        this.dash = " - ";
    }

    public Movie(String date, String dash, String qty) {
        this.date = date;
        this.dash = dash;
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public String getDash() {
        return dash;
    //    return " - ";
    }

    public String getQty() {
        return qty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDash(String dash) {
        this.dash = dash;
    }

    public void setDash() {
        this.dash = " - ";
    }


    public void setQty(String qty) {
        this.qty = qty;
    }
}
