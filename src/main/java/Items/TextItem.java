package Items;

public class TextItem extends ListItem {
    public TextItem(String itemText) {
        super(itemText);
    }

    @Override
    public String getItemType() {
        return "Items.TextItem";
    }

    @Override
    public String getListedStringRepresentation(int longestItemLength, int i) {
        return i + 1
                + ". "
                + itemName
                + " ".repeat(Math.max(0, (longestItemLength - itemName.length())))
                + " "
                + (ticked ? "[x]" : "[ ]")
                + "\n";
    }
}
