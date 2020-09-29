package uk.ac.cam.pn330.prejava.ex2;

public class PackedLong {
	public static boolean get(long packed, int position){
		long check = (packed >>> position) & 1;
		return (check == 1);
	}
	public static long set(long packed, int position, boolean value){
		long compare = 1L << position;
		if (value) {
			packed = packed | compare;
		}
		else {
			packed = packed & ~ compare;
		}
		return packed;
	}
}
