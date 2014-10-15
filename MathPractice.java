/**
 * MathPractice - A Class implementing various math algorithms and puzzles
 * @author Colin Heffernan
 *
 */
public class MathPractice {
	/**
	 * karatsuba - performs karatsuba multiplication on the specified numbers using the specified base
	 * 	See http://en.wikipedia.org/wiki/Karatsuba_algorithm for the details of the algorithm
	 * Special Considerations: This currently only works for non-negative integers
	 * @return the produce of the two numbers
	 */
	public static int karatsuba(int base, int a, int b){
		/* handle trivial cases separately */
		if(a == 0 || b== 0){
			return 0;
		}
		if (a == 1){
			return b;
		}
		else if (b == 1){
			return a;
		}
		int m = 0;
		int baseToM = (int) Math.pow(base, m);
		while(baseToM < a && baseToM < b){
			baseToM = (int) Math.pow(base, ++m);
		}
		baseToM = (int) Math.pow(base, --m);
		int a0 = a % baseToM;
		int b0 = b % baseToM;
		int a1 = a / baseToM;
		int b1 = b / baseToM;
		int z0 = a0 * b0;
		int z2 = a1*b1;
		int z1 = (a1 + a0)*(b1 + b0) - z2 - z0;
		return z2 * (int)Math.pow(baseToM, 2) + z1 * baseToM + z0;
	}
	
	public static void main(String [] args){
		int x = 5321, y = 438;
		System.out.println(karatsuba(10, x, y));
	}
}
