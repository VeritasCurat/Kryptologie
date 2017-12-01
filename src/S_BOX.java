import java.util.HashMap;

public class S_BOX {
	
	static HashMap<Integer, Integer> s_value = new HashMap<Integer, Integer>();

	public static void main(String[] args) {
		//A=10, B=11, C=12, D=13, E=14, F=15
		//fill HS
		
		//S-Box aus Vorlesung:
//			s_value.put(0, 14);
//			s_value.put(1, 4);
//			s_value.put(2, 13);
//			s_value.put(3, 1);
//			
//			s_value.put(4, 2);
//			s_value.put(5, 15);
//			s_value.put(6, 11);
//			s_value.put(7, 8);
//			
//			s_value.put(8, 3);
//			s_value.put(9, 10);	
//			s_value.put(10, 6);
//			s_value.put(11, 12);
//			
//			s_value.put(12, 5);
//			s_value.put(13, 9);
//			s_value.put(14,0);
//			s_value.put(15, 7);
		
		//für Aufgabe 33a entkommentieren 
		
			s_value.put(0, 8);
			s_value.put(1, 4);
			s_value.put(2, 2);
			s_value.put(3, 1);
			
			s_value.put(4, 12);
			s_value.put(5, 6);
			s_value.put(6, 3);
			s_value.put(7, 13);
			
			s_value.put(8, 10);
			s_value.put(9, 5);	
			s_value.put(10, 14);
			s_value.put(11, 7);
			
			s_value.put(12, 15);
			s_value.put(13, 11);
			s_value.put(14, 9);
			s_value.put(15, 0);
		
		
		
			//System.out.println("(3,9): "+get_L_a_b(2, 9, 4));
			printLAB_field(get_all_L_a_b_combination(4));
	}
	
	
	private static void printLAB_field(int[][] L_a_b) {
		int length = L_a_b.length;
		System.out.print("a|b ");
		for(int i=0; i<L_a_b.length;i++)System.out.print(i+"  ");
		System.out.println();
		for(int zeile=0; zeile<length; zeile++) {
			for(int spalte=0; spalte<length; spalte++) {
				//System.out.println(spalte);
				if(spalte == 0) {
					System.out.print(zeile+ "  ");
					if(zeile < 10 )System.out.print(" ");
					System.out.print(L_a_b[zeile][spalte]);
					if(L_a_b[zeile][spalte]<10)System.out.print(" ");

				System.out.print(" ");
				}
				else {
					System.out.print(L_a_b[zeile][spalte]+" ");
					if(spalte>10)System.out.print(" ");
					if(spalte==10)System.out.print(" ");
					if(L_a_b[zeile][spalte] <10)System.out.print(" ");
				}
			}
			System.out.println();
			
		}
	}
	
	private static int[][] get_all_L_a_b_combination(int m) {
		int[][] LAB_feld= new int[(int) Math.pow(2, m)][(int) Math.pow(2, m)];
		
		for(int a=0; a<(int) Math.pow(2, m); a++) {
			for(int b=0; b<(int) Math.pow(2, m); b++) {
				LAB_feld[a][b] = get_L_a_b(a, b, m);
			}
		}
		
	return LAB_feld;
	}
	
	private static int zehnerpotenz(int a) {
		if(a < 0)a*=-1;
		int z=0;
		while(a>0) {
			a/=10;
			z++;
		}
		return z;
	}
	
	private static int return_n_digit(int a, int n, int z) {
		int zehnerpotenz = z;
		int div = (int) Math.pow(10, zehnerpotenz-n);
		return a/div % 10;
	} 
	
	private static int sum_mul_digit(int a, int b, int length) {
		int sum=0;
		for(int stelle=1; stelle<=length;stelle++) {
			int stelle_a= return_n_digit(a, stelle, length);
			int stelle_b= return_n_digit(b, stelle, length);	
			sum+=stelle_a*stelle_b;
		}
		return sum;
	}
	
	private static int get_L_a_b(int a, int b, int m) {
		int a_bin =  Integer.valueOf(Integer.toBinaryString(a));
		int b_bin =  Integer.valueOf(Integer.toBinaryString(b));
		int L_a_b = 0;
		for(int x=0; x<(int) Math.pow(2, m); x++) {
			int x_bin = Integer.valueOf(Integer.toBinaryString(x));
			int y_bin = Integer.valueOf(Integer.toBinaryString(s_value.get(x)));
			
			int sum_x = 0; int sum_y = 0;

			sum_x += sum_mul_digit(a_bin, x_bin, m);
			sum_y += sum_mul_digit(b_bin, y_bin, m);

			sum_x = sum_x %2;	sum_y = sum_y %2;
			//System.out.println(a_bin+" "+x_bin+"= "+sum_x +", "+ b_bin+" "+y_bin+"= "+sum_y);

			if(sum_x == sum_y)L_a_b++;
		}
		
		return L_a_b;
	}
	
	private static HashMap<Integer, Integer> convert_HS_to_Binary(HashMap<Integer, Integer> eingabe) {
		for(Integer i: eingabe.values()) {
			eingabe.replace(i, Integer.valueOf(Integer.toBinaryString(i)));
		}
		return eingabe;
	}

}
