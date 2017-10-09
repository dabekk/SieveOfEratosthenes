import java.util.Scanner;
import java.awt.Font;
import java.awt.Color;
public class SieveOfEratosthenes2{
public static final double SIZEOFSCREENFORBOX = 0.6;
public static final double POSYOFMATRICES = 0.9;
public static final Color STANDARDCOLOR = new Color(160, 160, 160);
	public static void main(String[] args) {
		System.out.println("******Use Finals********");
		Scanner scan = new Scanner(System.in);
		int size = 2;
		do
		{
			System.out.print("Enter the size of the board: ");
			size = scan.nextInt();
			if(size < 2)
			{
				System.out.println("You must enter a number >= 2");
			}
		}while(size < 2);
		displayNumbers2ToN(size);
		sieve(size+1);
		scan.close();
	}

	public static void displayNumber(int num, Color c, int size)
	{
		int dimensions = (int) Math.ceil(Math.sqrt(size));
		double spacing = SIZEOFSCREENFORBOX/dimensions;
		double posY = POSYOFMATRICES;
		double posX = spacing;
		int index = size;
		int counter = 1;
		Font font = new Font("Arial", Font.PLAIN, (int) 125/dimensions);		//scaling
		StdDraw.setFont(font);
		for(int x = dimensions; x >= 0 && counter <= size; x--)
		{
			
			for(index = 0; index < dimensions && counter <= size; index++)
			{
				if(num == counter)
				{
				
					StdDraw.setPenColor(c);
					StdDraw.filledSquare(posX, posY, spacing/2.2);
					StdDraw.setPenRadius(0.2);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(posX, posY, "" + counter);
					StdDraw.setPenRadius();
				}
				posX = posX + spacing;
				counter++;
			}
			posX = spacing;
			posY = posY - spacing;
		}
	}
	public static void displayNumbers2ToN(int size)
	{
		StdDraw.text(((1 - SIZEOFSCREENFORBOX)/2) + SIZEOFSCREENFORBOX, POSYOFMATRICES + 0.1, "Prime Numbers");
		for(int i = 2; i <= size; i++)
		{
			displayNumber(i, STANDARDCOLOR, size);
		}
	}
	public static void displayComposite(int m, int primeCount, int size)
	{
		Color color;
		switch(primeCount)
		{
		case 1:
			color = Color.BLUE;
			break;
		case 2:
			color = Color.PINK;
			break;
		case 3:
			color = Color.ORANGE;
			break;
		case 4:
			color = Color.MAGENTA;
			break;
		case 5:
			color = Color.YELLOW;
			break;
		case 6:
			color = Color.RED;
			break;
		case 7:
			color = Color.GREEN;
			break;
		default:
			color = Color.CYAN;
			break;
		}
		displayNumber(m, color, size);
		try {
		    Thread.sleep(50);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	public static void displayPrime(int prime, int primeCount)
	{
		double posY = POSYOFMATRICES;
		double spacing = 0.07;
		double posX = (SIZEOFSCREENFORBOX) + spacing;
		int width = 6;
		int height = 20;
		Font font = new Font("Arial", Font.PLAIN, 14);	
		StdDraw.setFont(font);
		int counter = 1;
		for(int x = height; x >= 0; x--)
		{
			
			for(int index = 0; index < width; index++)
			{
				if(counter == primeCount)
				{
					StdDraw.setPenColor(StdDraw.WHITE);
					StdDraw.filledSquare(posX, posY, spacing/2.2);
					StdDraw.setPenRadius(0.2);
					StdDraw.setPenColor(StdDraw.BLACK);
					StdDraw.text(posX, posY, "" + prime);
					StdDraw.setPenRadius();
				}
				posX = posX + spacing;
				counter++;
			}
			posX = (SIZEOFSCREENFORBOX) + spacing;
			posY = posY - spacing;
		}
		
	}
	//*************************************************************************************
	public static boolean[] createSequence(int size)
	{
			boolean[] sequence = new boolean[size];
			for(int i = 0; i < size; i++)
			{
				sequence[i] = true;
			}
			return sequence;

	}
	public static void crossOutHigherMultiples(boolean[] sequence, int size)
	{
		int testNum = 2;
		int primeCount = 0;
		for(int x = testNum; x <= Math.sqrt(size); x++)
		{
			primeCount++;
			if(sequence[x])
			{
				testNum = x;
				for(int i = testNum + testNum; i < size; i = i + testNum)
				{
					if(sequence[i])
					{
						displayComposite(i, primeCount, size);
					}
						sequence[i] = false;
				}
			}
		}
		primeCount = 0;
		for(int index = 2; index < sequence.length; index++)
		{
			if(sequence[index])
			{
				primeCount++;
				displayPrime(index, primeCount);
				System.out.println(index + " " + primeCount);
			}
		}
	}
	public static boolean[] sieve(int size)
	{
		boolean[] sequence = createSequence(size);
		crossOutHigherMultiples(sequence, size);
		return sequence;
	}
	public static String sequenceToString(boolean[] sequence)
	{
		String sequenceString = "";
		for(int i = 2; i <= sequence.length - 1; i++)
		{
			if(sequence[i])
			{
				sequenceString = sequenceString + i + ", ";
			}
			else
			{
				sequenceString = sequenceString + "[" + i + "], ";
			}
		}
		return sequenceString;
	}
	public static String nonCrossedOutSubseqToString(boolean[] sequence)
	{
		String sequenceString = "";
		for(int i = 2; i <= sequence.length - 1; i++)
		{
			if(sequence[i])
			{
				sequenceString = sequenceString + i + ", ";
			}
		}
		return sequenceString;
	}
}
