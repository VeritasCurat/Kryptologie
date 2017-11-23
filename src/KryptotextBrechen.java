import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.Size2DSyntax;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.danielnaber.jwordsplitter.*;

 class permutation {
    public static int factorial(int x) {
            int f = 1;
            while (x > 1) {
                    f = f * x;
                    x--;
            }
            return f;
    }

    public static ArrayList<Integer> permute(ArrayList<Integer> list, int iteration) {
            if (list.size() <= 1) return list;
            int fact = factorial(list.size() - 1);
            int first = iteration / fact;
            ArrayList<Integer> copy = new ArrayList<Integer>(list);
            Integer head = copy.remove(first);
            int remainder = iteration % fact;
            ArrayList<Integer> tail = permute(copy, remainder);
            tail.add(0, head);
            return tail;
    }

    
    public static ArrayList<ArrayList<Integer>> permutation(ArrayList<Integer> arr){
    	ArrayList<ArrayList<Integer>> ret = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < factorial(arr.size()); i++) {
        	ret.add(permute(arr, i));
        }
        return ret;
    }
    
}

public class KryptotextBrechen {
	
	static Dictionary dictionary;;
	
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	static char[] haeufigBuchstaben_de = "enisratdhulcgmobwfkzpvjyxq".toCharArray();
	static char[] haeufigBuchstaben_en = "etaoinshrdlcumwfgypbvkjxqz".toCharArray();
	
	static String[] haeuf_Bi_de_top20 = {"ER", "EN" ,"CH" ,"DE", "EI", "ND", "TE" ,"IN", "IE", "GE", "ES", "NE", "UN", "ST", "RE", "HE", "AN", "BE", "SE", "NG", "DI", "SC"};
	static String[] haeuf_Bi_en_top20= {"TH", "HE", "AN","IN",  "ER",  "RE" , "ON" , "ES", "TI",  "AT", "ST", "EN", "OR", "ND", "TO", "NT", "ED", "IS", "AR", "OU", "OF", "TE"};
	
	private static int index_alphabet(char x) {
		for(int i=0; i<alphabet.length; i++)if(x == alphabet[i])return i;
		return -1;
	}
	
	private static boolean contains(String suche, String[] arr) {
		for(String x: arr)if(x == suche)return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		dictionary = new Dictionary();
		// TODO Auto-generated method stub
		//test();
		//textTest("lookupintheairitsabirditsaplaneitssuperman");
		//blockchiffre_bigramme("IHEHR BWEAN RNEII NRKEU ELNZK RXTAE VLOTR ENGIE", 5, "de");
		//blockchiffre_allePermutationen("HSSIT OIENT THEHS AOTRE TSEHF RTEET", 5);;
		
		vigenere("lookupintheairitsabirditsaplaneitssuperman");
		//ArrayList<Integer> a = new ArrayList<Integer>(); a.add(12); a.add(32); a.add(20); System.out.println("ende: "+ggT(a));
	}
	
	
	public static void test() throws IOException {
		//buchstabenhauefigkeit testen
			String bsp_txt_de = "Ein Alphabet fruehneuhochdeutsch von kirchenlateinisch alphabetum von altgriechisch ist die Gesamtheit der kleinsten Schriftzeichen bzw Buchstaben einer Sprache oder mehrerer Sprachen in einer festgelegten Reihenfolge Die Buchstaben koennen ueber orthographische Regeln zu Woertern verknuepft werden und damit die Sprache schriftlich darstellen"
					+ "Die im Alphabet festgelegte Reihenfolge der Buchstaben erlaubt die alphabetische Sortierung von Woertern und Namen beispielsweise in Woerterbuechern Nach einigen Definitionen ist mit Alphabet nicht der Buchstabenbestand in seiner festgelegten Reihenfolge gemeint sondern die Reihenfolge selbst"
					+"Die Bezeichnung Alphabet geht auf die ersten beiden Buchstaben des griechischen Alphabets zurueck Ausgehend von den ersten drei Buchstaben des deutschen Alphabets des lateinischen Alphabets sagt man auch Abc die Schreibweise Abece verdeutlicht die Aussprache wird aber selten verwendet";          

			String bsp_txt_en = "Far far away behind the word mountains far from the countries Vokalia and Consonantia there live the blind texts Separated they live in Bookmarksgrove right at the coast of the Semantics a large language ocean A small river named Duden flows by their place and supplies it with the necessary regelialia It is a paradisematic country in which roasted parts of sentences fly into your mouth Even the allpowerful Pointing has no control about the blind texts it is an almost unorthographic life One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar The Big Oxmox advised her not to do so because there were thousands of bad Commas wild Question Marks and devious Semikoli but the Little Blind Text didnt listen She packed her seven versalia put her initial into the belt and made herself on the way When she reached the first hills of the Italic Mountains she had a last view back on the skyline of her hometown Bookmarksgrove the headline of Alphabet Village and the subline of her own road the Line ";
//			char[] bsp = new char[26];
//			bsp = Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(bsp_txt));
//			for(char x: bsp)System.out.println(x);
		//breche Additive Chiffre
			//System.out.println(additiveChiffre(AdditiveChiffre.verschluessele("lookupintheairitsabirditsaplaneitssuperman", 22),"en"));
	}
	
