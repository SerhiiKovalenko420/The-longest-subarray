package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //odczyt z pliku================================

        int n = 0;
        int maxDiff = 0;
        int [] E = null;
        List<String> lines = new ArrayList<String>();

        try{
            FileInputStream fstream = new FileInputStream(System.getProperty("user.home")+"/Array.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null){
                lines.add(strLine.trim());
            }
            n = Integer.parseInt(lines.get(0));
            maxDiff = Integer.parseInt(lines.get(1));
            E = new int [n];
            int iterator = 0;

            for (int i = 3; i < lines.size(); i++) {
                E[iterator] = Integer.parseInt(lines.get(i));
                iterator++;
            }

        }catch (IOException e){
            System.err.println("Error...");
        }

        //odczyt z pliku================================


        int[] tmpE = getLongestSubarray(E, maxDiff, n);

        for (int i = 0; i < tmpE.length; i++) {
            System.out.println(tmpE[i]);
        }

    }

    public static int getEnd (int [] E, int maxDiff, int p, int k){ // p - начало, k - конец
        for (int i = p+1; i <= k; i++) {
            if (Math.abs(E[p] - E[i]) > maxDiff) {
                k = i-1;
                break;
            }
        }
        if(p+1 < k){
            k = getEnd(E, maxDiff, p+1, k);
        }
        return k;
    }

    //метод, который вернет самый длинный стабильный подмассив массива E
    public static int[] getLongestSubarray (int [] E, int maxDiff, int n){
        int p = 0; //начало самого длинного подмассива
        int k = n-1; //конец

        int TMPp;
        int TMPk;

        int maxLength = 0;

        for (int i = 0; i < n; i++) {
            if((n-i+1) <= maxLength)
                break;

            TMPp = i;
            TMPk = getEnd(E, maxDiff, TMPp, n - 1);//(p+k+1)-1 = p+k razy

            if (TMPk - TMPp + 1 > maxLength) {
                maxLength = TMPk - TMPp + 1;
                p = TMPp;
                k = TMPk;
            }
        }


        System.out.println(p+" "+k+'\n');

        int [] result = new int[maxLength];
        int iterator = 0;
        for (int i = p; i <= k; i++) {
            result [iterator] = E[i];
            iterator++;
        }

        return result;
    }

}