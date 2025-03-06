package duchess.list;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a list item with a deadline, extending the base ListItem class.
 */
public class DeadlineItem extends ListItem {

    /** The deadline as a string in "dd-MM-yyyy" format. */
    private String deadline;

    /** The parsed deadline date, or null if parsing fails. */
    private LocalDate deadlineDate;

    /**
     * Constructs a DeadlineItem with a name and deadline.
     *
     * @param itemName The name of the item.
     * @param deadline The deadline for the item, in "dd-MM-yyyy" format.
     */
    public DeadlineItem(String itemName, String deadline) {
        super(itemName);
        this.deadline = deadline;
        try {
            this.deadlineDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            this.deadlineDate = null; // Fallback if the date cannot be parsed
        }
    }

    /**
     * Returns a string representation of the item, formatted with its index,
     * name, deadline, and ticked status.
     *
     * @param whiteSpaceCount The number of spaces to add after the item information.
     * @param i The index of the item in the list (used for numbering, 0-based).
     * @return A formatted string representing the item.
     */
    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        return (i + 1) + ". [Deadline] " + itemName + " (by "
                + (deadlineDate != null ? deadlineDate.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy")) : deadline)
                + ")" + " ".repeat(whiteSpaceCount)
                + " " + (ticked ? "[x]" : "[ ]") + "\n";
    }

    /**
     * Calculates the length of the string representation of this item.
     * The length includes the fixed prefix, the item name, and the deadline string.
     *
     * @return The total length of the string representation.
     */
    @Override
    public int getLengthOfString() {
        return 17 + itemName.length() + deadline.length();
    }
}