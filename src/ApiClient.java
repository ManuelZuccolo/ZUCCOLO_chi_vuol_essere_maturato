import com.google.gson.Gson;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

public class ApiClient
{
    private final HttpClient client = HttpClient.newHttpClient();

    public APIQuestion[] fetchQuestions(int amount, String difficulty, String type)
    {
        String url = "https://opentdb.com/api.php?amount=" + amount + "&difficulty=" + difficulty + "&type=" + type;

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .GET()
                .build();

        try
        {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new Gson();
            APIResponse apiResponse = gson.fromJson(response.body(), APIResponse.class);

            if (apiResponse.response_code != 0)
            {
                System.out.println("Errore API: " + apiResponse.response_code);
                return new APIQuestion[0];
            }

            return apiResponse.results;

        } catch (IOException | InterruptedException e)
        {
            System.out.println("Errore: " + e.getMessage());
            return new APIQuestion[0];
        }
    }

    //Funzione per mescolare e stampare le opzioni
    public void printQuestion(APIQuestion q)
    {
        List<String> options = new ArrayList<>();
        options.add(q.correct_answer);
        options.addAll(Arrays.asList(q.incorrect_answers));
        Collections.shuffle(options);

        System.out.println("\nDomanda: " + q.question);
        char letter = 'A';
        for (String opt : options)
        {
            System.out.println(letter + ") " + opt);
            letter++;
        }
    }
}

