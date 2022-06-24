package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Szeregowanie82 {

    static class Process{
        int index;
        int czasWycinania;
        int czasWiercenia;
        int czasUkonczeniaWycinania;
        int czasukonczeniaWiercenia;

        public Process(int index, int czasWyciecia, int czasWiercenia) {
            this.index = index;
            this.czasWycinania = czasWyciecia;
            this.czasWiercenia = czasWiercenia;
            this.czasUkonczeniaWycinania = 0;
            this.czasukonczeniaWiercenia = 0;
        }

        @Override
        public String toString() {
            return index + " " + czasUkonczeniaWycinania + " " + czasukonczeniaWiercenia;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Process> processes = new ArrayList<>();
        for (int i = 0; i<n; i++){
            processes.add(new Process(i+1, scanner.nextInt(), scanner.nextInt()));
        }
        List<Process> aList = processes.stream().filter(x->x.czasWiercenia>x.czasWycinania).collect(Collectors.toList());
        List<Process> bList = processes.stream().filter(x->x.czasWiercenia<=x.czasWycinania).collect(Collectors.toList());

//        aList.forEach(System.out::println);
//        bList.forEach(System.out::println);


        int time1 = 0;
        int time2 = 0;
        for(int i = 0; i<n; i++){
            Process proc;
            if(!aList.isEmpty()){
                proc = aList.stream().min(Comparator.comparing(x->x.czasWycinania)).orElse(null);
                aList.remove(proc);
            } else {
                proc = bList.stream().max(Comparator.comparing(x->x.czasWiercenia)).orElse(null);
                bList.remove(proc);
            }
            processes.get(processes.indexOf(proc)).czasUkonczeniaWycinania = time1 + proc.czasWycinania;
            time1+=proc.czasWycinania;


            time2 = Math.max(time1, time2);
            processes.get(processes.indexOf(proc)).czasukonczeniaWiercenia = time2 + proc.czasWiercenia;
            time2+=proc.czasWiercenia;


        }

        processes.stream().sorted(Comparator.comparing(x->x.czasUkonczeniaWycinania)).forEach(System.out::println);

    }
}
