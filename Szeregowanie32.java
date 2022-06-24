package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Szeregowanie32 {

    static class Gospodarstwo{
        int index;
        int czasBudowyP;
        int a, b, c;
        double wysokoscStrat;
        ArrayList<Integer> poprzedniki = new ArrayList<>();
        ArrayList<Integer> nastepniki = new ArrayList<>();

        public Gospodarstwo(int index, int czasBudowyP, int a, int b, int c) {
            this.index = index;
            this.czasBudowyP = czasBudowyP;
            this.a = a;
            this.b = b;
            this.c = c;

        }

        public void checkWysokoscStrat(int time) {
            this.wysokoscStrat = a*Math.pow(time,2)+b*time+c;
        }

        @Override
        public String toString() {
            return "Gospodarstwo{" +
                    "index=" + index +
                    ", czasBudowyP=" + czasBudowyP +
                    ", a=" + a +
                    ", b=" + b +
                    ", c=" + c +
                    '}';
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Gospodarstwo> gospodarstwa = new ArrayList<>();
        int liczbaGospodarstw = scanner.nextInt();
        for (int i = 1; i<=liczbaGospodarstw; i++){
            int p = scanner.nextInt();
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            gospodarstwa.add(new Gospodarstwo(i, p, a, b, c));

        }

        int instrukcje = scanner.nextInt();
        for(int i = 0; i < instrukcje; i++){
            int before = scanner.nextInt();
            int oper = scanner.nextInt();
            gospodarstwa.get(before-1).nastepniki.add(oper);
            gospodarstwa.get(oper-1).poprzedniki.add(before);
        }

        Stack<Gospodarstwo> stack = new Stack<>();
        int totalTime = gospodarstwa.stream().map(x->x.czasBudowyP).reduce(Integer::sum).orElse(0);
//        System.out.println(totalTime);
//
        int i = 1;
        while(!gospodarstwa.isEmpty()){
//            System.out.println(i + ". ");
            i++;

            List<Gospodarstwo> bezNastepnikow =  gospodarstwa.stream().filter(x->x.nastepniki.isEmpty()).collect(Collectors.toList());
            for (Gospodarstwo gospodarstwo : bezNastepnikow) {
                gospodarstwo.checkWysokoscStrat(totalTime);
            }

            Gospodarstwo min = Collections.min(bezNastepnikow, Comparator.comparing(x->x.wysokoscStrat));
            for(Gospodarstwo gospodarstwo : gospodarstwa){
                if(gospodarstwo.nastepniki.contains(min.index)){
                    gospodarstwo.nastepniki.remove((Integer)min.index);
                }
            }
            stack.add(min);
            gospodarstwa.remove(min);
            totalTime-=min.czasBudowyP;

//            bezNastepnikow.forEach(x-> System.out.println(x.index+ "-> " + x.wysokoscStrat));
//            stack.forEach(x-> System.out.print(x.index + " "));
//            System.out.println("\n");
        }
        System.out.println((int)Collections.max(stack, Comparator.comparing(x->x.wysokoscStrat)).wysokoscStrat);

    }
}
