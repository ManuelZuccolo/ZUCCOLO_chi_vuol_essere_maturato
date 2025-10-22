import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        ApiClient api = new ApiClient();
        Scanner scan = new Scanner(System.in);

        System.out.println("Benvenuto in Chi vuol essere Maturato?");
        System.out.print("Inserisci il tuo nome: ");
        String name = scan.nextLine();
        System.out.print("Inserisci il numero di domande: ");
        int amount = scan.nextInt();

        //variabili da aggiornate durante il gioco
        int correctAnswers = 0;
        boolean used5050 = false;
        boolean usedAudience = false;

        //Domands
        APIQuestion[] questions = api.fetchQuestions(amount, "easy", "multiple");
        for (APIQuestion q : questions)
        {
            api.printQuestion(q);
            System.out.println();

            System.out.println("Risposta: ");
            char risp = scan.next().charAt(0);

            if(Character.toUpperCase(risp) == q.correct_answer.charAt(0))
            {
                correctAnswers++;
                System.out.println("Correct!");
            }else
            {
                System.out.println("Wrong!");
            }
        }

        //Parte da sistemare, questo dev'essere svolto alla fine
        //salva statistiche
        PlayerStatistics stats = new PlayerStatistics(name, correctAnswers, used5050, usedAudience);
        StatsManager.saveStats(stats);
        System.out.println("Statistiche salvate con successo!");
    }
}
