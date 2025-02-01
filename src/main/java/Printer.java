import static java.lang.Thread.sleep;

public class Printer {

    public static void printNicely(String input) throws InterruptedException {
        printNicely(input, 1);
    }

    public static void printNicely(String input, double speedMultiplier) throws InterruptedException {
        for (int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            int sleepTime = (int) (Math.max(10, Math.min(100, 600 / input.length())) / speedMultiplier);
            Thread.sleep(sleepTime);
        }
        System.out.println();
    }

    public static void printLogo(int slopeyness, String logo, int milliseconds) {
        try {
            String[] lines = logo.split("\n");
            int maxLength = 0;
            int height = lines.length; // Use lines.length as the height

            // Calculate the maximum line length
            for (String line : lines) {
                maxLength = Math.max(maxLength, line.length());
            }

            int totalPrintedLines = 0;

            System.out.print("\033[" + lines.length + "A");

            // Iterate through each position in the pattern
            for (int i = 0; i < maxLength + (slopeyness * (height - 1)) + 1; i++) {
                int j = 0;
                boolean printedAny = false; // To track if we printed anything

                // Print each line in the current cycle
                for (String line : lines) {
                    // Correct substring length calculation
                    int substringLength = Math.max(0, Math.min(i - j, line.length()));  // Fix the calculation
                    if (substringLength > 0) {
                        System.out.println(line.substring(0, substringLength));
                        printedAny = true;
                        totalPrintedLines++;
                    } else {
                        // If nothing is printed from this line, print a blank line
                        System.out.println();
                        totalPrintedLines++;
                    }
                    j+=slopeyness;
                }
                sleep(milliseconds);

                // Move the cursor up by lines.length lines to overwrite the previous block
                if (printedAny) {
                    System.out.print("\033[" + height + "A");  // Move up `lines.length` lines
                }
            }

            // Move the cursor down to the position where the logo ends
            System.out.print("\033[" + totalPrintedLines + "B");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
