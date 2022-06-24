package com.company;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Szeregowanie53 {

    static class Modul{
        int index;
        List<Integer> poprzednicy;
        int nastepnik;
        int poziom;

        public Modul(int index) {
            this.index = index;
            this.poprzednicy = new ArrayList<>();
            this.poziom = 0;
        }

        @Override
        public String toString() {
            return "Modul{" +
                    "index=" + index +
                    ", poprzednicy=" + poprzednicy +
                    ", nastepnik=" + nastepnik +
                    ", poziom=" + poziom +
                    '}';
        }
    }

    static class Tester{
        int index;
        List<Integer> testowaneModuly;

        public Tester(int index) {
            this.index = index;
            testowaneModuly = new ArrayList<>();
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(index).append(": ");
            testowaneModuly.forEach(x->{
                stringBuilder.append(x);
                stringBuilder.append(" ");
            });
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        List<Tester> testerzy = new ArrayList<>();
        ArrayList<Modul> moduly = new ArrayList<>();

        for (int i = 1; i<= m; i++){
            testerzy.add(new Tester(i));
        }

        for(int i = 1; i<=n; i++){
            moduly.add(new Modul(i));
        }


        for(int i = 0; i<n; i++){
            int num = scanner.nextInt();
            for(int j = 0; j<num; j++){
                int mod = scanner.nextInt();
                moduly.get(i).poprzednicy.add(mod);
                moduly.get(mod-1).nastepnik = i+1;
            }

        }

        Modul korzen = moduly.stream().filter(x->x.nastepnik==0).findAny().get();
        checkPoziom(korzen, moduly);
//        moduly.forEach(System.out::println);


        int time = 0;
        while(!moduly.isEmpty()){
            ArrayList<Modul> sorted = moduly.stream()
                .filter(x->x.poprzednicy.isEmpty())
                .sorted((x1,x2) -> Integer.compare(x2.poziom, x1.poziom))
                .collect(Collectors.toCollection(ArrayList::new));
             for(int i = 0; i<m; i++){
                 if(sorted.isEmpty()){
                     break;
                 } else {
                     Modul mod = sorted.get(0);
                     testerzy.get(i).testowaneModuly.add(mod.index);

                     moduly.stream().filter(x->x.poprzednicy.contains(mod.index))
                             .forEach(x->x.poprzednicy.remove(Integer.valueOf(mod.index)));

                     moduly.remove(mod);
                     sorted.remove(0);

                 }

             }
            time++;

    }
        System.out.println(time);
        for(Tester tester :testerzy){
            if (tester.index < m){
                System.out.println(tester);
            }else{
                System.out.print(tester);
            }
        }
    }

    static void checkPoziom(Modul modul, ArrayList<Modul> arrayList){

       if(modul.nastepnik==0){
           modul.poziom = 0;
       }else {
           modul.poziom = arrayList.get(modul.nastepnik-1).poziom + 1;
       }
       for(Integer i: modul.poprzednicy){
           checkPoziom(arrayList.get(i-1),arrayList);
       }

    }
}
