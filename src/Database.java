import java.util.Scanner;

public class Database {

    private final Superhero[] list;
    private int size;

    public Database(int cap) {
        list = new Superhero[cap];
        size = 0;
    }

    public void addSuperhero() {
        Scanner input = new Scanner(System.in);

        if (size < list.length) {
            System.out.println("Indtast data:\n");
            System.out.print("Superheltenavn (skriv \"n\" hvis de ikke har et): ");
            String name = input.next();
            if (name.equals("n")) {
                name = "";
            }

            System.out.print("\nRigtige navn: ");
            String realName = input.next();

            System.out.print("\nSuperkræft: ");
            String superPower = input.next();

            System.out.print("\nÅrstal for skabelse: ");
            int yearCreated = input.nextInt();

            System.out.print("\nEr superhelten et menneske? [y/n]: ");
            boolean isHuman;
            isHuman = input.next().equals("y");

            System.out.println("\nStyrke: ");
            int strength = input.nextInt();
            System.out.println();

            Superhero superhero = new Superhero(name, realName, superPower, yearCreated, isHuman, strength);
            list[size] = superhero;
            size++;
            System.out.println("Superhelt tilføjet til databasen.\n");
        } else {
            System.out.println("Database er fuld.\n");
        }
    }

    public void databaseMenu() {
        System.out.println("Velkommen til SUPERHERO UNIVERSET.");
        System.out.print("─".repeat(33) + "\n");
        System.out.println("1. Opret superhelt");
        System.out.println("9. Afslut");
        System.out.print("> ");
    }

    public void runDatabase() {
        Scanner input = new Scanner(System.in);
        boolean run = true;
        int choice;
        do {
            databaseMenu();
            choice = input.nextInt();

            switch (choice) {
                case 1 -> addSuperhero();
                case 9 -> run = false;

                default -> System.out.println("\nForkert input.\n");
            }

        } while (run);
    }
}
