package moebiusSolutionsInc_TestCode;

public class Q4_Bug1 {
    private Integer rating;

    public Q4_Bug1(Integer rating) {
        this.rating = rating;
    }

    public int rating() {
        return rating;
    }

    public static void main(String[] args) {
        System.out.println("rating:"
                + new Q4_Bug1(5).rating());
    }

    // since rating reference variable has been never assigned a new object and we try to use it, it throws null pointer exception
    // we can fix it by creating a constructor which takes rating as a parameter.
}