package PharmacyCounter;

import Records.DrugEntry;
import Records.DrugMap;

import Utility.Util;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.Map;

/**
 * The RecordKeeper is the main object that generates the required statistics.
 * It has only one public function.
 */

public class RecordKeeper {

    private String inputName;
    private String outputName;
    private DrugMap records;

    private static final String HEADER = "drug_name,num_prescriber,total_cost";

    public RecordKeeper(String input, String output) {
        inputName = input;
        outputName = output;
        records = new DrugMap();
    }

    public void generatePrescriptionStatistics() throws IOException {
        Map<String, DrugEntry> map = this.readDrugRecords();
        this.writeDrugStatistics(map);
    }

    private Map<String, DrugEntry> readDrugRecords() throws IOException {
        BufferedReader br = Util.getReader(inputName);
        br.readLine();

        int count = 0;
        for (String line; (line = br.readLine()) != null; ) {
            count++;
            System.out.println(line);
            if (count % 1000000 == 0) System.out.println("Parsed " + count + " lines of data");
            records.put(Util.parsePrescription(line));
        }
        System.out.println("Finished Parsing Input File " + inputName);
        return records.convertToSortedMap();
    }

    private void writeDrugStatistics(Map<String, DrugEntry> records) throws IOException {
        Util.write(outputName, HEADER, records);
        System.out.println("Done Generating Statistics. Output is in: " + outputName);
    }
}
