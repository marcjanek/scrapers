package model.shop.category;

import java.util.HashSet;
import java.util.Set;

public class RTVCategory extends Category {

    @Override
    public void load(String s) {
        for (String split : s.split("<a href=\"")) {
            final int endIndex = split.indexOf("?");
            if (endIndex != -1)
                categories.add(split.substring(0, endIndex));
        }
    }

    public void repairURLs() {
        Set<String> repaired = new HashSet<>();
        for (String category : categories) {
            final String s = category.replaceFirst("\\.", ",strona-{num}.");
            repaired.add(s);
        }
        categories = repaired;
    }

    public Set<String> getCategories() {
        return this.categories;
    }
}
