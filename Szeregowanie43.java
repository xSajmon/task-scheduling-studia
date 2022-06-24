package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Szeregowanie43 {
    static class Order{
        int index;
        int czasOczekiwania;
        int czasWykonania;
        int czasZakonczenia;
        int termin;
        boolean isUkonczone;

        public Order(int index, int czasOczekiwania, int czasWykonania, int termin) {
            this.index = index;
            this.czasOczekiwania = czasOczekiwania;
            this.czasWykonania = czasWykonania;
            this.termin = termin;
            this.isUkonczone = false;
            this.czasZakonczenia = 0;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "index=" + index +
                    ", czasOczekiwania=" + czasOczekiwania +
                    ", czasWykonania=" + czasWykonania +
                    ", termin=" + termin +
                    ", czasZakonczenia=" + czasZakonczenia +
                    '}';
        }
    }
    public static boolean checkUkonczone(Collection<Order> processes) {
        return processes.stream().allMatch(x->x.isUkonczone);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        List<Order> orders = new ArrayList<>();

        for(int i = 0; i<num; i++){
            orders.add(new Order(i+1, scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }

        List<Order> sortedOrders = orders.stream().sorted(Comparator
                        .comparingInt(x->x.czasOczekiwania))
                .collect(Collectors.toList());

        int i = 1;
        while(!checkUkonczone(sortedOrders)) {
            List<Order> readyTasks = sortedOrders.stream()
                    .filter(x -> x.czasOczekiwania == 0 && !x.isUkonczone)
                    .collect(Collectors.toList());
            if(readyTasks.isEmpty()){
                sortedOrders.forEach(x->{
                    if (x.czasOczekiwania >0){
                        x.czasOczekiwania--;
                        x.czasZakonczenia++;
                    }
                });
            } else{
                Order min = readyTasks.stream().min(Comparator.comparing(x->x.termin)).orElse(null);
                min.czasWykonania--;


                sortedOrders.forEach(x->{
                    if (!x.isUkonczone){
                        if(x.czasOczekiwania>0) {
                            x.czasOczekiwania--;
                        }
                        x.czasZakonczenia++;
                    }
                });
                if(min.czasWykonania==0){
                    min.isUkonczone = true;
                    readyTasks.remove(min);
                }
            }
//            System.out.println(i++);
//            sortedOrders.forEach(System.out::println);
//            System.out.println();
        }
        int max = sortedOrders.stream().mapToInt(x->x.czasZakonczenia-x.termin).max().orElse(0);
        System.out.println(max);
    }
}
