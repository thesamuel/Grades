import java.util.*;
import java.io.*;
public class Grades {

	public static final int	MAX_INST	= 25;

	public static void main(String[]	args)	throws FileNotFoundException {
		Scanner console =	new Scanner(System.in);
		boolean shouldSearch	= true;
		while	(shouldSearch)	{
			Scanner file =	new Scanner(new File("grades.csv"));
			System.out.print("Search for course number (return to quit): ");
			String search = console.nextLine();
			shouldSearch =	search.length() != 0;
			if	(shouldSearch)	{
				double averageGPA	= 0;
			   int numGPAs	= 0;
			   while	(file.hasNextLine())	{
			     	String line	= file.nextLine();
			   	String[]	splits =	line.split(",");
		   		String instructor	= (splits[4] +	"," +	splits[5]).replaceAll("\"", "");
		   		if	(splits[2].toLowerCase().contains(search.toLowerCase()) || instructor.toLowerCase().contains(search.toLowerCase())) {
		   			double gpa = Double.parseDouble(splits[splits.length - 1]);
		   			String quarter	= splits[1].split("[()]")[1];
		   			System.out.println(formatOutput(instructor, gpa, quarter));
		   			averageGPA += gpa;
		   			numGPAs++;
		   		}
		   	}
		   	if	(averageGPA	!=	0)	{
		   		System.out.println();
		   		System.out.println("Average of GPAs printed: " + round1(averageGPA /	numGPAs));
		   	}
		   	System.out.println();
			}
		}
	}
	
	public static String	formatOutput(String instructor, double	gpa, String	quarter)	{
		String instructorStd	= instructor.substring(0, Math.min(MAX_INST,	instructor.length()));
		for (int	i = instructorStd.length(); i	< MAX_INST;	i++) {
			instructorStd += " ";
		}
		return "Instructor: " +	instructorStd + " | GPA: "	+ gpa	+ " | Quarter: " + quarter;
	}

	//	returns the	specified value rounded	to	one decimal	place
	public static double	round1(double val) {
		return Math.round(val *	100) / 100.0;
	}
}
