package dnd.aiapi.client.requests;

public class OpenAiRequestFactory {


    public static OpenAiRequest questIdeaBasedOnEncounter(String encounter){
        OpenAiMessage message = OpenAiMessage.builder()
                .content("Give an idea for quest that contains this monsters: " + encounter)
                .role("assistant")
                .build();
        OpenAiRequest request = OpenAiRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(new OpenAiMessage[]{message})
                .temperature(1.5f)
                .build();
        return request;
    }

}
