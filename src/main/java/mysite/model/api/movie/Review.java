package mysite.model.api.movie;

import java.util.Date;

public class Review {
    private final Date date;
    private final String movie;
    private final String username;
    private final float stars;
    private final String content;

    public Date getDate() {
        return date;
    }

    public String getMovie() {
        return movie;
    }

    public float getStars() {
        return stars;
    }

    public String getContent() {
        return content;
    }

    public String getUsername() {
        return username;
    }


    public Review(Date date, String movie, String username, float stars, String content) {
        this.date = date;
        this.movie = movie;
        this.username = username;
        this.stars = stars;
        this.content = content;
    }
}
