import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Test {
    public static void main(String args[] ) throws Exception {

        /*BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();                // Reading input from STDIN
        int totalElements = Integer.parseInt(line) ;  // Writing output to STDOUT
        line = br.readLine();*/
      //  String line="2,4,6,10,11,13";
        //String strArr[] = line.split(",");
        int arr[] = new int[args.length];
        for(int i=0;i<args.length;i++)
        {
            arr[i] = Integer.parseInt(args[i]);    
        }
        System.out.println(findDivisibleNUmbers(arr));
    }

    static int findDivisibleNUmbers(int arr[]){
    Set<Integer> list=  new HashSet();
        for(int i=0;i<arr.length;i++) {
            int element = arr[i];

            for(int j=0;j<arr.length;j++) {
                if(arr[j] != element && (element%arr[j] ==0)){
                        list.add(element);
                        break;
                }
            }
        }
        return list.size();
    }
}





