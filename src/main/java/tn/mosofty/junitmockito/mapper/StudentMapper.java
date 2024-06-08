package tn.mosofty.junitmockito.mapper;

import org.springframework.stereotype.Component;
import tn.mosofty.junitmockito.dto.StudentResponseDto;
import tn.mosofty.junitmockito.entity.Student;

@Component
public class StudentMapper {

	public Student toStudent(StudentResponseDto studentResponseDto) {
		if (studentResponseDto == null) {
			throw new NullPointerException("studentResponseDto cannot be null");
		}
		Student student = new Student();
		student.setFirstname(studentResponseDto.firstName());
		student.setLastname(studentResponseDto.lastName());
		student.setEmail(studentResponseDto.email());
		student.setAge(studentResponseDto.age());

		return student;
	}

	public StudentResponseDto toStudentResponseDto(Student student) {
		return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail(),
				student.getAge());
	}

}
