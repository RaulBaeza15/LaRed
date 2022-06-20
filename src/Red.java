import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Red {
    private ArrayList<Matriz> salidas;
    private ArrayList<Matriz> errores;
    private ArrayList<Matriz> deltasW=new ArrayList<>();
    private ArrayList<Matriz> deltasBias=new ArrayList<>();

    private ArrayList<Capa> capas;
    private double aprendizaje;
    private int Ncapas;


    public ArrayList<Capa> getCapas() {
        return capas;
    }

    public int getNcapas() {
        return Ncapas;
    }

    public double getAprendizaje() {
        return aprendizaje;
    }

    public void setCapas(ArrayList<Capa> capas) {
        this.capas = capas;
    }

    public Red() {
        capas = new ArrayList<>();

        System.out.println("Las matrices que tengas que introducir tienen que seguir el formato: \n" +
                "Para la matriz:\n" +
                "1.0 2.0 \n" +
                "3.0 4.0 \n" +
                "5.0 6.0 \n" +
                "Tendrías que poner [1,2][3,4][5,6]\n" +
                "Y para la matriz:\n" +
                "1.0 2.0 3.0 4.0\n" +
                "5.1 6.2 7.3 8.4 \n" +
                "Tendrías que poner [1,2,3,4][5.1,6.2,7.3,8.4]\n" +
                "Para los decimales 1/4~0.25");
        Scanner sc = new Scanner(System.in);
        System.out.println("Pon el factor de aprendizaje");

        aprendizaje=Double.parseDouble(sc.nextLine());

        System.out.println("Pon el numero de capas que va a haber");
        Ncapas=sc.nextInt();
        for(int k =0;k<Ncapas;k++){
            System.out.println("Pon la matriz de pesos(W) de la "+(k+1)+"º capa:");

            Scanner sc1 = new Scanner(System.in);
            double [][] matriz1;

            String cadena= sc1.nextLine();

            Pattern p = Pattern.compile("\\[(.*?)\\]");
            Matcher m = p.matcher(cadena);


            ArrayList<String> filas=new ArrayList<>();
            while(m.find()) {
                String fila=m.group(1);

                filas.add(fila);

            }

            if (filas.size()>0){
                matriz1=new double[filas.size()][filas.get(0).split("\\,").length];


                int ancho=filas.get(0).split("\\,").length,alto=filas.size();

                for (int i = 0; i < alto; i++) {
                    for (int j = 0; j < ancho; j++) {
                        matriz1[i][j]=Double.parseDouble(filas.get(i).split("\\,")[j]);
                    }
                }
            }else{
                matriz1=new double[0][0];
            }

            double [][] matrizW= matriz1;

            //
            System.out.println("Pon la matriz del bias(b) de la "+(k+1)+"º capa:");

            double [][] matriz2;

             cadena= sc1.nextLine();

             p = Pattern.compile("\\[(.*?)\\]");
             m = p.matcher(cadena);


            ArrayList<String> filas2=new ArrayList<>();
            while(m.find()) {
                String fila=m.group(1);

                filas2.add(fila);

            }

            if (filas2.size()>0){
                matriz2=new double[filas2.size()][filas2.get(0).split("\\,").length];


                int ancho=filas2.get(0).split("\\,").length,alto=filas2.size();

                for (int i = 0; i < alto; i++) {
                    for (int j = 0; j < ancho; j++) {
                        matriz2[i][j]=Double.parseDouble(filas2.get(i).split("\\,")[j]);
                    }
                }
            }else{
                matriz2=new double[0][0];
            }

            double [][] matrizBias=matriz2;





            capas.add(new Capa(matrizW,matrizBias));
            System.out.println("W de la capa "+(k+1)+"\n"+getCapas().get(k).getW());
            System.out.println("Bias de la capa "+(k+1)+"\n"+getCapas().get(k).getBias());

        }

    }
    public void propagacion(){
        salidas=new ArrayList<>();
        System.out.println("¿Cual es la entrada?");
        salidas.add(new Matriz(Matriz.preguntarMatriz()));

        for (int i = 0; i < Ncapas; i++) {
            Matriz matriz;
            Matriz matrizSalidaAnterior=new Matriz(salidas.get(i).getMatrizIndep());
            Matriz matrizW=new Matriz(capas.get(i).getW().getMatrizIndep());
            Matriz  matrizBias=new Matriz(capas.get(i).getBias().getMatrizIndep());
            matriz=(matrizSalidaAnterior.multiplicar(matrizW)).suma(matrizBias).funcionActivacion();
            System.out.println("s("+(i+1)+"): \n"+matriz);
            salidas.add(new Matriz(matriz.getMatrizIndep()));

        }


    }

    public void retroPropagacion(){
        errores=new ArrayList<>();
        System.out.println("¿Cual es la salida deseada?");
        Matriz salidaDeseada=new Matriz(Matriz.preguntarMatriz());

        Matriz resultadoFinal=salidas.get(salidas.size()-1);

        Matriz matriz=salidaDeseada.resta(resultadoFinal).multiplicacionPuntoAPunto(resultadoFinal).multiplicacionPuntoAPunto(Matriz.matrizDeUnos(resultadoFinal).resta(resultadoFinal));
        System.out.println("Error("+capas.size()+"):\n"+matriz);
        errores.add(matriz);

        for (int i = 0; i < Ncapas-1; i++) {
            Matriz matriz2;
            Matriz error=new Matriz (errores.get(0).getMatrizIndep());
            Matriz wTranspuesta = new Matriz (capas.get(capas.size()-i-1).getW().getMatrizIndep()).transpuesta();
            Matriz salida= new Matriz(salidas.get(salidas.size()-i-2).getMatrizIndep());
            matriz2=error.multiplicar(wTranspuesta).multiplicacionPuntoAPunto(salida).multiplicacionPuntoAPunto(Matriz.matrizDeUnos(salida).resta(salida));
            System.out.println("Error("+(capas.size()-i-1)+"):\n"+matriz2);
            errores.add(0,matriz2);

        }
        for (int i = 0; i < Ncapas; i++) {
            //delta w = aprendizaje por salida(k-1)trans por error k
            Matriz salidaTrans = new Matriz(salidas.get(i).transpuesta().getMatrizIndep());
            Matriz errorK= new Matriz(errores.get(i).getMatrizIndep());


            Matriz deltaW=salidaTrans.multiplicarPorValor(aprendizaje).multiplicar(errorK);
            Matriz deltaBias=errorK.multiplicarPorValor(aprendizaje);

            System.out.println("Delta W("+(capas.size()-i)+"):\n"+deltaW);
            System.out.println("Delta Bias("+(capas.size()-i)+"):\n"+deltaBias);
            deltasW.add(deltaW);
            deltasBias.add(deltaBias);
            Matriz nuevoW=deltaW.suma(capas.get(i).getW());
            Matriz nuevoBias=deltaBias.suma(capas.get(i).getBias());
            System.out.println("Nuevo W("+(capas.size()-i)+"):\n"+nuevoW);
            System.out.println("Nuevo Bias("+(capas.size()-i)+"):\n"+nuevoBias);
            Capa capaNueva =new Capa(nuevoW.getMatrizIndep(),nuevoBias.getMatrizIndep());
            capas.remove(i);
            capas.add(i,capaNueva);


        }






    }
}
