package gibson.liam.leaguequiz;

/**
 * This class builds string through concatenation of all Memory element strings
 */

public class Utils {
    public static String memoryToString(Memory[] myMemoryArray) {
        StringBuilder builder = new StringBuilder();

        for (Memory memory : myMemoryArray) {
            if(memory != null) {
                /*
                Utilise memory.toString() rather than Array.toString() so that class specific
                toString is respected, appending newlines and returning custom data
                 */
                builder.append(memory.toString());
            }
        }

        String string = builder.toString();

        return string;
    }
}
