package f1nd_th3_numb3r;

import java.text.SimpleDateFormat;
import java.util.*;

public class F1ND_TH3_NUMB3R {
	public static void main(String[] args) {
               mostrarMenu();
               
		
		
	}

	// Muestra por pantalla el menu principal del juego.
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

	// Muestra por pantalla las instrucciones del juego.
	public static void instrucciones() {
		System.out.println("¡Bienvenido a 'F1ND TH3 NUMB3R'!\n");
		System.out.println(
				"Instrucciones:\n==> El objetivo principal del juego es descubrir el n�mero oculto creado por nuestro RNG.\n==> El n�mero oculto posee 4 cifras, y �stas deben ser descubiertas �por ti!, usando tu perspicacia y capacidad de analizar las pistas que te entregaremos.");
		System.out.println(
				"\nLas pistas son las siguientes:\n==> Cuando aparezca una 'Y' en pantalla, significar� que acertaste a una de las cifras del n�mero.\n==> Cuando aparezca una 'X' en pantalla, significar� que acertaste a la posici�n de una cifra del n�mero.\n");
		System.out.println(
				"El juego finaliza cuando aciertas a todas las cifras en su respectiva posici�n, es decir, cuando obtienes 'XXXX'\n\n                                          ~ Good Luck! ~\n");
	}

	// Inicializa el juego.
	public static void inicioJuego() {
		int generado[] = new int[4];
		generarNum(generado);
		// for(int i=0;i<4;i++) System.out.println(generado[i]);
		Date inicio = tomarTiempoJuego();
		leerJugada(generado);
		// System.out.println("Has terminado!");
		Date fin = tomarTiempoJuego();
	}

	// Lee la jugada del usuario, llama a metodo que valida la jugada y metodo
	// que compara la jugada con el numero generado.
	public static void leerJugada(int[] numGenerado) {
		String jugada;
		Scanner leer = new Scanner(System.in);
		System.out.print("Ingrese numero: ");
		jugada = leer.nextLine();
		// validarJugada(jugada);
		compararNumero(numGenerado, jugada);
	}

	// Genera un numero aleatorio de n cifras
	public static void generarNum(int[] numGenerado) {
		Random rnd = new Random();
		for (int i = 0; i < numGenerado.length; i++) {
			numGenerado[i] = rnd.nextInt(10);
			repeticionNumGen(numGenerado, i);
		}
	}

	// Verifica si las cifras del numero aleatorio generado se repiten, si es
	// as� genera un nuevo numero
	public static int[] repeticionNumGen(int[] numGenerado, int i) {
		Random rnd = new Random();
		if (i > 0) {
			for (int j = 0; j < i; j++) {
				if (numGenerado[j] == numGenerado[i]) {
					numGenerado[i] = rnd.nextInt(10);
					j = -1;
				}
			}
		}
		return numGenerado;
	}

	// Convierte numero jugado a un arreglo para comparar con el numero
	// generado.
	public static int[] num_a_vector(String numero) {
		int vectorNum[] = new int[numero.length()];
		for (int i = 0; i < numero.length(); i++) {
			vectorNum[i] = Integer.parseInt(String.valueOf(numero.charAt(i)));
		}
		return vectorNum;
	}

	// Convierte la jugada en arreglo, y llama a metodos que evaluan cantidad
	// de 'Y' y 'X'.
	public static String compararNumero(int[] numGenerado, String jugada) {
		int numJugado[] = num_a_vector(jugada);
		String Y = cantidadY(numGenerado, numJugado);
		String X = cantidadX(numGenerado, numJugado);
		return (X + Y);
	}

	// Compara la jugada con el numero generado y retorna String con cantidad
	// de 'Y'.
	public static String cantidadY(int[] generado, int[] jugada) {
		String cantY = "";
		for (int i = 0; i < jugada.length; i++) {
			for (int j = 0; j < jugada.length; j++) {
				if ((jugada[i] == generado[j]) && (i != j)) {
					cantY += "Y";
				}
			}
		}
		return cantY;
	}

	// Compara la jugada con el numero generado y retorna String con cantidad
	// de 'X'.
	public static String cantidadX(int[] generado, int[] jugada) {
		String cantX = "";
		for (int i = 0; i < jugada.length; i++) {
			for (int j = 0; j < jugada.length; j++) {
				if ((jugada[i] == generado[j]) && (i == j)) {
					cantX += "X";
				}
			}
		}
		return cantX;
	}

	// Guarda en una variable el tiempo al instante.
	public static Date tomarTiempoJuego() {
		return new Date();
	}

	// retorna tiempo de juego en formato mm:ss
	public static String tiempoTotal(Date inicio, Date fin) {
		SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
		long tiempo = fin.getTime() - inicio.getTime();
		return sdf.format(tiempo);
	}
        
        //Lee y retorna número ingresado por el usuario
        public static int leerNumero() {
            Scanner leer = new Scanner(System.in);
            int num = leer.nextInt();
            return num;
        }
        
        //Usa método TryCatch en el método leerNumero() para validar entrada de números
        public static int tryCatch() {
            int resp=0;
            boolean salir;            
            do {
                salir = false;
                try {
                    resp = leerNumero();
                }catch(Exception ex) {
                    salir = true;
                }               
            } while (salir);
            return resp;
        }
        
        //Valida opción menú entre: 1<= opc <= 4
        public static int validarMenu() {
            int opc;
            do {
                opc = tryCatch();
            } while (opc<1 || opc>4);
            return opc;
        }
        
        //Método selección múltiple menuPrincipal
        public static void switchCase() {
            switch (validarMenu()) {
                case 1: inicioJuego();
                        break;
            //  case 2: highScore();
            //          break;
                case 3: instrucciones();
                        break;
            //  case 4: salir();
            //          break;
                default: break;
            }
        }
        //Muestra por pantalla menú principal
        public static void mostrarMenu() {
            do {
                menuPrincipal();
            }while (validarMenu() != 4);
        }
}
