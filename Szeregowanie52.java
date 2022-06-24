package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Szeregowanie52 {

    static class Maszyna {

        int index;
        ArrayList<String> procesy;

        public Maszyna(int index) {
            this.index = index;
            procesy = new ArrayList<>();
        }
        public String wypiszProcesy(){
            StringBuilder stringBuilder = new StringBuilder();
            for(String str: procesy){
                stringBuilder.append(str).append(" ");
            }
            return stringBuilder.toString();
        }
        @Override
        public String toString() {
            return index + ": " + wypiszProcesy();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        List<Integer> times = new ArrayList<>();

        for(int i = 0; i<n; i++){
            times.add(scanner.nextInt());
        }

        int suma = times.stream().reduce(0, Integer::sum);
        int maxValue = Collections.max(times);
        int max = Math.max(suma/m, maxValue);

//        System.out.println(times);
//        System.out.println(suma);
//        System.out.println("maxValue = " + maxValue + ", max = " + max);


        List<Maszyna> maszyny = new ArrayList<>();
        for(int i = 0; i<m; i++){
            maszyny.add(new Maszyna(i+1));
        }

        int time = 0;
        int i = 0;
        for (int j = 0; j<n; j++){
            if(time+times.get(j) <= max){

                maszyny.get(i).procesy.add(String.format("%s[%s,%s]", j + 1, time, time + times.get(j)));

                time+=times.get(j);
            } else {
                if(time!=max) {
                    maszyny.get(i).procesy.add(String.format("%s[%s,%s]", j + 1, time, max));
                }
                maszyny.get(i+1).procesy.add(String.format("%s[%s,%s]", j+1, 0, times.get(j)-(max-time)));
                i++;
                time = times.get(j)-(max-time);
            }
        }
//        System.out.println(maszyny);

        System.out.println(max);
        maszyny.forEach(x->{
            System.out.println(x.toString());

        });
    }
}
