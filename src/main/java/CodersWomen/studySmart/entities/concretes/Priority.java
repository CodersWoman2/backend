package CodersWomen.studySmart.entities.concretes;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "priorities")
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String level;  // e.g., "High", "Medium", "Low"

    @OneToOne(mappedBy = "priority")
    private Homework homework;
}
