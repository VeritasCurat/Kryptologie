package src;

import java.util.ArrayList;

public class DES {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "dgbiuashglbuiadghuadghnoadbgiuzashdgoudsahuighdsaugniudahbugv7fdhiudefgiuzasdfhgudsncuogihdsauighdusjkbguvifsdfghvuidasfnbvusadfhügoadwsHBIÜ KÖFDYBGPJDE<NHGUIPÖOÖBNDFEFDPOHBFHADÜ)H";
	
		
	}
	
	public static int[] f(int[] eingabe, int[] K) {
		if(eingabe.length != 32 )System.err.println("eingabelänge bei f nicht 32!");
		if(K.length != 48 )System.err.println("eingabelänge bei f nicht 48!");
		int[] expansion = expansion(eingabe);
		int[] add = new int[48];
		for(int pos=0; pos < 48; pos++) add[pos] = (expansion[pos] + K[pos])%2;
		

	}
	
	public static int[] rundenfunktion() {
		return null;
	}
	
	public static int[] permutation(int[] eingabe) {
		if(eingabe.length != 32 )System.err.println("eingabelänge bei permutation nicht 32!");
		int[] permutation = {16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,24,14,32,27,3,9,19,13,30,6,22,11,4,25};
		int[] ausgabe = new int[48];
		for(int i =0; i<32; i++) {
			ausgabe[i] = eingabe[permutation[i]];
		}
		return ausgabe;
	}
	
	public static int[] expansion(int[] eingabe) {
		if(eingabe.length != 32 )System.err.println("eingabelänge bei expansion nicht 32!");
		int[] expansion = {32,1,2,3,4,5,4,5,6,7,8,9,8,9,10,11,12,13,12,13,14,15,16,17,16,17,18,19,20,21,20,21,22,23,24,25,24,25,26,27,28,29,28,29,30,31,32,10};
		int[] ausgabe = new int[48];
		for(int i =0; i<48; i++) {
			ausgabe[i] = eingabe[expansion[i]];
		}
		return ausgabe;
	}
	
	public static int[] initialpermutation(int[] eingabe) {
		if(eingabe.length < 64) {
			int[] a = new int[64];
			for(int k=0; k<eingabe.length; k++)a[k] = eingabe[k];
			for(int l=eingabe.length; l<64; l++)a[l] = 0;
			eingabe = a.clone();
		}
		int[] ip = {58,50,42,34,26,18,10,2,60,52,44,36,28,20,12,4,62,54,46,38,30,22,14,6,64,56,48,40,32,24,16,8,57,49,41,33,25,17,9,1,59,51,43,35,27,19,11,3,61,53,45,37,29,21,13,5,63,55,47,39,31,23,15,7};		
		int[] ausgabe = new int[64];
		for(int pos=0; pos<64; pos++) {
			ausgabe[pos] = eingabe[(ip[pos])];
		}
		return ausgabe;
	}
	
	public static ArrayList<String> block(String klartext) {
		ArrayList<String> ausgabe = new ArrayList<String>();
		int pos=0;
		int pos_1;
		while(pos < klartext.length()) {
			pos_1 = pos;
			pos = Math.min(klartext.length(),pos+=64 );
			ausgabe.add(klartext.substring(pos_1,pos));
		}
		return ausgabe;
	}
	
	public static String encrypt(String klartext) {
		
		
		return "";
	}

}
