import java.util.ArrayList;
import java.util.Scanner;

class simple {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<String> todoList = new ArrayList<>();

        while (true) {

            System.out.println("\n****** TO-DO List ******");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Mark as complete");
            System.out.println("4. Delete task");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {

                case 1:
                    System.out.print("Enter the task to add: ");
                    String task = sc.nextLine();
                    todoList.add(task);
                    System.out.println("Task added!");
                    break;

                case 2:
                    System.out.println("Your Tasks:");
                    for (int i = 0; i < todoList.size(); i++) {
                        System.out.println((i + 1) + ". " + todoList.get(i));
                    }
                    break;

                case 3:
                    System.out.print("Enter the task number completed: ");
                    int completedNum = sc.nextInt();
                    sc.nextLine();

                    int index = completedNum - 1;
                    if (index >= 0 && index < todoList.size()) {
                        todoList.set(index, todoList.get(index) + "   Completed");
                        System.out.println("Task marked as complete!");
                    } else {
                        System.out.println("Invalid task number!");
                    }
                    break;

                case 4:
                    System.out.print("Enter task number to delete: ");
                    int deleteNum = sc.nextInt();
                    sc.nextLine();

                    int deleteIndex = deleteNum - 1;
                    if (deleteIndex >= 0 && deleteIndex < todoList.size()) {
                        todoList.remove(deleteIndex);
                        System.out.println("Task deleted!");
                    } else {
                        System.out.println("Invalid task number!");
                    }
                    break;

                case 5:
                    System.out.println("Goodbye!!!");
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
