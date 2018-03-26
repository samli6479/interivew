package gowatchit.com.matcher.dto;

import java.util.List;

public class Movie {
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public List<String> getCasts() {
        return Casts;
    }

    public void setCasts(List<String> casts) {
        Casts = casts;
    }

    public int getGwiId() {
        return gwiId;
    }

    public void setGwiId(int gwiId) {
        this.gwiId = gwiId;
    }

    public String getXboxId() {
        return xboxId;
    }

    public void setXboxId(String xboxId) {
        this.xboxId = xboxId;
    }

    private String Title;

    private String Year;

    private String Director;

    private List<String> Casts;

    private int gwiId;

    private String xboxId;


}
