package Records;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;


public class DrugEntry implements Comparable<DrugEntry>{

    private String drugName;
    private Double totalCost;
    private HashSet<String> prescribers;

    DrugEntry(String prescriber, String drugName, Double value) {
        super();

        this.drugName = drugName;
        this.totalCost = value;

        prescribers = new HashSet<>();
        prescribers.add(prescriber);
    }

    void addPrescriber(String prescriber) {
        prescribers.add(prescriber);
    }

    void addCost(double cost) {
        this.totalCost += cost;
    }

    public int getNumberOfPrescribers() {
        return prescribers.size();
    }

    /**
        First compare based on the totalCost. If the totalCost is the same, then compare based on drugName.
     */
    public int compareTo(DrugEntry o) {
        return Objects.equals(totalCost, o.totalCost)
                ? drugName.compareTo(o.drugName)
                : (int) (o.totalCost - totalCost);
    }

    public Double getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString() {
        return "Drug: " + this.drugName
                + " | Prescribers: " + this.getNumberOfPrescribers()
                + " | Total Cost: " + this.totalCost;
    }

}

