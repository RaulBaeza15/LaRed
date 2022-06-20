public class Capa {
    private Matriz W;
    private Matriz bias;
    public Capa(){

    }
    public Capa(double[][] W,double[][] bias){
        this.W=new Matriz(W);
        this.bias=new Matriz(bias);
    }

    public Matriz getBias() {
        return bias;
    }

    public Matriz getW() {
        return W;
    }
}
