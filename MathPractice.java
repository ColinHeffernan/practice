/**
 * MathPractice - A Class implementing various math algorithms and puzzles
 * @author Colin Heffernan
 *
 */
public class MathPractice {
	/**
	 * karatsuba - performs karatsuba multiplication on the specified numbers using the specified base
	 * 	See http://en.wikipedia.org/wiki/Karatsuba_algorithm for the details of the algorithm
	 * @return the product of the two numbers
	 */
	public static int karatsuba(int base, int a, int b){
		/* set x and y to the absolute values of a and b respectively */
		int x = (a < 0) ? (0 - a): a;
		int y = (b < 0) ? (0 - b): b;
		/* small cases must be multiplied directly */
		if(x < Math.pow(base,2) || y < Math.pow(base,  2)){
			return a * b;
		}
		boolean negative = (a < 0) ^ (b < 0); // XOR operator determines sign of the product
		
		int m = 0;
		int baseToM = (int) Math.pow(base, m);
		/* determine the exponent of the base to be used */
		while(baseToM <= x && baseToM <= y){
			baseToM = (int) Math.pow(base, ++m);
		}
		baseToM = (int) Math.pow(base, --m);
		int x0 = x % baseToM;
		int y0 = y % baseToM;
		int x1 = x / baseToM;
		int y1 = y / baseToM;
		int z0 = karatsuba(base, x0, y0);
		int z2 = karatsuba(base, x1, y1);
		int z1 = karatsuba(base, (x1 + x0), (y1 + y0)) - z2 - z0;
		int abs = karatsuba(base, z2, (int) Math.pow(baseToM, 2)) + karatsuba(base, z1,  baseToM) + z0;
		return negative ? 0 - abs: abs;
	}
	
	public static void main(String [] args){
		int x = 3532, y = -35;
		System.out.println(karatsuba(10, x, y));
	}
}
