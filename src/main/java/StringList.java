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


}
