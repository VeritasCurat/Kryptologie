
public class AffineChiffre {
	
	static Restklassen Restklassenrechner = new Restklassen(81);
	
	static int[] key_en = new int[2];
	static int[] key_de = new int[2];
	static int restklasse = 26;;
	
	private static final char[] Alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

	
	public AffineChiffre(int q, int k, int modul){
		key_en[0] = q;
		key_de[1] = k;
		berechneDechiffrierungKey();
		restklasse = modul;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();

	}
	
	private static void test(){
		String klartext = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		setzeEnchiffrierungKey(9, 2);
		System.out.println(dechifrieren(enchifrieren(klartext)));
	}
	
	public static void setzeEnchiffrierungKey(int q, int k){
		key_en[0]=q; key_en[1]=k;
		berechneDechiffrierungKey();
			//System.out.println(key_de[0]+ " "+ key_de[1]);
	}
	
	public static void berechneDechiffrierungKey(){
		key_de[0] = Restklassenrechner.inverses(key_en[0], restklasse);
		key_de[1] = key_en[1];
	}
	
	public static String enchifrieren(String eingabe){
		String ausgabe = new String();
		for(int i=0; i<eingabe.length(); i++){
			//suche Position im Alphabet
			int pos;
			for(pos=0; pos<26; pos++){
				if(eingabe.charAt(i) == Alphabet[pos])break;
			}
			pos = (pos*key_en[0] + key_en[1]) % restklasse;
			ausgabe += Alphabet[pos];
		}
		return ausgabe;
	}
	
	public static String dechifrieren(String eingabe){
		String ausgabe = new String();
		for(int i=0; i<eingabe.length(); i++){
			//suche Position im Alphabet
			int pos;
			for(pos=0; pos<26; pos++){
				if(eingabe.charAt(i) == Alphabet[pos])break;
			}
				//System.out.print(pos + "<= "+ key_de[0]+ "*( "+ pos + "- " + key_de[1]+")) % "+restklasse);
			pos = (key_de[0]*(pos - key_de[1])) % restklasse;
			while(pos < 0)pos+=restklasse;
			ausgabe += Alphabet[pos];
				//System.out.println("= "+pos +" / "+ ausgabe.charAt(i));
		}
		return ausgabe;
	}

}
