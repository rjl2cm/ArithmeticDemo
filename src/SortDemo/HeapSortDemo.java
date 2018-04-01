package SortDemo;

import java.util.Arrays;
/**
 * @Author: franer
 * @Description: 堆排序
 * @Date: Create in 2018/四月/1 : 22:01
 * @Modify:
 */
public class HeapSortDemo {

    public static void main(String[] args) {
        HeapSortEntity hse = new HeapSortEntity(new int[]{93,22,11,64,65,23,77,88});
        System.out.println(Arrays.toString(hse.getHeapArray()));
        hse.doSort();
        System.out.println(Arrays.toString(hse.getHeapArray()));

    }
}

class HeapSortEntity{
    //这个标示需要构造最大堆，最大堆排序出来是增值排列
    private boolean isMaxHeap = false;
    private int[] mHeapArray;   //需要排序的序列
    private int mHeapRealSize;  //排序序列的大小
    public HeapSortEntity() {}

    public HeapSortEntity(int[] array) {
        this.mHeapArray = array;
        mHeapRealSize = array.length;
    }

    public void setHeapArray(int[] array) {
        this.mHeapArray = array;
        mHeapRealSize = array.length;
    }

    public int[] getHeapArray() {
        return mHeapArray;
    }

    public String getHeapArrayStr() {
        return mHeapArray.toString();
    }

    public boolean isMaxHeap() {
        return isMaxHeap;
    }

    public void setMaxHeap(boolean maxHeap) {
        isMaxHeap = maxHeap;
    }

    public int[] doSort(){
        int[] tempResult = this.mHeapArray;
        int puredIndex = mHeapRealSize;
        while(puredIndex > 1){
            tempResult = sortLoop(tempResult, puredIndex);
            puredIndex --;//循环完一次，纯净排列的标识位减一
        }
        return tempResult;
    }

    //每次循环的内容
    private int[] sortLoop(int array[], int puredIndex){
        for (int i = 0, len = array.length; i < len; i++) {
            //做孩子角标
            int leftChildIndex = i*2+1;
            //右孩子角标
            int rightChildIndex = i*2+2;

            if (leftChildIndex >= puredIndex){
                //如果该节点没有左子节点或者左子节点已经是排好的干净区域
                break;
            } else{
                //将该节点与左子节点进行比对
                if (isMaxHeap?array[i] < array[leftChildIndex]: array[i] > array[leftChildIndex]){
                    //交换两个数值
                    array = exchangeNodes(array, i, leftChildIndex);
                }
            }


            if (rightChildIndex >= puredIndex){
                //如果该节点没有右节点或者右子节点已经是排好的干净区域
                break;
            } else{
                //将该节点与左子节点进行比对
                if (isMaxHeap?array[i] < array[rightChildIndex]: array[i] > array[rightChildIndex]){
                    //交换两个数值
                    array = exchangeNodes(array, i, rightChildIndex);
                }
            }
        }//end of sortLoop.for()

        //交换第一个值与脏数据的的最后一个值
        array = exchangeNodes(array, 0, puredIndex - 1);
        return array;
    }

    private int[] exchangeNodes(int[] array , int x, int y){
        array[x] += array[y];
        array[y] = array[x] - array[y];
        array[x] -= array[y];
        return array;
    }

}