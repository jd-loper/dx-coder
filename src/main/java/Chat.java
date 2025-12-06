import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

void main() throws IOException, InterruptedException {
    Scanner scanner = new Scanner(System.in);
    StringBuilder chatHistory = new StringBuilder();
    Gson gson = new Gson();
    Client client = new Client();

    Type listType = new TypeToken<List<MedicalCode>>() {
    }.getType();

    while (true) {
        IO.print("You: ");
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("exit")) {
            break;
        }

        chatHistory.append("You: ").append(userInput).append("\n");

        String jsonBody = """
                {
                  "generationConfig": {
                    "response_mime_type": "application/json",
                    "response_schema": {
                      "type": "ARRAY",
                      "items": {
                        "type": "OBJECT",
                        "properties": {
                          "code": { "type": "STRING" },
                          "description": { "type": "STRING" },
                          "type": { "type": "STRING" }
                        }
                      }
                    }
                  },
                  "system_instruction": {
                    "parts": [{
                      "text": "You are a medical coding assistant. Analyze the input and extract the relevant ICD-10-CM and CPT codes. If the input is NOT related to a medical scenario (like medical coding), return an empty list: []."
                    }]
                  },
                  "contents": [{
                    "parts":[{
                      "text": "%s"
                    }]
                  }]
                }
                """
                .formatted(chatHistory);

        String responseBody = client.sendRequest(jsonBody);

        JsonObject root = JsonParser.parseString(responseBody).getAsJsonObject();

        String innerJson = root.getAsJsonArray("candidates")
                .get(0).getAsJsonObject()
                .getAsJsonObject("content")
                .getAsJsonArray("parts")
                .get(0).getAsJsonObject()
                .get("text").getAsString();

        List<MedicalCode> codes = gson.fromJson(innerJson, listType);

        if (codes.isEmpty()) {
            IO.println("Invalid query. Please provide medical documentation.");
        } else {
            for (MedicalCode code : codes) {
                IO.println(code);
            }
        }
    }
    scanner.close();
    IO.println("Chatbot session ended.");
}