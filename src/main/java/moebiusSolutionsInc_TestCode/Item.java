package moebiusSolutionsInc_TestCode;

public class Item implements ItemStore.Item {
    private String id;
    private String tag;


    @Override
    public String getID() {
        return this.id;
    }

    @Override
    public String getTag() {
        return this.tag;
    }
}