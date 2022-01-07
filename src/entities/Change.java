package entities;

import auxiliars.ChildrenUpdates;

import java.util.ArrayList;

public final class Change {

    private final Double newBudget;
    private final ArrayList<Present> newGiftList;
    private final ArrayList<Child> newChildren;
    private final ArrayList<ChildrenUpdates> childrenUpdates;

    public Change(final Double newBudget, final ArrayList<Present> newGiftList,
                  final ArrayList<Child> newChildren,
                  final ArrayList<ChildrenUpdates> childrenUpdates) {
        this.newBudget = newBudget;
        this.newGiftList = newGiftList;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public Double getNewBudget() {
        return newBudget;
    }

    public ArrayList<Present> getNewGiftList() {
        return newGiftList;
    }

    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    public ArrayList<ChildrenUpdates> getChildrenUpdates() {
        return childrenUpdates;
    }
}
