import java.util.*;

class ListManager {
    private HashMap<String, StringList> lists;

    public ListManager() {
        lists = new HashMap<>();
    }

    public void addList(String name) {
        lists.put(name, new StringList());
    }

    public String getListContent(String name) {
        return lists.containsKey(name) ? lists.get(name).toString() : "I’m afraid I have no list under that name, darling.";
    }

    public String addItemToList(String name, String itemName) {
        if (!lists.containsKey(name)) {
            return "I’m afraid I have no list under that name, darling.";
        }

        StringList list = lists.get(name);
        List<ListItem> items = list.getItems();

        String newItemName = itemName;
        int count = 1;

        HashSet<String> existingNames = new HashSet<>();
        for (ListItem item : items) {
            existingNames.add(item.toString());
        }

        while (existingNames.contains(newItemName)) {
            newItemName = itemName + "(" + count + ")";
            count++;
        }

        list.addItem(new ListItem(newItemName));
        return "Added '" + newItemName + "' to list '" + name + "'.";
    }

    public String toggleItem(String name, String itemName, boolean tick) {
        if (!lists.containsKey(name)) {
            return "I’m afraid I have no list under that name, darling.";
        }
        for (ListItem item : lists.get(name).getItems()) {
            if (item.toString().equals(itemName)) {
                if (tick) {
                    item.tick();
                    return "Ticked '" + item + "' in list '" + name + "'.";
                } else {
                    item.untick();
                    return "Unticked '" + item + "' in list '" + name + "'.";
                }
            }
        }
        return "I’m afraid I have no item by that name in list '" + name + "', darling.";
    }
}