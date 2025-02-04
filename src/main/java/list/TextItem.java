package list;

public class TextItem extends ListItem {
    public TextItem(String itemText) {
        super(itemText);
    }

    @Override
    public String getListedStringRepresentation(int whiteSpaceCount, int i) {
        return i + 1
                + ". "
                + itemName
                + " ".repeat(whiteSpaceCount)
                + " "
                + (ticked ? "[x]" : "[ ]")
                + "\n";
    }

    @Override
    public int getLengthOfString() {
        return itemName.length();
    }
}
