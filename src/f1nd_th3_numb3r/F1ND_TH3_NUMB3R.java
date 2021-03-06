package f1nd_th3_numb3r;

import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import java.util.Random;

public class F1ND_TH3_NUMB3R {
    public static void main(String[] args) {
        switchCase();
    }
    
    //Método selección múltiple menuPrincipal().
    public static void switchCase() {
        boolean continuar = true;
        do{
            menuPrincipal();
            switch (validarMenu()) {
                case 1: 
                    inicioJuego();
                    volverMenu(); // Analizar la posibilidad de agregar un método que permita volver a jugar de inmediato.
                    break;
                case 2: 
        //          highScore();
                    System.out.println("\nEstamos trabajando para usted...");
                    volverMenu();
                    break;
                case 3: 
                    instrucciones();
                    volverMenu();
                    break;
                default: 
                    continuar = false;
                    System.out.println("~ See you next time! ~");
                    break;
            }
        }while(continuar);    
    }
     
    // Muestra por pantalla el menu principal del juego.
    public static void menuPrincipal() {
        System.out.println(
            "\n-------------------------------------------------------------------------------------------------");
        System.out.println(
            "|                                     << F1ND TH3 NUMB3R >>                                     |");
        System.out.println(
            "-------------------------------------------------------------------------------------------------");
        System.out.println(
            "| 1) JUGAR\t\t\t\t\t\t\t\t\t\t\t|\n| 2) MEJORES PUNTAJES\t\t\t\t\t\t\t\t\t\t|\n| 3) INSTRUCCIONES\t\t\t\t\t\t\t\t\t\t|\n| 4) SALIR\t\t\t\t\t\t\t\t\t\t\t|");
        System.out.println(
            "-------------------------------------------------------------------------------------------------");
    }
    
    //Valida opción menú entre: 1 <= opc <= 4.
    public static int validarMenu() {
        int opc;
        do {
            opc = tryCatch();
        } while (opc<1 || opc>4);
        return opc;
    }
        
    //Usa Try-Catch en el método leerNumero() para validar entrada de números.
    public static int tryCatch() {
        int resp=0;
        boolean salir;            
        do {
            System.out.print("\nSeleccione su opción: ");
            salir = false;
            try {
                resp = leerNumero();
            }catch(Exception ex) {
                salir = true;
            }               
        } while (salir);
        return resp;
    }
         
    //Lee y retorna el número ingresado por el usuario.
    public static int leerNumero() {
        Scanner leer = new Scanner(System.in);
        int num = leer.nextInt();
        return num;
    }
     
    // Muestra por pantalla las instrucciones del juego.
    public static void instrucciones() {
        System.out.println(
            "\n¡Bienvenido a 'F1ND TH3 NUMB3R'!\n");
        System.out.println(
            "Instrucciones:\n==> El objetivo principal del juego es descubrir el número oculto creado por nuestro RNG. (Random Number Generator)\n==> El número oculto posee 4 cifras, y éstas deben ser descubiertas ¡por ti!, usando tu perspicacia\n    y capacidad de analizar las pistas que te entregaremos.");
        System.out.println(
            "==> Para alcanzar este objetivo deberás ir ingresando diversas soluciones hasta dar con la acertada,\n    intentando resolver el acertijo en la menor cantidad de intentos y tiempo posibles.");
        System.out.println(
            "\nLas pistas son las siguientes:\n==> Cuando aparezca una 'Y' en pantalla, significará que acertaste a una de las cifras del número.\n==> Cuando aparezca una 'X' en pantalla, significará que acertaste a la posición de una cifra del número.\n");
        System.out.println(
            "El juego finaliza cuando aciertas a todas las cifras en su respectiva posición, es decir, cuando obtienes 'XXXX'\n\n                                          ~ Good Luck! ~\n");
    }

