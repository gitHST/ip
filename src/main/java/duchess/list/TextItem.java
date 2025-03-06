package duchess.list;

/**
 * Represents an item in a list with a text label and a checkable state.
 */
public class TextItem extends ListItem {

    /**
     * Constructs a {@code TextItem} with the specified text.
     *
     * @param itemText the text label of this item
     */
    public TextItem(String itemText) {
        super(itemText);
    }

    /**
     * Returns the formatted string representation of this item.
     * The format includes the item's index, its text, additional whitespace for alignment,
     * and a checkbox indicating whether the item is ticked.
     *
     * @param whiteSpaceCount the number of spaces for alignment
     * @param i the index of this item in the list (zero-based)
     * @return a formatted string representing this item
     */
    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        return String.format("%d. %s%s %s%n",
                i + 1,
                itemName,
                " ".repeat(whiteSpaceCount),
                (ticked ? "[x]" : "[ ]"));
    }

    /**
     * Returns the length of the item's text label.
     *
     * @return the length of {@code itemName}
     */
    @Override
    public int getLengthOfString() {
        return itemName.length();
    }
}
