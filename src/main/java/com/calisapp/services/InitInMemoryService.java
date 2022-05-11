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
import com.calisapp.model.ProcessExercise;
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
	
	//@Autowired
	//private ExerciseService exerciseService;
	
	@Autowired
	private ProcessExerciseService processExercise;
	
	@Autowired
	private RoutineService routineService;
	
	@PostConstruct
	public void initialize() throws Exception {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialData();
		}
	}
	
	private void fireInitialData() {
		User user1 = new User("Cesar", "perez.cesaar@gmail.com", "1234");
		User user2 = new User("Marcelo", "jmdicostanzo11@gmail.com", "1234");

		ProcessExercise processExercise1 = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("dominadas australianas")
				.withComplexityNumber(1)
				.withDescription("ejercicio colgado")
				.withUrlVideo("video1")
				.withMainMuscle("espalda")
				.build();

		ProcessExercise processExercise2  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("fondos")
				.withComplexityNumber(1)
                .withDescription("ejercicio en paralelas")
                .withUrlVideo("video2")
                .withMainMuscle("triceps").build();
		
		ProcessExercise processExercise3  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("flexiones")
				.withComplexityNumber(1)
                .withDescription("ejercicio en suelo")
                .withUrlVideo("video3")
                .withMainMuscle("pectoral")
                .build();
		
		ProcessExercise processExercise4  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("sentadillas")
				.withComplexityNumber(1)
                .withDescription("ejercicio en suelo")
                .withUrlVideo("video4")
                .withMainMuscle("piernas")
                .build();
		
		ProcessExercise processExercise5 = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("dominadas supinas")
				.withComplexityNumber(3)
				.withDescription("ejercicio colgado")
				.withUrlVideo("video5")
				.withMainMuscle("espalda")
				.build();
		
		ProcessExercise processExercise6  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("flexiones diamantes")
				.withComplexityNumber(3)
				.withDescription("ejercicio en suelo")
				.withUrlVideo("video6")
				.withMainMuscle("pectoral")
				.build();
		
		ProcessExercise processExercise7  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("zancadas")
				.withComplexityNumber(3)
				.withDescription("ejercicio en suelo")
				.withUrlVideo("video7")
				.withMainMuscle("piernas")
				.build();
		
		ProcessExercise processExercise8  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("plancha")
				.withComplexityNumber(3)
				.withDescription("ejercicio en suelo")
				.withUrlVideo("video8")
				.withMainMuscle("abdominales")
				.build();
		
		ProcessExercise processExercise9  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("burpees")
				.withComplexityNumber(3)
                .withDescription("ejercicio en suelo")
                .withUrlVideo("video9")
                .withMainMuscle("abdominales")
                .build();
		
		ProcessExercise processExercise10  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("flexiones a pino")
				.withComplexityNumber(5)
                .withDescription("ejercicio en suelo")
                .withUrlVideo("video10")
                .withMainMuscle("pectoral")
                .build();
		
		ProcessExercise processExercise11  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("fondos explosivos")
				.withComplexityNumber(5)
                .withDescription("ejercicio en paralelas")
                .withUrlVideo("video11")
                .withMainMuscle("triceps")
                .build();
		
		ProcessExercise processExercise12 = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("muscle up")
				.withComplexityNumber(5)
				.withDescription("ejercicio colgado")
				.withUrlVideo("video12")
				.withMainMuscle("hombros")
				.build();
		
		ProcessExercise processExercise13  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("pistol squat")
				.withComplexityNumber(5)
				.withDescription("ejercicio en suelo")
				.withUrlVideo("video13")
				.withMainMuscle("piernas")
				.build();
		
		ProcessExercise processExercise14 = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("dominadas pronas")
				.withComplexityNumber(5)
				.withDescription("ejercicio colgado")
				.withUrlVideo("video14")
				.withMainMuscle("espalda")
				.build();
		
		Set<String> allMuscles15 =  new HashSet<String>();
		allMuscles15.add("Espalda");
		allMuscles15.add("Pecho");
		
		ProcessExercise processExercise15  = new ProcessExercise.ProcessExerciseBuilder()
				.withNameExercise("dragon flag")
				.withComplexityNumber(5)
                .withDescription("ejercicio en suelo")
                .withUrlVideo("video15")
                .withMainMuscle("abdominales")
                .withAllMuscles(allMuscles15)
                .build();
		
		Exercise exercise1  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise1)
				.withRepetitions(15)
				.withSeries(3)
				.build();
		
		Exercise exercise2  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise2)
				.withRepetitions(25)
				.withSeries(2)
				.withDayExercise(2)
				.build();
		
		Exercise exercise3  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise3)
				.withRepetitions(10)
				.withSeries(4)
				.withDayExercise(4)
				.build();
		
		Exercise exercise4  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise4)
				.withRepetitions(8)
				.withSeries(5)
				.build();
		
		Exercise exercise5  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise5)
				.withRepetitions(12)
				.withSeries(4)
				.build();
		
		Exercise exercise6  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise6)
				.withRepetitions(6)
				.withSeries(6)
				.build();
		
		Exercise exercise7  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise7)
				.withRepetitions(3)
				.withSeries(5)
				.build();
		
		Exercise exercise8  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise8)
				.withRepetitions(5)
				.withSeries(6)
				.build();
		
		Exercise exercise9  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise9)
				.withRepetitions(22)
				.withSeries(3)
				.build();
		
		Exercise exercise10  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise10)
				.withRepetitions(12)
				.withSeries(3)
				.build();
		
		Exercise exercise11  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise11)
				.withRepetitions(15)
				.withSeries(4)
				.build();
		
		Exercise exercise12  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise12)
				.withRepetitions(8)
				.withSeries(5)
				.build();
		
		Exercise exercise13  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise13)
				.withRepetitions(10)
				.withSeries(4)
				.build();
		
		Exercise exercise14  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise14)
				.withRepetitions(15)
				.withSeries(3)
				.build();
		
		Exercise exercise15  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise15)
				.withRepetitions(21)
				.withSeries(4)
				.build();
		
		//Rutina espartana
		Exercise dominadasPronas  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise14)
				.withRepetitions(50)
				.withSeries(2)
				.build();

		Exercise sentadillas  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise4)
				.withRepetitions(100)
				.withSeries(2)
				.build();
		
		Exercise fondos  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise11)
				.withRepetitions(50)
				.withSeries(2)
				.build();
		
		Exercise flexiones  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise3)
				.withRepetitions(100)
				.withSeries(2)
				.build();
		
		Exercise burpees  = new Exercise.ExerciseBuilder()
				.withProcessExercise(processExercise9)
				.withRepetitions(50)
				.withSeries(2)
				.build();
		
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

		Set<Exercise> ejerciciosEspartanos = new HashSet<Exercise>();
		ejerciciosEspartanos.add(dominadasPronas);
		ejerciciosEspartanos.add(sentadillas);
		ejerciciosEspartanos.add(fondos);
		ejerciciosEspartanos.add(flexiones);
		ejerciciosEspartanos.add(burpees);

		Routine rutina1 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina Principiantes")
				.withExercises(ejerciciosPrincipiantes)
				.withLevel("1")
				.build();
		
		Routine rutina2 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina Intermedios")
				.withExercises(ejerciciosIntermedios)
				.withLevel("2")
				.build();
		
		Routine rutina3 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina Avanzados")
				.withExercises(ejerciciosAvanzados)
				.withLevel("3")
				.build();
		
		Routine rutina4 = new RoutineByLevel.RoutineByLevelBuilder()
				.withNameRoutine("Rutina Espartana")
				.withExercises(ejerciciosEspartanos)
				.withLevel("3")
				.build();
		
		/*	
		exerciseService.save(exercise1);
		exerciseService.save(exercise2);
		exerciseService.save(exercise3);
		exerciseService.save(exercise4);
		exerciseService.save(exercise5);
		exerciseService.save(exercise6);
		exerciseService.save(exercise7);
		exerciseService.save(exercise8);
		exerciseService.save(exercise9);
		exerciseService.save(exercise10);
		exerciseService.save(exercise11);
		exerciseService.save(exercise12);
		exerciseService.save(exercise13);
		exerciseService.save(exercise14);
		exerciseService.save(exercise15);
		exerciseService.save(dominadasPronas);
		exerciseService.save(sentadillas);
		exerciseService.save(fondos);
		exerciseService.save(flexiones);
		exerciseService.save(burpees);
		*/
		
		processExercise.save(processExercise1);
		processExercise.save(processExercise2);
		processExercise.save(processExercise3);
		processExercise.save(processExercise4);
		processExercise.save(processExercise5);
		processExercise.save(processExercise6);
		processExercise.save(processExercise7);
		processExercise.save(processExercise8);
		processExercise.save(processExercise9);
		processExercise.save(processExercise10);
		processExercise.save(processExercise11);
		processExercise.save(processExercise12);
		processExercise.save(processExercise13);
		processExercise.save(processExercise14);
		processExercise.save(processExercise15);
			
		routineService.save(rutina1);
		routineService.save(rutina2);
		routineService.save(rutina3);
		routineService.save(rutina4);
		
		userService.save(user1);
		userService.save(user2);
	}
}
