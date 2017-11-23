import java.io.IOException;

public class AdditiveChiffre {
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static void main(String[] args) throws IOException {
		System.out.println(breche_additiveChiffre("BEEAKFYDJXUQYHYJIQRYHTYJIQFBQDUYJIIKFUHCQD".toLowerCase(),"en"));
	}
	
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

	
	public static String verschluessele(String x, int i){
		x = x.toLowerCase().replaceAll(" ", "");
		//System.out.println(x);
		String y = new String("");
			for(int l=0; l<x.length(); l++){
				y += addition(x.charAt(l), i);
			}
		return y;
	}
	
	static void zeigeAlleAdditionen(String x){
		for(int i=0; i<26; i++){
			System.out.print(i+ ": ");
			for(int l=0; l<x.length(); l++){
				System.out.print(addition(x.charAt(l), i));
			}
			System.out.println("");
		}
	}
	
	static char addition(char c, int i){
		int k = 0;
		while(c != alphabet[k]){
			k++;
			if(k>=26)System.err.println(c+" ist kein Zeichen des lateinischen Alphabets!");
		}
		return alphabet[(k+i)%26];
	}
	

	
	
	
	public static String breche_additiveChiffre(String kryptotext, String sprache) throws IOException {
		//System.out.println("krypto: "+kryptotext);
		int max_value=0; int max_index=0;
		
		//hier gilt die Verteilungsannahme wahrscheinlich nicht -> gesamten loesungsraum durchsuchen
		if(kryptotext.length() < 100) {
			TextAnalyse.dictionary = new Dictionary();
			String klartext="";
			for(int i=1; i<26; i++){ //berechne alle Additionen der Texte
				for(int l=0; l<kryptotext.length(); l++){
					klartext += AdditiveChiffre.addition(kryptotext.charAt(l), i);
				}
				
				int d = TextAnalyse.textTest_en(klartext);
				if(d > max_value) {
					max_value = d;
					max_index = i;
				}
				//System.out.println(klartext + " " + d + "MAX: "+max_index);
				klartext="";

			}
			klartext = "";
			
			for(int l=0; l<kryptotext.length(); l++){
				klartext += AdditiveChiffre.addition(kryptotext.charAt(l), max_index);
			}
			return klartext;
		}
		
		
		if(kryptotext.length() >= 100) {
			//finde den schlüssel
			char hauf_kry = Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0];
			char hauf_eng = haeufigBuchstaben_en[0];
			int k = (hauf_kry - hauf_eng +26) % 26;
				System.out.println(hauf_kry+" "+hauf_eng+" "+k);
			char[] klartext = new char[kryptotext.length()];
			for(int i=0; i<kryptotext.length(); i++) {
				klartext[i] = AdditiveChiffre.addition(kryptotext.charAt(i), 26-k);
			}
			String rueckgabe="";
			for(char x: klartext)rueckgabe+=x;
			return rueckgabe;			
		}
		return "";
	}
}
