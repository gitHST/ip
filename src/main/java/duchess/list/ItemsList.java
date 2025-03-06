package duchess.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of items and provides methods to add items, retrieve them,
 * and generate a formatted string representation of the list.
 */
public class ItemsList {

    private final List<ListItem> items;

    /**
     * Constructs an empty {@code ItemsList}.
     */
    public ItemsList() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a new item to the list.
     *
     * @param item the item to add to the list
     */
    public void addItem(ListItem item) {
        items.add(item);
    }

    /**
     * Retrieves all items in the list.
     *
     * @return a list containing all items in this {@code ItemsList}
     */
    public List<ListItem> getItems() {
        return items;
    }

    /**
     * Returns a formatted string representation of the list. If the list is empty,
     * it returns a message indicating that the list is empty. Otherwise, it formats
     * the list items for display, aligning them based on the longest item's length.
     *
     * @return a string representation of the list's contents
     */
    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "The list is empty.";
        }

        StringBuilder sb = new StringBuilder("\nList contents:\n");

        // Get the maximum length of the string representations of the items in the list
        int maxLength = items.stream()
                .mapToInt(ListItem::getLengthOfString)
                .max()
                .orElse(0);

        // Construct the string representation of the list items
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i)
                    .getListedStringRepresentation(maxLength - items.get(i).getLengthOfString(), i));
        }

        return sb.toString();
    }
}