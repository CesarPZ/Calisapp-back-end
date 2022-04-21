package com.calisapp.services;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calisapp.model.Exercise;
import com.calisapp.model.Routine;
import com.calisapp.model.RoutineByLevel;
import com.calisapp.model.User;

@Service
@Transactional
public class InitInMemoryService {
	protected final Log logger = LogFactory.getLog(getClass());

	@Value("${spring.datasource.driverClassName:NONE}")
	private String className;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExerciseService exerciseService;
	
	@Autowired
	private RoutineService routineService;
	
	@PostConstruct
	public void initialize() throws Exception {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}
	
	private void fireInitialData() throws Exception {
		User user1 = new User("Cesar", "perez.cesaar@gmail.com", "1234");
		User user2 = new User("Marcelo", "jmdicostanzo11@gmail.com", "1234");
	
		Exercise exercise1 = new Exercise.ExerciseBuilder().withNameExercise("dominadas australianas").withComplexityNumber(1)
				.withDescription("ejercicio colgado").withUrlVideo("video1").withMuscle("espalda").build();

		Exercise exercise2  = new Exercise.ExerciseBuilder().withNameExercise("fondos").withComplexityNumber(1)
                .withDescription("ejercicio en paralelas").withUrlVideo("video2").withMuscle("triceps").build();
		
		Exercise exercise3  = new Exercise.ExerciseBuilder().withNameExercise("flexiones").withComplexityNumber(1)
                .withDescription("ejercicio en suelo").withUrlVideo("video3").withMuscle("pectoral").build();
		
		Exercise exercise4  = new Exercise.ExerciseBuilder().withNameExercise("sentadillas").withComplexityNumber(1)
                .withDescription("ejercicio en suelo").withUrlVideo("video4").withMuscle("piernas").build();
		
		Exercise exercise5 = new Exercise.ExerciseBuilder().withNameExercise("dominadas supinas").withComplexityNumber(3)
				.withDescription("ejercicio colgado").withUrlVideo("video5").withMuscle("espalda").build();
		
		Exercise exercise6  = new Exercise.ExerciseBuilder().withNameExercise("flexiones diamantes").withComplexityNumber(3)
				.withDescription("ejercicio en suelo").withUrlVideo("video6").withMuscle("pectoral").build();
		
		Exercise exercise7  = new Exercise.ExerciseBuilder().withNameExercise("zancadas").withComplexityNumber(3)
				.withDescription("ejercicio en suelo").withUrlVideo("video7").withMuscle("piernas").build();
		
		Exercise exercise8  = new Exercise.ExerciseBuilder().withNameExercise("plancha").withComplexityNumber(3)
				.withDescription("ejercicio en suelo").withUrlVideo("video8").withMuscle("abdominales").build();
		
		Exercise exercise9  = new Exercise.ExerciseBuilder().withNameExercise("burpees").withComplexityNumber(3)
                .withDescription("ejercicio en suelo").withUrlVideo("video9").withMuscle("abdominales").build();
		
		Exercise exercise10  = new Exercise.ExerciseBuilder().withNameExercise("flexiones a pino").withComplexityNumber(5)
                .withDescription("ejercicio en suelo").withUrlVideo("video10").withMuscle("pectoral").build();
		
		Exercise exercise11  = new Exercise.ExerciseBuilder().withNameExercise("fondos explosivos").withComplexityNumber(5)
                .withDescription("ejercicio en paralelas").withUrlVideo("video11").withMuscle("triceps").build();
		
		Exercise exercise12 = new Exercise.ExerciseBuilder().withNameExercise("muscle up").withComplexityNumber(5)
				.withDescription("ejercicio colgado").withUrlVideo("video12").withMuscle("hombros").build();
		
		Exercise exercise13  = new Exercise.ExerciseBuilder().withNameExercise("pistol squat").withComplexityNumber(5)
				.withDescription("ejercicio en suelo").withUrlVideo("video13").withMuscle("piernas").build();
		
		Exercise exercise14 = new Exercise.ExerciseBuilder().withNameExercise("dominadas pronas").withComplexityNumber(5)
				.withDescription("ejercicio colgado").withUrlVideo("video14").withMuscle("espalda").build();
		
		Exercise exercise15  = new Exercise.ExerciseBuilder().withNameExercise("dragon flag").withComplexityNumber(5)
                .withDescription("ejercicio en suelo").withUrlVideo("video15").withMuscle("abdominales").build();
		
		Set<Exercise> ejerciciosPrincipiantes = new HashSet<Exercise>();
		ejerciciosPrincipiantes.add(exercise1);
		ejerciciosPrincipiantes.add(exercise2);
		ejerciciosPrincipiantes.add(exercise3);
		ejerciciosPrincipiantes.add(exercise4);

		Set<Exercise> ejerciciosIntermedios = new HashSet<Exercise>();
		ejerciciosIntermedios.add(exercise5);
		ejerciciosIntermedios.add(exercise6);
		ejerciciosIntermedios.add(exercise7);
		ejerciciosIntermedios.add(exercise8);
		ejerciciosIntermedios.add(exercise9);

		Set<Exercise> ejerciciosAvanzados = new HashSet<Exercise>();
		ejerciciosAvanzados.add(exercise10);
		ejerciciosAvanzados.add(exercise11);
		ejerciciosAvanzados.add(exercise12);
		ejerciciosAvanzados.add(exercise13);
		ejerciciosAvanzados.add(exercise14);
		ejerciciosAvanzados.add(exercise15);

		Routine rutina1 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina 1")
				.withExercises(ejerciciosPrincipiantes)
				.withLevel("1").build();
			
		Routine rutina2 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina 2")
				.withExercises(ejerciciosIntermedios)
				.withLevel("3").build();
		
		Routine rutina3 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina 3")
				.withExercises(ejerciciosAvanzados)
				.withLevel("5").build();
		
		userService.save(user1);
		userService.save(user2);
		
		routineService.save(rutina1);
		routineService.save(rutina2);
		routineService.save(rutina3);
	}
}
