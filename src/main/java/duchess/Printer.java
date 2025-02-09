package duchess;

/**
 * Utility class for printing text and logos with animated effects.
 */
public class Printer {

    private static final int MIN_SLEEP_TIME = 10;
    private static final int MAX_SLEEP_TIME = 100;
    private static final int BASE_SLEEP_DIVISOR = 600;

    /**
     * Prints the given text with a typewriter effect at default speed.
     *
     * @param input The text to print.
     * @throws InterruptedException If thread sleep is interrupted.
     */
    public static void printNicely(String input) throws InterruptedException {
        printNicely(input, 1.0);
    }

    /**
     * Prints the given text with a typewriter effect at a specified speed.
     *
     * @param input          The text to print.
     * @param speedMultiplier Speed multiplier for adjusting the printing speed.
     * @throws InterruptedException If thread sleep is interrupted.
     */
    public static void printNicely(String input, double speedMultiplier) throws InterruptedException {
        if (System.console() == null) {
            System.out.println(input);
            return;
        }

        int sleepTime = (int) ((Math.max(MIN_SLEEP_TIME, Math.min(MAX_SLEEP_TIME, BASE_SLEEP_DIVISOR / input.length()))) / speedMultiplier);

        for (char c : input.toCharArray()) {
            System.out.print(c);
            Thread.sleep(sleepTime);
        }
        System.out.println();
    }

    /**
     * Prints an ASCII logo with a slanted effect over a specified duration.
     *
     * @param slopeyness    The degree of slant applied to the logo.
     * @param logo          The ASCII logo to print.
     * @param milliseconds  The delay between each frame in milliseconds.
     */
    public static void printLogo(int slopeyness, String logo, int milliseconds) {
        if (System.console() == null) {
            System.out.println(logo);
            return;
        }

        try {
            String[] lines = logo.split("\n");
            int maxLength = getMaxLineLength(lines);
            int height = lines.length;
            int totalPrintedLines = 0;

            System.out.print("\033[" + height + "A");

            for (int i = 0; i < maxLength + (slopeyness * (height - 1)) + 1; i++) {
                totalPrintedLines += printFrame(lines, i, slopeyness);
                Thread.sleep(milliseconds);
                System.out.print("\033[" + height + "A");
            }

            System.out.print("\033[" + totalPrintedLines + "B");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Printing interrupted.");
        }
    }

    /**
     * Computes the maximum line length from an array of strings.
     *
     * @param lines The array of lines.
     * @return The maximum line length.
     */
    private static int getMaxLineLength(String[] lines) {
        int maxLength = 0;
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length());
        }
        return maxLength;
    }

    /**
     * Prints a single frame of the animated logo.
     *
     * @param lines      The array of logo lines.
     * @param frameIndex The current frame index.
     * @param slopeyness The degree of slant applied.
     * @return The number of printed lines.
     */
    private static int printFrame(String[] lines, int frameIndex, int slopeyness) {
        int printedLines = 0;
        int offset = 0;

        for (String line : lines) {
            int substringLength = Math.max(0, Math.min(frameIndex - offset, line.length()));
            System.out.println(substringLength > 0 ? line.substring(0, substringLength) : "");
            printedLines++;
            offset += slopeyness;
        }
        return printedLines;
    }
}
