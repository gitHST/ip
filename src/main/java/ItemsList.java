import java.util.ArrayList;
import java.util.List;

public class ItemsList {
    private final List<ListItem> items;

    public ItemsList(List<ListItem> items) {
        this.items = new ArrayList<>(items);
    }

    public ItemsList() {
        this.items = new ArrayList<>();
    }

    public void addItem(ListItem item) {
        items.add(item);
    }

    public ItemsList removeItem(ListItem item) {
        List<ListItem> newItems = new ArrayList<>(items);
        newItems.remove(item);
        return new ItemsList(newItems);
    }

    public ItemsList clearList() {
        return new ItemsList();
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
            sb.append(items.get(i).getListedStringRepresentation(longestItemLength, i));
        }
        return sb.toString();
    }
}