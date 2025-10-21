import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApiClient api = new ApiClient();
        Scanner scan = new Scanner(System.in);

        System.out.println("Benvenuto in Chi vuol essere Maturato?");
        System.out.print("Inserisci il tuo nome: ");
        String name = scan.nextLine();

        // (In futuro queste saranno variabili aggiornate durante il gioco)
        int correctAnswers = 0;
        boolean used5050 = false;
        boolean usedAudience = false;

        // --- Simulazione: scarica domande e mostra ---
        APIQuestion[] questions = api.fetchQuestions(3, "easy", "multiple");
        for (APIQuestion q : questions) {
            api.printQuestion(q);
            System.out.println();
        }

        // --- Fine gioco, salva statistiche ---
        PlayerStatistics stats = new PlayerStatistics(name, correctAnswers, used5050, usedAudience);
        StatsManager.saveStats(stats);
        System.out.println("Statistiche salvate con successo!");
    }
}
