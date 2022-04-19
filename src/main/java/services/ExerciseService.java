package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Exercise;
import repositories.ExerciseRepository;

@Service
public class ExerciseService {

	@Autowired
	private ExerciseRepository  exerciseRepository;
	
	public List<Exercise> findAll() {
		return this.exerciseRepository.findAll();
	}
}
