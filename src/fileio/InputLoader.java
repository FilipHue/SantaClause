package fileio;

import common.Constants;
import database.Database;
import entities.Change;
import entities.Child;
import auxiliars.ChildrenUpdates;
import entities.Present;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public final class InputLoader {

    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * read the data from the json file
     */
    public void readData() {
        JSONParser jsonParser = new JSONParser();
        Integer numberOfYears = null;
        Double santaBudget = null;
        ArrayList<Child> children = new ArrayList<>();
        ArrayList<Present> presents = new ArrayList<>();
        ArrayList<Change> changes = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            Long jsonNumberOfYears = (Long) jsonObject.get(Constants.NOY);
            Long jsonSantaBudget = (Long) jsonObject.get(Constants.SANTA_BUDGET);
            JSONObject jsonInitialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray) jsonInitialData.get(Constants.CHILDREN);
            JSONArray jsonGifts = (JSONArray) jsonInitialData.get(Constants.GIFT_LIST);
            JSONArray jsonChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);

            if (jsonNumberOfYears != null) {
                numberOfYears = Integer.parseInt(String.valueOf(jsonNumberOfYears));
            } else {
                System.out.println("SANTA DOESN'T LEAVE THIS TIME :D");
            }

            if (jsonNumberOfYears != null) {
                santaBudget = Double.parseDouble(String.valueOf(jsonSantaBudget));
            } else {
                System.out.println("SANTA DOESN'T HAVE A PENNY TO HIS NAME :'(");
            }

            if (jsonChildren != null) {
                for (Object jsonChild: jsonChildren) {
                    children.add(new Child.Builder(
                            Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID)
                                    .toString()),
                            Integer.parseInt(((JSONObject) jsonChild).get(Constants.AGE)
                                    .toString()),
                            (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                            (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                            Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild)
                                    .get(Constants.GIFT_PREFERENCES)),
                            (String) ((JSONObject) jsonChild).get(Constants.CITY))
                            .niceScore(Double.parseDouble(((JSONObject) jsonChild)
                                    .get(Constants.NICE_SCORE).toString()))
                            .build());
                }
            } else {
                children = null;
                System.out.println("THERE ARE NO KIDS >_<");
            }

            if (jsonGifts != null) {
                for (Object jsonGift: jsonGifts) {
                    presents.add(new Present(
                            Double.parseDouble(((JSONObject) jsonGift).get(Constants.PRICE)
                                    .toString()),
                            (String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                            (String) ((JSONObject) jsonGift).get(Constants.CATEGORY)
                    ));
                }
            } else {
                presents = null;
                System.out.println("THERE ARE NO PRESENTS FOR THE KIDS THIS YEAR >.<");
            }

            if (jsonChanges != null) {
                for (Object jsonChange: jsonChanges) {
                    ArrayList<Child> newChildren = new ArrayList<>();
                    if (((JSONObject) jsonChange).get(Constants.NEW_CHILDREN) != null) {
                        for (Object newChild: (JSONArray) ((JSONObject) jsonChange)
                                .get(Constants.NEW_CHILDREN)) {
                            newChildren.add(new Child.Builder(
                                    Integer.parseInt(((JSONObject) newChild).get(Constants.ID)
                                            .toString()),
                                    Integer.parseInt(((JSONObject) newChild).get(Constants.AGE)
                                            .toString()),
                                    (String) ((JSONObject) newChild).get(Constants.FIRST_NAME),
                                    (String) ((JSONObject) newChild).get(Constants.LAST_NAME),
                                    Utils.convertJSONArray((JSONArray) ((JSONObject) newChild)
                                            .get(Constants.GIFT_PREFERENCES)),
                                    (String) ((JSONObject) newChild).get(Constants.CITY))
                                    .niceScore(Double.parseDouble(((JSONObject) newChild)
                                            .get(Constants.NICE_SCORE).toString()))
                                    .build()
                            );
                        }
                    } else {
                        newChildren = null;
                    }

                    ArrayList<Present> newGifts = new ArrayList<>();
                    if (((JSONObject) jsonChange).get(Constants.NEW_GIFT_LIST) != null) {
                        for (Object newGift: (JSONArray) ((JSONObject) jsonChange)
                                .get(Constants.NEW_GIFT_LIST)) {
                            newGifts.add(new Present(
                                    Double.parseDouble(((JSONObject) newGift).get(Constants.PRICE)
                                            .toString()),
                                    (String) ((JSONObject) newGift).get(Constants.PRODUCT_NAME),
                                    (String) ((JSONObject) newGift).get(Constants.CATEGORY)
                            ));
                        }
                    } else {
                        newGifts = null;
                    }

                    ArrayList<ChildrenUpdates> childrenUpdates = new ArrayList<>();
                    if (((JSONObject) jsonChange).get(Constants.CHILDREN_UPDATES) != null) {
                        for (Object childUpdate: (JSONArray) ((JSONObject) jsonChange)
                                .get(Constants.CHILDREN_UPDATES)) {
                            Double niceScore = null;
                            if (((JSONObject) childUpdate)
                                    .get(Constants.NICE_SCORE) != null) {
                                niceScore =
                                        Double.parseDouble(((JSONObject) childUpdate)
                                                .get(Constants.NICE_SCORE).toString());
                            }
                            childrenUpdates.add(new ChildrenUpdates(
                                    Integer.parseInt(((JSONObject) childUpdate).get(Constants.ID)
                                            .toString()),
                                    niceScore,
                                    Utils.convertJSONArray((JSONArray) ((JSONObject) childUpdate)
                                            .get(Constants.GIFT_PREFERENCES))
                            ));
                        }
                    } else {
                        childrenUpdates = null;
                    }

                    changes.add(new Change(
                            Double.parseDouble(((JSONObject) jsonChange).get(Constants.NEW_BUDGET)
                                    .toString()),
                            newGifts,
                            newChildren,
                            childrenUpdates

                    ));
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        Database database = Database.getInstance();
        database.setNumberOfYears(numberOfYears);
        database.setSantaBudget(santaBudget);
        database.setChildren(children);
        database.setPresents(presents);
        database.setChanges(changes);
    }
}
