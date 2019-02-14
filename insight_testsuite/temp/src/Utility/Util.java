package Utility;

import Records.DrugEntry;
import Prescription.Prescription;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 *  This class consists mainly of functions used by the RecordKeeper to handle File IO as efficiently as possible.
 *
 */
public class Util {

    private static final String INPUT = "input/";
    private static final String OUTPUT = "output/";
    private static final String CSV_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; // Regex for Ignoring commas within quotes

    public static BufferedReader getReader(String name) throws IOException {
        return Files.newBufferedReader(Paths.get(name), StandardCharsets.UTF_8);
    }

    /**
     * parsePrescription reads each line, and generates a Prescription from the comma separated string.
     *
     * NOTE: This will ignore commas within quotes. E.g. "Drug Name, 2000"
     *
     * @param line
     * @return Prescription object, that can be added to the drug map.
     */
    public static Prescription parsePrescription(String line) {
        String[] parameters = line.split(CSV_REGEX);
        String lastName = parameters[1];
        String firstName = parameters[2];
        String drug = parameters[3];

        double cost = Double.parseDouble(parameters[4]);

        return new Prescription(drug, firstName, lastName, cost);
    }

    public static void write(String name, String header, Map<String, DrugEntry> map) throws IOException{
        String path = name;

        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write(header + "\n");

        for (Map.Entry<String, DrugEntry> entry : map.entrySet()) {
            String drug = entry.getKey();
            DrugEntry de = entry.getValue();

            bw.write(drug + "," + de.getNumberOfPrescribers() + "," + String.format("%.0f", de.getTotalCost()) + "\n");
        }

        bw.close();
        fw.close();
    }

    public static String[] argsParser(String[] args) {

        if (args.length < 2) {
            System.out.println("NOTE! Using demo input file");
            args = new String[2];
            args[0] = INPUT + "itcont.txt";
            args[1] = OUTPUT + "top_cost_drug.txt";

            return args;
        }

        return args;
    }
}
