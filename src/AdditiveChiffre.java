
//test

public class AdditiveChiffre {
	static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

	public static void main(String[] args) {
		zeigeAlleAdditionen("BEEAKFYDJXUQYHYJIQRYHTYJIQFBQDUYJIIKFUHCQD".toLowerCase());
	}
	
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

}
