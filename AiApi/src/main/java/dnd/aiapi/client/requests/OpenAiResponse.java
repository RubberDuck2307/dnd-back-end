package dnd.aiapi.client.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OpenAiResponse {

    private String id;
    private String object;
    private Long created;
    private String model;
    private TokensUsage tokensUsage;
    private OpenAiMessage[] choices;



}
