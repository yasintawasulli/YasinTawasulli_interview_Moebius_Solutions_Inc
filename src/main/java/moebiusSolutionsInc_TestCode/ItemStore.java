package moebiusSolutionsInc_TestCode;

interface ItemStore {
    interface Item {
        String getID ();
        String getTag ();
    }

    /**
     * Adds an {@link Item} to the store, replacing any existing item with the
     * same {@link Item #id} value.
     */
    public void put(Item item);

    /**
     * Retrieves the {@link Item} with the given {@link Item #id} value, or
     * null if no such {@link Item} exists in the store.
     */
    public Item get(String id);

    /**
    * Delete all {@link Item}s with the specified tag.
    */
    public void dropAllByTag(String tag);

    /**
     * Returns the number of Items in the store
     */
    public int size();
}