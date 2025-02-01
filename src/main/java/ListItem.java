public class ListItem {
    String itemText;
    boolean ticked;
    public ListItem(String itemText) {
        this.itemText = itemText;
        this.ticked = false;
    }
    public void tick() {
        this.ticked = true;
    }
    public void untick() {
        this.ticked = false;
    }
    public String toString() {
        return itemText;
    }
    public boolean isTicked() {
        return ticked;
    }
}
