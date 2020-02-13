package model.shop;

import lombok.Getter;
import model.shop.category.RTVCategory;
import model.shop.product.Product;
import requests.Request;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Shop implements IShop {
    final protected String substringBegin;
    final protected String substringEnd;
    @Getter
    final protected Set<Product> products = new HashSet<>();
    final private String base_URL;
    final private RTVCategory rtvCategory;

    public Shop(String base_URL, String substringBegin, String substringEnd) throws IOException {
        this.base_URL = base_URL;
        this.substringBegin = substringBegin;
        this.substringEnd = substringEnd;

        this.rtvCategory = new RTVCategory();
        this.rtvCategory.load(new Request(base_URL).getResponseBody());
        this.rtvCategory.repairURLs();
    }

    @Override
    public void load(int pages) throws IOException {

    }

    protected Set<URL> createURLs(final int pages) throws MalformedURLException {
        Set<URL> urls = new HashSet<>();

        for (String postfix : rtvCategory.getCategories()) {
            for (int i = 1; i <= pages; ++i) {
                urls.add(new URL(base_URL + postfix.replace("{num}", String.valueOf(i))));
            }
        }

        return urls;
    }
}
