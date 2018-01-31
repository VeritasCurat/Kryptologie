package chiffren;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Blockchiffre {

	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	static char[] haeufigBuchstaben_de = "enisratdhulcgmobwfkzpvjyxq".toCharArray();
	static char[] haeufigBuchstaben_en = "etaoinshrdlcumwfgypbvkjxqz".toCharArray();

	static String[] haeuf_Bi_de_top20 = {"er", "en" ,"ch" ,"de", "ei", "nd", "te" ,"in", "ie", "ge", "es", "ne", "un", "st", "re", "he", "an", "be", "se", "ng", "di", "sc"};
	static String[] haeuf_Bi_en_top20= {"th", "he", "an","in",  "er",  "re" , "on" , "es", "ti",  "ar", "st", "en", "or", "nd", "to", "nt", "ed", "is", "ar", "ou", "of", "te"};
	
	private static int index_alphabet(char x) {
		for(int i=0; i<alphabet.length; i++)if(x == alphabet[i])return i;
		return -1;
	}
	
	private static boolean contains(String suche, String[] arr) {
		for(String x: arr)if(x.equals(suche))return true;
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
//		int[] k_de =  {3,0,2,4,1};
//		int[] k_en =  {1,4,2,0,3};
//		System.out.println(enchiffrieren("HIERHABENWIREINENKURZENKLARTEXTVORLIEGEN",k_en));
//		System.out.println(dechiffrieren("IHEHRBWEANRNEIINRKEUELNZKRXTAEVLOTRENGIE", k_de));
		
		String krypto = "IHEHR BWEAN RNEII NRKEU ELNZK RXTAE VLOTR ENGIE";
		System.out.println(blockchiffre_bigramme(krypto, 5, "de"));

	}
	
	public static void test() {
		
	}
	
	public static String dechiffrieren(String kryptotext, int[] permutation) {
		String ret="";
		int k =0;
		for(int i=0; i<kryptotext.length(); i++) {
			k = ((int) i / permutation.length)*permutation.length + permutation[(i % permutation.length)];
			ret += Character.toString(kryptotext.charAt(k));
		}
		return ret;
	}
	
	public static String enchiffrieren(String klartext, int[] permutation) {
		String ret="";
		int k =0;
		for(int i=0; i<klartext.length(); i++) {
			k = ((int) i / permutation.length)*permutation.length + permutation[(i % permutation.length)];
			ret += Character.toString(klartext.charAt(k));
		}
		return ret;
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
		kryptotext = kryptotext.toLowerCase();
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
			while(kandidaten.size() > 0) {
				//trenne schluessel(spalten) - kandidaten(spalten) - muessen disjunkt sein 
				Collection<Integer> sl = schluessel;
				kandidaten.removeAll(sl);
				//System.out.println(schluessel); System.out.println(kandidaten);

				//for(int i: schluessel)if(kandidaten.contains(schluessel.get(i)))kandidaten.remove(schluessel.get(i));
				
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
							kandidat_value += Character.toString(bloecke[j].charAt(kandidat_index[0]))  + Character.toString(bloecke[j].charAt(kandidat_index[1])); 
							//System.out.println(kandidat_value+" "+contains(kandidat_value, haeuf_Bi_de_top20));
							if(sprache.contains("de") && contains(kandidat_value, haeuf_Bi_de_top20)) {
								anz_bi_gramme++;
								//System.out.println(kandidat_index[0]+" "+kandidat_index[1]+" "+kandidat_value);
							}
							if(sprache.contains("en") && contains(kandidat_value, haeuf_Bi_en_top20)) {
								anz_bi_gramme++;
								//System.out.println(kandidat_index[0]+" "+kandidat_index[1]+" "+kandidat_value);
							}
						}
						if(anz_bi_gramme > anz_bi_gramme_max) {
							//System.out.println(kandidat_index[0]+" "+kandidat_index[1]);
							anz_bi_gramme_max = anz_bi_gramme;
							kandidat_index_max = kandidat_index.clone();
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
								//System.out.println(kandidat_index[0]+" "+kandidat_index[1]+" "+kandidat_value);
							}
							if(sprache.contains("en") && contains(kandidat_value, haeuf_Bi_en_top20)) {
								anz_bi_gramme++;	
								//System.out.println(kandidat_index[0]+" "+kandidat_index[1]+" "+kandidat_value);
							}
						}
						if(anz_bi_gramme > anz_bi_gramme_max) {
							anz_bi_gramme_max = anz_bi_gramme;
							kandidat_index_max = kandidat_index;
						}
					}
					//System.out.println(kandidat_index_max[0]+" "+kandidat_index_max[1]+"\n");

				//kandidatenspalte abziehen und zum schluessel hinzufuegen
					//kandidat hinten anfuegen
						if(kandidat_index_max[0] == schluessel.get(schluessel.size()-1)) {
							schluessel.add(kandidat_index_max[1]);
						}
					//kandidat vorne anfuegen
						if(kandidat_index_max[1] == schluessel.get(0)) {
							schluessel.add(schluessel.get(schluessel.size()-1));
							for(int i=schluessel.size()-1; i>0;i--)schluessel.set(i, schluessel.get(i-1));
							schluessel.set(0, kandidat_index_max[0]);
						}
			}
		//klartext mit schluessel berechnen
		int[] schluessel_arr = new int[schluessel.size()]; for(int i: schluessel)schluessel_arr[i] = schluessel.get(i);
		kryptotext = kryptotext.replace(" ", "");
		return dechiffrieren(kryptotext, schluessel_arr);	
	}

}
