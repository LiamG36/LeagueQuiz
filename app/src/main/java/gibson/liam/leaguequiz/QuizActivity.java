package gibson.liam.leaguequiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;

/*
The QuizActivity class runs the quiz. It deals with changing and setting the questions and
associated images, checking whether the answer was true or false, displaying a toast depending
on the answer, handling the cheat, skip, true and false buttons ,and also dealing with changing
the score when appropriate. Also contains the same toolbar from RegisterActivity. Transitions to
either the ScoreScreenActivity or ScoreScreen2PlayerActivity at the end of the quiz.
 */

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mSkipButton;
    private ImageView mQuestionImageView; //The images for each question
    private TextView mQuestionTextView; //The text for each question
    final Handler mHandler = new Handler(); //Adds a delay to the question change
    static int score = 0;

    /*
    Contains the array of questions. Each object has the resource of the string, the answer, and
    the resource for the image file for each particular question.
     */

    private Questions[] mQuestionBank = new Questions[] {
            new Questions(R.string.question_ashe, true, R.drawable.ashe),
            new Questions(R.string.question_diana, false, R.drawable.diana),
            new Questions(R.string.question_yi, false, R.drawable.yi),
            new Questions(R.string.question_vi, true, R.drawable.vi),
            new Questions(R.string.question_azir, true, R.drawable.azir),
            new Questions(R.string.question_graves, true, R.drawable.graves),
            new Questions(R.string.question_kayle, true, R.drawable.kaylemorg),
            new Questions(R.string.question_darius, false, R.drawable.darius),
            new Questions(R.string.question_nunu, false, R.drawable.nunu),
            new Questions(R.string.question_braum, true, R.drawable.braum)

    };



    static int mCurrentIndex = 0; //The question in the array
    static int totalQuestions = 10; //What your score is out of in the score screen
    int cheatCount = 0; //Takes record on whether the player cheated on the particular question.

    /*
    Changes the question and associated image on screen. Deals with skipping questions if they were
    selected in RegisterActivity. Also takes the player through to the score screen if they have
    completed the quiz.
     */

    private void updateQuestion(){
        scoreupdate();
        //Skips the question if selected in RegisterActivity and also reduces what your score is out of
        if (mCurrentIndex<10){
            while (RegisterActivity.questionChecked[mCurrentIndex] == true && mCurrentIndex<9){
                mCurrentIndex++;
                totalQuestions--;}
        }
        /*
        When on the final question and it was selected to be skipped, prevents app from crashing
        while still being able to be skipped
        */
        if (RegisterActivity.questionChecked[9] == true && mCurrentIndex == 9){
            totalQuestions--;
        }
        //Changes the question and it's image
        if (mCurrentIndex < 9 || mCurrentIndex ==9 && RegisterActivity.questionChecked[9] == false) {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
            int questionimage = mQuestionBank[mCurrentIndex].getImageResId();
            mQuestionImageView.setImageResource(questionimage);
        }
        //Takes the player to the relevant scorescreen.
        else {
            //Only used when 2-player mode was selected for the 1st player.
            if (MainMenu.player2>0){
                MainMenu.player2 =0; //So the quiz doesn't loop in 2-player mode
                Intent i = new Intent(QuizActivity.this, ScoreScreen2PlayerActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(QuizActivity.this, ScoreScreenActivity.class);
                startActivity(i);
            }
        }
    }

    private void score(){
        score++;
            }

    //Checks the answer provided against the particular question
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;

        //Increases score by one if your answer matches the answer in the array
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
            score();
            //Minuses one from your score if you got it correct and also cheated
            if  (cheatCount > 0){
                score--;
                cheatCount = 0; //Resets cheat count so you can get a score on the next question
            }
        } else {
            messageResId = R.string.incorrect_toast;
            cheatCount = 0;
        }
        //Outputs a toast saying whether you were correct or incorrect
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //Checks what the answer is and provides it in a toast message if the cheat button was pressed
    private void cheatCheck(){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId=0;

        if (answerIsTrue == true){
            messageResId = R.string.cheat_toast_true;
        } else {
            messageResId = R.string.cheat_toast_false;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    //Slows down the question change transition to allow toast to pop up and fade before it changes
    private void slowChange(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateQuestion();
            }
        }, 1500);
    }

    //Updates the score text at the bottom of the screen after every question
    private void scoreupdate(){
        TextView scoreatm = (TextView)findViewById(R.id.score_for_now);
        String scorefornow = "Score: " + score;
        scoreatm.setText(scorefornow);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        mQuestionImageView = (ImageView) findViewById(R.id.question_image_view);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mTrueButton = (Button) findViewById(R.id.true_button);
        //Checks the answer provided, adds 1 to the question array index and changes the question
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
                mCurrentIndex=(mCurrentIndex + 1);
                slowChange();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        //Checks the answer provided, adds 1 to the question array index and changes the question
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mCurrentIndex=(mCurrentIndex + 1);
                slowChange();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        //Displays the answer to the particular question as a toast.
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cheatCheck();
                cheatCount ++;
            }
        });

        mSkipButton = (Button) findViewById(R.id.skip_button);
        //Skips to the next question
        mSkipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex + 1);
                slowChange();
            }
        });

        updateQuestion();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_setting){
            Intent i = new Intent(QuizActivity.this, FragmentLayout.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.menu_setting) {
            Intent i = new Intent(QuizActivity.this, MainMenu.class);
            startActivity(i);
            mCurrentIndex=0;
            totalQuestions=10;
            score = 0;
            for (int index = 0; index<10; index++){
                RegisterActivity.questionChecked[index] = false;
            }
        }
            return super.onOptionsItemSelected(item);

    }

    /*
    Prevents the back button from working. Stops the user from accidentally backing out of the
    quiz and having to start again. Main Menu button on toolbar allows for leaving the quiz.
    */
    @Override
    public void onBackPressed(){
    }
}
