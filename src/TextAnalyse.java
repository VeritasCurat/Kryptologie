import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.attribute.Size2DSyntax;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import de.danielnaber.jwordsplitter.*;



public class TextAnalyse {
	
	static Dictionary dictionary;

	public static void main(String[] args) throws IOException {
		dictionary = new Dictionary();
		// TODO Auto-generated method stub
		//test();
		//textTest("lookupintheairitsabirditsaplaneitssuperman");
		//blockchiffre_bigramme("IHEHR BWEAN RNEII NRKEU ELNZK RXTAE VLOTR ENGIE", 5, "de");
		//blockchiffre_allePermutationen("HSSIT OIENT THEHS AOTRE TSEHF RTEET", 5);;
		
		//vigenere("lookupintheairitsabirditsaplaneitssuperman");
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
	
	
	
	
	/**
	 * versucht ueber google ergebnisse sinnvolle woerter zu finden. double gibt den prozentualen anteil von woertern im text an
	 * @param x 
	 * @throws IOException 
	 */
	static int textTest_en(String txt) throws IOException {
		//System.out.println(txt);
		int h=0;
		
		int i=0; int j = 1;
		while(i < txt.length()-1 && j <txt.length()) {
				String sub0 = txt.substring(i, j);
				String sub1 = txt.substring(i, j+1);
				//System.out.println(sub0 + " " +sub1);
				if(dictionary.contains(sub0) && !dictionary.contains(sub1)) {
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
	
	
	
	
	
	
	
	
	
	

}
	