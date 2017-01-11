package gibson.liam.leaguequiz;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by Liam on 29/12/2016.
 */

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

public class DetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Checks whether the device is in a landscape orientation
        if (getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        //Checks whether any help data was saved
        if (savedInstanceState == null) {

            //Creates the DetailsFragment if no data was saved
            DetailsFragment details = new DetailsFragment();

            //Get the Bundle and assign to DetailFragment
            details.setArguments(getIntent().getExtras());

            //Adds the details Fragment
            getFragmentManager().beginTransaction().add(android.R.id.content, details).commit();
        }
    }
}