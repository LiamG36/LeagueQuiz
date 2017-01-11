package gibson.liam.leaguequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/*
The HighscoreActivity is where the users scores are displayed in descending order(Highest to lowest)
The array is sorted via the score portion of the objects and then displayed as a list
 */

public class HighscoreActivity extends AppCompatActivity {

    private Button mMenuButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        output(MainMenu.scoreName);

        mMenuButton = (Button) findViewById(R.id.menu_button);
        mMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                Intent i = new Intent(HighscoreActivity.this, MainMenu.class);
                startActivity(i);
            }
        });


    }

    //Outputs the highscores
    private void output(Memory[] aMemoryList){
        Memory[] displayCopy = aMemoryList; //Copy of the array so original data isn't altered.
        Arrays.sort(displayCopy, Memory.SCORE_COMPARATOR); //Sorts the array via the scores.
        TextView scoreList = (TextView)findViewById(R.id.highscore_scores);
        //Scores are set to the screen in the sorted descending order.
        scoreList.setText(Utils.memoryToString(displayCopy));
    }
}
