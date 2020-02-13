package requests;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Request {

    final private URL url;

    public Request(URL url) {
        this.url = url;
    }

    public Request(String url) throws MalformedURLException {
        this.url = new URL(url);
    }

    public String getResponseBody() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);

        if (httpURLConnection.getResponseCode() == 200) {
            return inputStreamToString(httpURLConnection.getInputStream());
        } else {
            return "";
        }
    }

    public String getResponseBody(final String begin, final String end) throws IOException {
        final String responseBody = getResponseBody();
        final int beginIndex = responseBody.indexOf(begin);
        final int endIndex = responseBody.indexOf(end);

        return responseBody.substring(beginIndex + begin.length(), endIndex);
    }

    private String inputStreamToString(final InputStream inputStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new BufferedReader(new InputStreamReader
                (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

}
