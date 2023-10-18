package dnd.aiapi.client.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class TokensUsage {

    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
}
