package src;

import java.math.BigInteger;

public class Rabin {

	public static void main(String[] args) {
		aufgabe_a();
		Rabin2.encrypt(32767, 199*211);
	}
	
	
	/** Betrachten Sie das Rabin-System mit dem Schlüssel p = 199, q = 211, n = pq und e = 1357.
	  * (a) Berechnen Sie den Kryptotext y des Klartextes x = 32767.
	 */
	private static void aufgabe_a() {
		int n = 199 * 211;
		int e = 1357;
		int[] schlüssel = {e,n};
		int[] klartext = {3,2,7,6,7};
		int[] kryptotext = verschlüsseln(klartext, schlüssel);
		for(int k: kryptotext)System.out.print(k+".");
	}
	
	
	public static int[] verschlüsseln(int[] klartext, int[] schlüssel) {
		int[] kryptotext = new int [klartext.length];
		for(int pos=0; pos<kryptotext.length; pos++) {
			kryptotext[pos] = (klartext[pos]*(klartext[pos] + schlüssel[0])) % schlüssel[1];
		}
		return kryptotext;
	}

}
