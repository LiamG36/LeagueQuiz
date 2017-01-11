package gibson.liam.leaguequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
The main menu class is the starting menu of the application. It contains the buttons that lead
to the 1 player and 2 player quiz's and also one to view the highscores.
*/

public class MainMenu extends AppCompatActivity {

    private Button mPlayer1Button;
    private Button mHighscoresButton;
    private Button mPlayer2Button;
    /*
    Integer which when above 0 due to the 2-player button being clicked, will mean the first player
    is taken to a different score screen after finishing the quiz, so the second player can then
    take the quiz. Used by if-else statement in QuizActivity class.
    */
    static int player2 = 0;
    final static int MAX_PLAYERS = 15;
    /*
    Array where players names and scores are stored as objects.
     */
    static Memory[] scoreName = new Memory[MAX_PLAYERS];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mPlayer1Button = (Button)findViewById(R.id.player1_button);
        mPlayer1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(MainMenu.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        mHighscoresButton = (Button) findViewById(R.id.highscores_button);
        mHighscoresButton.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                Intent i = new Intent(MainMenu.this, HighscoreActivity.class);
                startActivity(i);
            }
        });

        mPlayer2Button = (Button)findViewById(R.id.player2_button);
        mPlayer2Button.setOnClickListener(new View.OnClickListener(){
            @Override public void onClick(View v){
                player2++; //Activates the two player mode
                Intent i = new Intent(MainMenu.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
