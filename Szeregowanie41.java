package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Szeregowanie41 {

    static class Order{
        int index;
        int czasWykonania;
        int czasUkonczenia;
        int termin;


        public Order(int index, int czasWykonania, int termin) {
            this.index = index;
            this.czasWykonania = czasWykonania;
            this.termin = termin;
            this.czasUkonczenia = 0;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "index=" + index +
                    ", czasWykonania=" + czasWykonania +
                    ", czasUkonczenia=" + czasUkonczenia +
                    ", termin=" + termin +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        List<Order> orders = new ArrayList<>();
        for(int i = 0; i< num; i++){
            orders.add(new Order(i+1, scanner.nextInt(), scanner.nextInt()));
        }
        List<Order> sortedOrders = orders.stream().sorted(Comparator
                .comparingInt(x->x.termin))
                .collect(Collectors.toList());

        AtomicInteger time = new AtomicInteger(0);
        sortedOrders.forEach(x->{
            x.czasUkonczenia = x.czasWykonania+time.get();
            time.getAndSet(x.czasUkonczenia);
        });

        AtomicInteger time2 = new AtomicInteger(0);
        List<Order> finalOrder = new ArrayList<>();
        sortedOrders.forEach(x->{
//                    System.out.println(finalOrder);
                    time2.set(time2.get() + x.czasWykonania);
                    finalOrder.add(x);
                    if(!(x.termin-time2.get()>=0)){
                        Order max = finalOrder.stream()
                                .max(Comparator.comparing(y->y.czasWykonania))
                                .orElse(null);
                        finalOrder.remove(max);
                        time2.set(time2.get() - max.czasWykonania);
                    }

        }
    );


//        System.out.println(sortedOrders);
        System.out.println(finalOrder.size());

    }
}
