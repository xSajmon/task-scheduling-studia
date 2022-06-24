package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Szeregowanie71 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> raporty = new ArrayList<>();
        raporty.add(0);
        for(int i = 1; i<=n; i++){
            raporty.add(scanner.nextInt());
        }

        int sum = raporty.stream().mapToInt(Integer::intValue).sum();
        int b = sum/2;
        boolean[][] matrix = new boolean[n+1][b+1];
        for(int i = 0; i<=n; i++){
            matrix[i][0] = true;
        }
        for(int j = 1; j<=b; j++){
            matrix[0][j] = false;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= b; j++){
                matrix[i][j] = matrix[i-1][j];
                if(raporty.get(i)<=j){
                    matrix[i][j] = matrix[i][j] || matrix[i-1][j-raporty.get(i)];
                }
            }
        }
        int c = 0;
        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=b; j++){
//                System.out.print(matrix[i][j] ? 1 + " " : 0 + " ");
                if(i==n){
                    if(matrix[i][j]) c = j;
                }
            }
//            System.out.println();
        }
        System.out.println(c + " " + (sum-c));
    }
}
