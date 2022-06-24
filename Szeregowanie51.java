package com.company;

import java.util.*;

public class Szeregowanie51 {

    static class Pracownik {

        int index;
        int czasPracy;

        public Pracownik(int index) {
            this.index = index;
            this.czasPracy = 0;
        }

        @Override
        public String toString() {
            return "Pracownik{" +
                    "index=" + index +
                    ", czasPracy=" + czasPracy +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        List<Integer> raports = new ArrayList<>();
        for(int i = 0; i<n; i++){
            raports.add(scanner.nextInt());
        }

        raports.sort(Comparator.reverseOrder());

        List<Pracownik> pracownikList = new ArrayList<>();
        for(int i = 0; i<m; i++){
            pracownikList.add(new Pracownik(i+1));
        }

        for(int i=0; i<n; i++){
            Pracownik min = pracownikList.stream().min(Comparator.comparing(x->x.czasPracy)).orElse(null);
            min.czasPracy+=raports.get(i);
        }

//        System.out.println(raports);
//        System.out.println(pracownikList);


        int max = pracownikList.stream().max(Comparator.comparing(x->x.czasPracy)).get().czasPracy;
        System.out.println(max);
    }
}
