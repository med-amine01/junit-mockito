package tn.mosofty.junitmockito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.mosofty.junitmockito.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
