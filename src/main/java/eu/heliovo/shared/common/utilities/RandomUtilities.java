package eu.heliovo.shared.common.utilities;

import java.util.Random;

public class RandomUtilities 
{
	Random	rnd	=	new Random();
	
	public	int	getRandomBetween(int min, int max)
	{
		return min + rnd.nextInt(max-min);
	}
}
