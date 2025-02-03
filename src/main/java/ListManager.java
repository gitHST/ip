import Items.ListItem;
import Items.TextItem;
import Items.DeadlineItem;

import java.util.*;

class ListManager {
    private HashMap<String, ItemsList> lists;

    public ListManager() {
        lists = new HashMap<>();
    }

    public void addList(String name) {
        lists.put(name, new ItemsList());
    }

    public String getListContent(String name) {
        return lists.containsKey(name) ? lists.get(name).toString() : "I’m afraid I have no list under that name, darling.";
    }

    public String handleListCommand(String userInput) {
        String[] parts = userInput.trim().split("\\s+", 8);

        if (parts.length == 2) {
            String name = parts[1].trim();
            if (!getListContent(name).contains("no list")) {
                return getListContent(name);
            }
            addList(name);
            return "A new list, by the name of '" + name + "' has been created.";
        } else if (parts.length == 3) {
            return addItemToList(parts[1].trim(), new TextItem(parts[2].trim()));
        } else if (parts.length == 4) {
            return toggleItem(parts[1].trim(), parts[2].trim(), parts[3].trim().equals("tick"));
        } else if (parts.length == 6) {
            String listName = parts[1].trim();
            String command = parts[2].trim();
            String eventName = parts[3].trim();
            String byKeyword = parts[4].trim();
            String deadline = parts[5].trim();

            if (command.equalsIgnoreCase("deadline") && byKeyword.equalsIgnoreCase("by")) {
                return addItemToList(listName, new DeadlineItem(eventName, deadline));
            }
            return "Are you trying to add a deadline darling? I'm afraid I don't follow... Please type -h for a hand xo";
        } else if (parts.length == 8) {
            String listName = parts[1].trim();
            String command = parts[2].trim();
            String eventName = parts[3].trim();
            String fromKeyword = parts[4].trim();
            String fromDate = parts[5].trim();
            String toKeyword = parts[6].trim();
            String toDate = parts[7].trim();

            if (command.equalsIgnoreCase("event") && fromKeyword.equalsIgnoreCase("from") && toKeyword.equalsIgnoreCase("to")) {
                return addItemToList(listName, new Items.EventItem(eventName, fromDate, toDate));
            }
            return "Are you trying to add an event darling? I'm afraid I don't follow... Please type -h for a hand xo";
        }

        return "I’m afraid I didn’t catch that command properly.";
    }

    public String addItemToList(String name, ListItem item) {
        if (!lists.containsKey(name)) {
            return "I’m afraid I have no list under that name, darling.";
        }

        ItemsList list = lists.get(name);
        List<ListItem> items = list.getItems();

        String newItemName = item.toString();
        int count = 1;

        HashSet<String> existingNames = new HashSet<>();
        for (ListItem existingItem : items) {
            existingNames.add(existingItem.toString());
        }

        while (existingNames.contains(newItemName)) {
            newItemName = item.toString() + "(" + count + ")";
            count++;
        }

        if (item instanceof TextItem) {
            item = new TextItem(newItemName);
        }

        list.addItem(item);
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