	public static int ggT(int a, int b) {
		if(a == b)return a;
		else if(a < b)return ggT(a, b-a);
		else return ggT(a-b, b);
	}
	
	public static int ggT(ArrayList<Integer> input) {
		//kopiere alle zueinander verschiedenen werte
			ArrayList<Integer> can = new ArrayList<Integer>();
			for(int i: input) {
				if(!can.contains(i))can.add(i);
			}
			
		while(can.size() > 1) {
			int a = can.get(0); can.remove(0);
			int b = can.get(0); can.remove(0);
			can.add(ggT(a,b)); 
            
            for(int i: can) System.out.print(i+" ");
            System.out.println();
		}
		return can.get(0);
	}
	
	public static void vigenere(String kryptotext) {
		ArrayList<Integer> abstaende = new ArrayList<Integer>();
		//finde periode d 
			//finde alle substring der laenge k
		 	for(int laenge=2; laenge<kryptotext.length(); laenge++) {
		 		int anfang = 0;
		 		int ende = anfang+laenge;
		 		while(anfang<kryptotext.length() && ende<kryptotext.length()) {
		 			String wort = kryptotext.substring(anfang, ende);
		 			int abstand = kryptotext.lastIndexOf(wort) - kryptotext.indexOf(wort);
		 			if(abstand > 0) {
		 				abstaende.add(abstand);
			 			System.out.println(wort + " " + abstand);
		 			}
		 			anfang++; ende++;
		 		}
		 	}
		 
		 //finden gemeinsame teiler
		 System.out.println("ggT: "+ggT(abstaende));
	}
	
