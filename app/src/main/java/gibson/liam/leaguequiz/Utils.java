package gibson.liam.leaguequiz;

/**
 * Created by Liam on 28/12/2016.
 */

public class Utils {
    public static String memoryToString(Memory[] myMemoryArray) {
        StringBuilder builder = new StringBuilder();

        for (Memory memory : myMemoryArray) {
            if(memory != null) {
                builder.append(memory.toString());
            }
        }

        String string = builder.toString();

        return string;
    }
}
