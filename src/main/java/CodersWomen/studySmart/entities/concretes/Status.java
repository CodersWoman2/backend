package CodersWomen.studySmart.entities.concretes;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "statuses")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // e.g., "In Progress", "Completed", "Overdue"

    @OneToOne(mappedBy = "status")
    private Homework homework;
}
