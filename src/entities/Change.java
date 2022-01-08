package entities;

import auxiliars.ChildrenUpdates;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public final class Change {

    @JsonProperty("newSantaBudget")
    private Double newBudget;
    @JsonProperty("newGifts")
    private ArrayList<Present> newGiftList;
    private ArrayList<Child> newChildren;
    private ArrayList<ChildrenUpdates> childrenUpdates;

    public Change() {
    }

    public Change(final Double newBudget, final ArrayList<Present> newGiftList,
                  final ArrayList<Child> newChildren,
                  final ArrayList<ChildrenUpdates> childrenUpdates) {
        this.newBudget = newBudget;
        this.newGiftList = newGiftList;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    /**
     *
     * @param newBudget
     */
    public void setNewBudget(final Double newBudget) {
        this.newBudget = newBudget;
    }

    /**
     *
     * @param newGiftList
     */
    public void setNewGiftList(final ArrayList<Present> newGiftList) {
        this.newGiftList = newGiftList;
    }

    /**
     *
     * @param newChildren
     */
    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     *
     * @param childrenUpdates
     */
    public void setChildrenUpdates(final ArrayList<ChildrenUpdates> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    /**
     *
     * @return
     */
    public Double getNewBudget() {
        return newBudget;
    }

    /**
     *
     * @return
     */
    public ArrayList<Present> getNewGiftList() {
        return newGiftList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    /**
     *
     * @return
     */
    public ArrayList<ChildrenUpdates> getChildrenUpdates() {
        return childrenUpdates;
    }
}
