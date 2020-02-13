package model.shop.product;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Value
public class Product {
    String id;
    String name;
    String price;

    public Product(final String s) {
        Map<String, String> map = new HashMap<>();
        for (String split : s.split(",\n")) {
            final String[] pair = split.split(":");
            if (pair.length != 2)
                throw new IllegalArgumentException("must be pair: " + split);
            map.put(pair[0].replaceAll("\"", "").trim(), pair[1].replaceAll("\"", "").trim());
        }

        final String id = map.get("id");
        final String name = map.get("name");
        final String price = map.get("price");
        if (containsValue(id) && containsValue(name) && containsValue(price)) {
            this.id = id;
            this.name = name;
            this.price = price;
        } else {
            throw new IllegalArgumentException(s + " have to contain all class variables");
        }
    }

    private boolean containsValue(final String s) {
        return Objects.nonNull(s) && !s.equals("");
    }
}
