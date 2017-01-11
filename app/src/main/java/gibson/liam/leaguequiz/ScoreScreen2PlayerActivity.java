package gibson.liam.leaguequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static gibson.liam.leaguequiz.MainMenu.scoreName;

/*
The ScoreScreen2PlayerActivity is used to display the score after the first player completes the
quiz when the 2-player mode is selected. Similarly to ScoreScreen activity it inputs the players
name and score into an array and resets the values for the next quiz.
 */

public class ScoreScreen2PlayerActivity extends AppCompatActivity {

    private Button mNextPlayerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen2_player);

        TextView totalScore = (TextView)findViewById(R.id.totalScore);
        String scoreoutput = QuizActivity.score + "/" + QuizActivity.totalQuestions;
        //Displays the users score
        totalScore.setText(scoreoutput);

        //Players name and score being inputted into the array
        scoreName[ScoreScreenActivity.playerindex] = new Memory();
        inputDetails(scoreName[ScoreScreenActivity.playerindex]);
        ScoreScreenActivity.playerindex++; //Increased so the next players score does not write over the previous player



        mNextPlayerButton = (Button) findViewById(R.id.next_player_button);
        //Starts the quiz for the next player while resetting values for the next quiz
        mNextPlayerButton.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                QuizActivity.score = 0;
                QuizActivity.totalQuestions = 10;
                QuizActivity.mCurrentIndex=0;
                for (int index = 0; index<10; index++){
                    RegisterActivity.questionChecked[index] = false;
                }
                Intent i = new Intent(ScoreScreen2PlayerActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }

    //Sets the players name and score in the array.
    private static void inputDetails(Memory aMemory){
        PlayerName(aMemory);
        score(aMemory);
    }

    //Sets the uses name in the memory class
    private static String PlayerName(Memory aMemory){
        String name = RegisterActivity.name;
        aMemory.setName(name);
        return name;
    }

    //Sets the players score in the memory class
    private static void score(Memory aMemory){
        int playerScore = QuizActivity.score;
        aMemory.setScore(playerScore);
    }
}
