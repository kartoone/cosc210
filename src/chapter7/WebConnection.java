package chapter7;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

public class WebConnection {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("https://mybiketraffic.com/rides/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        int code = conn.getResponseCode();
        System.out.println(code);
        InputStream webIn = conn.getInputStream();
        Scanner in = new Scanner(webIn);
        while (in.hasNextLine()) {
            String line = in.nextLine();
            System.out.println(line);
        }
    }
}
