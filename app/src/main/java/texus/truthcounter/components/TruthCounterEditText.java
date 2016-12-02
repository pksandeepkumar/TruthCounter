package texus.truthcounter.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import texus.truthcounter.ApplicationClass;


/**
 * Created by sandeep on 10/26/2016.
 */

public class TruthCounterEditText extends EditText {



    public TruthCounterEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TruthCounterEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TruthCounterEditText(Context context) {
        super(context);
        init();
    }

    private void init() {
        setTypeface(ApplicationClass.getInstance().appFont);
    }
}
