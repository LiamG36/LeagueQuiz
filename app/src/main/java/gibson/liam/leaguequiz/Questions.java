package gibson.liam.leaguequiz;

/**
 * Created by Liam on 20/12/2016.
 */

public class Questions {

    private int mTextResId;
    private boolean mAnswerTrue;
    private int mImageResId;

    public int getImageResId() {
        return mImageResId;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public Questions(int textResId, boolean answerTrue, int imageResId){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mImageResId = imageResId;
    }

}