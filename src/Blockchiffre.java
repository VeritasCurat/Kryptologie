import java.io.IOException;
import java.util.ArrayList;

public class Blockchiffre {

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
	
	 static class permutation {
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
			System.out.println(TextAnalyse.textTest_en(permutation_txt));
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
