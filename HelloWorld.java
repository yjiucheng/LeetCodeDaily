import java.lang.reflect.InvocationTargetException;

public class HelloWorld {
    String aa = "123456";


    /**
     * [1,2,3,1]    选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
     * <p>
     * [2,7,9,3,1]  选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
     * <p>
     * [2,1,4,5,3,1,1,3]   选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。
     *
     * @param args
     */
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        ArrayList<String> arrayList1 = new ArrayList<String>();
//        arrayList1.add("abc");
//        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
//        arrayList2.add(123);
//        System.out.println(arrayList1.getClass() == arrayList2.getClass());
//
//
//        ArrayList<Integer> arrayList3 = new ArrayList<Integer>();
//        arrayList3.add(1);//这样调用add方法只能存储整形，因为泛型类型的实例为Integer
//        arrayList3.getClass().getMethod("add", Object.class).invoke(arrayList3, "asd");
//        for (int i = 0; i < arrayList3.size(); i++) {
//            System.out.println(arrayList3.get(i));
//        }

//        System.err.println("123456");
//        System.err.println(getRes("123456"));
        long cur = System.currentTimeMillis();
        System.err.println(getNum(6498063));
        System.err.println("耗时：" + (System.currentTimeMillis() - cur));
    }

    public static int getNum(int input) {
        int i = 1;
        int ans = 0;
        while (true) {
            int re = Integer.valueOf(getRes(i + ""));
            if (re * i == input) {
                ans = i;
                break;
            }
            i++;
            if (i > input) {
                return -1;
            }
        }
        return ans;
    }

    public static String getRes(String num) {
        char[] ans = new char[num.length()];
        char[] nums = num.toCharArray();
        for (int i = 0; i < nums.length; i++) {
            ans[i] = nums[nums.length - 1 - i];
        }
        return new String(ans);
    }


}
