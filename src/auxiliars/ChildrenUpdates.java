package auxiliars;

import java.util.ArrayList;

public final class ChildrenUpdates {

    private final Integer id;
    private final Double niceScore;
    private final ArrayList<String> giftPreferences;

    public ChildrenUpdates(final Integer id, final Double niceScore,
                           final ArrayList<String> giftPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftPreferences = giftPreferences;
    }

    public Integer getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftPreferences() {
        return giftPreferences;
    }
}
