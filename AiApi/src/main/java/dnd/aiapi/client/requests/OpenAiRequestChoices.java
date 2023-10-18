package dnd.aiapi.client.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OpenAiRequestChoices {

    private OpenAiMessage message;
    private String finishReason;
    private Long index;

}
