import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class Proxy {

    private HashMap<String,Image> p = new HashMap<String,Image>();

    public Image getImage(String address) {

        if( !p.containsKey(address) ) {
            System.out.println("getImage: Laster ned bilde.");
            downloadImage( address );
        }
        else {
            System.out.println("getImage: Laster IKKE ned bilde.");
        }

        return p.get( address ) ;
    }


    public void downloadImage(String url) {

        try {
            URL realurl = new URL(url);
            Image image = ImageIO.read( realurl );
            p.put( url, image );

            System.out.println("downloadImage: successfully put image in hashmap.");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
