
public class AffineChiffre {
	
	static Restklassen Restklassenrechner = new Restklassen(81);
	
	static int[] key_en = new int[2];
	static int[] key_de = new int[2];
	static int restklasse = 26;;
	
	static char[] haeufigBuchstaben_de = "enisratdhulcgmobwfkzpvjyxq".toCharArray();
	static char[] haeufigBuchstaben_en = "etaoinshrdlcumwfgypbvkjxqz".toCharArray();

	private static int index_alphabet(char x) {
		for(int i=0; i<Alphabet.length; i++)if(x == Alphabet[i])return i;
		return -1;
	}
	
	public static int[] haeufigkeitenBuchstaben(String text) {	
		text = text.toLowerCase();
		for(char x: text.toCharArray()) {
			if(!Character.isAlphabetic(x))text = text.replace(x, '\0');
		}

		int[] haeufigkeiten  = new int[26];
		for(char x: text.toCharArray()) {
			switch(x) {
				case 'a': haeufigkeiten[0]++; break;
				case 'b': haeufigkeiten[1]++; break;
				case 'c': haeufigkeiten[2]++; break;
				case 'd': haeufigkeiten[3]++; break;
				case 'e': haeufigkeiten[4]++; break;
				case 'f': haeufigkeiten[5]++; break;
				case 'g': haeufigkeiten[6]++; break;
				case 'h': haeufigkeiten[7]++; break;
				case 'i': haeufigkeiten[8]++; break;
				case 'j': haeufigkeiten[9]++; break;
				case 'k': haeufigkeiten[10]++; break;
				case 'l': haeufigkeiten[11]++; break;
				case 'm': haeufigkeiten[12]++; break;
				case 'n': haeufigkeiten[13]++; break;
				case 'o': haeufigkeiten[14]++; break;
				case 'p': haeufigkeiten[15]++; break;
				case 'q': haeufigkeiten[16]++; break;
				case 'r': haeufigkeiten[17]++; break;
				case 's': haeufigkeiten[18]++; break;
				case 't': haeufigkeiten[19]++; break;
				case 'u': haeufigkeiten[20]++; break;
				case 'v': haeufigkeiten[21]++; break;
				case 'w': haeufigkeiten[22]++; break;
				case 'x': haeufigkeiten[23]++; break;
				case 'y': haeufigkeiten[24]++; break;
				case 'z': haeufigkeiten[25]++; break;
			}
		}
		return haeufigkeiten;
	}
	
	private static double RangHaufigkeitsAnalyse(String text, String sprache) {
		char[] hauf_text = Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(text));
			int anz_buch_hauf_text = 0; for(char x: hauf_text)if(x!='\0')anz_buch_hauf_text++;
		int anz_richtige=0;
		for(int i=0; i<anz_buch_hauf_text; i++) {
			if(sprache.contains("en")) {
				if(hauf_text[i] == haeufigBuchstaben_en[i])anz_richtige++;
			}
			else if(sprache.contains("de")) {
				if(hauf_text[i] == haeufigBuchstaben_de[i])anz_richtige++;
			}
		}
		System.out.println(anz_richtige);
		return (double) (anz_richtige/anz_buch_hauf_text);
	}
	
	public static char[] Buchstabenhaeufigkeiten_rang(int[] haeufigkeitenBuchstaben) {
		char[] haeufigkeiten = new char[26];
		int max_index=0;
		int max_value=0;
		int nr=0;
		for(int j=0; j<26; j++) { //get all 26 haeufigkeiten
			max_value = 0;
			max_index = -1;
			for(int i=0; i<26; i++) { //find max of haeufigkeitenBuchstaben
				if(haeufigkeitenBuchstaben[i]>max_value) {
					max_value=haeufigkeitenBuchstaben[i];
					max_index = i;
				}
			}
			if(max_index < 0)haeufigkeiten[nr] = '\0';
			else {
				haeufigkeiten[nr] = Alphabet[max_index];
				haeufigkeitenBuchstaben[max_index] = Integer.MIN_VALUE;
				nr++;
			}
		}
		return haeufigkeiten;
	}
	
	private static final char[] Alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};


	private static int kongruenzgleichung(int a, int m, int b) {
		for(int i=0;;i++) {
			if(a*i % m == b)return i;
		}
	}
	
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
	
	public static String affineChiffre_brechen(String kryptotext, String sprache) {
		int b=0;
		int c=0;
		if(sprache.contains("en")) {
			int abst_1_2_alp = Math.abs(index_alphabet(haeufigBuchstaben_en[0]) - index_alphabet(haeufigBuchstaben_en[1]));
			int abst_1_2_txt = Math.abs(Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0] - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[1]); 
			b = kongruenzgleichung(abst_1_2_alp, 26, abst_1_2_txt);
			c = Math.abs(index_alphabet(Alphabet[b*index_alphabet(haeufigBuchstaben_en[0])]) - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0]);
		}
		if(sprache.contains("de")) {
			int abst_1_2_alp = Math.abs(index_alphabet(haeufigBuchstaben_de[0]) - index_alphabet(haeufigBuchstaben_de[1]));
			int abst_1_2_txt = Math.abs(Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0] - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[1]); 
			b = kongruenzgleichung(abst_1_2_alp, 26, abst_1_2_txt);
			c = Math.abs(index_alphabet(Alphabet[b*index_alphabet(haeufigBuchstaben_en[0])]) - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0]);
		}
		AffineChiffre af = new AffineChiffre(b, c, 26);
		System.out.println(af.dechifrieren(kryptotext));
		return "";
	}

}
