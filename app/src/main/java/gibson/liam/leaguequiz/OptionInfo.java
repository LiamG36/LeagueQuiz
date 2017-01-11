package gibson.liam.leaguequiz;

/**
 * Holds the strings used for the Help section on the toolbar in two seperate arrays that match
 * up with each other.
 */

public class OptionInfo {

    public static final String[] OPTIONS =
            {
                    "True or False",
                    "Cheat",
                    "Skip",
                    "Name",
                    "List of Questions"
            };


    public static final String[] OPTIONDETAILS =
            {
                    "The true and false buttons are the two choices for the question that appears above them. All you need to do is pick the option you believe is correct. After you have clicked either" +
                            " button, a small popup will appear and will say whether you were correct or incorrect. After a short delay the next question will appear to be answered.",
                    "The cheat button will create a small popup message which will say whether the answer to the question is correct or incorrect. If you press cheat however, " +
                            "you will not be able to score a point on this question",
                    "The skip button simply takes you to the next question. You won't score a point as you have not attempted the question",
                    "If your name isn't being accepted by the system, the most likely cause is that you aren't following the correct parameters. The system will only accept letters and up to 10 of them. If you" +
                            " think you have done this, check if there are any spaces or you have entered in a number instead of a character by accident, such as '0' instead of an 'o'.",
                    "All the questions will appear automatically; if you don't want to do a certain question, check its box in the register screen and it will be skipped. You have to do at least one question" +
                            ", so at least one box has to remain unchecked."

            };
}
