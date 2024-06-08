package tn.mosofty.junitmockito.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.mosofty.junitmockito.dto.StudentResponseDto;
import tn.mosofty.junitmockito.entity.Student;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

	private StudentMapper studentMapper;

	@BeforeEach
	void setUp() {
		studentMapper = new StudentMapper();
	}

	@Test
	@DisplayName("should throw null pointer exception when mapping student dto to student")
	public void mapToStudentResponseDtoWithNull() {
		assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
	}

	@Test
	@DisplayName("should map StudentResponseDto to Student")
	public void mapStudentResponseDtoToStudent() {
		var studentResponseDto = new StudentResponseDto("john", "doe", "john.doe@gmail.com", 25);
		var student = studentMapper.toStudent(studentResponseDto);

		assertAll(() -> assertEquals(studentResponseDto.firstName(), student.getFirstname()),
				() -> assertEquals(studentResponseDto.lastName(), student.getLastname()),
				() -> assertEquals(studentResponseDto.email(), student.getEmail()),
				() -> assertEquals(studentResponseDto.age(), student.getAge()));
	}

	@Test
	@DisplayName("should map Student to StudentResponseDto")
	public void mapStudentToStudentResponseDto() {
		// Given
		var student = new Student(1, "john", "doe", "john.doe@gmail.com", 25);

		// When
		var studentResponseDto = studentMapper.toStudentResponseDto(student);

		// Then
		assertAll(() -> assertEquals(studentResponseDto.firstName(), student.getFirstname()),
				() -> assertEquals(studentResponseDto.lastName(), student.getLastname()),
				() -> assertEquals(studentResponseDto.email(), student.getEmail()),
				() -> assertEquals(studentResponseDto.age(), student.getAge()));
	}

}