package tn.mosofty.junitmockito.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.mosofty.junitmockito.dto.StudentResponseDto;
import tn.mosofty.junitmockito.mapper.StudentMapper;
import tn.mosofty.junitmockito.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentRepository studentRepository;

	private final StudentMapper studentMapper;

	public StudentResponseDto saveStudent(StudentResponseDto studentResponseDto) {
		var student = studentMapper.toStudent(studentResponseDto);
		studentRepository.save(student);
		return studentMapper.toStudentResponseDto(student);
	}

	public List<StudentResponseDto> getAllStudents() {
		// studentRepository.findAll().stream().map(student -> studentMapper.toStudentResponseDto(student)).toList();
		return studentRepository.findAll().stream().map(studentMapper::toStudentResponseDto).toList();
	}

	public StudentResponseDto getStudentById(Integer id) {
		return studentRepository.findById(id).map(studentMapper::toStudentResponseDto).orElse(null);
	}

}
