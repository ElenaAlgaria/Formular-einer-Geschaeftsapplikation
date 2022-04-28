package desktopx.assignment_2.cantonform.view.util;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dieter Holz
 */
public interface ImageCache {
    String NO_IMG = ImageCache.class.getResource("/images/noImg.jpg").toExternalForm();

    Map<String, Image> cache = new HashMap<>();

    default Image getImage(String url){
        final String lookupURL = (url == null || url.isBlank()) ?
                                 NO_IMG :
                                 url;

        return cache.computeIfAbsent(url, s -> {
            try {
                return new Image(lookupURL, true);
            } catch (IllegalArgumentException ex) {
                return new Image(NO_IMG, true);
            }
        });
    }
}