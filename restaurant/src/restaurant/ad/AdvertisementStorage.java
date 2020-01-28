package restaurant.ad;

import java.util.ArrayList;
import java.util.List;

class AdvertisementStorage {
    private static final AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage() {
        Object someContent = new Object();
        add(new Advertisement("First Video", 5000, 100, 3 * 60));
        add(new Advertisement("Second Video", 100, 10, 15 * 60));
        add(new Advertisement("Third Video", 400, 2, 10 * 60));
    }

    public static AdvertisementStorage getInstance() {
        return instance;
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
