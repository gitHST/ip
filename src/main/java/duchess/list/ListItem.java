package duchess.list;

/**
 * Represents an item in the list, either ticked or unticked.
 * This is an abstract class meant to be extended by specific item types.
 */
public abstract class ListItem {

    /** The name of the list item. */
    protected String itemName;

    /** A flag to indicate whether the item is ticked or not. */
    protected boolean ticked;

    /**
     * Constructs a ListItem with the given name, initializing the ticked flag to false.
     *
     * @param itemName the name of the item
     */
    public ListItem(String itemName) {
        this.itemName = itemName;
        this.ticked = false;
    }

    /**
     * Marks the item as ticked.
     */
    public void tick() {
        this.ticked = true;
    }

    /**
     * Marks the item as unticked.
     */
    public void untick() {
        this.ticked = false;
    }

    /**
     * Returns the string representation of the item.
     *
     * @return the name of the item
     */
    @Override
    public String toString() {
        return itemName;
    }

    /**
     * Retrieves a string representation of the item, formatted with the specified white space.
     *
     * @param whiteSpaceCount the number of spaces to prepend
     * @param i an index or identifier for the item (used in specific implementations)
     * @return the formatted string representation of the item
     */

    public abstract String getListedStringRepresentation(int whiteSpaceCount, int i);


    /**
     * Returns the length of the string representation of the item.
     *
     * @return the length of the string representation
     */
    public abstract int getLengthOfString();
}
