package workout;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
NOTES:

All names should be converted to lower case before use in database.

 */

public class Main {
	
	public static void main(String[] args) { // for testing
		ExerciseCtrl eCtrl = new ExerciseCtrl();
		WorkoutCtrl wCtrl = new WorkoutCtrl();

		System.out.println(eCtrl.getAll());
		System.out.println(wCtrl.getAll());
		System.out.println(wCtrl.getWorkout("strength"));
		
		wCtrl.createWorkout("mondays", 120, "I love mondays");
		System.out.println(wCtrl.getAll());
		System.out.println(wCtrl.getWorkout("mondays"));
		
		
		eCtrl.createExercise("squats", "weight lift", null, null, 90, 3, 10, 0, 0);
		System.out.println(eCtrl.getExercise("squats"));
		
		
		System.out.println("works?..");
		System.out.println();
		//testing
		wCtrl.addExercise(wCtrl.getWorkout("mondays"), eCtrl.getExercise("squats"));
		System.out.println("inserted");
		System.out.println(wCtrl.getExercises(wCtrl.getWorkout("mondays"), eCtrl));
		
	}
	
	
	
	/*  FOR USER INTERACTION:
	 * 
	public static void main(String[] args){
		ExerciseCtrl ex = new ExerciseCtrl();
		WorkoutCtrl work = new WorkoutCtrl();
		
		System.out.println("### FOR TESTING PURPOSES ###");
		System.out.println(ex.getAll());
		System.out.println(work.getAll());
		
		// Commands:
		ArrayList<String> commands = new ArrayList();
		String show = "show";		// Creating
		String add = "add";			// Creating
		String newCommand = "new"; // Creating
		String doCommand = "do";	// Execute workout etc.. 
		String edit = "edit";
		String exit = "exit";
		String help = "help";
		commands.add(show);
		commands.add(add);
		commands.add(newCommand);
		commands.add(edit);
		commands.add(exit);
		commands.add(help);
		
		
		
		//  Variables and objects for user interaction:
		String input, command, request;
		Scanner in = new Scanner(System.in);
		
		System.out.println("#### MAIN PROGAM ####");
		System.out.println("Welcome!");
		System.out.println("- type \"help\" to see commands");
		
		// Main loop
		
		while (true){
			
			System.out.print(">");
			input = in.nextLine().toLowerCase();
			command = input.split(" ")[0];
			request = input.replace(command+" ", "");
			
			
			if (command.equals(show)){
				if (request.contains("exercise")){
					System.out.println(ex.toString());
				}
				else if(request.contains("workout")){
					System.out.println(work.getAll());
				}
				else if(request.contains("week")){
					System.out.println("not available yet.."); // fix week summary
				}
				
				else{
					System.out.println("# You can show: exercise, workout, week. Eg. type: > show exercise");
				}
			}
			else if (command.equals(newCommand)){
				if (request.contains("exercise")){

				}
				else if (request.equals("workout")){

				}
			}
			else if (command.contains(add)){
				if (request.contains("exercise")){
					System.out.println("# Choose workout:");
					System.out.println(work.getAll());
					System.out.print(">");
					String workout = in.nextLine();
					
					System.out.println("# Choose exercise:");
					System.out.println(ex.getAll());
					System.out.print(">");
					String exercise = in.nextLine();
					
					work.addExercise(ex.getExercise(exercise), work.getWorkout(workout) );
				}
			}
			else if (command.equals(doCommand)){
				while (true){
					if (request.equals("") || request.equals(" ") || request == null){
						System.out.println("# Choose workout:");
						System.out.println(work.getWorkouts());					System.out.print(">");
						request = in.nextLine();
					}
					else{
						System.out.println("doWorkout"); // debug
						work.doWorkout(request);
					}
				}
			}
			else if (command.equals(edit)){ // Wait with implementing (not important)
				
				}
			else if (command.equals(help)){
				System.out.println(commands);
			}
			else if (command.equals(exit)){
				break;
			}
			else{
				System.out.println("Invalid command");
			}
			// for testing:
			//exit = true;
			
		}
	}
	
	
	// USER INTERACTION:
	
	private static void edit(String request){
		// user wants to edit something
	}
	private static void create(String request){
		// this method is used whenever the user wants to create something new
	}
	private static void show(String request, ExerciseCtrl exCtrl) throws SQLException{
		// this method is used if the user wants to show something
		if ( request.equals("exercises") ){
			System.out.println("Showing all exercises:");
			System.out.println( exCtrl.getAll() );
		}
	}

*/

	}
