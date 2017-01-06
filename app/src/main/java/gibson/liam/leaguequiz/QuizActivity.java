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

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private Button mSkipButton;
    private ImageView mQuestionImageView;
    private TextView mQuestionTextView;
    final Handler mHandler = new Handler();

    static int score = 0;




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



    static int mCurrentIndex = 0;
    static int totalQuestions = 10;
    int cheatCount = 0;

    private void updateQuestion(){
        scoreupdate();
        if (mCurrentIndex<10){
            while (RegisterActivity.questionChecked[mCurrentIndex] == true && mCurrentIndex<9){
                mCurrentIndex++;
                totalQuestions--;}
        }
        if (RegisterActivity.questionChecked[9] == true && mCurrentIndex == 9){
            totalQuestions--;
        }
        if (mCurrentIndex < 9 || mCurrentIndex ==9 && RegisterActivity.questionChecked[9] == false) {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
            int questionimage = mQuestionBank[mCurrentIndex].getImageResId();
            mQuestionImageView.setImageResource(questionimage);
        } else {
            if (MainMenu.x>0){
                MainMenu.x =0;
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

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId=0;

        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
            score();
            if  (cheatCount > 0){
                score--;
                cheatCount = 0;
            }
        } else {
            messageResId = R.string.incorrect_toast;
            cheatCount = 0;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

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

    private void slowChange(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateQuestion();
            }
        }, 1500);
    }

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



       // Questions theQuestion = new Questions();


            // TextView output = (TextView) findViewById(R.id.output);
            // output.setText(Questions.QuestionPick(i));

           // mNextButton = (Button) findViewById(R.id.next_button);

           // mNextButton.setOnClickListener(new View.OnClickListener() {
          //      @Override
          //      public void onClick(View v) {

         //       }
          //  });
       // i++;



        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkAnswer(true);
                mCurrentIndex=(mCurrentIndex + 1);
                slowChange();
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                mCurrentIndex=(mCurrentIndex + 1);
                slowChange();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                cheatCheck();
                cheatCount ++;
            }
        });

        mSkipButton = (Button) findViewById(R.id.skip_button);
        mSkipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex + 1);
                slowChange();
            }
        });

       // mNextButton = (Button) findViewById(R.id.next_button);
      //  mNextButton.setOnClickListener(new View.OnClickListener(){
       //     @Override
       //     public void onClick(View v){
       //         mCurrentIndex=(mCurrentIndex + 1) % mQuestionBank.length;
       //         updateQuestion();
       //     }
       // });
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

    @Override
    public void onBackPressed(){
    }
}
