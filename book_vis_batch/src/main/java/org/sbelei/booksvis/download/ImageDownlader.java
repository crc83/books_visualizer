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

import com.jayway.jsonpath.JsonPath;

public class ImageDownlader {

    public boolean download(String name) {
        try {
            String json = readJsonFromGoogle(name);

            List<String> imageUrls = JsonPath.read(json, "$.items[*].link");
            int i =0;
            for(String imageUrl :imageUrls) {
                String imageDest = String.format("./pictures/%s-%d.png", name, i++);
                saveImage(imageUrl, imageDest);
                if (i>10) {	//google returns 10 images per page. it's enough for us
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
        String key = System.getenv("GOOGLE_KEY").replaceAll(":", "%3A"); //this is mandatory
        //String urlTemplate = "https://www.googleapis.com/customsearch/v1/siterestrict?q=\"%s\"&%s&fileType=png";
        String urlTemplate =
                "https://www.googleapis.com/customsearch/v1?q=\"%s\"&%s"+
        "&fileType=png&searchType=image"+	//looking for images png
        //"&filter=1&gl=ua&hl=ua" + //looking for ukrainian resources
        "&imgColorType=color" +
        "&imgSize=large" + //looking for medium color images
        "&imgType=clipart" +
//        "&imgType=photo" +
        "&safe=active"; //safe images (no 18+)
        URL url = new URL(String.format(urlTemplate, name, key));
        URLConnection connection = url.openConnection();

        String line;
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        System.out.println(builder.toString());
        try {
            new PrintStream("./out/google_responce_"+name+".json").println(builder.toString());
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
