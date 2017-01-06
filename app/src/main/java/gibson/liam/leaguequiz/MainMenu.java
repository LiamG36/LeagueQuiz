package gibson.liam.leaguequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainMenu extends AppCompatActivity {

    private Button mPlayer1Button;
    private Button mHighscoresButton;
    private Button mPlayer2Button;
    static int x = 0;
    static int List = 15;
    static Memory[] scoreName = new Memory[List];

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
                x++;
                Intent i = new Intent(MainMenu.this, RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
