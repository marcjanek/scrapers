import model.shop.RTVShop;
import model.shop.product.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class Scraper {
    public static void main(String[] args) throws IOException {
        final Scraper scraper = new Scraper();

        final RTVShop shop = scraper.RTV_EURO_AGD_scraper();
        String time = LocalTime.now().toString();
        for (Product e : shop.getProducts()) {
            scraper.writeToFile("config/RTV/out1" + time, e.toString());
        }
    }

    public RTVShop RTV_EURO_AGD_scraper() throws IOException {
        final RTVShop shop = new RTVShop("https://www.euro.com.pl/",
                "// Google Tag Manager dataLayer\n\t\tUA.push({\"products\": [",
                "]});\n\t\t// koniec Google Tag Manager dataLayer\n"
        );
        shop.load(20);
        return shop;
    }

    private void writeToFile(String path, String out) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {
            writer.write(out + "\n");
        }
    }
}