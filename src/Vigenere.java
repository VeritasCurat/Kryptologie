import java.util.ArrayList;

public class Vigenere {
	
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();


	
	//hilfsfunktionen
		private static int index_alphabet(char x) {
			for(int i=0; i<alphabet.length; i++)if(x == alphabet[i])return i;
			return -1;
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
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(entschluesseln("RQKAVINM", "wie"));
	}
	
	public static String entschluesseln(String kryptotext, String schluessel) {
		kryptotext = kryptotext.toLowerCase();
		String ret = "";
		for(int i=0; i<kryptotext.length(); i++) {
			int pos_k = index_alphabet(kryptotext.charAt(i));
			int pos_s = index_alphabet(schluessel.charAt(i%schluessel.length()));
			ret += alphabet[((pos_k-pos_s)+26) % 26];
		}
		return ret;
	}
	
	
	public static String verschluesseln(String klartext, String schluessel) {
		klartext = klartext.toLowerCase();
		String ret = "";
		for(int i=0; i<klartext.length(); i++) {
			int pos_k = index_alphabet(klartext.charAt(i));
			int pos_s = index_alphabet(schluessel.charAt(i%schluessel.length()));
			ret += alphabet[((pos_k-pos_s)+26) % 26];
		}
		return ret;
	}
	
	public static String vigenere_brechen(String kryptotext) {
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
		 
		 
		 return "";
	}

}
