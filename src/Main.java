import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApiClient api = new ApiClient();
        Scanner scan = new Scanner(System.in);

        System.out.println("Quante domande vuoi? ");
        int amount = scan.nextInt();

        APIQuestion[] questions = api.fetchQuestions(amount, "easy", "multiple");

        for (APIQuestion q : questions) {
            api.printQuestion(q);
            System.out.println();
        }
    }
}
