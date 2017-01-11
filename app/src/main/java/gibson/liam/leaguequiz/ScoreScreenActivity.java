package gibson.liam.leaguequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static gibson.liam.leaguequiz.MainMenu.scoreName;

/*
The ScoreScreen activity is used to display the score after 1-player quiz and to display the score
of the second player in a 2-player quiz. It is also where the players name and score is paired as
an object and inputted into the array. The values used during the quiz such as the score and
question index are reset for the next quiz after the previous players scores are recorded.
 */

public class ScoreScreenActivity extends AppCompatActivity {

    private Button mHighscoresButton;
    private Button mMenuButton;
    static int playerindex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        TextView totalScore = (TextView)findViewById(R.id.totalScore);
        String scoreoutput = QuizActivity.score + "/" +QuizActivity.totalQuestions;
        //Displays the users score
        totalScore.setText(scoreoutput);

        //Players name and score being inputted into the array
        scoreName[playerindex] = new Memory();
        inputDetails(scoreName[playerindex]);
        playerindex++; //Increased so the next players score does not write over the previous player

        mHighscoresButton = (Button) findViewById(R.id.highscores_button);
        //Takes the player to the highscore screen while resetting values for the next quiz.
        mHighscoresButton.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                QuizActivity.score = 0;
                QuizActivity.totalQuestions = 10;
                QuizActivity.mCurrentIndex=0;
                for (int index = 0; index<10; index++){
                    RegisterActivity.questionChecked[index] = false;
                }
                Intent i = new Intent(ScoreScreenActivity.this, HighscoreActivity.class);
                startActivity(i);
            }
        });

        mMenuButton = (Button) findViewById(R.id.menu_button);
        //Takes the player to the menu screen while resetting values for the next quiz.
        mMenuButton.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                QuizActivity.score = 0;
                QuizActivity.totalQuestions = 10;
                QuizActivity.mCurrentIndex=0;
                for (int index = 0; index<10; index++){
                    RegisterActivity.questionChecked[index] = false;
                }
                Intent i = new Intent(ScoreScreenActivity.this, MainMenu.class);
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
