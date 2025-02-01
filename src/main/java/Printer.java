public class Printer {

    public static void PrintNicely(String input) throws InterruptedException {
        PrintNicely(input, 1);
    }

    public static void PrintNicely(String input, double speedMultiplier) throws InterruptedException {
        for (int i = 0; i < input.length(); i++) {
            System.out.print(input.charAt(i));
            int sleepTime = (int) Math.max(10, Math.min(100, 600 / input.length()) * speedMultiplier);
            Thread.sleep(sleepTime);
        }
        System.out.println();
    }
}
