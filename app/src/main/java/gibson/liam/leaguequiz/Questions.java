package gibson.liam.leaguequiz;

/**
 The question class contains the getters for the changing of the question. It gets the string and
 image resource, and also whether the answer was true or false.
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