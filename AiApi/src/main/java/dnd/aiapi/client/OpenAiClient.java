package dnd.aiapi.client;


import dnd.aiapi.client.requests.OpenAiRequest;
import dnd.aiapi.client.requests.OpenAiResponse;
import dnd.aiapi.client.requests.OpenAiRequestFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@RequiredArgsConstructor
public class OpenAiClient implements AiClient {

    @Value("${openai.api.key}")
    private String apiKey;
    private static final String URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate;

    @Override
    public String getIdeaForQuestBasedOnEncounter(String encounter) {
        org.springframework.http.HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        HttpEntity<OpenAiRequest> entity = new HttpEntity<>(OpenAiRequestFactory.questIdeaBasedOnEncounter(encounter)
                , headers);

        ResponseEntity<OpenAiResponse>  stringResponseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity,
                OpenAiResponse.class);
        return Objects.requireNonNull(stringResponseEntity.getBody()).getChoices()[0].getContent();
    }

}
