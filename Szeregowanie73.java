package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Szeregowanie73 {

    static class Zadanie{
        int index;
        int czasWykonania;
        int liczbaPunktow;
        int termin;

        public Zadanie(int index, int czasWykonania, int liczbaPunktow, int termin) {
            this.index = index;
            this.czasWykonania = czasWykonania;
            this.liczbaPunktow = liczbaPunktow;
            this.termin = termin;
        }

        @Override
        public String toString() {
            return "Zadanie{" +
                    "index=" + index +
                    ", czasWykonania=" + czasWykonania +
                    ", liczbaPunktow=" + liczbaPunktow +
                    ", termin=" + termin +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Zadanie> zadania = new ArrayList<>();
        zadania.add(new Zadanie(0,0,0,0));
        for(int i = 0; i<n; i++){
            zadania.add(new Zadanie(i+1,
                    scanner.nextInt(),
                    scanner.nextInt(),
                    scanner.nextInt()));

        }


        int max = zadania.get(zadania.size()-1).termin;
        int [][] F = new int[n+1][max+1];

        for(int j = 0; j<=n; j++){
            for(int t = 0; t <= max; t++){
                if(j==0){
                    F[j][t] = 0;
                } else if (t==0){
                    int finalJ = j;
                    F[j][t] = zadania.stream().filter(x->x.index<= finalJ).mapToInt(x->x.liczbaPunktow).sum();
                } else if (t<=zadania.get(j).termin){
                    if(t>=zadania.get(j).czasWykonania){
                        F[j][t] = Math.min(F[j-1][t-zadania.get(j).czasWykonania], F[j-1][t]+zadania.get(j).liczbaPunktow);
                    } else {
                        F[j][t] = F[j-1][t] + zadania.get(j).liczbaPunktow;
                    }
                } else {
                    F[j][t] = F[j][zadania.get(j).termin];
                }
            }
        }

//        for(int j = 0; j<=n; j++){
//            for(int t = 0; t<=max; t++){
//                System.out.print(F[j][t] + " ");
//            }
//            System.out.println();
//        }

        int t2 = max;
        for(int j = n; j>0; j--){
            t2 = Math.min(t2, zadania.get(j).termin);
            if(F[j][t2] == F[j-1][t2] + zadania.get(j).liczbaPunktow){
                zadania.remove(zadania.get(j));
            } else {
                t2-=zadania.get(j).czasWykonania;
            }
        }

        System.out.println(zadania.stream().mapToInt(x -> x.liczbaPunktow).sum());
        zadania.stream().skip(1).mapToInt(x->x.index).forEach(System.out::println);
    }
}
