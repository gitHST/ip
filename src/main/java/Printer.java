import static java.lang.Thread.sleep;

public class Printer {

    public static void printNicely(String input) throws InterruptedException {
        printNicely(input, 1);
    }

    public static void printNicely(String input, double speedMultiplier) throws InterruptedException {
        if (System.console() == null) {
            System.out.println(input);
            return;
        }
        for (int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            int sleepTime = (int) (Math.max(10, Math.min(100, 600 / input.length())) / speedMultiplier);
            Thread.sleep(sleepTime);
        }
        System.out.println();
    }

    public static void printLogo(int slopeyness, String logo, int milliseconds) {
        if (System.console() == null) {
            System.out.println(logo);
            return;
        }
        try {
            String[] lines = logo.split("\n");
            int maxLength = 0;
            int height = lines.length;

            for (String line : lines) {
                maxLength = Math.max(maxLength, line.length());
            }

            int totalPrintedLines = 0;

            System.out.print("\033[" + lines.length + "A");

            for (int i = 0; i < maxLength + (slopeyness * (height - 1)) + 1; i++) {
                int j = 0;
                boolean printedAny = false;

                for (String line : lines) {
                    int substringLength = Math.max(0, Math.min(i - j, line.length()));
                    if (substringLength > 0) {
                        System.out.println(line.substring(0, substringLength));
                        printedAny = true;
                    } else {
                        System.out.println();
                    }
                    totalPrintedLines++;
                    j+=slopeyness;
                }
                sleep(milliseconds);

                if (printedAny) {
                    System.out.print("\033[" + height + "A");
                }
            }

            System.out.print("\033[" + totalPrintedLines + "B");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
