package task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TaskOne {
    public static void main(String[] args) {
        String[] input;
        int[][] dataCenters;
        int[] numReset;
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            input = bf.readLine().split(" ");
            dataCenters = createDataCenters(Integer.parseInt(input[0]),Integer.parseInt(input[1]));
            numReset = createNumReset(Integer.parseInt(input[0]));
            readEvents(dataCenters,Integer.parseInt(input[2]),numReset, bf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[][] createDataCenters(int n, int m){
        int[][] array = new int[n][m];
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                array[i][j] = 1;
            }
        }
        return array;
    }

    public static int[] createNumReset(int n){
        int[] array = new int[n];
        for(int i=0; i<n; i++){
            array[i]=0;
        }
        return array;
    }

    public static void resetDataCenter(int[][] array, int i, int[] arrayNum){
        for(int j=0; j<array[i-1].length; j++){
            array[i-1][j]=1;
        }
        arrayNum[i-1] += 1;
    }

    public static void disableServer(int[][] array, int i, int j){
        array[i-1][j-1]=0;
    }

    public static void getMax(int[][] arrayData, int[] arrayNum){
        int a = 0;
        int ra = -1;
        int numDataCenter = 0;

        for(int i=0; i<arrayNum.length; i++){
            for(int j=0; j<arrayData[i].length; j++){
                a += arrayData[i][j];
            }
            if(ra<(a*arrayNum[i])){
                ra=(a*arrayNum[i]);
                numDataCenter=i+1;
            }
            a=0;
        }
        System.out.println(numDataCenter);
    }

    public static void getMin(int[][] arrayData, int[] arrayNum){
        int a = 0;
        int ra = Integer.MAX_VALUE;
        int numDataCenter = 0;

        for(int i=0; i<arrayNum.length; i++){
            for(int j=0; j<arrayData[i].length; j++){
                a += arrayData[i][j];
            }
            if(ra>(a*arrayNum[i])){
                ra=(a*arrayNum[i]);
                numDataCenter=i+1;
            }
            a=0;
        }
        System.out.println(numDataCenter);
    }

    public static void readEvents(int[][] array, int q, int[] arrayNum, BufferedReader bf){
        try{
            StringBuilder  stringBuilder = new StringBuilder();

            for(int i=0; i<q; i++){
                stringBuilder.append(bf.readLine());

                if(stringBuilder.toString().contains("DISABLE")){
                    stringBuilder.delete(0,8);

                    disableServer(array,Integer.parseInt(stringBuilder.substring(0,stringBuilder.indexOf(" "))), Integer.parseInt(stringBuilder.substring(stringBuilder.indexOf(" ")+1,stringBuilder.length())));

                    stringBuilder.delete(0,stringBuilder.length());
                }
                else if(stringBuilder.toString().contains("RESET")){

                    resetDataCenter(array,Integer.parseInt(stringBuilder.substring(6,stringBuilder.length())), arrayNum);

                    stringBuilder.delete(0,stringBuilder.length());
                }
                else if(stringBuilder.toString().contains("GETMAX")){
                    getMax(array, arrayNum);

                    stringBuilder.delete(0,stringBuilder.length());
                }
                else if(stringBuilder.toString().contains("GETMIN")){
                    getMin(array, arrayNum);

                    stringBuilder.delete(0,stringBuilder.length());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}