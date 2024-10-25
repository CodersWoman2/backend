package CodersWomen.studySmart.entities.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HomeworkDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private boolean completed;
    private Long studentId;


}
