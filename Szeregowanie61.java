package com.company;

import java.util.*;

public class Szeregowanie61 {

    static class Maszyna {

        int index;
        List<Swider> procesy;

        public Maszyna(int index) {
            this.index = index;
            procesy = new ArrayList<>();
        }
    }

    static class Swider{
        int index;
        int time;

        public Swider(int index, int time) {
            this.index = index;
            this.time = time;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        List<Swider> swidry = new ArrayList<>();
        List<Maszyna> maszyny = new ArrayList<>();

        for(int i = 0; i<m; i++){
            maszyny.add(new Maszyna(i+1));
        }

        for(int i = 0; i<n; i++){
            swidry.add(new Swider(i+1, scanner.nextInt()));
        }

        swidry.sort(Comparator.comparing(x->x.time));
//        swidry.forEach(System.out::println);

        int time = 0;
        boolean check = true;
        while(check){
            for(int i = 0; i<m; i++){
                if(swidry.isEmpty()){
                    check = false;
                    break;
                }
                maszyny.get(i).procesy.add(swidry.get(0));
                time += maszyny.get(i).procesy.stream().mapToInt(x->x.time).sum();
                swidry.remove(0);
            }
        }
//        maszyny.forEach(System.out::println);
        System.out.println(time);
    }
}
