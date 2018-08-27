package ir.serenade.minerva.domain;

import java.util.Date;

/**
 * Created by serenade on 8/10/18.
 */
public class AggregatedActivity {

    private long count;
    private String author;
    private String artist;
    private String bookPublisher;
    private String service;
    private String action;
    private String album;
    private String publisher;
    private String aggregator;
    private String date;


    public AggregatedActivity() {
    }

    public AggregatedActivity(String author, String artist, String bookPublisher, String service, String action, String album, String publisher, String aggregator, String date, long count) {
        this.count = count;
        this.author = author;
        this.artist = artist;
        this.bookPublisher = bookPublisher;
        this.service = service;
        this.action = action;
        this.album = album;
        this.publisher = publisher;
        this.aggregator = aggregator;
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public String getAuthor() {
        return author;
    }

    public String getArtist() {
        return artist;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public String getService() {
        return service;
    }

    public String getAction() {
        return action;
    }

    public String getAlbum() {
        return album;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAggregator() {
        return aggregator;
    }

    public String getDate() {
        return date;
    }
}
