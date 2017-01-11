package gibson.liam.leaguequiz;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;


/*
This class contributes into creating the help fragment in the toolbar by bringing together some
components and also checking the savestate and orientation of the device.
 */

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