package ralobh.prd_test_1;

import java.util.Random;
import java.util.Scanner;

public class PRD {

    public static double K; //Variabel Konstanta Global

    public static void thePRD(double kons){
        Random rand = new Random(); // Random
        boolean proc = false; //Nandain Klo proc atau ga
        int prdCounter = 1; // P(A) = C*N - prdCounter ini sama dengan N
        int tryCounter = 0; // Berapa Kali Coba

        while(tryCounter != 10){
            if(kons*(prdCounter) >= 1.0)
            {
                proc = true;
                prdCounter = 1;
                tryCounter++;
                System.out.println("proc");

				/*Debug
				System.out.printf("Konstanta * %d\n", prdCounter);
				System.out.println(kons*(prdCounter));*/
            }
            else if(rand.nextDouble() <= kons*(prdCounter))
            {
                proc = true;
                prdCounter = 1;
                tryCounter++;
                System.out.println("proc");

				/*Debug
				System.out.printf("Konstanta * %d\n", prdCounter);
				System.out.println(kons*(prdCounter));*/
            }
            else
            {
                proc = false;
                prdCounter++;
                tryCounter++;
                System.out.println("noproc");

				/*Debug
				System.out.printf("Konstanta * %d\n", prdCounter);
				System.out.println(kons*(prdCounter));*/
            }
        }
    }

    public static void theCases(int a){
        switch(a)
        {
            case 1:
                //1. Axe - Counter Helix (20% Chance)
                K = 0.05570;
                thePRD(K);
                break;
            case 21:
                //21. Brewmaster - Drunken Brawler level 1 (10% Chance)
                K = 0.01475;
                thePRD(K);
                break;
            case 22:
                //22. Brewmaster - Drunken Brawler level 2 (15% Chance)
                K = 0.03221;
                thePRD(K);
                break;
            case 23:
                //23. Brewmaster - Drunken Brawler level 3 (20% Chance)
                K = 0.05570;
                thePRD(K);
                break;
            case 24:
                //24. Brewmaster - Drunken Brawler level 4 (25% Chance)
                K = 0.08475;
                thePRD(K);
                break;
            case 3:
                //3. Chaos Knight - Chaos Strike (10% Chance)
                K = 0.01475;
                thePRD(K);
                break;
            case 41:
                K = 0.01475;
                thePRD(K);
                break;
            case 42:
                K = 0.03221;
                thePRD(K);
                break;
            case 43:
                K = 0.05570;
                thePRD(K);
                break;
            case 44:
                K = 0.08475;
                thePRD(K);
                break;
        }
    }

    public static void main(String[] args){
        System.out.println("Welcome to Dota 2 PRD Simulation");
        System.out.println("====================================================================================================");
        System.out.println("Select which items you want to simulate by entering the correct number according to the list below\n");
        System.out.println("1. Axe - Counter Helix (20% Chance)");
        System.out.println("21. Brewmaster - Drunken Brawler level 1 (10% Chance)");
        System.out.println("22. Brewmaster - Drunken Brawler level 2 (15% Chance)");
        System.out.println("23. Brewmaster - Drunken Brawler level 3 (20% Chance)");
        System.out.println("24. Brewmaster - Drunken Brawler level 4 (25% Chance)");
        System.out.println("3. Chaos Knight - Chaos Strike (10% Chance)");
        System.out.println("41. Faceless Void - Time Lock level 1 (10% Chance)");
        System.out.println("42. Faceless Void - Time Lock level 2 (15% Chance)");
        System.out.println("43. Faceless Void - Time Lock level 1 (20% Chance)");
        System.out.println("44. Faceless Void - Time Lock level 1 (25% Chance)");

        System.out.println("Enter your selection :");

        int select;
        Scanner s = new Scanner(System.in);
        select = s.nextInt();

        theCases(select); // Masuk Case
    }
}
