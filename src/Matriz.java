import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Matriz {
    private double[][] matriz;

    public Matriz(double[][] matriz) {


        if (matriz.length>0){
            this.matriz=new double[matriz.length][matriz[0].length];
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    this.matriz[i][j]=matriz[i][j];

                }
            }
        }else{
            this.matriz=new double[0][0];
        }



    }
    public static double [][] preguntarMatriz(){
        
        Scanner sc = new Scanner(System.in);
        double [][] matriz2;

        String cadena= sc.nextLine();

        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(cadena);


        ArrayList<String> filas=new ArrayList<>();
        while(m.find()) {
            String fila=m.group(1);

            filas.add(fila);

        }

        if (filas.size()>0){
            matriz2=new double[filas.size()][filas.get(0).split("\\,").length];


            int ancho=filas.get(0).split("\\,").length,alto=filas.size();

            for (int i = 0; i < alto; i++) {
                for (int j = 0; j < ancho; j++) {
                    matriz2[i][j]=Double.parseDouble(filas.get(i).split("\\,")[j]);
                }
            }
        }else{
            matriz2=new double[0][0];
        }

        return matriz2;
    }



    public double[][] getMatriz() {
        return matriz;
    }
    public double[][] getMatrizIndep() {
        double[][] matrizRes;
        if (matriz.length>0){
            matrizRes =new double[matriz.length][matriz[0].length];
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[0].length; j++) {
                    matrizRes[i][j]=matriz[i][j];

                }
            }
        }else{
            matrizRes=new double[0][0];
        }
        return matrizRes;
    }

    public void setMatriz(double[][] matriz) {
        this.matriz = matriz;
    }

    @Override
    public String toString() {
        String res="";
        for (int i = 0; i < matriz.length; i++) {
            res= res+"[";
            for (int j = 0; j < matriz[0].length; j++) {
               res=res + matriz[i][j]+" ";
            }
            res= res+"]\n";
        }
        return res;
    }
    public Matriz multiplicar(Matriz matriz){
        double[][] matriz1=this.getMatrizIndep();
        double[][] matriz2=matriz.getMatrizIndep();
        Matriz res=null;

        if(matriz1[0].length==matriz2.length){
            double[][] matrizRes= new double[matriz1.length][matriz2[0].length];
            for (int i = 0; i < matrizRes.length; i++) {
                for (int j = 0; j <matrizRes[0].length ; j++) {
                    matrizRes[i][j]=0;
                    for (int k = 0; k < matriz2.length; k++) {
                        matrizRes[i][j]=matrizRes[i][j]+matriz1[i][k]*matriz2[k][j];
                    }
                }
            }
            res=new Matriz(matrizRes);
        }
        return res;

    }
    public Matriz suma(Matriz matriz){
        double[][] matriz1=this.getMatrizIndep();
        double[][] matriz2=matriz.getMatrizIndep();
        Matriz res=null;

        if(matriz1.length==matriz2.length&&matriz1[0].length==matriz2[0].length){
            double[][] matrizRes= new double[matriz1.length][matriz1[0].length];

            for (int i = 0; i < matrizRes.length; i++) {
                for (int j = 0; j <matrizRes[0].length ; j++) {
                    matrizRes[i][j]=matriz1[i][j]+matriz2[i][j];

                }
            }
            res=new Matriz(matrizRes);
        }
        return res;
    }
    public Matriz resta(Matriz matriz){
        double[][] matriz1=this.getMatrizIndep();
        double[][] matriz2=matriz.getMatrizIndep();
        Matriz res=null;

        if(matriz1.length==matriz2.length&&matriz1[0].length==matriz2[0].length){
            double[][] matrizRes= new double[matriz1.length][matriz1[0].length];

            for (int i = 0; i < matrizRes.length; i++) {
                for (int j = 0; j <matrizRes[0].length ; j++) {
                    matrizRes[i][j]=matriz1[i][j]-matriz2[i][j];

                }
            }
            res=new Matriz(matrizRes);
        }
        return res;
    }
    public Matriz multiplicacionPuntoAPunto(Matriz matriz){
        double[][] matriz1=this.getMatrizIndep();
        double[][] matriz2=matriz.getMatrizIndep();
        Matriz res=null;

        if(matriz1.length==matriz2.length&&matriz1[0].length==matriz2[0].length){
            double[][] matrizRes= new double[matriz1.length][matriz1[0].length];

            for (int i = 0; i < matrizRes.length; i++) {
                for (int j = 0; j <matrizRes[0].length ; j++) {
                    matrizRes[i][j]=matriz1[i][j]*matriz2[i][j];

                }
            }
            res=new Matriz(matrizRes);
        }
        return res;
    }
    public static Matriz matrizDeUnos(Matriz copia){
        double[][] matriz=new double[copia.getMatriz().length][copia.getMatriz()[0].length] ;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                matriz[i][j]=1;
            }
        }

        return new Matriz(matriz);


    }
    public Matriz funcionActivacion(){
        double[][] matriz1=this.getMatrizIndep();

        Matriz res=null;


        double[][] matrizRes= new double[matriz1.length][matriz1[0].length];

        for (int i = 0; i < matrizRes.length; i++) {
            for (int j = 0; j <matrizRes[0].length ; j++) {
                matrizRes[i][j]=1/(1+Math.pow(Math.E,- matriz1[i][j]) );

            }
        }
        res=new Matriz(matrizRes);

        return res;
    }
    public Matriz multiplicarPorValor(double valor){
        double[][] matriz1=this.getMatrizIndep();

        Matriz res=null;


        double[][] matrizRes= new double[matriz1.length][matriz1[0].length];

        for (int i = 0; i < matrizRes.length; i++) {
            for (int j = 0; j <matrizRes[0].length ; j++) {
                matrizRes[i][j]=matriz1[i][j] *valor;

            }
        }
        res=new Matriz(matrizRes);

        return res;
    }
    public Matriz transpuesta(){
        double[][] matriz1=this.getMatrizIndep();

        Matriz res=null;


        double[][] matrizRes= new double[matriz1[0].length][matriz1.length];

        for (int i = 0; i < matrizRes.length; i++) {
            for (int j = 0; j <matrizRes[0].length ; j++) {
                matrizRes[i][j]=matriz1[j][i];

            }
        }
        res=new Matriz(matrizRes);

        return res;
    }
}
