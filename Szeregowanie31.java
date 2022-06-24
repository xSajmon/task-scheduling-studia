package com.company;

import java.util.*;

public class Szeregowanie31 {

    public static class Process {
        private int index;
        private int czasWykonania;
        private int czasOczekiwania;
        private int czasUkonczenia;
        private boolean isUkonczone;
        private ArrayList<Integer> poprzedniki = new ArrayList<>();

        public Process(int index, int czasOczekiwania, int czasWykonania) {
            this.index = index;
            this.czasWykonania = czasWykonania;
            this.czasOczekiwania = czasOczekiwania;
            this.isUkonczone = false;
            this.czasUkonczenia = 0;
        }

        @Override
        public String toString() {
            return "P" +
                    index +
                    ", wyk:" + czasWykonania +
                    ", oczek=" + czasOczekiwania +
                    ", ukon=" + czasUkonczenia +
                    ", isU=" + isUkonczone +
                    ", pop=" + poprzedniki +
                    '}';
        }

    }

    public static boolean checkUkonczone(Collection<Process> processes) {
        return processes.stream().allMatch(x->x.isUkonczone);
    }

    public static Process findNajkrotsze(Collection<Process> processes) {
        return processes.stream()
                .filter(x->!x.isUkonczone)
                .min(Comparator.comparingInt(x->x.czasWykonania))
                .orElse(null);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Process> processes = new ArrayList<>();

        int czasy = scanner.nextInt();
        for (int i = 1; i <= czasy; i++) {
            int czekanie = scanner.nextInt();
            int wykonanie = scanner.nextInt();
            processes.add(new Process(i, czekanie, wykonanie));
        }

        int instrukcje = scanner.nextInt();
        for (int i = 0; i < instrukcje; i++) {
            int before = scanner.nextInt();
            int oper = scanner.nextInt();
            processes.get(oper - 1).poprzedniki.add(before);
        }

        while (!checkUkonczone(processes)) {

            Queue<Process> queue = new LinkedList<>();
            for (Process process : processes) {
                if (process.czasOczekiwania == 0 && process.poprzedniki.isEmpty()) {
                    queue.add(process);
                }
            }

            if (queue.isEmpty()) {
                for (Process p : processes) {
                    if (p.czasOczekiwania > 0 && p.czasWykonania > 0) {
                        p.czasOczekiwania--;
                        p.czasUkonczenia++;
                    }
                }
            } else {
                Process process = findNajkrotsze(queue);
//                System.out.println(process);
                for (Process p : processes) {
                    if (p.equals(process)) {
                        if (p.czasWykonania > 0) {
                            p.czasWykonania--;
                            if (p.czasWykonania == 0) {
                                p.isUkonczone = true;
                                for (Process p2 : processes) {

                                    if (p2.poprzedniki.contains(process.index)) {
                                        p2.poprzedniki.remove((Integer) process.index);
                                        if (p2.poprzedniki.size() == 0) {
                                            queue.add(p2);
                                        }
                                    }
                                }
                            }
                            p.czasUkonczenia++;
                        }
                    } else {
                        if (p.czasOczekiwania == 0 && !p.isUkonczone) {
                            p.czasUkonczenia++;

                        } else if (p.czasWykonania > 0 && p.czasOczekiwania > 0) {
                            if (!p.isUkonczone) {
                                p.czasOczekiwania--;
                                p.czasUkonczenia++;
                            }
                        }
                    }
                }
            }
        }
//        processes.forEach(x -> System.out.println(x.index + " -> " + x.czasUkonczenia));
        System.out.println(Collections.max(processes, Comparator.comparing(x -> x.czasUkonczenia)).czasUkonczenia);
    }
}
