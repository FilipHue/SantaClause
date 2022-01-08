package auxiliars;

import com.fasterxml.jackson.annotation.JsonProperty;
import entities.Change;

import java.util.ArrayList;

public class InputParser {
    @JsonProperty("numberOfYears")
    private Integer numberOfYears;
    @JsonProperty("santaBudget")
    private Double santaBudget;
    @JsonProperty("initialData")
    private InitialData initialData;
    private ArrayList<Change> annualChanges;

    public InputParser() {
    }

    /**
     *
     * @return
     */
    public ArrayList<Change> getAnnualChanges() {
        return annualChanges;
    }

    /**
     *
     * @param annualChanges
     */
    public void setAnnualChanges(final ArrayList<Change> annualChanges) {
        this.annualChanges = annualChanges;
    }

    /**
     *
     * @return
     */
    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    /**
     *
     * @param numberOfYears
     */
    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /**
     *
     * @return
     */
    public Double getSantaBudget() {
        return santaBudget;
    }

    /**
     *
     * @param santaBudget
     */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     *
     * @return
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     *
     * @param initialData
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }
}
