package f1nd_th3_numb3r;

import java.util.*;

public class F1ND_TH3_NUMB3R {
	public static void main(String[] args) {
		menuPrincipal();
		instrucciones();
	}

	public static void menuPrincipal() {
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.println(
				"|                                     << F1ND TH3 NUMB3R >>                                     |");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
		System.out.println(
				"| 1) JUGAR\t\t\t\t\t\t\t\t\t\t\t|\n| 2) MEJORES PUNTAJES\t\t\t\t\t\t\t\t\t\t|\n| 3) INSTRUCCIONES\t\t\t\t\t\t\t\t\t\t|\n| 4) SALIR\t\t\t\t\t\t\t\t\t\t\t|");
		System.out.println(
				"-------------------------------------------------------------------------------------------------");
	}

	public static void instrucciones() {
		System.out.println("¡Bienvenido a 'F1ND TH3 NUMB3R'!\n");
		System.out.println(
				"Instrucciones:\n==> El objetivo principal del juego es descubrir el número oculto creado por nuestro RNG.\n==> El número oculto posee 4 cifras, y éstas deben ser descubiertas ¡por ti!, usando tu perspicacia y capacidad de analizar las pistas que te entregaremos.");
		System.out.println(
				"\nLas pistas son las siguientes:\n==> Cuando aparezca una 'Y' en pantalla, significará que acertaste a una de las cifras del número.\n==> Cuando aparezca una 'X' en pantalla, significará que acertaste a la posición de una cifra del número.\n");
		System.out.println(
				"El juego finaliza cuando aciertas a todas las cifras en su respectiva posición, es decir, cuando obtienes 'XXXX'\n\n                                          ~ Good Luck! ~\n");
	}

	public static void inicioJuego() {// metodo principal inicializar juego
		int generado[] = new int[4];
		generarNum(generado);
	}

	public static void generarNum(int[] numero) {// generacion numero aleatorio
		Random rnd = new Random();
		for (int i = 0; i < numero.length; i++) {
			numero[i] = rnd.nextInt(10);
			if (i > 0) {
				for (int j = 0; j < i; j++) {
					if (numero[j] == numero[i]) {
						numero[i] = rnd.nextInt(10);
						j = -1;
					}
				}
			}
		}
	}

	public static int[] num_a_vector(String numero) {// convierte numero jugado
														// a un vector para
														// comparar facilmente
		int vectorNum[] = new int[numero.length()];
		for (int i = 0; i < numero.length(); i++) {
			vectorNum[i] = Integer.parseInt(String.valueOf(numero.charAt(i)));
		}
		return vectorNum;
	}

	public static void compararNumero(int[] numGenerado, int jugada) {// muestra
																		// X e Y
		String juego = String.valueOf(jugada);
		int numJugado[] = num_a_vector(juego);
		String toque = cantidadToque(numGenerado, numJugado);
		String fama = cantidadFama(numGenerado, numJugado);
		System.out.println(toque + fama);
	}

	public static String cantidadToque(int[] generado, int[] jugada) {// cuenta
																		// los Y
		String toque = "";
		for (int i = 0; i < jugada.length; i++) {
			for (int j = 0; j < jugada.length; i++) {
				if ((jugada[i] == generado[j]) && i != j) {
					toque += "Y";
				}
			}
		}
		return toque;
	}

	public static String cantidadFama(int[] generado, int[] jugada) {// cuenta
																		// los X
		String fama = "";
		for (int i = 0; i < jugada.length; i++) {
			for (int j = 0; j < jugada.length; i++) {
				if ((jugada[i] == generado[j]) && i == j) {
					fama += "X";
				}
			}
		}
		return fama;
	}
}
