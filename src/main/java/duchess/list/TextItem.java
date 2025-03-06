package duchess.list;

/**
 * Represents an item in a list with text representation and checkable state.
 */
public class TextItem extends ListItem {

    /**
     * Constructs a TextItem with the specified text.
     *
     * @param itemText the text to represent this item
     */
    public TextItem(String itemText) {
        super(itemText);
    }

    /**
     * Returns the string representation of the item to be listed, formatted with
     * the index, the item text, whitespace for alignment, and the ticked state.
     *
     * @param whiteSpaceCount the number of spaces to align the list item
     * @param i the index of the item in the list
     * @return the formatted string representation of this item
     */
    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        // Using String.format for better readability and clarity
        return String.format("%d. %s%s %s%n",
                i + 1,
                itemName,
                " ".repeat(whiteSpaceCount),
                (ticked ? "[x]" : "[ ]"));
    }

    @Override
    public String toCsv() {
        return "TextItem," + (ticked ? "1" : "0") + "," + itemName;
    }

    /**
     * Returns the length of the item's text representation.
     *
     * @return the length of the item's text (itemName)
     */
    @Override
    public int getLengthOfString() {
        return itemName.length();
    }
}
