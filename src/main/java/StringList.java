import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringList {
    private final List<ListItem> items;

    public StringList(List<ListItem> items) {
        this.items = new ArrayList<>(items);
    }

    public StringList() {
        this.items = new ArrayList<>();
    }

    public void addItem(ListItem item) {
        items.add(item);
    }

    public StringList removeItem(ListItem item) {
        List<ListItem> newItems = new ArrayList<>(items);
        newItems.remove(item);
        return new StringList(newItems);
    }

    public StringList clearList() {
        return new StringList();
    }

    public List<ListItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        if (items.isEmpty()) {
            return "The list is empty.";
        }
        int longestItemLength = items
                .stream()
                .mapToInt(item -> item
                        .toString()
                        .length())
                .max()
                .orElse(0);
        StringBuilder sb = new StringBuilder("\nList contents:\n");
        for (int i = 0; i < items.size(); i++) {
            ListItem item = items.get(i);
            sb
                    .append(i + 1)
                    .append(". ")
                    .append(item)
                    .append(" ".repeat(Math.max(0, (longestItemLength - item.toString().length()))))
                    .append(" ")
                    .append(item.isTicked() ? "[x]" : "[ ]")
                    .append("\n");
        }
        return sb.toString();
    }

}
