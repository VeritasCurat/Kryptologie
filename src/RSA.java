package src;

import java.math.BigInteger;
import java.util.ArrayList;

public class RSA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] a = {4,4,4};
//		int[]b= {170,170,170};
//		System.out.println("a)"+verschlüsseln(a, 613, 989));
//System.out.println("b)"+verschlüsseln("444", Restklassen.inverses(613, phi(989)),989));
		
		//BigInteger n = 9382619383;
		
		int[] ret = verfahrenDerDifferenzen(9382619383L); //Vorl.124711, p=401,q=311
		System.out.println(ret[0]+ " " + ret[1]);
		
//		int[] ret2 = faktorisieren_phi_primitiv(4386607);
//		System.out.println(ret2[0]+ " " + ret2[1]);

		
		
		System.out.println("nicht so schön ...");
		System.out.println("c) "+faktorisieren_primitiv(9382619383L));
		System.out.println("d) "+faktorisieren_primitiv(4386607));
	}
	

	static ArrayList<Integer> faktorisieren_primitiv(long n) {
		ArrayList<Integer> faktoren = new ArrayList<Integer>();
		if(isPrime(n)) {faktoren.add(1);return faktoren;}
		int p=0;
		while(n > 1) {
			for(p=2; ; p++) {
				if(isPrime(p) && n%p == 0) {
					n /= p;
					faktoren.add(p);
					break;
				}
				if(p*p==n)break;

			}
		}
		return faktoren;
	}
	
	//Faktorisieren Sie die Zahl n = 4386607 bei Kenntnis von phi(n) = 4382136.
	static int[] faktorisieren_phi_primitiv(int n) {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for(int k=0; (k*k)+1<n; k++) if(isPrime(k))primes.add(k);
		int ret[] = new int[2];
		for(int p: primes) {
			for(int q: primes) {
				if((p)*(q) == n) {
					ret[0] = p-1; ret[1] = q-1;
				}
			}
		}
		return new int[2];
	}
	
	static public boolean isPrime(long p) {
		if(p<2)return false;
		if(p==2) return true;
		for(long q=2; q*q <p+2; q++) {
			if(p%q == 0)return false;
		}
		return true;
	}
	
	/**
	 * wie in Skript(Einführung in die Kryptologie, Köbler), Seite 90 beschrieben
	 * @param n
	 * @return
	 */
	public static int[] verfahrenDerDifferenzen(long n) {
		//1. Berechne Wurzel von n =: w
		int w = (int) Math.sqrt(n);
		//System.out.println(w);
		
		//2. finde quadratzahl q in Nähe von w, sodass q² - n = b²
		int q=0;
		int a = 0;
		for(int dif=1; ;dif++) {

			int h = w + dif;
			int l = w - dif;
			System.out.println(dif+" "+(int) Math.sqrt(n - h*h)+" "+(int) Math.sqrt(l*l - n));
			if(n - h*h < 0) break;


			//System.out.println(Math.sqrt(h*h - n) +" "+  Math.sqrt(l*l - n));
			
			if( Math.pow((int) Math.sqrt(h*h - n),2) == h*h - n) {
			    // integer type
				a = h;
				q = (int) Math.sqrt(h*h - n);
			}
			if(Math.pow((int) Math.sqrt(l*l - n),2) == l*l - n) {
			    // integer type
				a = l;
				q=(int) Math.sqrt(l*l - n);
			}
		}
		System.out.println(q+ " " + a);
		//3. berechne p = a + b, q = a - b
		int[] ret = new int[2]; ret[0] = a + q; ret[1] = a - q;
		return ret;
	}
	
	public static String verschlüsseln(int[] x, int schlüssel, int mod) {
		String ausgabe = "";
		for(int x_:x) {
			ausgabe += (Primzahltests.HornerPot(x_,schlüssel, mod));
		}
		return ausgabe;
	}
	
	//Faktorisieren Sie die Zahl n 	= 9382619383 mit dem Verfahren der Differenz der Quadrate.
	
	

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
