package workoutA;

public class Main {
	public static void main(String[] args) {
		ExerciseCtrl eCtrl = new ExerciseCtrl();
		WorkoutCtrl wCtrl = new WorkoutCtrl();
		System.out.println(eCtrl.getAll());
		eCtrl.createExercise("squats", "work hard, play hard", "100kg", "90kg x3", 70, 3, 1, 0, 0);
		System.out.println(eCtrl.getAll());
		System.out.println(wCtrl.getAll());
		wCtrl.createWorkout("Legday", 120, "Best day...");
		wCtrl.addExercise(wCtrl.getLast(), eCtrl.getAll().get(0));
	}
}
