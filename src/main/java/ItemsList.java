import Items.ListItem;

import java.util.ArrayList;
import java.util.List;

public class ItemsList {
    private final List<ListItem> items;

    public ItemsList() {
        this.items = new ArrayList<>();
    }

    public void addItem(ListItem item) {
        items.add(item);
    }

    public List<ListItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "The list is empty.";
        }

        StringBuilder sb = new StringBuilder("\nList contents:\n");
        int maxLength = items.stream().mapToInt(ListItem::getLengthOfString).max().orElse(0);

        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i).getListedStringRepresentation(maxLength - items.get(i).getLengthOfString(), i));
        }
        return sb.toString();
    }

}