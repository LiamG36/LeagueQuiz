package gibson.liam.leaguequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static gibson.liam.leaguequiz.MainMenu.scoreName;

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
        totalScore.setText(scoreoutput);

        scoreName[playerindex] = new Memory();
        inputDetails(scoreName[playerindex]);
        playerindex++;


        mHighscoresButton = (Button) findViewById(R.id.highscores_button);
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
    private static void inputDetails(Memory aMemory){
        PlayerName(aMemory);
        score(aMemory);
    }

    private static String PlayerName(Memory aMemory){
        String name = RegisterActivity.name;
        aMemory.setName(name);
        return name;
    }

    private static void score(Memory aMemory){
        int playerScore = QuizActivity.score;
        aMemory.setScore(playerScore);
    }
}
