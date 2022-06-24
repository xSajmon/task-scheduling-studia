package com.company;

import java.util.*;

public class Szeregowanie83 {



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        int [][]times = new int[m+1][n+1];

        for(int i = 0; i<=n; i++){
            for(int j = 0; j<=m; j++){
                if(i==0 || j == 0){
                    times[j][i] = 0;
                } else {
                    times[j][i] = scanner.nextInt();
                }

            }
        }
//        for(int i = 0; i<m; i++){
//            for(int j = 0; j<n; j++){
//                System.out.print(times[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();

        int [][]matrix = new int[m+1][n+1];
        for(int i = 0; i<=m; i++){
            for(int j = 0; j<=n; j++ ){
                if(i==0 || j==0){
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Math.max(matrix[i-1][j], matrix[i][j-1])+times[i][j];
                }
            }
        }

//        for(int i = 0; i<=m; i++) {
//            for (int j = 0; j <= n; j++) {
//                System.out.print(matrix[i][j] + " ");
//            }
//            System.out.println();
//        }

        for(int i = 1; i<=n; i++){
            System.out.println(matrix[m][i]);
        }
}
}
