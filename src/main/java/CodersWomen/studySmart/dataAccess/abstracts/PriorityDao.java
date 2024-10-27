package CodersWomen.studySmart.dataAccess.abstracts;


import CodersWomen.studySmart.entities.concretes.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityDao extends JpaRepository<Priority, Long> {

    Priority findByLevel(String level);
}
