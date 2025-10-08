import com.google.gson.Gson;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class ApiClient
{
    private final HttpClient client = HttpClient.newHttpClient();

    public String fetchQuestions(int amount, String difficulty, String type)
    {
        //https://opentdb.com/api.php?amount=10&difficulty=easy&type=multiple
        String url = "https://opentdb.com/api.php?amount=" + amount + "&difficulty=" + difficulty + "&type=" + type;

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(java.net.URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response;
        try
        {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        }catch (IOException | InterruptedException e)
        {
            return "Error" + e.getMessage();
        }

        Gson gson = new Gson();
        APIResponse apiResponse = gson.fromJson(response.body(), APIResponse.class);

        if(apiResponse.response_code != 0)
        {
            return "Errore: " + apiResponse.response_code;

        }

        for (APIQuestion question : apiResponse.results)
        {
            System.out.println(question.question + "\n");
            Random  rand = new Random();
            int r =  rand.nextInt(3);

            switch(r)
            {
                case 0:
                    System.out.println("A " + question.correct_answer + " " + "B " + question.incorrect_answers[0] + " " + "C " + question.incorrect_answers[1] + " " + "D " + question.incorrect_answers[2] + " ");
            }
        }
        return response.body();
    }
}
