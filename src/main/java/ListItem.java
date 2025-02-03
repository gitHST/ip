abstract class ListItem {
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

    public abstract String getItemType();

    public boolean isTicked() {
        return ticked;
    }

    public String getItemName() {
        return itemName;
    }

    @Override
    public String toString() {
        return itemName;
    }

    public abstract String getListedStringRepresentation(int longestItemLength, int i);
}