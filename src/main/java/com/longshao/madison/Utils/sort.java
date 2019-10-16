package com.longshao.madison.Utils;

public class sort {
    public static void main(String[] args) {
        int[] arr = {6, 3, 5, 9, 1, 4, 0, 7, 8};
        fastSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 主要思想：
     * 1.从数据集中选出一个基准
     * 2.建立两个数组，left和right
     * left：比基准小的
     * right：比基准大的
     * 3.每次利用递归比较
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void fastSort(int[] arr, int left, int right) {
        //选一个基准
        int base = arr[left];
        //指定变量i 指向最左边
        int i = left;
        //指定变量j 指向最右边
        int j = right;
        if (i > j) {
            return;
        }
        while (i != j) {
            //先由j从右向左检索比基准小的，如果检索到比基准小的就停下
            //检索到比基准大的或相等的，继续检索
            while (arr[j] >= base && i < j) {
                j--;
            }
            //再由i从左往右检索比基准大的，如果检索到比基准大的就停下
            //检索到比基准小的或相等的，继续检索
            while (arr[i] <= base && i < j) {
                i++;
            }
            //等到i停下 ，j也停下了  然后交换i和j位置的元素
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        //如果外面的while循环不成立，则跳出循环，往下执行
        //如果上面while不成立 说明i和j相遇了
        //如果i和j相遇了，就交换基准数和相遇位置的元素

        //把相遇位置元素赋值基准数
        arr[left] = arr[i];
        //把基准数赋值为相遇位置
        arr[i] = base;
        //上面两步骤就是为了  让基准数跟相遇位置元素互换位置

        //基准数在这里就归位了 左边数字都比他小，右边都比他大
        //排基准数的左边
        fastSort(arr, left, i - 1);
        //排基准数右边的
        fastSort(arr, j + 1, right);
    }
}
