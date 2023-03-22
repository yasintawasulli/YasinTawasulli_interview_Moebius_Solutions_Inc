package moebiusSolutionsInc_TestCode;

import java.util.HashMap;
import java.util.Map;

public class Q2_ProgrammingTask implements ItemStore {

    private Map<String, Item> items = new HashMap<>();

    @Override
    public void put(Item item) {
        items.put(item.getID(), item);
    }

    @Override
    public Item get(String id) {
        return items.get(id);
    }

    @Override
    public void dropAllByTag(String tag) {
        items.entrySet()
                .removeIf(entry -> entry.getValue().getTag().equals(tag));
    }

    @Override
    public int size() {
        return items.size();
    }

}