package dnd.monster_service.http.dto.cr;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CrRangeDto {
    private Float minCr;
    private Float maxCr;
}
