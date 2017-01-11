package gibson.liam.leaguequiz;

import java.util.Comparator;

/**
 *This class sets the name and scores into an object to be put into the array. Also contains the
 * comparator that orders the objects in the array by their score.
 */

public class Memory{
    private int score;
    private String name;

    public Memory (){
        score = 0;
    }

    public void setName (String nameValue){
        name = nameValue;
    }

    public String getName(){
        return name;
    }

    public void setScore (int scoreValue){
        score = scoreValue;
    }

    //Compares the score elements of each object in the array against each other and orders them in descending order
    public static Comparator<Memory> SCORE_COMPARATOR = new Comparator<Memory>() {
        @Override
        public int compare(final Memory o1, final Memory o2) {
            int out;

            if ((o1 != null)&&(o2 == null)){
                out=1;
            } else if ((o1 ==null)&&(o2==null)){
                out = 0;
            } else if ((o1==null)&&(o2!=null)){
                return -1;
            } else {
                out=-Integer.valueOf(o1.score).compareTo(o2.score);
            }
            return out;
        }
    };

    public String toString(){
        return name + "\t\t" + score +"\n";
    }
}
