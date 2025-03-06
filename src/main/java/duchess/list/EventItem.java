package duchess.list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event item in the list, extending the ListItem class.
 * Contains information about the event's name, start time, and end time.
 */
public class EventItem extends ListItem {

    /** The start time of the event */
    private String from;

    /** The end time of the event */
    private String to;

    /** Parsed start and end dates, if applicable */
    private LocalDate fromDate;
    private LocalDate toDate;

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
     * Returns a string representation of the event item with the specified
     * whitespace count and index.
     *
     * @param whiteSpaceCount the number of spaces to add after the event details
     * @param i the index of the event in the list
     * @return a formatted string representation of the event item
     */
    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        // Format dates if they were successfully parsed, otherwise use original strings
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
     *
     * @return the length of the string representation
     */
    @Override
    public int getLengthOfString() {
        // Improved readability by calculating the total length clearly
        return 20 + itemName.length() + from.length() + to.length();
    }
}
