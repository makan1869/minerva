package ir.serenade.minerva.domain;

import java.util.Date;

/**
 * Created by serenade on 8/10/18.
 */
public interface AggregatedActivity {
    public Long getCount();

    public String getAuthor();

    public String getArtist();

    public String getBookPublisher();

    public String getService();

    public String getAction();

    public String getAlbum();

    public String getPublisher();

    public String getAggregator();

    public String getDate();
}
