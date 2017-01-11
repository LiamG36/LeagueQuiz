package gibson.liam.leaguequiz;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/*
This class helps put the fragment onto the screen and allows the user to scroll through and select
different options in the help screen.
 */

public class DetailsFragment extends Fragment {

    //Creates a DetailsFragment that contains the help data for the correct index
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        //Passes data using "index" and a value
        Bundle args = new Bundle();
        args.putInt("index", index);

        //Assigns the value to the fragment
        f.setArguments(args);

        return f;
    }

    //Returns the index that is assigned
    public int getShownIndex() {

        return getArguments().getInt("index", 0);
    }

    @Override
    //Puts the fragment onto the screen
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView scroller = new ScrollView(getActivity());//Allows user to scroll through data

        TextView text = new TextView(getActivity());

        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());

        //Sets padding for the TextView
        text.setPadding(padding, padding, padding, padding);

        //Adds the text into the ScrollView
        scroller.addView(text);

        //Set the currently selected help data into the textview
        text.setText(OptionInfo.OPTIONDETAILS[getShownIndex()]);
        return scroller;
    }
}