	/**
	 * versucht ueber google ergebnisse sinnvolle woerter zu finden. double gibt den prozentualen anteil von woertern im text an
	 * @param x 
	 * @throws IOException 
	 */
	private static int textTest_en(String txt) throws IOException {
		//System.out.println(txt);
		int h=0;
		
		int i=0; int j = 0;
		while(i < txt.length() && j <txt.length()) {
				//System.out.println(txt.substring(i,j));
				if(dictionary.contains(txt.substring(i, j)) && !dictionary.contains(txt.substring(i,j+1))) {
					System.out.println(txt.substring(i,j));
					h+=(j-i)*(j-i);
					i=j;
					continue;
				}
				j++;			
		}
		//System.out.println(woertner);
		//System.out.println((double) woerter.length() / txt.length());
		return h;
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
				haeufigkeiten[nr] = alphabet[max_index];
				haeufigkeitenBuchstaben[max_index] = Integer.MIN_VALUE;
				nr++;
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
	
	private static int kongruenzgleichung(int a, int m, int b) {
		for(int i=0;;i++) {
			if(a*i % m == b)return i;
		}
	}
	
	public static String affineChiffre(String kryptotext, String sprache) {
		int b=0;
		int c=0;
		if(sprache.contains("en")) {
			int abst_1_2_alp = Math.abs(index_alphabet(haeufigBuchstaben_en[0]) - index_alphabet(haeufigBuchstaben_en[1]));
			int abst_1_2_txt = Math.abs(Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0] - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[1]); 
			b = kongruenzgleichung(abst_1_2_alp, 26, abst_1_2_txt);
			c = Math.abs(index_alphabet(alphabet[b*index_alphabet(haeufigBuchstaben_en[0])]) - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0]);
		}
		if(sprache.contains("de")) {
			int abst_1_2_alp = Math.abs(index_alphabet(haeufigBuchstaben_de[0]) - index_alphabet(haeufigBuchstaben_de[1]));
			int abst_1_2_txt = Math.abs(Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0] - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[1]); 
			b = kongruenzgleichung(abst_1_2_alp, 26, abst_1_2_txt);
			c = Math.abs(index_alphabet(alphabet[b*index_alphabet(haeufigBuchstaben_en[0])]) - Buchstabenhaeufigkeiten_rang(haeufigkeitenBuchstaben(kryptotext))[0]);
		}
		AffineChiffre af = new AffineChiffre(b, c, 26);
		System.out.println(af.dechifrieren(kryptotext));
		return "";
	}
	
	public static String additiveChiffre(String kryptotext, String sprache) throws IOException {
		System.out.println("krypto: "+kryptotext);
		int max_value=0; int max_index=0;
		if(kryptotext.length() < 200) {
			String klartext="";
			for(int i=0; i<26; i++){
				for(int l=0; l<kryptotext.length(); l++){
					klartext += AdditiveChiffre.addition(kryptotext.charAt(l), i);
				}
				int d = textTest_en(klartext);
					System.out.println(klartext + " " +d);
				if(d > max_value) {
					max_value = d;
					max_index = i;
				}
				klartext="";
			}
			klartext = "";
			for(int l=0; l<kryptotext.length(); l++){
				klartext += AdditiveChiffre.addition(kryptotext.charAt(l), max_value);
			}
			System.out.println(klartext + " " +max_value);
		}
		if(kryptotext.length() >= 200) {//hier gilt die Verteilungsannahme
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
	
	public static void blockchiffre_allePermutationen(String kryptotext, int blocklaenge) throws IOException {
		//einteilen in bloecke
		String[] bloecke = new String[kryptotext.length()/blocklaenge];
		if(kryptotext.contains(" "))bloecke = kryptotext.split(" ");
		else {
			for(int i=0; i<kryptotext.length(); i++) {
				bloecke[(int) i/blocklaenge] += kryptotext.charAt(i);
			}
		}
		ArrayList<Integer> origin = new ArrayList<Integer>();for(int i=0;i<blocklaenge; i++)origin.add(i);
		ArrayList<ArrayList<Integer>> perm = permutation.permutation(origin);
			//for(ArrayList<Integer> x: perm)System.out.println(x);
		
		String permutation_txt ="";
		//permuatation des textes darstellen
		kryptotext = kryptotext.replace(" ", "");
		if(kryptotext.length() % blocklaenge != 0)System.err.println("falsche blocklaenge!");
		
		ArrayList<Integer> suche = new ArrayList<Integer>(); suche.add(3); suche.add(0); suche.add(2); suche.add(4); suche.add(1);
		for(ArrayList<Integer> p: perm) {
			//if(p != suche)continue;
			permutation_txt = "";
			System.out.println(p);
			for(int i=0;i<kryptotext.length(); i++) {
				int k=((int) i/bloecke[0].length())*bloecke[0].length() + p.get((i%bloecke[0].length()));
				permutation_txt +=  Character.toString(kryptotext.charAt(k));
				//if(i>=bloecke[0].length() && i % bloecke[0].length()==0)permutation_txt+=" ";
			}
			System.out.println(permutation_txt);
			System.out.println(textTest_en(permutation_txt));
		}
		
	}
	
	public static String blockchiffre_bigramme(String kryptotext, int blocklaenge, String sprache) {

		kryptotext = kryptotext.toUpperCase();
		ArrayList<Integer> schluessel = new ArrayList<Integer>(); schluessel.add(0);
		ArrayList<Integer> kandidaten = new ArrayList<Integer>(); for(int i=1; i<blocklaenge; i++)kandidaten.add(i);

		//if(kryptotext.length() % blocklaenge != 0)return "FEHLER: Blockkaenge stimmt nicht!";
		//einteilen in bloecke
			String[] bloecke = new String[kryptotext.length()/blocklaenge];
			if(kryptotext.contains(" "))bloecke = kryptotext.split(" ");
			else {
				for(int i=0; i<kryptotext.length(); i++) {
					bloecke[(int) i/blocklaenge] += kryptotext.charAt(i);
				}
			}
		//schluessel bestimmen - fange mit spalte 1 an
			while(schluessel.size() < blocklaenge) {
				//trenne schluessel(spalten) - kandidaten(spalten) - muessen disjunkt sein 
				for(int i: schluessel)kandidaten.remove(schluessel.get(i));
				
				//bestimme eine vorgaenger oder nachfolgerspalte
				int anz_bi_gramme=0;
				int anz_bi_gramme_max = 0;
				int kandidat_index[] = new int[2]; 
				int kandidat_index_max[] = new int[2]; 
				String kandidat_value = "";
				//gehe alle kandidaten_moglichkeiten_durch
					//bigramme der form schluessel[n]kandidaten[i]
					for(int i=0; i<kandidaten.size(); i++) {
						anz_bi_gramme = 0; 
						kandidat_index[0] = schluessel.get(schluessel.size()-1); kandidat_index[1] = kandidaten.get(i);
						for(int j=0; j<bloecke.length; j++) {
							kandidat_value = "";
							kandidat_value += bloecke[j].substring(kandidat_index[0], kandidat_index[0]+1) + bloecke[j].substring(kandidat_index[1], kandidat_index[1]+1); 
							System.out.println(kandidat_value+" "+contains(kandidat_value, haeuf_Bi_de_top20));

							if(sprache.contains("de") && contains(kandidat_value, haeuf_Bi_de_top20))anz_bi_gramme++;
							if(sprache.contains("en") && contains(kandidat_value, haeuf_Bi_en_top20))anz_bi_gramme++;	
						}
						if(anz_bi_gramme > anz_bi_gramme_max) {
							anz_bi_gramme_max = anz_bi_gramme;
							kandidat_index_max = kandidat_index;
						}
					}
					//bigramme der form kandidaten[i]schluessel[1]
					for(int i=0; i<kandidaten.size(); i++) {
						anz_bi_gramme = 0; 
						kandidat_index[0] = kandidaten.get(i); kandidat_index[1] = schluessel.get(0);
						for(int j=0; j<bloecke.length; j++) {
							kandidat_value = "";
							kandidat_value += Character.toString(bloecke[j].charAt(kandidat_index[0])) + Character.toString(bloecke[j].charAt(kandidat_index[1]));
							if(sprache.contains("de") && contains(kandidat_value, haeuf_Bi_de_top20)) {
								anz_bi_gramme++;
								System.out.println(kandidat_value);
							}
							if(sprache.contains("en") && contains(kandidat_value, haeuf_Bi_en_top20)) {
								anz_bi_gramme++;	
								System.out.println(kandidat_value);
							}
						}
						if(anz_bi_gramme > anz_bi_gramme_max) {
							anz_bi_gramme_max = anz_bi_gramme;
							kandidat_index_max = kandidat_index;
						}
					}
					System.out.println(kandidat_index_max[0] + " "+kandidat_index_max[1]);
					
				//kandidatenspalte abziehen und zum schluessel hinzufuegen
				if(kandidat_index_max[1] == schluessel.get(0)) {
					//kandidat vorne anfuegen
					for(int i=1; i<schluessel.size();i++)schluessel.set(i, schluessel.get(i+1));
					schluessel.set(0, kandidat_index_max[1]);
				}
				if(kandidat_index_max[0] == schluessel.get(schluessel.size())) {
					schluessel.add(kandidat_index_max[1]);
				}
			}
		String rueckgabe="";
		kryptotext = kryptotext.replace(" ", "");
		for(int i=0; i<bloecke.length; i++) {
			rueckgabe +=  Character.toString(kryptotext.charAt(i/bloecke.length + schluessel.get(i%bloecke.length)));
		}
		
		return rueckgabe;	
	}

}
	