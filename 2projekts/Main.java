// 211REC027 Jānis Lejnieks 4.grupa

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter; // add writes new lines (rewrites)
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet; // add ID reading
import java.util.List;

public class Main {
    private static final String fileName = "db.csv";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String command;
        while (true) {
            System.out.println("\nVaicājumi: print, add, del, edit, sort, find, avg, exit");
            System.out.print("Ievadiet komandu: ");
            command = scanner.nextLine().trim();

            switch (command) {
                case "print":
                    print();
                    break;
                case "add":
                    add();
                    break;
                case "del":
                    System.out.print("Ievadi ceļojuma ID, kuru vēlies izdzēst.");
                    String idToDelete = scanner.nextLine().trim();
                    delete(idToDelete);
                    break;
                case "edit":
                    System.out.print("Ievadiet komandu (id;city;date;days;price;vehicle): ");
                    String editCommand = scanner.nextLine().trim();
                    edit(editCommand);
                    break;
                case "sort":
                    sort();
                    break;
                case "find":
                    System.out.print("Ievadiet maksimālo cenu: ");
                    double maxPrice = scanner.nextDouble();
                    find(maxPrice);
                    // pārveido uz input uz double un ar catch neļauj izpildīties -> defualt blokam
                    try {
                        maxPrice = Double.parseDouble(scanner.nextLine());
                        find(maxPrice);
                    } catch (NumberFormatException e) {
                    }
                    break;
                case "avg":
                    calculateAverage();
                    break;
                case "exit":
                    System.out.println("Programma tiek izbeigta un saglabāta.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nepareiza komanda. Lūdzu, mēģiniet vēlreiz.");
            }
        }
    }

    public static void print() {
        System.out.println("------------------------------------------------------------");
        System.out.printf("%-4s%-21s%-11s%-6s%-10s%-8s \n", "ID", "City", "Date", "Days", "Price", "Vehicle"); // atstarpju
                                                                                                               // formāts
        System.out.println("------------------------------------------------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String id = parts[0];
                    String city = parts[1];
                    String date = parts[2];
                    String days = parts[3];
                    String price = parts[4];
                    String vehicle = parts[5];

                    System.out.printf("%-4s%-21s%-11s%-6s%-10s%-8s \n", id, city, date, days, price, vehicle); // atstarpju
                                                                                                               // formāts
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------------------------");
    }

    // ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD
    // ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD ADD
    public static void add() {
        // ID - Faila lasīšana
        HashSet<Integer> usedIds = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0) {
                    int existingId = Integer.parseInt(parts[0]);
                    usedIds.add(existingId);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.print("Ievadiet jaunā ceļojuma informāciju (id;city;date;days;price;vehicle): ");
        String input = scanner.nextLine().trim();
        String[] parts = input.split(";");
        if (parts.length != 6) {
            System.out.println("nepareizs laukumu skaits");
            return;
        }
        String idStr = parts[0];
        String city = parts[1];
        String date = parts[2];
        String daysStr = parts[3];
        String priceStr = parts[4];
        String vehicle = parts[5];

        // CITY
        city = formatCity(city);

        // ID - 3 symbols
        if (!idStr.matches("\\d{3}")) {
            System.out.println("nepareizs id");
            return;
        }
        int id = Integer.parseInt(idStr);
        // Existing of ID
        if (usedIds.contains(id)) {
            System.out.println("Šis ID jau ir izmantots. Lūdzu, ievadiet citu.");
            return;
        }

        // DATE
        String[] dateParts = date.split("/");
        if (dateParts.length != 3) {
            System.out.println("nepareizs datums");
            return;
        }

        int day = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        int year = Integer.parseInt(dateParts[2]);

        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 2000 || year > 3100) {
            System.out.println("wrong date");
            return;
        }
        // VEHICLE
        vehicle = vehicle.toUpperCase();

        if (!vehicle.equals("PLANE") && !vehicle.equals("BUS") && !vehicle.equals("TRAIN") && !vehicle.equals("BOAT")) {
            System.out.println("wrong vehicle type");
            return;
        }
        // PRICE
        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            System.out.println("nepareizs cenas formāts");
            return;
        }
        // DAYS
        int days;
        try {
            days = Integer.parseInt(daysStr);
        } catch (NumberFormatException e) {
            System.out.println("nepareizs dienas formāts");
            return;
        }
        // Pievieno jauno ceļojumu sarakstam
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(idStr + ";" + city + ";" + date + ";" + days + ";" + price + ";" + vehicle + ";\n");
            System.out.println("Ceļojuma informācija ir pievienota.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // City formatēšana
    }

    public static String formatCity(String city) {
        String[] words = city.toLowerCase().split(" ");
        StringBuilder formattedCity = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                char firstChar = Character.toUpperCase(word.charAt(0));
                formattedCity.append(firstChar).append(word.substring(1)).append(" ");
            }
        }
        return formattedCity.toString().trim();
    }

    // DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL
    // DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DEL DELDEL DEL DEL
    // DEL DEL DEL DEL DEL DEL
    public static void delete(String idToDelete) {
        // Check if the ID format is correct
        if (!idToDelete.matches("\\d{3}")) {
            System.out.println("Nepareizs ID formāts.");
            return;
        }

        int id = Integer.parseInt(idToDelete);
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length > 0) {
                    int existingId = Integer.parseInt(parts[0]);
                    if (existingId == id) {
                        found = true;
                        continue; // izlaiž rindu to izdzēšot
                    }
                }
                buffer.append(line).append("\n");
            }
            // Atjauniniet failu ja ID tika atrasts un izdzēsts // rewrites
            if (found) {
                try (FileWriter writer = new FileWriter(fileName)) {
                    writer.write(buffer.toString());
                }
                System.out.println("Journey with ID " + idToDelete + " deleted.");
            } else {
                System.out.println("Nav atrasts norādītais ID");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT
    // EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT EDIT
    public static void edit(String command) {
        String[] parts = command.split(";");
        if (parts.length < 6) {
            System.out.println("wrong field count");
            return;
        }

        String idStr = parts[0].trim();
        String city = parts[1].trim();
        String date = parts[2].trim();
        String daysStr = parts[3].trim();
        String priceStr = parts[4].trim();
        String vehicle = parts[5].trim();

        // ID - 3 simboli
        if (!idStr.matches("\\d{3}")) {
            System.out.println("wrong id");
            return;
        }
        int id = Integer.parseInt(idStr);

        // noalsa failu
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // atrod un atjauno ievadi ar norādīto ID
        boolean found = false;
        for (int i = 0; i < lines.size(); i++) {
            String[] existingParts = lines.get(i).split(";");
            if (existingParts.length > 0 && Integer.parseInt(existingParts[0]) == id) {
                // Update fields if provided
                if (!city.isEmpty()) {
                    city = formatCity(city);
                    existingParts[1] = city;
                }
                if (!date.isEmpty()) {
                    existingParts[2] = date;
                }
                if (!daysStr.isEmpty()) {
                    existingParts[3] = daysStr;
                }
                if (!priceStr.isEmpty()) {
                    existingParts[4] = priceStr;
                }
                if (!vehicle.isEmpty()) {
                    vehicle = vehicle.toUpperCase();
                    existingParts[5] = vehicle;
                }

                lines.set(i, String.join(";", existingParts));
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("wrong id");
            return;
        }
        // Ierakstam atpakaļ failā
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("changed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT
    // SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT SORT
    // SORT SORT SORT SORT SORT
    public static void sort() {
        List<String> lines = new ArrayList<>();
        // Lasa
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Collections.sort(lines, new Comparator<String>() {
            public int compare(String line1, String line2) {
                String[] parts1 = line1.split(";");
                String[] parts2 = line2.split(";");
                String date1 = parts1[2];
                String date2 = parts2[2];

                String[] dateParts1 = date1.split("/");
                String[] dateParts2 = date2.split("/");
                // salīdzina gadu
                int yearComparison = Integer.compare(Integer.parseInt(dateParts1[2]), Integer.parseInt(dateParts2[2]));
                if (yearComparison != 0) {
                    return yearComparison;
                }
                // salīdzina mēnesi
                int monthComparison = Integer.compare(Integer.parseInt(dateParts1[1]), Integer.parseInt(dateParts2[1]));
                if (monthComparison != 0) {
                    return monthComparison;
                }
                // salīdzina dienu
                return Integer.compare(Integer.parseInt(dateParts1[0]), Integer.parseInt(dateParts2[0]));
            }
        });
        // Ierakstam atpakaļ failā
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
            System.out.println("sorted");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND
    // FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND FIND
    // FIND FIND
    public static void find(double maxPrice) {
        if (maxPrice <= 0) {
            System.out.println("wrong price");
            return;
        }

        System.out.println("------------------------------------------------------------");
        System.out.printf("%-4s%-21s%-11s%-6s%-10s%-8s \n", "ID", "City", "Date", "Days", "Price", "Vehicle");
        System.out.println("------------------------------------------------------------");

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 6) {
                    String id = parts[0];
                    String city = parts[1];
                    String date = parts[2];
                    String days = parts[3];
                    String priceStr = parts[4];
                    String vehicle = parts[5];

                    // Pārbauda vai cena ir zemāka vai vienāda ar maksimālo cenu
                    double price;
                    try {
                        price = Double.parseDouble(priceStr);
                    } catch (NumberFormatException e) {
                        System.out.println("wrong price");
                        return;
                    }

                    if (price <= maxPrice) {
                        System.out.printf("%-4s%-21s%-11s%-6s%-10s%-8s \n", id, city, date, days, priceStr, vehicle);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("------------------------------------------------------------");
    }

    public static void calculateAverage() {
        double total = 0;
        int count = 0;
        // nolasa ar BufferedReader (katrs ieraksts atdalīts ar ; )
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");

                if (parts.length == 6) {
                    String priceStr = parts[4]; // price ir 5. sarakstā
                    try {
                        double price = Double.parseDouble(priceStr);
                        total += price;
                        count++;
                    } catch (NumberFormatException e) {
                        // Ignore invalid prices
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count > 0) {
            double average = total / count;
            System.out.printf("average=%.2f\n", average); // formāts
        } else {
            System.out.println("No valid prices found to calculate average.");
        }
    }
}