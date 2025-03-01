// 211REC027 Jānis Lejnieks 4.grupa

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class Prece {
	private String nosaukums;
	private double cena;
	private int daudzums;

	Prece(String n, double c, int d) {
		nosaukums = n;
		cena = c;
		daudzums = d;
	}

	static Prece inputPrece(Scanner sc) {
		System.out.print("nosaukums: ");
		String n = sc.next();
		System.out.print("cena: ");
		double c = sc.nextDouble();
		System.out.println("daudzums: ");
		int d = sc.nextInt();
		return new Prece(n, c, d);
	}

	void outputPrece() {
		System.out.printf("%-20s%-10.2f%-10d\n", nosaukums, cena, daudzums);
	}
	public String getNosaukums() {
        return nosaukums;
    }

    public void setNosaukums(String nosaukums) {
        this.nosaukums = nosaukums;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getDaudzums() {
        return daudzums;
    }

    public void setDaudzums(int daudzums) {
        this.daudzums = daudzums;
    }
}
public class Main {
	public static Scanner sc;

	public static void main(String[] args) {
		HashMap<String, LinkedList<Prece>> pasutijumi = 
				new HashMap<String, LinkedList<Prece>>();
		
		sc = new Scanner(System.in);
		String cmd = "";

		while (!cmd.equals("done")) {
			System.out.print("command:> ");
			cmd = sc.next();
			switch (cmd) {
			case "add":
				add(pasutijumi);
				break;

			case "print":
				print(pasutijumi);
				break;
			
			case "sum":
				sum(pasutijumi);
				break;

			case "inc":
				inc(pasutijumi);
				break;

			case "del":
				del(pasutijumi);
				break;
		
			case "done":
				System.out.println("good bye");
				break;

			default:
				System.out.println("unknown command");
				break;
			}
		}

		sc.close();
	}

	public static void add(HashMap<String, LinkedList<Prece>> pasutijumi) {
		LinkedList<Prece> grozs;

		System.out.print("klienta ID: ");
		String id = sc.next();
		Prece p = Prece.inputPrece(sc);
		grozs = pasutijumi.get(id);
		if (grozs != null) {
			grozs.add(p);
		} else {
			grozs = new LinkedList<Prece>();
			grozs.add(p);
			pasutijumi.put(id, grozs);
		}

	}

	public static void print(HashMap<String, LinkedList<Prece>> pasutijumi) {
		LinkedList<Prece> grozs;

		for (String id : pasutijumi.keySet()) {
			System.out.println("ID: " + id);
			grozs = pasutijumi.get(id);
			String str = String.format("%-20s%-10s%-10s", "nosaukums", "cena", "daudzums");
			System.out.println(str);
			for (Prece prece : grozs) {
				prece.outputPrece();
			}
		}
		System.out.println();
	}
	public static void sum(HashMap<String, LinkedList<Prece>> pasutijumi) {
		System.out.print("klienta ID: ");
    	String id = sc.next();
		LinkedList<Prece> grozs = pasutijumi.get(id);
    	double total = 0.0;
    	if (grozs != null) {
        	for (Prece prece : grozs) {
            	total += prece.getCena() * prece.getDaudzums();
        }
        	System.out.println("Kopējā cena klientam ar ID " + id + " ir: " + total);
    	}else{
        	System.out.println("unknown client");
    }
	}
	public static void inc(HashMap<String, LinkedList<Prece>> pasutijumi) {
		System.out.print("klienta ID: ");
    	String id = sc.next();
		LinkedList<Prece> grozs = pasutijumi.get(id);
    	if (grozs == null) {
        	System.out.println("unknown client");
        	return;
		}
		System.out.print("preces nosaukums: ");
		String n = sc.next();
		boolean preceAtrasta = false;

    	for (Prece prece : grozs) {
        	if (prece.getNosaukums().equals(n)) {
            	prece.setDaudzums(prece.getDaudzums() + 1);
            	preceAtrasta = true;
            	System.out.println("Preces daudzums palielināts par 1.");
            	break;
        }
    }
    	if (!preceAtrasta) {
        	System.out.println("not found");
    }
	}
	public static void del(HashMap<String, LinkedList<Prece>> pasutijumi) {
		System.out.print("klienta ID: ");
    	String id = sc.next();
		LinkedList<Prece> grozs = pasutijumi.get(id);
    	if (grozs == null) {
        	System.out.println("unknown client");
        	return;
		}
		System.out.print("preces nosaukums: ");
		String n = sc.next();
		boolean preceAtrasta = false;

    	for (Prece prece : grozs) {
        	if (prece.getNosaukums().equals(n)) {
           		grozs.remove(prece);
            	preceAtrasta = true;
            	System.out.println("Prece izdzēsta");
            	break;
        }
    }
    	if (!preceAtrasta) {
        	System.out.println("not found");
    }
	}
}
