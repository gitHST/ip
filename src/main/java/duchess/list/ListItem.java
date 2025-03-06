package duchess.list;

/**
 * Represents an item in a list, which can be either ticked or unticked.
 * This is an abstract class meant to be extended by specific item types.
 */
public abstract class ListItem {

    /** The name of the list item. */
    protected String itemName;

    /** Indicates whether the item is ticked. */
    protected boolean ticked;

    /**
     * Constructs a ListItem with the given name, initializing it as unticked.
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
     * Retrieves a formatted string representation of the item.
     *
     * @param whiteSpaceCount the number of leading spaces for formatting
     * @param i an index or identifier for the item (usage may vary in implementations)
     * @return the formatted string representation of the item
     */
    public abstract String getListedStringRepresentation(int whiteSpaceCount, int i);

    /**
     * Returns the length of the string representation of the item.
     *
     * @return the length of the item's string representation
     */
    public abstract int getLengthOfString();
}