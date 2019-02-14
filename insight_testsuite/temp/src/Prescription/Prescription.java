package Prescription;

/**
 * Prescription is a model that stores information pertaining a particular prescription.
 */
public class Prescription {

    private String drug;
    private String prescriber;
    private double cost;

    public Prescription(String drug, String firstName, String lastName, double cost) {
        this.drug = drug;
        this.prescriber = firstName + " " + lastName;
        this.cost = cost;
    }

    public String getDrug() {
        return drug;
    }

    public String getPrescriber() {
        return prescriber;
    }

    public double getCost() {
        return cost;
    }


}
