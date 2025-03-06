package duchess.list;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a list item with a deadline, extending the base ListItem class.
 */
public class DeadlineItem extends ListItem {

    /** The deadline associated with the item. */
    private String deadline;
    private LocalDate deadlineDate;

    /**
     * Constructs a DeadlineItem with a name and deadline.
     *
     * @param itemName The name of the item.
     * @param deadline The deadline for the item.
     */
    public DeadlineItem(String itemName, String deadline) {
        super(itemName);
        this.deadline = deadline;
        try {
            this.deadlineDate = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            this.deadlineDate = null;
        }
    }

    /**
     * Returns a string representation of the item, formatted with its index,
     * name, deadline, and ticked status.
     *
     * @param whiteSpaceCount The number of spaces to add after the item information.
     * @param i The index of the item in the list (used for numbering).
     * @return A formatted string representing the item.
     */
    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        // Using explicit parentheses to make the grouping clear
        return (i + 1) + ". [Deadline] " + itemName + " (by "
                + (deadlineDate != null ? deadlineDate.format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy")) : deadline)
                + ")" + " ".repeat(whiteSpaceCount)
                + " " + (ticked ? "[x]" : "[ ]") + "\n";
    }

    /**
     * Calculates the length of the string representation of this item.
     *
     * @return The length of the string representation of this item.
     */
    @Override
    public int getLengthOfString() {
        // Avoid magic numbers by explaining the constant length (17 is a fixed prefix length)
        return 17 + itemName.length() + deadline.length();
    }
}
