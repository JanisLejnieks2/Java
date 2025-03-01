// 211REC027 Jānis Lejnieks 4.grupa
//import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

       while (true) {
            System.out.print("Enter command (comp, decomp, about, exit): ");
            command = scanner.next();

            switch (command) {
                case "comp":
                    String input = scanner.next().toUpperCase();
                    if (!input.matches("[ACGT]+")) {
                        System.out.println("wrong command format");
                        break;
                    }
                    comp(input);
                    break;
                case "decomp":
                    decomp(scanner);
                    break;
                case "about":
                    System.out.println("211REC027 Jānis Lejnieks 4.grupa");
                    break;
                case "exit":
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("wrong command");
                    break;
            }
        }
    }

    private static void comp(String input) {
        int length = input.length();
        byte[] bytes = new byte[length + 1];
    
        for (int i = 0; i < length; i++) {
            char c = input.charAt(i);
            bytes[i + 1] = (byte) (c == 'A' ? 0b00 : c == 'C' ? 0b01 : c == 'G' ? 0b10 : 0b11);
        }
    
        bytes[0] = (byte) length;
    
        // Print byte array with elements separated by spaces and in hexadecimal format
        for (int i = 0; i < bytes.length; i++) {
            System.out.printf("%02X ", bytes[i] & 0xFF);
        }
        System.out.println();
    }

    private static void decomp(Scanner scanner) {
        int symbolCount = scanner.nextInt();
        if (symbolCount <= 0) {
            System.out.println("wrong command format");
            return;
        }
    
        byte[] bytes = new byte[symbolCount];
        for (int i = 0; i < symbolCount; i++) {
            if (!scanner.hasNextByte()) {
                System.out.println("wrong command format");
                return;
            }
            bytes[i] = scanner.nextByte();
        }
    
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < symbolCount; i++) {
            byte b = bytes[i];
            char c = b == 0b00 ? 'A' : b == 0b01 ? 'C' : b == 0b10 ? 'G' : 'T';
            sb.append(c);
        }
    
        // Print symbol count
        System.out.print(symbolCount + " ");
    
        // Print hexadecimal representation of the bytes
        for (int i = 1; i < symbolCount; i++) {
            System.out.print(String.format("%02X ", bytes[i]));
        }
        System.out.println();
    
        // Print the decoded sequence
        System.out.println(sb.toString());
    }
}