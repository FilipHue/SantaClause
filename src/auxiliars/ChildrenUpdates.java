package auxiliars;

import java.util.ArrayList;

public final class ChildrenUpdates {

    private Integer id;
    private Double niceScore;
    private ArrayList<String> giftsPreferences;

    public ChildrenUpdates() {
    }

    public ChildrenUpdates(final Integer id, final Double niceScore,
                           final ArrayList<String> giftPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftPreferences;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    public void setGiftsPreferences(final ArrayList<String> giftPreferences) {
        this.giftsPreferences = giftPreferences;
    }

    public Integer getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftPreferences() {
        return giftsPreferences;
    }
}
