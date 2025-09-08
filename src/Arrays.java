public class Arrays {
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

        // (in python)
        // for i in range(len(nums)):
        //   nums[i] = i+1
        
        // USE THIS VERSION OF THE LOOP IF YOU NEED TO UPDATE AN ARRAY VALUE
        for (int i=0; i<nums.length; i++) {
            nums[i] = i+1;
        }
        
        // (in python)
        // for v in nums:
        //   print(v)

        // USE THIS VERSION IF YOU ONLY NEED THE VALUES AND DON'T CARE ABOUT THE POSITION WITHIN THE ARRAY
        for (int v : nums) {
            System.out.println(v);
        }
    }
}

