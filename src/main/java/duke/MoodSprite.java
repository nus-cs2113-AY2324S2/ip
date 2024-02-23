package duke;

public class MoodSprite {
    private static final String HAPPY =   "            ███         \n"
                                        + "          ███████       \n"
                                        + "         ████ ████      \n"
                                        + "        ███     ███     \n"
                                        + "       ██         ██    \n"
                                        + "   ██                 ██\n"
                                        + "    ███             ███ \n"
                                        + "      ███████████████   \n\n";

    private static final String IDLE =    "       █████████████    \n"
                                        + "      ██           ██   \n"
                                        + "      ██   █████   ██   \n"
                                        + "      ██   █████   ██   \n"
                                        + "      ██           ██   \n"
                                        + "       █████████████    \n"
                                        + "   ██                  ██\n"
                                        + "     ██████████████████ \n\n";

    private static final String LINEBREAK = "----------------------------------------------------------";

    public static String getHappy() {
        return HAPPY;
    }

    public static String getIdle() {
        return IDLE;
    }

    public static String getLineBreak() {return LINEBREAK;}
}
