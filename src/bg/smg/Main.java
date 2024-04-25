package bg.smg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("insurances.txt");

        Scanner input = null;
        Map<String, Car> list = new HashMap<>();
        //VIN, counter insurances
        Map<String, Integer> insCars = new HashMap<>();
        //VIN, owners
        Map<String, Set<String>> vinOwners = new HashMap<>();
        //VIN, Car obj latest insured
        Map<String, Car> insRegNo = new HashMap<>();
        try {
            input = new Scanner(file);

            while (input.hasNext()) {
                String vin = input.next();
                String regNumber = input.next();
                int year = input.nextInt();
                String dateOfInsurance = input.next();
                String ownerFirstName = input.next();
                String ownerLastName = input.next();
                String owner = ownerFirstName + " " + ownerLastName;
                Car car = new Car(vin, regNumber, year, dateOfInsurance, ownerFirstName, ownerLastName);
                //add to list
                list.put(vin, car);
                if(vinOwners.get(vin)==null)
                    vinOwners.put(vin, new HashSet<>());
                vinOwners.get(vin).add(owner);

                if(insCars.containsKey(vin)) {
                    insCars.put(vin, insCars.get(vin)+1);
                } else {
                    insCars.put(vin, 1);
                }

                if(!insRegNo.containsKey(vin) || (insRegNo.containsKey(vin) && compareDates(car.getDateOfInsurance(), insRegNo.get(vin).getDateOfInsurance())>0)) {
                    insRegNo.put(vin, car);
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } finally {
            input.close();
        }

        //топ 5 по брой застраховки
        List<Map.Entry<String, Integer>> vinInsCount = new ArrayList<>(insCars.entrySet());
        vinInsCount.sort(Map.Entry.comparingByValue());
        List<Map.Entry<String, Integer>> top5ByInsCount = vinInsCount.subList(vinInsCount.size()-6, vinInsCount.size()-1);

        List<Result> result = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : top5ByInsCount){
            Result r = new Result();
            r.setInsuranceCount(entry.getValue());
            r.setOwnersCount(vinOwners.get(entry.getKey()).size());
            r.setRegNo(insRegNo.get(entry.getKey()).getRegNumber());
            result.add(r);
        }
        Collections.sort(result);

        //писане във файл
        File file2 = new File("output.txt");
        if (file2.exists()) {
            System.out.println("File already exists");
            System.exit(1);
        }

        PrintWriter output = null;
        try {
            output = new PrintWriter(file2);

            int i = 1;
            for (Result r : result){
                output.println(i + ". " + r.getRegNo() + " " + r.getOwnersCount() + " owners " + r.getInsuranceCount() + " insurances");
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            output.close();
        }
    }

    private static int compareDates(String date1, String date2){
        String[] d1 = date1.split("\\.");
        String[] d2 = date2.split("\\.");
        return (d1[2]).compareTo(d2[2]);
    }
}
