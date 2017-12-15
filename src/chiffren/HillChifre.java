package chiffren;

public class HillChifre {

	static Restklassen Restklassenrechner = new Restklassen(81);
	static char[] Alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	
	static double[][] key_en = {{11, 13, 8, 21},
							    {24, 17, 3, 25},
							    {18, 12, 23, 17},
							    {6, 15, 2, 15}};
	static double[][] key_de;

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
		
	}
	
	private static void test(){
		berechneDechiffrierungKey();
		//System.out.println(testDechiffrierungKey());
		//Restklassen.printMatrix(key_de);
		//System.out.println(enchiffrieren("HILL"));
		//System.out.println(dechiffrieren("NERX"));
	}
	
	public static void setzeEnchiffrierungKey(double[][] key){
		key_en = key;
		berechneDechiffrierungKey();
	}
	
	private static boolean testDechiffrierungKey(){
		Restklassenrechner.printMatrix(key_en);
		System.out.println("\n");
		Restklassenrechner.printMatrix(key_de);
		System.out.println("\n");
		
		double[][] neutral = Restklassenrechner.matrixmultiplikation(key_en,key_de);
		Restklassenrechner.printMatrix(neutral);
		for(int i=0; i<neutral.length; i++){
			if(Math.round(neutral[i][i]) != 1) return false;
		}
		return true;
	}
	
	
	private static void berechneDechiffrierungKey(){
		key_de = Restklassen.inverseMatrix(key_en);
	}
	
	public static String enchiffrieren(String eingabe){
		Restklassenrechner.n = 26;
			if(eingabe.length()!=key_en.length)System.err.println("Eingabestring kann nur Blockweise enchiffriert werden!");
		//berechne "Positionsmatrix"
			double[][] positionen = new double [1][eingabe.length()];
			for(int i=0; i<eingabe.length(); i++){
				int pos=0;
				for(pos=0; pos<Alphabet.length; pos++){
					if(eingabe.charAt(i) == Alphabet[pos])break;
				}
				positionen[0][i] = (double) pos;
			}			
		//multipliziere die Matrizen
			double[][] chiffre = Restklassen.matrixmultiplikation(positionen, key_en);
		//erstelle Verschluesserlungsstring
			String ausgabe ="";
			for(int i=0; i<eingabe.length();i++){
				ausgabe += Alphabet[(int) chiffre[0][i]];
			}
		return ausgabe;
	}
	
	public static String dechiffrieren(String eingabe){
		Restklassenrechner.n = 26;
			if(eingabe.length()!=key_en.length)System.err.println("Eingabestring kann nur Blockweise enchiffriert werden!");
		//berechne "Positionsmatrix"
			double[][] positionen = new double [1][eingabe.length()];
			for(int i=0; i<eingabe.length(); i++){
				int pos=0;
				for(pos=0; pos<Alphabet.length; pos++){
					if(eingabe.charAt(i) == Alphabet[pos])break;
				}
				positionen[0][i] = (double) pos;
			}
		//multipliziere die Matrizen
			double[][] chiffre = Restklassen.matrixmultiplikation(positionen, key_de);
        //erstelle Verschluesserlungsstring
			String ausgabe ="";
			for(int i=0; i<eingabe.length();i++){
				ausgabe += Alphabet[(int) chiffre[0][i]];
			}
	return ausgabe;
	}

}
