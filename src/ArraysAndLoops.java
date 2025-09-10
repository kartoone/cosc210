public class ArraysAndLoops {
    public static void main(String[] args) {
        // in python it would look like this
        // nums = []
        int nums[] = new int[10];

        // without a loop ... ughhh
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        nums[3] = 4;
        nums[4] = 5;
        nums[5] = 6;
        nums[6] = 7;
        nums[7] = 8;
        nums[8] = 9;
        nums[9] = 10;

        // with a loop
        // (in python)
        // for v in nums:
        //   print(v)
        // for i in range(len(nums)):
        //   nums[i] = i+1
        for (int i=0; i<nums.length; i++) {
            nums[i] = i+1;
        }
        // for v in nums:
        for (int v : nums) {
            System.out.println(v);
        }

        int i = 0;
        while (i < nums.length) {
            nums[i] = i + 1;
            i++;
        }

        i = 0;
        do {
            nums[i] = i + 1;
        } while (i<nums.length);


    }
}
