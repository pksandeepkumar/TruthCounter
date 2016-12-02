package texus.truthcounter;

import android.app.Application;
import android.graphics.Typeface;

/**
 * Created by sandeep on 1/12/2016
 */
public class ApplicationClass extends Application {

    public static String INTERNAL_RESPONSE_FOLDER = "FOLDER_RESPONSE";

    public Typeface appFont;


    private static ApplicationClass ourInstance = new ApplicationClass();

    public static ApplicationClass getInstance() {
        return ourInstance;
    }


    public void loadFont() {
        try {
            appFont = Typeface.createFromAsset(getAssets(), "Ubuntu-Medium.ttf");
        } catch ( Exception e) {
            e.printStackTrace();;
        }
    }


    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
        loadConfiguration();
    }

    private void loadConfiguration() {
        loadFont();
    }


}
