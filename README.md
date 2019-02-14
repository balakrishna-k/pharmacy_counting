# Pharmacy Counting - Insight Fellowship

## Brief Description:

I basically used a HashMap whose entries consisted of the Drug Name, and the associated value consisted of a DrugEntry.
The DrugEntry class essentially has a HashSet of prescribers (to maintain uniqueness) and a total cost field.
It also has a field containing the drug name itself to allow for easy sorting using the comparator.

My project works with the de_cc_data.txt data as well, but it was too big for me to push to github.

PLEASE NOTE: I made the decision to format the output cost to drop all decimals, as that was the format expected in the test provided.

## Trade Offs
I chose to use a HashMap for the following reasons:

* It seemed adequated clear that the entire file needed to be read. As such, this would mean that insertions would
need to be as efficient as possible. So I chose the HashMap which has 0(1) [constant time] insertion time complexity.
* Because the output needed to be sorted, I basically converted this HashMap into a List, and sorted it.
* This list was added into a LinkedHashMap. The reason being other implementations do not guarantee order of insertion,
and it would have been a waste of resources after spending the previous step sorting the map.
* I made use of buffered readers and writers to avoid memory wastage during the read/write phase.

## Prerequisites
- Java 8
- Command Line Access (MAC OS or Linux should be fine. Do not know about Windows' Bash)

## Installation
* Clone the repo if needed:
```
git clone https://github.com/balakrishna-k/pharmacy_counting.git
```

* Run the program:
```bash
./run.sh input_file output_file
```

* View Outputs:
```
Look in the output directory for default cases.
```

## Project Packages and Files
* `CountPrescriptions.java`: This is the main java class that combines my separate packages to peform the counting.
* `PharmacyCounter/RecordKeeper.java`: The Record Keeper class was envisioned to be the object orchestrating the file IO and statistic calculation
* `Prescription/Prescription.java`: This is a model of each prescription, used primarily during the data reading phase.
* `Records/DrugMap.java`: This file contains the implementations for the HashMap and comparator
* `Records/DrugEntry.java`: This class describes each value associated with the drug in the DrugMap. The drug entry has a hashset and two fields: drug name and total cost
* `Utility/Util.java`: This contains the general purpose utility functions for file IO and args parsing. There are not too many of them.

### Tests
* `Test 1`: Default Test. Passed
* `Decimals in Data`: Test to make sure it works with decimals.
* `Different order`: Permuted ordering of default test case.
* `Weird_Commas`: While testing with the large dataset, I realised that there was a weird format for one of the records.
I decided to correct for that and handle that scenario as well. This is a test case containing multiple commas between quotes.

Due to a lack of time, I was unable to write a full fledged make file. Apologies for that.