import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    // attributes
    private final ArrayList<Superhero> heroList;
    private int size;
    private final int maxSize;

    // konstruktør
    public Database() {
        heroList = new ArrayList<>();
        size = 0;
        maxSize = 10;

        // temp superhelte for testing
        Superhero superhero = new Superhero("Batman", "Bruce Wayne", "Money", 1980, "JA", 200);
        Superhero superhero1 = new Superhero("Superman", "Clark Kent", "Flight", 1990, "NEJ", 2000);
        Superhero superhero2 = new Superhero("Martian", "Jon", "Flight, Strength", 2000, "NEJ", 500);
        heroList.add(superhero);
        heroList.add(superhero1);
        heroList.add(superhero2);
    }

    // tilføj superhelte til database
    public void addSuperhero() {
        Scanner input = new Scanner(System.in);

        if (size < maxSize) {
            System.out.println("Indtast data:\n");
            System.out.print("Superheltenavn (skriv \"n\" hvis de ikke har et): ");
            String name = input.nextLine();
            if (name.equals("n")) name = "";

            System.out.print("\nRigtige navn: ");
            String realName = input.nextLine();

            System.out.print("\nSuperkræft: ");
            String superPower = input.nextLine();

            System.out.print("\nÅrstal for skabelse: ");
            while (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.next();
            }
            int yearCreated = input.nextInt();
            
            System.out.print("\nEr superhelten et menneske? [y/n]: ");
            String svar = input.next();
            String isHuman;
            while (!svar.equals("y") && !svar.equals("n")) {
                System.out.println("Ugyldigt svar.");
            }
            if (svar.equals("y")) isHuman = "JA";
            else isHuman = "NEJ";

            System.out.print("\nStyrke: ");
            while (!input.hasNextInt()) {
                System.out.println("Du skal indtaste et tal.");
                input.next();
            }
            int strength = input.nextInt();
            System.out.println();

            Superhero superhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
            heroList.add(superhero);
            size++;
            System.out.println("Superhelt tilføjet til databasen.\n");
        } else System.out.println("Database er fuld.\n");
    }

    // info metode som kaldes fra andre metoder
    public void showInfo(Superhero superhero) {
        System.out.printf("1. Superheltenavn: %s\n", superhero.getName());
        System.out.printf("2. Virkeligt navn: %s\n", superhero.getRealName());
        System.out.printf("3. Superkræft: %s\n", superhero.getSuperPower());
        System.out.printf("4. Oprindelsesår: %s\n", superhero.getYearCreated());
        System.out.printf("5. Er menneske: %s\n", superhero.isHuman());
        System.out.printf("6. Styrke: %d\n", superhero.getStrength());
        System.out.println();
    }

    // rediger metode
    public void edit() {
        Scanner input = new Scanner(System.in);
        System.out.println("Superhelte i database:");
        for (Superhero i: heroList) System.out.println(i.getName());

        System.out.print("Hvilken superhelt vil du redigere?: ");
        String search = input.nextLine();
        System.out.println();
        Superhero chosenSuperhero = null;
        for (Superhero i : heroList) {
            if (i.getName().toLowerCase().contains(search.toLowerCase()) ||
                    i.getRealName().toLowerCase().contains(search.toLowerCase())) {
                showInfo(i);
                chosenSuperhero = i;
            }
        }

        if (chosenSuperhero != null) {
            String changeValueMessage = "Indtast ny værdi: ";
            System.out.print("Hvad vil du ændre?: ");
            System.out.println();
            switch (input.nextInt()) {
                case 1 -> {
                    System.out.print(changeValueMessage);
                    chosenSuperhero.setName(input.nextLine());
                }
                case 2 -> {
                    System.out.print(changeValueMessage);
                    chosenSuperhero.setRealName(input.nextLine());
                }
                case 3 -> {
                    System.out.print(changeValueMessage);
                    chosenSuperhero.setSuperPower(input.nextLine());
                }
                case 4 -> {
                    System.out.println(changeValueMessage);
                    chosenSuperhero.setYearCreated(input.nextInt());
                }
                case 5 -> {
                    System.out.println(changeValueMessage);
                    chosenSuperhero.setHuman(input.nextLine());
                }
                case 6 -> {
                    System.out.println(changeValueMessage);
                    chosenSuperhero.setStrength(input.nextInt());
                }
                default -> System.out.println("Ugyldigt svar.");
            }
            showInfo(chosenSuperhero);
        } else System.out.println("Superhelt ikke fundet.");
    }

    // søge metode
    // break efter den finder første resultat der passer
    public void search() {
        Scanner input = new Scanner(System.in);
        System.out.println("Søg efter superhelt: ");
        String search = input.nextLine();
        boolean found = false;
        for (Superhero i : heroList) {
            if (i.getName().toLowerCase().contains(search.toLowerCase()) ||
                    i.getRealName().toLowerCase().contains(search.toLowerCase())) {
                showInfo(i);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Superhelt ikke fundet.");
    }

    // liste metode
    public void showList() {
        System.out.println("Liste af superhelte:");
        System.out.println();
        for (Superhero i: heroList) showInfo(i);
    }

    // slet metode
    public void delete() {
        Scanner input = new Scanner(System.in);
        int index = 1;
        System.out.println();
        for (Superhero i : heroList) {
            System.out.println(index++ + ". " + i.getName());
        }
        System.out.print("Hvem skal slettes fra databasen?: ");
        int choice = input.nextInt();
        heroList.remove(choice - 1);
        System.out.println("\nSletter fra database...");
        System.out.println("Superhelt slettet.\n");
    }

    // database menu
    // kan nemt ændres efter behov
    public void databaseMenu() {
        System.out.print("─".repeat(25) + "\n");
        System.out.println("MENU");
        System.out.print("─".repeat(25) + "\n");
        System.out.println("1. Opret superhelt");
        System.out.println("2. Vis liste");
        System.out.println("3. Søg efter superhelt");
        System.out.println("4. Rediger superhelt");
        System.out.println("5. Slet en superhelt");
        System.out.println("9. Afslut");
        System.out.print("> ");
    }

    // kører databasen
    // switch bruges til at vælge mellem muligheder i menu
    public void runDatabase() {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int choice;
        System.out.println("Velkommen til SUPERHERO UNIVERSET.");
        do {
            databaseMenu();
            choice = input.nextInt();
            switch (choice) {
                case 1 -> addSuperhero();
                case 2 -> showList();
                case 3 -> search();
                case 4 -> edit();
                case 5 -> delete();
                case 9 -> run = false;
                default -> System.out.println("\nUgyldigt input.\n");
            }
        } while (run);
    }
}
