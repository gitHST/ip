package Items;

public abstract class ListItem {
    protected String itemName;
    protected boolean ticked;

    public ListItem(String itemName) {
        this.itemName = itemName;
        this.ticked = false;
    }

    public void tick() {
        this.ticked = true;
    }

    public void untick() {
        this.ticked = false;
    }

    @Override
    public String toString() {
        return itemName;
    }

    public abstract String getListedStringRepresentation(int whiteSpaceCount, int i);

    public abstract int getLengthOfString();
}