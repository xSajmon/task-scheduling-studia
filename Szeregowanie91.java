package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Szeregowanie91 {

    static class Process{
        int index;
        int czasWykrawania;
        int czasSzycia;
        int czasOzdabiania;
        int czasUkonczeniaSzycia;
        int czasUkonczeniaOzdabiania;

        public Process(int index, int czasWykrawania, int czasSzycia, int czasOzdabiania) {
            this.index = index;
            this.czasWykrawania = czasWykrawania;
            this.czasSzycia = czasSzycia;
            this.czasOzdabiania = czasOzdabiania;
            this.czasUkonczeniaSzycia = 0;
            this.czasUkonczeniaOzdabiania = 0;
        }

        @Override
        public String toString() {
            return "Process{" +
                    "index=" + index +
                    ", czasWykrawania=" + czasWykrawania +
                    ", czasSzycia=" + czasSzycia +
                    ", czasOzdabiania=" + czasOzdabiania +
                    ", czasUkonczeniaSzycia=" + czasUkonczeniaSzycia +
                    ", czasUkonczeniaOzdabiania=" + czasUkonczeniaOzdabiania +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Process> processes = new ArrayList<>();
        int sum = 0;
        for(int i = 1; i<=n; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            processes.add(new Process(i, a, a+b,b+c));
            sum +=b;
        }

        List<Process> aList = processes.stream().filter(x->x.czasSzycia<=x.czasOzdabiania).collect(Collectors.toList());
        List<Process> bList = processes.stream().filter(x->x.czasSzycia>x.czasOzdabiania).collect(Collectors.toList());
//        aList.forEach(System.out::println);
////        System.out.println();
//        bList.forEach(System.out::println);

        int time1 = 0;
        int time2 = 0;
        for(int i = 0; i<n; i++){
            Process proc;
            if(!aList.isEmpty()){
                proc = aList.stream().min(Comparator.comparing(x->x.czasSzycia)).orElse(null);
                aList.remove(proc);
            } else {
                proc = bList.stream().max(Comparator.comparing(x->x.czasOzdabiania)).orElse(null);
            bList.remove(proc);
            }


            processes.get(processes.indexOf(proc)).czasUkonczeniaSzycia = proc.czasSzycia + time1;
            time1+=proc.czasSzycia ;


            time2 = Math.max(time1, time2);
            processes.get(processes.indexOf(proc)).czasUkonczeniaOzdabiania = time2 + proc.czasOzdabiania;
            time2+=proc.czasOzdabiania;

        }

        int max = processes.stream().max(Comparator.comparing(x->x.czasUkonczeniaOzdabiania)).get().czasUkonczeniaOzdabiania;
        System.out.println(max-sum);
    }

}
