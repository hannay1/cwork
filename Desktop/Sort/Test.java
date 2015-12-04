import java.util.Arrays;
public class Test {

public static void main(String args[]){

    //INSERTION SORT
    int[] arr = {59,38,92,99,39,13,54,55,80,24,6,22,94,63,23,9}; // This is my array
    //int[] merg;
    //selectionSort(arr);
    //bubbleSort(arr);
    //insertionSort(arr);
    mergeSort(arr);

    for(int c=0; c < arr.length; c++)
        {
            System.out.println("merge sorted : " + arr[c]);
        }



    
}

public static int[] bubbleSort(int[] intz)
    {
       
        for(int k = 1; k < intz.length - 1; k++)
        {
            for(int i = 0; i < intz.length -  k - 1; i++)
            {
                if(intz[i] > intz[i+1])
                {
                    int temp = intz[i];
                    intz[i] = intz[i+1];
                    intz[i+1] = temp;

                }
                
            }
        }

        for(int c=0; c < intz.length; c++)
        {
            System.out.println("bubble sorted : " + intz[c]);
        }

        return intz;

    }

    public static int[] selectionSort(int[] intz)
    {

    int pos_min;
    for(int i = 0;i<intz.length;i++)
    {
        //Assume first element is pos_min
        pos_min = i;
        for(int n = i + 1;n<intz.length;n++)
        {
            if(intz[n] < intz[pos_min]) 
            {
                pos_min = n;
            }
        }

        int temp = intz[i];
        intz[i] = intz[pos_min];
        intz[pos_min] = temp;
        System.out.println("selection sorted " + intz[i]);//I print the in ascending order 
        }
     return intz;

    }

    public static int[] insertionSort(int[] intz)
    {
        for(int i = 1; i<intz.length -1;i++)
        {
            int value = intz[i];
            int nope = i;
            while(nope > 0 && intz[nope -1] > value)
            {
                intz[nope] = intz[nope -1];
                nope = nope -1;
            }
            intz[nope] = value;
        }
        for(int c=0; c < intz.length; c++)
        {
            System.out.println("insertion sorted : " + intz[c]);
        }

        return intz;

        
    }

    public static void merge(int[] left, int[] right, int[] intz)
    {

        int t = left.length + right.length;

        int i = 0;
        int l_i =0;
        int r_i = 0;

        while (i< t)
        {
            if(l_i < left.length && r_i  < right.length)
            {
                if(left[l_i] < right[r_i])
                {
                    intz[i++] = left[l_i++];
                   
                }else
                {
                    intz[i++] = right[r_i++];
                    
                }
                
            }else
            {
                if(l_i >= left.length)
                {
                    while(r_i < right.length) 
                    {
                        intz[i++] = right[r_i++];
                       
                    }
                }
                if(r_i >= right.length)
                {
                    while(l_i < left.length)
                    {
                        intz[i++] = left[l_i++];
                       
                    }
                }
            }
        }

       
    }

    public static void mergeSort(int[] intz)
    {
        if(intz.length < 2) //base case
        {
            return;
        }

        int mid = intz.length/2;
        int[] left = Arrays.copyOfRange(intz, 0, mid);
        int[] right = Arrays.copyOfRange(intz, mid, intz.length);
        mergeSort(left);
        mergeSort(right);
        merge(left, right, intz);
    }

    public static void quickSort(int[] intz)
    {

    }


}
