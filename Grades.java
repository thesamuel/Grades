import java.util.*;
import java.io.*;
public class Grades {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		boolean shouldSearch = true;
		while (shouldSearch) {
			Scanner file = new Scanner(new File("grades.csv"));
			System.out.print("Search for course number or instructor: ");
			String search = console.nextLine();
			double averageGPA = 0;
			int numGPAs = 0;
			while (file.hasNextLine()) {
				String line = file.nextLine();
				String[] splits = line.split(",");
				String instructor = (splits[4] + "," + splits[5]).replaceAll("\"", "");
				if (splits[2].toLowerCase().contains(search.toLowerCase()) || instructor.toLowerCase().contains(search.toLowerCase())) {
					String gpa = splits[splits.length - 1];
					String quarter = splits[1].split("[()]")[1];
					System.out.println("Instructor: " + instructor + " | GPA: " + gpa + " | Quarter: " + quarter);
					averageGPA += Double.parseDouble(gpa);
					numGPAs++;
				}
			}
			if (averageGPA != 0) {
				System.out.println();
				System.out.println("Average of GPAs printed: " + round1(averageGPA / numGPAs));
			}
			System.out.println();
			System.out.print("Search again? (y/n) ");
			if (!console.nextLine().toLowerCase().equals("y")) {
				shouldSearch = false;
			}
		}
	}

	// returns the specified value rounded to one decimal place
	public static double round1(double val) {
		return Math.round(val * 100) / 100.0;
	}
}
