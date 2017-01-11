package gibson.liam.leaguequiz;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Liam on 29/12/2016.
 */


public class TitlesFragment extends ListFragment {

    boolean mDuelPane; //Checks whether phone is in horizontal or Duelpane mode

    int mCurCheckPosition = 0; //Currently selected item in the ListView

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Create ArrayAdapter to connect array to ListView
        ArrayAdapter<String> connectArrayToListView = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                OptionInfo.OPTIONS);

        //Connects the ListView to the data
        setListAdapter(connectArrayToListView);

        View detailsFrame = getActivity().findViewById(R.id.details);

        //Sets mDuelPane based on whether you are in the horizontal layout
        //Also checkes if detailsFrame exists and is visible
        mDuelPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        //Stores which help option you were looking at if user rotates the screen
        if (savedInstanceState != null) {
            //Restores the last looked at help option
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        //Allows one item to be in the ListView to be selected at a time.
        if (mDuelPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition); //Displays the right help information.
        }
    }

    //Called when screen orientation changes and last selected item in the list is saved.
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mCurCheckPosition);
    }

    //Changes help information when different help option clicked
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }

    //Shows the help information
    void showDetails(int index) {

        mCurCheckPosition = index; //Latest selected help option

        //Checkes whether in horizontal mode and displays the ListView and help information if true
        if (mDuelPane) {

            getListView().setItemChecked(index, true); //Highlights selected help option

            //Creates an object that represents the FrameLayout that the help information goes into
            DetailsFragment details = (DetailsFragment)
                    getFragmentManager().findFragmentById(R.id.details);

            //If the index hasn't been assigned
            if (details == null || details.getShownIndex() != index) {

                //Creates the DetailsFragment and assigns it the currently selected help option
                details = DetailsFragment.newInstance(index);

                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.replace(R.id.details, details); //Replaces any other fragments with new DetailsFragment
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE); //Fades fragment away
                ft.commit();
            }

        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailsActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}