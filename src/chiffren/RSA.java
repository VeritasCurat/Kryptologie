package chiffren;

public class RSA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(verschlüsseln("444", 613, 989));
		System.out.println(phi(989));
	}
	
	public static String verschlüsseln(String x, int schlüssel, int mod) {
		String ausgabe = "";
		for(char c: x.toCharArray()) {
			int c_ = Integer.valueOf(c);
			ausgabe += Primzahltests.HornerPot(c_,schlüssel, mod);
		}
		return ausgabe;
	}

	//1. n gegebeb -> p,q (prim) mit p*q = n berechnen, berechne phi(n) = (p-1)*(q-1)
	//1'. phi(n) berechnen
	//2.
	
	public static int phi(int n) {
		int phi=0;
		for(int i=1; i <= n-1; i++) {
			if(Primzahltests.ggT(i, n)==1)phi++;
		}
		return phi;
	}
}
