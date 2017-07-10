package mehta.kushan.milktracker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class CircularTextView extends android.support.v7.widget.AppCompatTextView {

    public CircularTextView(Context context) {
        super(context);
    }

    public CircularTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CircularTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

      //  int h = this.getMeasuredHeight();
      //  int w = this.getMeasuredWidth();

      //  int h = this.getMeasuredHeight()/2;
       // int w = this.getMeasuredWidth()/2;

        int h = 450;
        int w = 450;

        int r = Math.max(w,h);

        setMeasuredDimension(r, r);

    }
}