import PharmacyCounter.RecordKeeper;
import Utility.Util;

import java.io.IOException;

public class CountPrescriptions {

    public static void main(String[] args) {

        args = Util.argsParser(args);

        try {
            RecordKeeper keeper = new RecordKeeper(args[0], args[1]);
            keeper.generatePrescriptionStatistics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