    // Inicializa el juego.
    public static void inicioJuego() {
        int generado[] = new int[4];
        boolean continuar;
        int contJugadas = 0;
        generarNum(generado);
        //for(int i=0;i<4;i++) System.out.println(generado[i]);
        Date inicio = tomarTiempoJuego();
        do{
            continuar=leerJugada(generado);
            contJugadas++;
        }while(continuar);
        Date fin = tomarTiempoJuego();
        mostrarFinJuego(contJugadas, inicio, fin);
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
    // así genera un nuevo numero
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

    // Lee la jugada del usuario, llama a metodo que valida la jugada, método
    // que compara la jugada con el numero generado y que valida el sólo ingreso de enteros.
    public static boolean leerJugada(int[] numGenerado) {
        String jugada;
        String resultado;
        do{
        	jugada=ingresoJugada();  
        }while(validarLongitud(numGenerado, jugada) || validarRepeticion(jugada) || validarInt(jugada) );
        resultado=compararNumero(numGenerado, jugada);
        System.out.println(resultado);
        return !resultado.equals("XXXX");
    }
    
    // Lee la cadena ingresada por el usuario.
    public static String ingresoJugada(){
    	Scanner leer=new Scanner(System.in);
    	System.out.print("Ingrese numero: ");
    	return leer.nextLine();
    }
    
    // Valida la longitud de la jugada para que sea igual al largo del numero generado.
    public static boolean validarLongitud(int []numGenerado, String jugada){
    	if (numGenerado.length!=jugada.length()){
    		System.err.println("Ingrese cantidad correcta de digitos");
    		return true;
    	}
    	return false;
    }
    
    // Valida la no repetición de números ingresados por el jugador.
    public static boolean validarRepeticion(String jugada) {
        for (int i = 0; i < jugada.length(); i++) {
            for (int j =0; j < jugada.length(); j++) {
                if (jugada.charAt(i) == jugada.charAt(j) && (i != j)) {
                	System.err.println("Los numeros se repiten, ingreselos nuevamente");
                	return true;
                }
            }
        }
        return false;
    }
    
    // Valida el sólo ingreso de números a la jugada.
    public static boolean validarInt(String jugada) {
        if (jugada.charAt(0) != '+' && jugada.charAt(0) != '-') {
            try {
                Integer.parseInt(jugada);
            }catch(NumberFormatException er) {
                System.err.println("Ingrese sólo números");
                return true;
            }
        }else{
            System.err.println("Ingrese sólo números");
            return true;
        }
        return false;
    }
    
    // Convierte la jugada en arreglo, y llama a metodos que evaluan cantidad
    // de 'Y' y 'X'.
    public static String compararNumero(int[] numGenerado, String jugada) {
        int numJugado[] = num_a_vector(jugada);
        String Y = cantidadY(numGenerado, numJugado);
        String X = cantidadX(numGenerado, numJugado);
        return (X + Y);
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

    // Retorna el tiempo de juego en formato mm:ss
    public static String tiempoTotal(Date inicio, Date fin) {
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        long tiempo = fin.getTime() - inicio.getTime();
        return sdf.format(tiempo);
    }
    
    // Da la opción al usuario, de volver al menú principal.
    public static void volverMenu() {
        System.out.print("\nIngrese cualquier tecla y presione enter para volver al menú principal. -> ");
        leerCualquierTecla();
    }
    
    // Básicamente se usa este método para confirmar el regreso al menú principal.
    public static char leerCualquierTecla() {
        char input;
        Scanner read = new Scanner(System.in);
        input = read.next().charAt(0);
        return input;
    }
    
    // Método que calcula el puntaje al finalizar el juego.
    public static int calcularPuntaje(int jugadas, String tiempo){
        int minutos = Integer.parseInt(String.valueOf(tiempo.charAt(0))) + Integer.parseInt(String.valueOf(tiempo.charAt(1)));
        int segundos = Integer.parseInt(String.valueOf(tiempo.charAt(3))) + Integer.parseInt(String.valueOf(tiempo.charAt(4)));
        int puntaje = 939070 - (12345*(jugadas)+(62*(minutos)+(segundos))*719); // <-- Fórmula que calcula el puntaje.
        return puntaje;
    }
    
    // Muestra detalles al finalizar el juego.
    public static void mostrarFinJuego(int jugadas, Date inicio, Date fin) {
        String tiempo = tiempoTotal(inicio, fin);
        System.out.println("\n¡¡Felicidades, has encontrado el número oculto!!\n");
        System.out.println("==> Cantitad total de intentos: "+jugadas);
        System.out.println("==> Tiempo total transcurrido: "+tiempo);
        System.out.println("==> Tu puntaje obtenido es: "+calcularPuntaje(jugadas, tiempo));
    }
}