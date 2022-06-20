import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        System.out.println("LLLLLLLLLLL                            AAA                    RRRRRRRRRRRRRRRRR   EEEEEEEEEEEEEEEEEEEEEEDDDDDDDDDDDDD        \n" +
                "L:::::::::L                           A:::A                   R::::::::::::::::R  E::::::::::::::::::::ED::::::::::::DDD     \n" +
                "L:::::::::L                          A:::::A                  R::::::RRRRRR:::::R E::::::::::::::::::::ED:::::::::::::::DD   \n" +
                "LL:::::::LL                         A:::::::A                 RR:::::R     R:::::REE::::::EEEEEEEEE::::EDDD:::::DDDDD:::::D  \n" +
                "  L:::::L                          A:::::::::A                  R::::R     R:::::R  E:::::E       EEEEEE  D:::::D    D:::::D \n" +
                "  L:::::L                         A:::::A:::::A                 R::::R     R:::::R  E:::::E               D:::::D     D:::::D\n" +
                "  L:::::L                        A:::::A A:::::A                R::::RRRRRR:::::R   E::::::EEEEEEEEEE     D:::::D     D:::::D\n" +
                "  L:::::L                       A:::::A   A:::::A               R:::::::::::::RR    E:::::::::::::::E     D:::::D     D:::::D\n" +
                "  L:::::L                      A:::::A     A:::::A              R::::RRRRRR:::::R   E:::::::::::::::E     D:::::D     D:::::D\n" +
                "  L:::::L                     A:::::AAAAAAAAA:::::A             R::::R     R:::::R  E::::::EEEEEEEEEE     D:::::D     D:::::D\n" +
                "  L:::::L                    A:::::::::::::::::::::A            R::::R     R:::::R  E:::::E               D:::::D     D:::::D\n" +
                "  L:::::L         LLLLLL    A:::::AAAAAAAAAAAAA:::::A           R::::R     R:::::R  E:::::E       EEEEEE  D:::::D    D:::::D \n" +
                "LL:::::::LLLLLLLLL:::::L   A:::::A             A:::::A        RR:::::R     R:::::REE::::::EEEEEEEE:::::EDDD:::::DDDDD:::::D  \n" +
                "L::::::::::::::::::::::L  A:::::A               A:::::A       R::::::R     R:::::RE::::::::::::::::::::ED:::::::::::::::DD   \n" +
                "L::::::::::::::::::::::L A:::::A                 A:::::A      R::::::R     R:::::RE::::::::::::::::::::ED::::::::::::DDD     \n" +
                "LLLLLLLLLLLLLLLLLLLLLLLLAAAAAAA                   AAAAAAA     RRRRRRRR     RRRRRRREEEEEEEEEEEEEEEEEEEEEEDDDDDDDDDDDDD        ");
        System.out.println("Hecho por Raúl Baeza Osuna");
        Red red1=new Red();

        int fin;
        Scanner sc = new Scanner(System.in);
        do{
            red1.propagacion();
            red1.retroPropagacion();
            System.out.println("Si quieres seguir pon un 1");
            fin =sc.nextInt();

        }while(fin==1);









    }
}
