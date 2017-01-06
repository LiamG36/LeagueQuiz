package gibson.liam.leaguequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;
import android.support.v7.widget.Toolbar;

import org.apache.commons.lang3.StringUtils;

public class RegisterActivity extends AppCompatActivity {

    static String name;

    static boolean questionChecked[] = {false, false, false, false, false, false, false, false, false, false};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        final CheckBox mCB1 = (CheckBox)findViewById(R.id.checkBox1);
        final CheckBox mCB2 = (CheckBox)findViewById(R.id.checkBox2);
        final CheckBox mCB3 = (CheckBox)findViewById(R.id.checkBox3);
        final CheckBox mCB4 = (CheckBox)findViewById(R.id.checkBox4);
        final CheckBox mCB5 = (CheckBox)findViewById(R.id.checkBox5);
        final CheckBox mCB6 = (CheckBox)findViewById(R.id.checkBox6);
        final CheckBox mCB7 = (CheckBox)findViewById(R.id.checkBox7);
        final CheckBox mCB8 = (CheckBox)findViewById(R.id.checkBox8);
        final CheckBox mCB9 = (CheckBox)findViewById(R.id.checkBox9);
        final CheckBox mCB10 = (CheckBox)findViewById(R.id.checkBox10);

        Button mEnterButton = (Button)findViewById(R.id.enter_button);
        mEnterButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                EditText mNameText = (EditText)findViewById(R.id.user_name);
                name = mNameText.getText().toString();
                if (name.length() > 10 || name.length() < 1 || StringUtils.isAlpha(name) == false){
                    Toast.makeText(getApplicationContext(), "Please re-enter your name within the correct parameters listed above", Toast.LENGTH_LONG).show();
                } else {
                    if (mCB1.isChecked()){
                        questionChecked[0] = true;
                    }
                    if (mCB2.isChecked()){
                        questionChecked[1] = true;
                    }
                    if (mCB3.isChecked()){
                        questionChecked[2] = true;
                    }
                    if (mCB4.isChecked()){
                        questionChecked[3] = true;
                    }
                    if (mCB5.isChecked()){
                        questionChecked[4] = true;
                    }
                    if (mCB6.isChecked()){
                        questionChecked[5] = true;
                    }
                    if (mCB7.isChecked()){
                        questionChecked[6] = true;
                    }
                    if (mCB8.isChecked()){
                        questionChecked[7] = true;
                    }
                    if (mCB9.isChecked()){
                        questionChecked[8] = true;
                    }
                    if (mCB10.isChecked()){
                        questionChecked[9] = true;
                    }
                    if (areAllTrue(questionChecked) == true){
                        Toast.makeText(getApplicationContext(), "You must have at least one question unchecked to take the quiz", Toast.LENGTH_LONG).show();
                        setAllFalse(questionChecked);
                    } else{
                Intent i = new Intent(RegisterActivity.this, QuizActivity.class);
                startActivity(i);}
                    }
            }
        });

    }

    private boolean areAllTrue(boolean[] array){
        for (int i = 0; i < array.length; i++) {
            if (!array[i]) return false;
        }
        return true;
    }

    private void setAllFalse(boolean[] array){
        for (int i = 0; i < array.length; i++) {
            questionChecked[i] = false;
        }
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
            Intent i = new Intent(RegisterActivity.this, FragmentLayout.class);
            startActivity(i);
        }
        if(item.getItemId() == R.id.menu_setting) {
            Intent i = new Intent(RegisterActivity.this, MainMenu.class);
            startActivity(i);
            for (int index = 0; index<10; index++){
                RegisterActivity.questionChecked[index] = false;
            }
        }
        return super.onOptionsItemSelected(item);

    }

}
