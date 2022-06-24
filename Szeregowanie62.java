package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Szeregowanie62 {

    static class Maszyna {

        int index;
        int czasWykonania;
        List<Swider> procesy;
        int waga;

        public Maszyna(int index, int czasWykonania) {
            this.index = index;
            this.czasWykonania = czasWykonania;
            this.procesy = new ArrayList<>();
            this.waga =  czasWykonania;
        }

        @Override
        public String toString() {
            return "Maszyna{" +
                    "index=" + index +
                    ", czasWykonania=" + czasWykonania +
                    ", procesy=" + procesy +
                    ", waga=" + waga +
                    '}';
        }
    }

    static class Swider{

        int index;
        int rozmiar;
        int czasUkonczenia;

        public Swider(int index, int rozmiar) {
            this.index = index;
            this.rozmiar = rozmiar;
            this.czasUkonczenia = 0;
        }

        @Override
        public String toString() {
            return "Swider{" +
                    "index=" + index +
                    ", rozmiar=" + rozmiar +
                    ", czasWykonania=" + czasUkonczenia +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        List<Maszyna> maszyny = new ArrayList<>();
        List<Swider> swidry = new ArrayList<>();

        for(int i = 0; i<m; i++){
            maszyny.add(new Maszyna(i+1, scanner.nextInt()));
        }

        for(int i = 0; i<n; i++){
            swidry.add(new Swider(i+1, scanner.nextInt()));
        }

//        maszyny.forEach(System.out::println);
        swidry = swidry.stream().sorted((x1,x2) -> Integer.compare(x2.rozmiar, x1.rozmiar)).collect(Collectors.toList());
//        swidry.forEach(System.out::println);

        for(int i = 0; i<n; i++){
            Maszyna min = maszyny.stream().min(Comparator.comparingDouble(x->x.waga)).orElse(null);
            min.procesy.add(0, swidry.get(i));
            int toAdd = swidry.get(i).rozmiar * min.czasWykonania;
            min.procesy.forEach(x->
                x.czasUkonczenia += toAdd
            );
            min.waga += min.czasWykonania;
        }

//        maszyny.forEach(System.out::println);
        int suma = swidry.stream().mapToInt(x->x.czasUkonczenia).sum();
        System.out.println(suma);
    }
}
