package peaksoft.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
@Data
@Builder
@NoArgsConstructor
public class CommentResponse {
    private String comment;
    private ZonedDateTime createdDate;


    public CommentResponse(String comment, ZonedDateTime createdDate) {
        this.comment = comment;
        this.createdDate = createdDate;
    }
}
