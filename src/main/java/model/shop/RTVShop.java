package model.shop;

import model.shop.product.Product;
import requests.Request;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class RTVShop extends Shop {
    public RTVShop(String base_URL, String substringBegin, String substringEnd) throws IOException {
        super(base_URL, substringBegin, substringEnd);
    }

    @Override
    public void load(final int pages) throws IOException {
        Set<String> responses = new HashSet<>();
        double i = 0, size = createURLs(pages).size();

        for (URL url : createURLs(pages)) {
            System.out.println(++i / size);
            try {
                responses.add(new Request(url).getResponseBody(substringBegin, substringEnd));
            } catch (Exception e) {
                System.out.println(e.getMessage() + " " + e.getCause());
            }
        }

        responses.forEach(response -> {
            for (String s : response.split("},\n")) {
                try {
                    products.add(new Product(s.replaceFirst("\\{", "").replaceAll("\t", "")));
                } catch (Exception ignored) {
                }

            }
        });
    }
}
