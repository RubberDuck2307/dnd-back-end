package dnd.aiapi.client.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OpenAiRequest {

    private String model;
    private float temperature;
    private OpenAiMessage[] messages;

}
