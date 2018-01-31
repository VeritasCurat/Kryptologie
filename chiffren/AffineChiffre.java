package chiffren;
import java.util.HashMap;
import java.util.Set;

public class AffineChiffre {
	
	static Restklassen Restklassenrechner = new Restklassen(81);
	
	static int[] key_en = new int[2];
	static int[] key_de = new int[2];
	static int restklasse = 26;;
	
	private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
	static String haeufigBuchstaben_de = "enisratdhulcgmobwfkzpvjyxq";
	static String haeufigBuchstaben_en = "etaoinshrdlcumwfgypbvkjxqz";

	private static int index_alphabet(char x) {
		if(alphabet.contains(Character.toString(x).toLowerCase()))
			return alphabet.indexOf(Character.toString(x).toLowerCase());
		return -1;
	}
	
	public static String StringToHaufBuchString(String x) {
		String ret="";
		while(x.length() > 0) {
			char max = findMaxChar(x);
			ret += max;
			x=x.replaceAll(Character.toString(max), "");
		}
		return ret;
	}

	private static char findMaxChar(String str) {
	    char[] array = str.toCharArray();
	    int maxCount = 1;
	    char maxChar = array[0];
	    for(int i = 0, j = 0; i < str.length() - 1; i = j){
	        int count = 1;
	        while (++j < str.length() && array[i] == array[j]) {
	            count++;
	        }
	        if (count > maxCount) {
	            maxCount = count;
	            maxChar = array[i];
	        }
	    }
	    return (maxChar);
	}
	
	public static int ggT(int a, int b) {
		if(a== 0 || a == b)return a;
		else if(a < b)return ggT(a, b-a);
		else return ggT(a-b, b);
	}

	private static int kongruenzgleichung(int a, int m, int b) {
		if(a==b)return 1;
		if(a != 0 && ggT(a,m) % b == 0 ) { //l�sbar
			for(int i=0;i<a;i++) {
				if(a*i % m == b)return i;
			}
		}
		else {System.err.println("kongruenzgleichung nicht l�sbar: "+a+"*x ="+ b+ "%"+m);System.exit(-1);}
		return -1;
	}
	
	public AffineChiffre(int q, int k, int modul){
		key_en[0] = q;
		key_en[1] = k;
		berechneDechiffrierungKey();
		restklasse = modul;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();

	}
	
	private static void test(){
		AffineChiffre ac = new AffineChiffre(3, 2, 26);
		System.out.println(ac.breche_affineChiffre("laoeaehoaphwvaeixobgjcbhothloblokheixopevbcixockixqoppoboapomohqceuogkopehojhkpleappjseobeixoapopmcu", "de"));
	}
	
	public void setzeEnchiffrierungKey(int q, int k){
		key_en[0]=q; key_en[1]=k;
		berechneDechiffrierungKey();
			//System.out.println(key_de[0]+ " "+ key_de[1]);
	}
	
	public void berechneDechiffrierungKey(){
		key_de[0] = Restklassenrechner.inverses(key_en[0], restklasse);
		key_de[1] = key_en[1];
	}
	
	public String enchifrieren(String eingabe){
		String ausgabe = new String();
		for(int i=0; i<eingabe.length(); i++){
			//suche Position im Alphabet
			int pos = index_alphabet(eingabe.charAt(i));
			pos = ((pos*key_en[0] + key_en[1])+restklasse)% restklasse;
			ausgabe += alphabet.toCharArray()[pos];
		}
		return ausgabe;
	}
	
	public  String dechifrieren(String eingabe){
		String ausgabe = new String();
		for(int i=0; i<eingabe.length(); i++){
			int pos = alphabet.indexOf(eingabe.charAt(i));
			pos = (key_de[0]*(pos - key_de[1])) % restklasse;
			while(pos < 0)pos+=restklasse;
			ausgabe += alphabet.toCharArray()[pos];
		}
		return ausgabe;
	}
	
	//TODO: vervollstaendigen
	public static String breche_affineChiffre(String kryptotext, String sprache) {
		int b=0;
		int c=0;
		String haufBuchString = StringToHaufBuchString(kryptotext);
			//System.out.println(haufBuchString);
		if(sprache.contains("en")) {
			int abst_1_2_alp = Math.abs(index_alphabet(haeufigBuchstaben_en.toCharArray()[0]) - index_alphabet(haeufigBuchstaben_en.toCharArray()[1]));
			int abst_1_2_txt = Math.abs(index_alphabet(haufBuchString.charAt(0)) - index_alphabet(haufBuchString.charAt(1))); 
			b = kongruenzgleichung(abst_1_2_alp, 26, abst_1_2_txt);
			c = Math.abs(alphabet.charAt(b*index_alphabet(haeufigBuchstaben_en.toCharArray()[0])%26) - alphabet.indexOf(haufBuchString.charAt(0)));
		}
		if(sprache.contains("de")) {
			int abst_1_2_alp = Math.abs(index_alphabet(haeufigBuchstaben_de.toCharArray()[1]) - index_alphabet(haeufigBuchstaben_de.toCharArray()[0]));
			int abst_1_2_txt = Math.abs(index_alphabet(haufBuchString.charAt(1)) - index_alphabet(haufBuchString.charAt(0))); 
			b = kongruenzgleichung(abst_1_2_alp, 26, abst_1_2_txt);
			
			int trans_char = (b*index_alphabet(haeufigBuchstaben_de.toCharArray()[1]))%26;
			c = alphabet.indexOf(haufBuchString.charAt(0)) - (trans_char);
		}
			//System.out.println(b+" "+c);
		AffineChiffre af = new AffineChiffre(b, c, 26);
			//System.out.println(af.key_de[0]+" "+af.key_de[1]);
		return af.dechifrieren(kryptotext);
	}
}
