package Records;

import Prescription.Prescription;

import java.util.*;

/**
 * DrugMap stores the prescription data. It is essentially a HashMap but it has a method to create a sorted map.
 *
 * Insertion of data is in O (1) but we can still sort the map once fully read in O(NLogN);
 * N being the number of unique records.
 */

public class DrugMap extends HashMap<String, DrugEntry> {

    public void put(Prescription p) {
        this.put(p.getDrug(), p.getPrescriber(), p.getCost());
    }

    private void put(String drugName, String prescriber, double cost) {

        if (this.containsKey(drugName)) {
            DrugEntry de = this.get(drugName);
            de.addPrescriber(prescriber);
            de.addCost(cost);
        } else
            super.put(drugName, new DrugEntry(prescriber, drugName, cost));
    }

    /**
     * Creates a sorted map using the DrugEntryComparator.
     * This function allows us to avoid using a TreeMap, speeding map insertion operations as they would occur in O(1).
     * @return
     */
    public Map<String, DrugEntry> convertToSortedMap() {

        List<Entry<String, DrugEntry>> list = new ArrayList<>(this.entrySet());
        list.sort(new DrugEntryComparator());

        Map<String, DrugEntry> map = new LinkedHashMap<>();

        for (Entry<String, DrugEntry> entry: list)
            map.put(entry.getKey(), entry.getValue());

        return map;
    }

    static class DrugEntryComparator implements Comparator<Entry<String, DrugEntry>> {

        @Override
        public int compare(Entry<String, DrugEntry> o1, Entry<String, DrugEntry> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }
}
