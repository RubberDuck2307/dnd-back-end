package dnd.aiapi.client.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OpenAiMessage {

    private String role;
    private String content;
}
