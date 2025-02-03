package Items;

public class DeadlineItem extends ListItem {
    String deadline;
    public DeadlineItem(String itemName, String deadline) {
        super(itemName);
        this.deadline = deadline;
    }

    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        return i + 1
                + ". [Deadline] "
                + itemName
                + " (by " + deadline + ")"
                + " ".repeat(whiteSpaceCount)
                + " "
                + (ticked ? "[x]" : "[ ]")
                + "\n";
    }

    @Override
    public int getLengthOfString() {
        return 17 + itemName.length() + deadline.length();
    }
}
