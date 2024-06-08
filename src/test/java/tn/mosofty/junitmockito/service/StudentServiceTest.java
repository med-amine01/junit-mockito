package tn.mosofty.junitmockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.mosofty.junitmockito.dto.StudentResponseDto;
import tn.mosofty.junitmockito.entity.Student;
import tn.mosofty.junitmockito.mapper.StudentMapper;
import tn.mosofty.junitmockito.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private StudentMapper studentMapper;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("should save student")
	public void saveStudentTest() {
		// Given
		StudentResponseDto studentResponseDto = new StudentResponseDto("john", "doe", "john.doe@gmail.com", 25);
		Student student = new Student(1, "john", "doe", "john.doe@gmail.com", 25);
		Student savedStudent = new Student(1, "john", "doe", "john.doe@gmail.com", 25);

		// Mock calls in service
		// 1 - mapping to student
		when(studentMapper.toStudent(studentResponseDto)).thenReturn(student);
		// 2 - saving using repository
		when(studentRepository.save(student)).thenReturn(savedStudent);
		// 3 - return student response dto
		when(studentMapper.toStudentResponseDto(savedStudent)).thenReturn(studentResponseDto);

		// When
		StudentResponseDto studentResponseDtoSaved = studentService.saveStudent(studentResponseDto);

		// Then
		assertAll(() -> assertEquals(studentResponseDtoSaved.firstName(), studentResponseDto.firstName()),
				() -> assertEquals(studentResponseDtoSaved.lastName(), studentResponseDto.lastName()),
				() -> assertEquals(studentResponseDtoSaved.email(), studentResponseDto.email()));

		// Making sure that save methode didn't call multiple times
		verify(studentMapper, times(1)).toStudent(studentResponseDto);
		verify(studentRepository, times(1)).save(student);
		verify(studentMapper, times(1)).toStudent(studentResponseDtoSaved);

	}

	@Test
	@DisplayName("should return list of student response dto")
	public void getAllStudentsTest() {
		// Given
		List<Student> students = new ArrayList<>();
		students.add(new Student(1, "john", "doe", "doe@gmail.com", 25));

		// Mock calls
		when(studentRepository.findAll()).thenReturn(students);
		when(studentMapper.toStudentResponseDto(any(Student.class)))
			.thenReturn(new StudentResponseDto("john", "doe", "doe@gmail.com", 25));

		// When
		List<StudentResponseDto> studentResponseDtos = studentService.getAllStudents();

		// Then
		assertEquals(students.size(), studentResponseDtos.size());
	}

	@Test
	@DisplayName("should return list of student response dto")
	public void getStudentByIdTest() {
		// Given
		Student student = new Student(1, "john", "doe", "john.doe@gmail.com", 25);
		StudentResponseDto studentResponseDto = new StudentResponseDto("john", "doe", "john.doe@gmail.com", 25);

		// Mock calls
		when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		when(studentMapper.toStudentResponseDto(any(Student.class)))
			.thenReturn(new StudentResponseDto("john", "doe", "john.doe@gmail.com", 25));

		// When
		StudentResponseDto response = studentService.getStudentById(1);

		// Then
		assertAll(
				() -> assertEquals(student.getEmail(), studentResponseDto.email()));
	}

}