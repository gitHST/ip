package Items;

public class TodoItem extends ListItem {
    public TodoItem(String itemName) {
        super(itemName);
    }

    @Override
    public String getItemType() {
        return null;
    }

    @Override
    public String getListedStringRepresentation(int longestItemLength, int i) {
        return null;
    }
}
