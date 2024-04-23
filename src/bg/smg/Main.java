package bg.smg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File file = new File("insurances.txt");

        Scanner input = null;
        List<Car> list = new ArrayList<>();
        //VIN, counter insurances
        Map<String, Integer> insCars = new HashMap<>();
        //VIN, owners
        Map<String, Set<String>> vinOwners = new HashMap<>();

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
                list.add(car);
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



        //обработка на данните
        /*
        2. сортиране възходящо по бр. собств
        3. брой записи за всяка кола - бр. застраховки за всяка
        1. Брой собственици на всяка кола
        5. VIN -> най-скорошния рег номер на колата
         */

        //1. Брой собственици на всяка кола
//        Set<Map.Entry<String, Integer>> listOwnersCounter = new ArrayList<>();
//        for(Car c : list){
//            Map.Entry<String, Integer> pair = new AbstractMap.SimpleEntry<>(c.getVIN(), );
//            listOwnersCounter.add()
//        }

        //писане във файл
        File file2 = new File("output.txt");
        if (file.exists()) {
            System.out.println("File already exists");
            System.exit(1);
        }
        PrintWriter output = null;
        try {
            output = new PrintWriter(file2);

//            4. да запишем във файла сортирани данни за колите
//            6. регНомер на колата - , бр. соств, бр. застраховки
//            output.print("John T Smith ");
//            output.println(90);
//            output.print("Eric K Jones ");
//            output.println(85);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            // Close the file
            output.close();
        }


    }

    private static int compareDates(String date1, String date2){
        String[] d1 = date1.split("\\.");
        String[] d2 = date2.split("\\.");
        return (d1[2]).compareTo(d2[2]);
    }
}
