package duchess.list;

import duchess.exceptions.EmptyTodoDescriptionException;

import java.util.*;

/**
 * Manages multiple lists, allowing for adding lists, adding items to lists,
 * toggling item states in those lists, and deleting items from lists.
 */
public class ListManager {
    private HashMap<String, ItemsList> lists;

    /**
     * Constructs a new ListManager, initializing an empty map of lists.
     */
    public ListManager() {
        lists = new HashMap<>();
    }

    /**
     * Adds a new list with the specified name.
     * @param name The name of the list to be added.
     */
    public void addList(String name) {
        lists.put(name, new ItemsList());
    }

    /**
     * Retrieves the content of a list as a string.
     * @param name The name of the list.
     * @return A string representing the list content, or an error message if the list does not exist.
     */
    public String getListContent(String name) {
        return lists.containsKey(name) ? lists.get(name).toString() : "I’m afraid I have no list under that name, darling.";
    }

    /**
     * Handles a user command related to list operations.
     * @param userInput The user's input command.
     * @return A response based on the user's command.
     */
    public String handleListCommand(String userInput) throws EmptyTodoDescriptionException {
        if (userInput.startsWith("list") && userInput.contains("todo") && userInput.split(" ").length == 2) {
            throw new EmptyTodoDescriptionException();
        }
        String[] parts = userInput.trim().split("\\s+", 8);

        return switch (parts.length) {
            case 2 -> handleListCreation(parts[1].trim());
            case 3 -> addItemToList(parts[1].trim(), new TextItem(parts[2].trim()));
            case 4 -> handleItemModification(parts[1].trim(), parts[2].trim(), parts[3].trim());
            case 6 -> handleDeadline(parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim());
            case 8 -> handleEvent(parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim());
            default -> "I’m afraid I didn’t catch that command properly.";
        };
    }

    private String handleItemModification(String listName, String itemName, String action) {
        return switch (action.toLowerCase()) {
            case "tick" -> toggleItem(listName, itemName, true);
            case "untick" -> toggleItem(listName, itemName, false);
            case "delete" -> deleteItemFromList(listName, itemName);
            default -> "I'm afraid I don't understand that action, darling.";
        };
    }

    private String deleteItemFromList(String listName, String itemName) {
        if (!lists.containsKey(listName)) {
            return "I’m afraid I have no list under that name, darling.";
        }

        ItemsList list = lists.get(listName);
        Iterator<ListItem> iterator = list.getItems().iterator();

        while (iterator.hasNext()) {
            ListItem item = iterator.next();
            if (item.toString().equals(itemName)) {
                iterator.remove();
                return "Deleted '" + itemName + "' from list '" + listName + "'.";
            }
        }
        return "I’m afraid I have no item by that name in list '" + listName + "', darling.";
    }

    private String handleListCreation(String name) {
        if (!getListContent(name).contains("no list")) {
            return getListContent(name);
        }
        addList(name);
        return "A new list, by the name of '" + name + "' has been created.";
    }

    private String handleDeadline(String listName, String command, String eventName, String byKeyword, String deadline) {
        if (command.equalsIgnoreCase("deadline") && byKeyword.equalsIgnoreCase("by")) {
            return addItemToList(listName, new DeadlineItem(eventName, deadline));
        }
        return "Are you trying to add a deadline darling? I'm afraid I don't follow... Please type -h for a hand xo";
    }

    private String handleEvent(String listName, String command, String eventName, String fromKeyword, String fromDate, String toKeyword, String toDate) {
        if (command.equalsIgnoreCase("event") && fromKeyword.equalsIgnoreCase("from") && toKeyword.equalsIgnoreCase("to")) {
            return addItemToList(listName, new EventItem(eventName, fromDate, toDate));
        }
        return "Are you trying to add an event darling? I'm afraid I don't follow... Please type -h for a hand xo";
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