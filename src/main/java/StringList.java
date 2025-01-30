import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringList {
    private final List<String> items;

    public StringList(List<String> items) {
        this.items = new ArrayList<>(items);
    }

    public StringList() {
        this.items = Collections.unmodifiableList(new ArrayList<>());
    }

    public StringList addItem(String item) {
        List<String> newItems = new ArrayList<>(items);
        newItems.add(item);
        return new StringList(newItems);
    }

    public StringList removeItem(String item) {
        List<String> newItems = new ArrayList<>(items);
        newItems.remove(item);
        return new StringList(newItems);
    }

    public StringList clearList() {
        return new StringList();
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}
