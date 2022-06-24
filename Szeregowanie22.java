package com.company;

import java.util.*;

public class Szeregowanie22 {

    public static class Process {
        private int index;
        private int czasWykonania;
        private ArrayList<Integer> poprzedniki = new ArrayList<>();
        private ArrayList<Integer> nastepniki = new ArrayList<>();


        public Process(int index, int czasWykonania) {
            this.index = index;
            this.czasWykonania = czasWykonania;
        }

        @Override
        public String toString() {
            return "P" +index +
                    "-> pop=" + poprzedniki

                    ;
        }
    }
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            List<Process> processes = new ArrayList<>();
            int czasy = scanner.nextInt();
            int instrukcje = scanner.nextInt();
            for (int i = 1; i <=czasy; i++) {
                processes.add(new Process(i, scanner.nextInt()));
            }
            for(int i = 0; i<instrukcje; i++){
                int before = scanner.nextInt();
                int oper = scanner.nextInt();
                processes.get(before-1).nastepniki.add(oper);
                processes.get(oper-1).poprzedniki.add(before);
            }
           //System.out.println(processes);

            List<Process> sorted = new ArrayList<>();
            Queue<Process> queue = new LinkedList<>();
            for(Process p : processes){
                if(p.poprzedniki.size()==0){
                    queue.add(p);
                }
            }
           //System.out.println(queue);

            while(!queue.isEmpty()){
                Process process = null;
                for (Process p: processes){
                    if(queue.contains(p)){
                        process = p;
                        queue.remove(p);
                        break;
                    }
                }
//                Process process = queue.poll();
                sorted.add(process);

                for(Process p : processes){
//                    System.out.println(process.index);
//                    System.out.println(p.poprzedniki.contains(process.index));
                    if(p.poprzedniki.contains(process.index)){
                        p.poprzedniki.remove((Integer) process.index);
                        if(p.poprzedniki.size()==0){
                            queue.add(p);
                        }
                    }
                }
            //    System.out.println("Processes:" + processes);

             //   System.out.println("Queue:" + queue);
            }
//            System.out.println(processes);


            int time = 0;
           for ( Process p : sorted){
               time+=p.czasWykonania;
               System.out.println(p.index);
           }
            System.out.println(time);
        }

    }