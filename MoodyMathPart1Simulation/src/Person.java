
public class Person {
	public static final int[] ageRegionProbabilities = new int[] {0, 86, 86+88, 86+88+85, 86+88+85+330, 86+88+85+330+243, 86+88+85+330+243+95, 1000};
	public static final int[] genderRegions = new int[] {0, 490, 1000};
	// 
	public static final int[] educationRegions = new int[]{0, 123, 123+296, 123+296+288, 123+296+288+189, 1000};
	
	public int[] minutesDrivenPerDay;
	public int[] milesDrivenPerDay;
	
	private AgeRegion age;
	private Gender gender;
	private Education education;
	
	private enum AgeRegion
	{
		Age16to19, Age20to24, Age25to29, Age30to49, Age50to64, Age65to74, Age75andOver
	}
	
	private enum Gender
	{
		Female, Male
	}
	
	private enum Education
	{
		GradeSchool, HighSchool, Associates, Bachelors, GraduateSchool
	}
	
	public Person()
	{
		int random = (int) (Math.random() * 1001);
		
		
		// Generate random age range
		for(int i = 0; i < ageRegionProbabilities.length - 1; i++)
		{
			if(random > ageRegionProbabilities[i] && random <= ageRegionProbabilities[i+1])
			{
				age = AgeRegion.values()[i];
				break;
			}
		}
		
		
		
		// Generate random gender
		random = (int) (Math.random() * 1001);
		for(int i = 0; i < genderRegions.length - 1; i++)
		{
			if(random > genderRegions[i] && random <= genderRegions[i+1])
			{
				gender = Gender.values()[i];
			}
		}
		
		
		// Generate random valid education
		if(age.ordinal() < 1)
		{
			random = (int) (Math.random() * (educationRegions[2] + 1));
			for(int i = 0; i < 3; i++)
			{
				if(random > educationRegions[i] && random <= educationRegions[i+1])
				{
					education = Education.values()[i];
					break;
				}
			}
		}
		else
		{
			random = (int) (Math.random() * 1001);
			for(int i = 0; i < educationRegions.length; i++)
			{
				if(random > educationRegions[i] && random <= educationRegions[i+1])
				{
					education = Education.values()[i];
					break;
				}
			}
		}
		
	}
	
	public double[] drive()
	{
		double[] data = new double[2];
		data[0] = ((31.658* Math.pow(education.ordinal(), .4073)) + (1.2778 * Math.pow(age.ordinal(), 3) - 16.488 * Math.pow(age.ordinal(), 2) + 62.091 * age.ordinal() - 19) + (gender.ordinal() * 10 + 41)) / 3.0;
		data[1] = ((-.8071 * Math.pow(education.ordinal(), 2)) + (.1812*Math.pow(age.ordinal(), 4) - 1.8292 * Math.pow(age.ordinal(), 3) + 2.4312*Math.pow(age.ordinal(), 2) + 14.585 * age.ordinal() + 4.25) + (8.7*gender.ordinal() + 24.9)) / 3.0;
		return data;
	}
	
	public String toString()
	{
		return "[" + age + ", " +  gender + ", " + education + "]";
	}
}
