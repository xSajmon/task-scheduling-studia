package com.company;

import java.util.*;

public class Szeregowanie72 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int d = scanner.nextInt();
        int[] czas = new int[n+1];
        czas[0] = 0;
        int[] punkty = new int[n+1];
        punkty[0]=0;
        for(int i = 1; i<=n; i++){
            czas[i] = scanner.nextInt();
            punkty[i] = scanner.nextInt();
        }

        int [][] matrix = new int[n+1][d+1];
        for(int i = 0; i <= n; i++){
            for(int j = 0; j <= d; j++){
                if( i == 0 || j == 0){
                    matrix[i][j] = 0;
                } else if (czas[i] > j){
                    matrix[i][j] = matrix[i-1][j];
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i-1][j-czas[i]]+punkty[i]);
                }
            }
        }

//        for(int i = 0; i<=n; i++){
//            for(int j = 0; j<=d; j++){
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }
        int j = d;
        int sum = 0;
        List<Integer> plecak = new ArrayList<>();
        for(int i = n; i>0; i--){
            if(matrix[i][j] != matrix[i-1][j]){
                plecak.add(i);
                j-=czas[i];
                sum+=punkty[i];
            }
        }
        System.out.println(sum);
        plecak.stream().sorted(Comparator.comparing(Integer::intValue)).forEach(System.out::println);
    }
}
