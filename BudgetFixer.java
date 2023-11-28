import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Cost implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private double amount;
    private String category;

    public Cost(String name, double amount, String category) {
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", category='" + category + '\'' +
                '}';
    }
}

public class BudgetFixer {
    private static ArrayList<Cost> costs = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addCost();
                    break;
                case 2:
                    viewCost();
                    break;
                case 3:
                    viewTotalCost();
                    break;
                case 4:
                    deleteCost();
                    break;
                case 5:
                    exitBudgetFixer();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void addCost() {
        System.out.print("\nEnter the cost name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the cost amount: ");
        double amount = scanner.nextDouble();

        scanner.nextLine();

        System.out.print("Enter the cost category: ");
        String category = scanner.nextLine();

        Cost cost = new Cost(name, amount, category);
        costs.add(cost);

        System.out.println("\nCost added successfully!\n");
    }

    private static void viewCost() {
        if (costs.isEmpty()) {
            System.out.println("\nNo cost recorded yet.\n");
        } else {
            System.out.println("\nCosts:");
            for (int i = 0; i < costs.size(); i++) {
                System.out.println((i + 1) + ". " + costs.get(i));
            }
            System.out.println();
        }
    }

    private static void viewTotalCost() {
        if (costs.isEmpty()) {
            System.out.println("\nNo costs recorded yet.\n");
        } else {
            double totalCosts = costs.stream().mapToDouble(Cost::getAmount).sum();
            System.out.println("\nTotal Cost: $" + totalCosts + "\n");
        }
    }

    private static void deleteCost() {
        viewCost();
        if (costs.isEmpty()) {
            return;
        }

        System.out.print("Enter the cost number to delete: ");
        int costNumber = scanner.nextInt();
        scanner.nextLine();

        if (costNumber >= 1 && costNumber <= costs.size()) {
            Cost removedCost = costs.remove(costNumber - 1);
            System.out.println("\nCost removed: " + removedCost + "\n");
        } else {
            System.out.println("\nInvalid cost number. No cost removed.\n");
        }
    }

    private static void printMenu() {
        System.out.println("\n                   |------ << Budget Fixer Menu >> ------|\n");
        System.out.print("1. Add Cost");
        System.out.print("   2. View Cost");
        System.out.print("   3. View Total Cost");
        System.out.print("   4. Delete Cost");
        System.out.println("   5. Exit\n");
        System.out.print("Enter your choice: ");
    }

    private static void exitBudgetFixer() {
        System.out.println("\n              |------ << Exiting Budget Fixer. Goodbye! >> -------|\n\n");
        System.exit(0);
    }
}
