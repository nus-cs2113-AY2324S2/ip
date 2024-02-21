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

    public String getHappy() {
        return HAPPY;
    }

    public String getIdle() {
        return IDLE;
    }
}
