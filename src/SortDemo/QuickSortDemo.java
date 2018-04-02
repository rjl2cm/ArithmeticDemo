package SortDemo;

import java.util.Arrays;

/**
 * @Author: franer
 * @Description: 快速排序的Java 代码实现
 * @Date: Create in 2018/四月/1 : 22:32
 * @Modify:
 */
public class QuickSortDemo {

    public static void main(String[] args) {
        QuickSortEntity qse = new QuickSortEntity(new int[]{10,62,98,1,2,55,78,32});
        System.out.println(Arrays.toString(qse.getArray()));
        qse.doSort();
        System.out.println(Arrays.toString(qse.getArray()));

    }

}
class QuickSortEntity{
    //最终结果排序，true 升序， false 降序
    private boolean isNeedIncrease = false;

    private int[] array;

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public QuickSortEntity() {}

    public QuickSortEntity(int[] array) {
        this.array = array;
    }

    public int[] doSort(){
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int p, int r) {
        if (p < r){
            int q = partition(array, p, r);
            quickSort(array, p, q-1);
            quickSort(array, q+1, r);
        }
    }

    private int partition(int[] array, int start, int end) {
        int endValue = array[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (isNeedIncrease ? array[j] < endValue : array[j] > endValue){
                i++;
                exchange(array, i, j);
            }
        }
        exchange(array, i+1, end);
        return i+1;
    }

    private void exchange(int[] array, int x, int y){
        if (x == y)
            return;
        array[x] = array[x] + array[y];
        array[y] = array[x] - array[y];
        array[x] = array[x] - array[y];
    }

}