package duchess.list;

import duchess.exceptions.EmptyTodoDescriptionException;

import java.util.*;

/**
 * Manages multiple lists, allowing for adding lists, adding items to lists,
 * and toggling item states in those lists.
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
     * Handles a user command related to list operations, such as creating a list,
     * adding an item, or toggling an item's status.
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
            case 4 -> toggleItem(parts[1].trim(), parts[2].trim(), parts[3].trim().equals("tick"));
            case 6 ->
                    handleDeadline(parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim());
            case 8 ->
                    handleEvent(parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim(), parts[6].trim(), parts[7].trim());
            default -> "I’m afraid I didn’t catch that command properly.";
        };
    }

    /**
     * Handles the creation of a new list if it doesn't already exist.
     * @param name The name of the list to be created.
     * @return A message confirming the creation of the list or indicating it already exists.
     */
    private String handleListCreation(String name) {
        if (!getListContent(name).contains("no list")) {
            return getListContent(name);
        }
        addList(name);
        return "A new list, by the name of '" + name + "' has been created.";
    }

    /**
     * Handles adding a deadline item to a list.
     * @param listName The name of the list to add the deadline to.
     * @param command The command associated with the deadline.
     * @param eventName The name of the event.
     * @param byKeyword The "by" keyword before the deadline.
     * @param deadline The deadline date.
     * @return A response confirming the deadline item has been added or an error message.
     */
    private String handleDeadline(String listName, String command, String eventName, String byKeyword, String deadline) {
        if (command.equalsIgnoreCase("deadline") && byKeyword.equalsIgnoreCase("by")) {
            return addItemToList(listName, new DeadlineItem(eventName, deadline));
        }
        return "Are you trying to add a deadline darling? I'm afraid I don't follow... Please type -h for a hand xo";
    }

    /**
     * Handles adding an event item to a list.
     * @param listName The name of the list to add the event to.
     * @param command The command associated with the event.
     * @param eventName The name of the event.
     * @param fromKeyword The "from" keyword before the event's starting date.
     * @param fromDate The starting date of the event.
     * @param toKeyword The "to" keyword before the event's ending date.
     * @param toDate The ending date of the event.
     * @return A response confirming the event item has been added or an error message.
     */
    private String handleEvent(String listName, String command, String eventName, String fromKeyword, String fromDate, String toKeyword, String toDate) {
        if (command.equalsIgnoreCase("event") && fromKeyword.equalsIgnoreCase("from") && toKeyword.equalsIgnoreCase("to")) {
            return addItemToList(listName, new list.EventItem(eventName, fromDate, toDate));
        }
        return "Are you trying to add an event darling? I'm afraid I don't follow... Please type -h for a hand xo";
    }

    /**
     * Adds an item to the specified list, ensuring there are no duplicate names.
     * @param name The name of the list to add the item to.
     * @param item The item to add to the list.
     * @return A message confirming the addition of the item or an error message.
     */
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

        // Ensure uniqueness by appending a number if needed
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

    /**
     * Toggles the checked status of an item in a list.
     * @param name The name of the list containing the item.
     * @param itemName The name of the item to toggle.
     * @param tick True if the item should be ticked, false to untick.
     * @return A message indicating the item was toggled or an error message if the item was not found.
     */
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
