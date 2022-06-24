package com.company;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Szeregowanie63 {


    static class Zabawka {
        int index;
        int premia;
        int termin;

        public Zabawka(int index, int premia, int liczbaGodzin) {
            this.index = index;
            this.premia = premia;
            this.termin = liczbaGodzin;
        }

        @Override
        public String toString() {
            return "Zabawka" + index +
                    "{ premia=" + premia +
                    ", termin=" + termin +
                    '}';
        }
    }

    static class Pracownik {
        int index;
        List<Zabawka> processes;

        public Pracownik(int index) {
            this.index = index;
            this.processes = new ArrayList<>();
        }

        @Override
        public String toString() {
            return "Pracownik" + index +
                    "{ processes=" + processes +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        List<Pracownik> pracownicy = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            pracownicy.add(new Pracownik(i + 1));
        }

        List<Zabawka> zabawki = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            zabawki.add(new Zabawka(i + 1, scanner.nextInt(), scanner.nextInt()));
        }

        zabawki.sort(Comparator.comparing(x -> x.termin));

        List<Zabawka> wykonane = new ArrayList<>();

        int iteracja = 1;
        int time = 1;
        int ind = 0;
        int sum = 0;
        while (ind < n) {
            for (int j = 0; j < m; j++) {
                if (ind == n) break;
                Zabawka nextToy = zabawki.get(ind);

                if (pracownicy.get(j).processes.size() < nextToy.termin) {
                    wykonane.add(nextToy);
                    pracownicy.get(j).processes.add(nextToy);

                } else {
                    Zabawka min = wykonane.stream()
                            .min(Comparator.comparing(x -> x.premia))
                            .orElse(null);
                    if (min.premia < nextToy.premia) {
                        wykonane.set(wykonane.indexOf(min), nextToy);
                        pracownicy.forEach(x -> {
                            if (x.processes.contains(min)) {
                                x.processes.set(x.processes.indexOf(min), nextToy);
                            }
                        });
                        sum += min.premia;
                    } else {
                        sum += nextToy.premia;
                    }

                }
                ind++;
//                System.out.println(iteracja);
//                System.out.println("Next toy to add: " + nextToy);
//                System.out.println("Time: " + time);
//                System.out.print("Completed: ");
//                wykonane.forEach(x -> System.out.print(x.index + ", "));
//                System.out.println();
//                System.out.println("Maszyny: ");
//                pracownicy.forEach(System.out::println);
//                System.out.println();
//                iteracja++;

            }
            time++;
        }
//        pracownicy.forEach(System.out::println);
//        wykonane.forEach(System.out::println);
        System.out.println(sum);
    }
}