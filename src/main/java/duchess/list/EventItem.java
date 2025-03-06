package duchess.list;

/**
 * Represents an event item in the list, extending the ListItem class.
 * Contains information about the event's name, start time, and end time.
 */
public class EventItem extends ListItem {

    /** The start time of the event */
    private String from;

    /** The end time of the event */
    private String to;

    /**
     * Constructs an EventItem with the specified name, start time, and end time.
     *
     * @param itemName the name of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public EventItem(String itemName, String from, String to) {
        super(itemName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of the event item with the specified
     * whitespace count and index.
     *
     * @param whiteSpaceCount the number of spaces to add after the event details
     * @param i the index of the event in the list
     * @return a formatted string representation of the event item
     */
    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        // Using String.format() for better readability and to avoid deep concatenation
        return String.format("%d. [Event] %s (from %s to %s)%s %s%n",
                i + 1,
                itemName,
                from,
                to,
                " ".repeat(whiteSpaceCount),
                (ticked ? "[x]" : "[ ]"));
    }


    /**
     * Returns the length of the string representation of this event item.
     *
     * @return the length of the string representation
     */
    @Override
    public int getLengthOfString() {
        // Improved readability by calculating the total length clearly
        return 20 + itemName.length() + from.length() + to.length();
    }
}
