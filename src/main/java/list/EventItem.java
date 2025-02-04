package list;

public class EventItem extends ListItem {
    String from;
    String to;
    public EventItem(String itemName, String from, String to) {
        super(itemName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        return i + 1
                + ". [Event] "
                + itemName
                + " (from " + from + " to " + to + ")"
                + " ".repeat(whiteSpaceCount)
                + " "
                + (ticked ? "[x]" : "[ ]")
                + "\n";
    }

    @Override
    public int getLengthOfString() {
        return 20 + itemName.length() + from.length() + to.length();
    }
}
