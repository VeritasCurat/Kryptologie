package src;

import java.util.ArrayList;

public class DES {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = "dgbiuashglbuiadghuadghnoadbgiuzashdgoudsahuighdsaugniudahbugv7fdhiudefgiuzasdfhgudsncuogihdsauighdusjkbguvifsdfghvuidasfnbvusadfhügoadwsHBIÜ KÖFDYBGPJDE<NHGUIPÖOÖBNDFEFDPOHBFHADÜ)H";
	
		
	}
	
	public static int[] substitution(int[] eingabe, int sub_ID) {
		int[] sub1 = {14 ,4 ,13 ,1 ,2 ,15 ,11 ,8 ,3 ,10 ,6 ,12 ,5 ,9 ,0 ,7 ,0 ,15 ,7 ,4 ,14 ,2 ,13 ,1 ,10 ,6 ,12 ,11 ,9 ,5 ,3 ,8 ,4 ,1 ,14 ,8 ,13 ,6 ,2 ,11 ,15 ,12 ,9 ,7 ,3 ,10 ,5 ,0 ,15 ,12 ,8 ,2 ,4 ,9 ,1 ,7 ,5 ,11 ,3 ,14 ,10 ,0 ,6 ,13};
		int[] sub2 = {15 ,1 ,8 ,14 ,6 ,11 ,3 ,4 ,9 ,7 ,2 ,13 ,12 ,0 ,5 ,10 ,3 ,13 ,4 ,7 ,15 ,2 ,8 ,14 ,12 ,0 ,1 ,10 ,6 ,9 ,11 ,5 ,0 ,14 ,7 ,11 ,10 ,4 ,13 ,1 ,5 ,8 ,12 ,6 ,9 ,3 ,2 ,15 ,13 ,8 ,10 ,1 ,3 ,15 ,4 ,2 ,11 ,6 ,7 ,12 ,0 ,5 ,14 ,9};
		int[] sub3 = {10 ,0 ,9 ,14 ,6 ,3 ,15 ,5 ,1 ,13 ,12 ,7 ,11 ,4 ,2 ,8 ,13 ,7 ,0 ,9 ,3 ,4 ,6 ,10 ,2 ,8 ,5 ,14 ,12 ,11 ,15 ,1 ,13 ,6 ,4 ,9 ,8 ,15 ,3 ,0 ,11 ,1 ,2 ,12 ,5 ,10 ,14 ,7 ,1 ,10 ,13 ,0 ,6 ,9 ,8 ,7 ,4 ,15 ,14 ,3 ,11 ,5 ,2 ,12};
		int[] sub4 = {7 ,13 ,14 ,3 ,0 ,6 ,9 ,10 ,1 ,2 ,8 ,5 ,11 ,12 ,4 ,15 ,13 ,8 ,11 ,5 ,6 ,15 ,0 ,3 ,4 ,7 ,2 ,12 ,1 ,10 ,14 ,9 ,10 ,6 ,9 ,0 ,12 ,11 ,7 ,13 ,15 ,1 ,3 ,14 ,5 ,2 ,8 ,4 ,3 ,15 ,0 ,6 ,10 ,1 ,13 ,8 ,9 ,4 ,5 ,11 ,12 ,7 ,2 ,14};
		int[] sub5 = {2 ,12 ,4 ,1 ,7 ,10 ,11 ,6 ,8 ,5 ,3 ,15 ,13 ,0 ,14 ,9 ,14 ,11 ,2 ,12 ,4 ,7 ,13 ,1 ,5 ,0 ,15 ,10 ,3 ,9 ,8 ,6 ,4 ,2 ,1 ,11 ,10 ,13 ,7 ,8 ,15 ,9 ,12 ,5 ,6 ,3 ,0 ,14 ,11 ,8 ,12 ,7 ,1 ,14 ,2 ,13 ,6 ,15 ,0 ,9 ,10 ,4 ,5 ,3};
		int[] sub6 = {12 ,1 ,10 ,15 ,9 ,2 ,6 ,8 ,0 ,13 ,3 ,4 ,14 ,7 ,5 ,11 ,10 ,15 ,4 ,2 ,7 ,12 ,9 ,5 ,6 ,1 ,13 ,14 ,0 ,11 ,3 ,8 ,9 ,14 ,15 ,5 ,2 ,8 ,12 ,3 ,7 ,0 ,4 ,10 ,1 ,13 ,11 ,6 ,4 ,3 ,2 ,12 ,9 ,5 ,15 ,10 ,11 ,14 ,1 ,7 ,6 ,0 ,8 ,13};
		int[] sub7 = {4 ,11 ,2 ,14 ,15 ,0 ,8 ,13 ,3 ,12 ,9 ,7 ,5 ,10 ,6 ,1 ,13 ,0 ,11 ,7 ,4 ,9 ,1 ,10 ,14 ,3 ,5 ,12 ,2 ,15 ,8 ,6 ,1 ,4 ,11 ,13 ,12 ,3 ,7 ,14 ,10 ,15 ,6 ,8 ,0 ,5 ,9 ,2 ,6 ,11 ,13 ,8 ,1 ,4 ,10 ,7 ,9 ,5 ,0 ,15 ,14 ,2 ,3 ,12};
		int[] sub8 = {13 ,2 ,8 ,4 ,6 ,15 ,11 ,1 ,10 ,9 ,3 ,14 ,5 ,0 ,12 ,7 ,1 ,15 ,13 ,8 ,10 ,3 ,7 ,4 ,12 ,5 ,6 ,11 ,0 ,14 ,9 ,2 ,7 ,11 ,4 ,1 ,9 ,12 ,14 ,2 ,0 ,6 ,10 ,13 ,15 ,3 ,5 ,8 ,2 ,1 ,14 ,7 ,4 ,10 ,8 ,13 ,15 ,12 ,9 ,0 ,3 ,5 ,6 ,11};
		
		int[] sub = new int[64];
		switch(sub_ID) {
			case 1: sub = sub1; break;
			case 2: sub = sub2; break;
			case 3: sub = sub3; break;
			case 4: sub = sub4; break;
			case 5: sub = sub5; break;
			case 6: sub = sub6; break;
			case 7: sub = sub7; break;
			case 8: sub = sub8; break;
		}
		
		int[] ausgabe = new int [eingabe.length];
		for(int pos=0; pos<eingabe.length; pos++) {
			ausgabe[pos] = sub[eingabe[pos]];
		}
		
		return ausgabe;
	}
	
	public static int[] f(int[] eingabe, int[] K) {
		if(eingabe.length != 32)System.err.println("eingabelänge bei f nicht 32!");
		if(K.length != 48 )System.err.println("eingabelänge bei f nicht 48!");
		//expansion
		int[] expansion = expansion(eingabe);
		
		
		//schlüsseladdition
		int[] add = new int[48];
		for(int pos=0; pos < 48; pos++) add[pos] = (expansion[pos] + K[pos])%2;
		
//		//substitution
		int[][] vor_sub = new int [8][6];
		for(int pos=0; pos < add.length; pos++) {
			vor_sub[((int) pos/6)][pos%6] = add[pos]; 
		}
		
		int[][] nach_sub = new int [8][4];
		for(int pos=0; pos <8; pos++) {
				nach_sub[pos] = substitution(eingabe, pos+1);
		}
		
		return null;
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
