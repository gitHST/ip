package duchess.list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event item in the list, extending the ListItem class.
 * Contains information about the event's name, start time, and end time.
 */
public class EventItem extends ListItem {

    /** The start time of the event as a string. */
    private String from;

    /** The end time of the event as a string. */
    private String to;

    /** The parsed start date of the event, if successfully parsed. */
    private LocalDate fromDate;

    /** The parsed end date of the event, if successfully parsed. */
    private LocalDate toDate;

    /**
     * Constructs an EventItem with the specified name, start time, and end time.
     * Attempts to parse the start and end times into LocalDate objects.
     *
     * @param itemName the name of the event
     * @param from the start time of the event in "dd-MM-yyyy" format
     * @param to the end time of the event in "dd-MM-yyyy" format
     */
    public EventItem(String itemName, String from, String to) {
        super(itemName);
        this.from = from;
        this.to = to;

        try {
            this.fromDate = LocalDate.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            this.fromDate = null;
        }

        try {
            this.toDate = LocalDate.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            this.toDate = null;
        }
    }

    /**
     * Returns a formatted string representation of the event item, including its index in the list.
     * If the start and end dates were successfully parsed, they are formatted in "dd-MMMM-yyyy" format.
     * Otherwise, the original string values are used.
     *
     * @param whiteSpaceCount the number of spaces to add after the event details
     * @param i the index of the event in the list (zero-based index)
     * @return a formatted string representation of the event item
     */
    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        String formattedFrom = (fromDate != null) ? fromDate.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy")) : from;
        String formattedTo = (toDate != null) ? toDate.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy")) : to;

        return String.format("%d. [Event] %s (from %s to %s)%s %s%n",
                i + 1,
                itemName,
                formattedFrom,
                formattedTo,
                " ".repeat(whiteSpaceCount),
                (ticked ? "[x]" : "[ ]"));
    }

    /**
     * Returns the length of the string representation of this event item.
     * The length calculation includes the base length of the format string,
     * plus the lengths of the event name, start time, and end time.
     *
     * @return the length of the string representation of this event item
     */
    @Override
    public int getLengthOfString() {
        return 20 + itemName.length() + from.length() + to.length();
    }
}