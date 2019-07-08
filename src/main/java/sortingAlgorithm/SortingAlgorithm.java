package sortingAlgorithm;

import org.junit.jupiter.api.Test;

/**
 * @author liuhai
 * @date 2019/6/27 8:13
 */
public class SortingAlgorithm {



    @Test
    void test1(){
        int[] a={1,2,3,4,5};
        int length=a.length;
        int insertNum;
        for(int i=1;i<length;i++){
            insertNum=a[i];
            int j=i-1;
            while(j>=0&&a[j]>insertNum){
                a[j+1]=a[j];
                j--;
            }
            a[j+1]=insertNum;
        }
    }

}
