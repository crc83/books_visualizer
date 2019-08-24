package org.sbelei.booksvis.download;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.json.JSONObject;

import com.jayway.jsonpath.JsonPath;

public class ImageDownlader {

    public boolean download(String name) {
        try {
            String json = readJsonFromGoogle(name);

            List<String> imageUrls = JsonPath.read(json, "$.items[*][*].imageobject[*].url");
            int i =0;
            for(String imageUrl :imageUrls) {
                String imageDest = String.format("./pictures/%s-%d.png", name, i++);
                saveImage(imageUrl, imageDest);
                if (i>5) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @SuppressWarnings("resource")
    private String readJsonFromGoogle(String name) throws MalformedURLException, IOException {
        String key = System.getenv("GOOGLE_KEY");
        String urlTemplate = "https://www.googleapis.com/customsearch/v1/siterestrict?%s&q=\"%s\"&fileType=png&imgType=clipart&searchType=image";
        URL url = new URL(String.format(urlTemplate, key, name));
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        System.out.println(builder.toString());
        try {
            new PrintStream("./out/google_responce.json").println(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }
}
