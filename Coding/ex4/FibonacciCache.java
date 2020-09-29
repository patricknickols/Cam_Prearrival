package uk.ac.cam.pn330.prejava.ex4;

public class FibonacciCache {
	
	public static long[] fib = null;
	
	public static void store() throws Exception {
		try{
			long penultimate = 1;
			long penpenultimate = 0;
			long current = 0;
			for(int j = 0; j < fib.length; j++){
				current = penpenultimate + penultimate;
				fib[j] = penultimate;
				penpenultimate = penultimate;
				penultimate = current;
			}
		} catch (NullPointerException nullFib) {
			throw new Exception("Null pointer, probably because fib was never initialised.");
		}
	}
	
	
	public static void reset(int cachesize) {
		fib = new long[cachesize];
		for(int j = 0; j < cachesize; j++) {
			fib[j] = 0;
		} 
	}
	
	public static long get(int i) throws Exception {
		try {
			return fib[i];
		} catch (NullPointerException nullFib){
			throw new Exception("Null pointer, probably because fib was never initialised.");
		} catch (ArrayIndexOutOfBoundsException toobig) {
			throw new Exception("That index is too large, it is not in the array.");
		}
	}
	
	public static void main(String[] args) throws Exception {
		int i = 0;
		reset(20);
		store();
		try {
			i = Integer.decode(args [0]);
		} catch (ArrayIndexOutOfBoundsException nothingPut){
			System.out.println("You must put what index you would like to access.");
		} catch (NumberFormatException nan) {
			System.out.println("That isn't a number I can recognise. Please try again, entering the digits.");
		}
		System.out.println(get(i));
	}
}
		