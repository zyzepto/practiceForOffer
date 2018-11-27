package AlgorithmDemo;

import java.util.*;

public class ArrayAlgorithmDemo {

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,3,2,4,1,5,3,2,4};
        int[] cost = {1,1,1,3,2,4,3,6,7,4,3,1};
        int[] nums = {95, 951, 8};
        System.out.println(largestNumber(nums));
    }

    /**
     * @author zepto
     * @Description 查找数字位置
     * @date 2018/9/15
     * @return int[]
     **/
    public static int[] findLocation(int[] arr, int target){
        int low = 0, high = arr.length-1;
        int mid = 0;
        int p = -1;
        while (low < high){
            mid = (low + high)/2;
            if (arr[mid] == target){
                p = mid;
                break;
            }else if (arr[mid] > target){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        int i = p, j = p;
        if (p != -1){
            while (i >0 && arr[i-1] == target) i--;
            while (j < arr.length - 1 && arr[j+1] == target) j++;
            return new int[]{i,j};
        }else return new int[]{-1,-1};
    }

    /**
     * @author zepto
     * @Description 最短路径和 LeetCode 64
     * @date 2018/9/15
     * @return
     **/
    public static int minPathSum(int[][] grid){
        int[][] dp =new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid[0].length; i++) {
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }

    public static int uniquePathsHelper(int m, int n){
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i-1] ;
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /**
     * @author zepto
     * @Description 有障碍情况下的不同路径
     * @date 2018/9/15
     * @return int
     **/
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 1; i < n; i++) {
            if(obstacleGrid[0][i] == 1){
                dp[0][i] = 0;
            }else dp[0][i] = dp[0][i-1] ;
        }
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                dp[i][0] = 0;
            } else dp[i][0] = dp[i-1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        permuteHelper(nums, new ArrayList<Integer>(), new ArrayList<Integer>(), lists);
        return lists;
    }

    public static void permuteHelper(int[] nums, List<Integer> target, List<Integer> list, List<List<Integer>> lists) {
        if (list.size() == nums.length){
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!target.contains(i)){
                list.add(nums[i]);
                target.add(i);
                permuteHelper(nums, target, list, lists);
                list.remove(list.size() - 1);
                target.remove(target.size() - 1);
                while(i + 1 < nums.length && nums[i] == nums[i+1]) i++;
            }

        }
    }

    /**
     * @author zepto
     * @Description leetCode 54. 螺旋矩阵
     * @date 2018/9/18
     * @return java.util.List<java.lang.Integer>
     **/
    public static List<Integer> spiralOrder(int[][] matrix){
        List<Integer> list = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        //逐次更新行和列的起始位和截止位
        int rowBegin = 0, rowEnd = m - 1, colBegin = 0, colEnd = n - 1;
        int mark = 0;
        while (mark < m*n) {
            for (int i = colBegin; i <= colEnd; i++) {
                list.add(matrix[rowBegin][i]);
                mark++;
            }
            rowBegin++;
            if (mark == m*n) break;
            for (int i = rowBegin; i <= rowEnd; i++) {
                list.add(matrix[i][colEnd]);
                mark++;
            }
            colEnd--;
            if (mark == m*n) break;
            for (int i = colEnd; i >= colBegin; i--) {
                list.add(matrix[rowEnd][i]);
                mark++;
            }
            rowEnd--;
            if (mark == m*n) break;
            for (int i = rowEnd; i >= rowBegin; i--) {
                list.add(matrix[i][colBegin]);
                mark++;
            }
            colBegin++;
        }
        return list;
    }

    /**
     * @author zepto
     * @Description leetCode 55.跳跃游戏1 贪心算法
     * @date 2018/9/18
     * @return boolean
     **/
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if(i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) return true;
        }
        return false;
    }

    /**
     * @author zepto
     * @Description leetCode 45.跳跃游戏2 贪心算法
     * @date 2018/9/18
     * @return int
     **/
    public static int jump(int[] nums){
        int step = 0;
        int i = 0, max = 0, index = 0;
        while (i < nums.length - 1){
            if(i + nums[i] - nums.length+1 >= 0){
                step++;
                break;
            }
            max = 0;
            index = i + 1;
            for (int j = i + 1; j - i <= nums[i]; j++) {
                if (max < j - i + nums[j]){
                    max = j - i + nums[j];
                    index = j;
                }
            }
            i = index;
            step++;
        }
        return step;
    }

    /**
     * @author zepto
     * @Description leetCode 59. 生成螺旋矩阵2
     * @date 2018/9/19
     * @return int[][]
     **/
    public static int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
        int count = 1;
        while (count <= n*n){
            for (int i = colBegin; i <= colEnd; i++) {
                arr[rowBegin][i] = count;
                count++;
            }
            rowBegin++;
            if (count > n*n) break;
            for (int i = rowBegin; i <= rowEnd; i++) {
                arr[i][colEnd] = count;
                count++;
            }
            colEnd--;
            if (count > n*n) break;
            for (int i = colEnd; i >= colBegin; i--) {
                arr[rowEnd][i] = count;
                count++;
            }
            rowEnd--;
            if (count > n*n) break;
            for (int i = rowEnd; i >= rowBegin; i--){
                arr[i][colBegin] = count;
                count++;
            }
            colBegin++;
        }
        return arr;
    }

    /**
     * @author zepto
     * @Description leetCode60. 第k个排列
     * @date 2018/9/19
     * @return java.lang.String
     **/
    public static String getPermutation(int n, int k) {
        //factorial
        int[] factorial = new int[]{1,1,2,6,24,120,720,5040,40320,362880};

        //初始化
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0 ; i<n ; i++){
            numbers.add(i+1);
        }

        StringBuilder result = new StringBuilder();
        k--;
        for(int i = 0 ; i<n ; i++){
            int currentNumber = numbers.remove(k / factorial[n-i-1]);
            result.append(currentNumber);
            k  %=  factorial[n-i-1] ;
        }
        return result.toString();
    }

    /**
     * @author zepto
     * @Description 二维数组查询
     * @date 2018/9/23
     * @return boolean
     **/
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int low = 0, high = m*n - 1;
        while (low <= high){
            int mid = (low + high)/2;
            if (matrix[mid/n][mid%n] == target){
                return true;
            }else if (matrix[mid/n][mid%n] < target){
                low = mid + 1;
            }else high = mid - 1;
        }
        return false;
    }

    /**
     * @author zepto
     * @Description leetCode 75 颜色分类 荷兰国旗问题 快排思想
     * @date 2018/9/25
     * @return void
     **/
    public static void sortColors(int[] arr){
        int low = 0, high = arr.length - 1;
        int i = 0;
        while (i < high){
            if (arr[i] < 1){
                swap(arr, low++, i++);
            }else if (arr[i] > 1){
                swap(arr, high--, i);
            }else i++;
        }
    }

    private static void swap(int[] arr, int low, int i) {
        int tem = arr[i];
        arr[i] = arr[low];
        arr[low] = tem;
    }

    /**
     * @author zepto
     * @Description
     * @date 2018/9/25
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combineHelper(n, k, 1, new ArrayList<Integer>(), res);
        return res;
    }

    public static void combineHelper(int n, int k, int start, List<Integer> list, List<List<Integer>> res){
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i <= n; i++) {
                list.add(i);
                combineHelper(n, k, i +1, list, res);
                list.remove((Integer) i);
        }
    }

    /**
     * @author zepto
     * @Description leetCode 78 子集的集合
     * @date 2018/9/25
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subsetsHelper(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }

    public static void subsetsHelper(int[] nums, int start, List<Integer> list, List<List<Integer>> res){
        if (start == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        subsetsHelper(nums, start + 1, list, res);
        list.add(nums[start]);
        subsetsHelper(nums, start + 1, list, res);
        list.remove((Integer) nums[start]);
    }

    /**
     * @author zepto
     * @Description leetCode 79查字典
     * @date 2018/9/25
     * @return boolean
     **/
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] mark = new boolean[board.length][board[0].length];
                if (dfs(board, word, 0, i, j, mark)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, String word, int index, int x, int y, boolean[][] mark){
        if (index == word.length()) return true;
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) return false;
        if (mark[x][y]) return false;
        if (board[x][y] != word.charAt(index)) return false;
        mark[x][y] = true;
        boolean res = dfs(board, word, index+1, x+1, y, mark) || dfs(board, word, index+1, x-1, y, mark) ||
                dfs(board, word, index+1, x, y+1, mark) || dfs(board, word, index+1, x, y-1, mark);
        mark[x][y] = false;
        return res;
    }

    /**
     * @author zepto
     * @Description leetCode 移除重复数字 空间复杂度o(1)
     * @date 2018/9/25
     * @return int
     **/
    public static int removeDuplicates(int[] nums) {
        int i = 1, k = 1;
        int count = 1;
        if (nums.length <= 2)
            return nums.length;
        for(; i < nums.length; i++){
            if(nums[i] == nums[i-1]){
                if(count < 2){
                    nums[k++] = nums[i];
                    count++;
                }
            }
            else {
                count = 1;
                nums[k++] = nums[i];
            }
        }
        return k;
    }

    /**
     * @author zepto
     * @Description leetCode 81 二分搜索旋转数组
     * @date 2018/9/25
     * @return boolean
     **/
    public static boolean search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high){
            int mid = (low + high)/2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[high]){
                if (nums[mid] > target && target > nums[low]) high = mid - 1;
                else low = mid + 1;
            }else if (nums[mid] < nums[high]){
                if (nums[mid] < target && target < nums[high]) low = mid + 1;
                else high = mid - 1;
            }else high--;
        }
        return false;
    }

    public static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean fRow = false, fCol = false;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0){
                fCol = true;
            }
        }
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0){
                fRow = true;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0){
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0){
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (fRow){
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (fCol){
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     * @author zepto
     * @Description leetCode 重复数组的子集
     * @date 2018/10/16
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupHelper(nums, res, list, 0);
        list.clear();
        res.add(list);
        return res;
    }

    public static void subsetsWithDupHelper(int[] nums, List<List<Integer>> res, List<Integer> list, int index){
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            subsetsWithDupHelper(nums, res, list, i+1);
            list.remove(list.size() - 1);
            while (i + 1< nums.length && nums[i+1] == nums[i]){
                i++;
            }
        }
    }

    public static String biggestNum(int[] arr){
        List<String> list = new ArrayList<>();
        biggestNum(arr,0, new String(), list);
        System.out.println(list);
        int mark = Integer.valueOf(list.get(0));
        String res = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            int temp = Integer.valueOf(list.get(i));
            if (temp < mark){
                res = list.get(i);
                mark =  temp;
            }
        }
        return res;
    }

    public static void biggestNum(int[] arr, int index, String temp, List<String> list){
        if (index >= arr.length) {
            list.add(new String(temp));
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(arr, index, i);
            String s = temp + String.valueOf(arr[index]);
            biggestNum(arr, index + 1, s, list);
            swap(arr,index, i);
            while (i+1 < arr.length && arr[i+1] == arr[i]) i++;
        }
    }

    /**
     * @author zepto
     * @Description 120. 三角形最小路径和 空间复杂度o(n) 从下往上遍历
     * @date 2018/10/18
     * @return int
     **/
    public int minimumTotal(List<List<Integer>> triangle) {
        List<Integer> list = triangle.get(triangle.size()-1);
        int size = list.size();
        int[] dp = new int[size];
        for (int i = 0; i < size; i++) {
            dp[i] = list.get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                dp[j] = Math.min(dp[j], dp[j+1]);
            }
        }
        return dp[0];
    }

    /**
     * @author zepto
     * @Description
     * @date 2018/10/18
     * @return void
     **/
    public static void solve(char[][] board) {
        int row = board.length, col = board[0].length;
        boolean[][] mark = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'X'){
                    mark[i][j] = true;
                }else if (i == 0 || i == row - 1 || j == 0 || j == col - 1){
                    helper(board, mark, i, j, row, col);
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!mark[i][j]){
                    board[i][j] = 'X';
                }
            }
        }
    }

    public static void helper(char[][] board, boolean[][] mark, int i, int j, int row, int col){
        if (i < 0 || i >= row || j < 0 || j >= col) return;
        if (board[i][j] == 'O') {
            mark[i][j] = true;
        }else return;
        if (j != 0 && j != col - 1) {
            if (i-1 >= 0 && !mark[i-1][j]) {
                helper(board, mark, i - 1, j, row, col);
            }
            if (i+1 < row && !mark[i+1][j]) {
                helper(board, mark, i + 1, j, row, col);
            }
        }
        if (i != 0 && i != row - 1) {
            if (j-1 >= 0 &&!mark[i][j-1]) {
                helper(board, mark, i, j - 1, row, col);
            }
            if (j+1 < col && !mark[i][j+1]) {
                helper(board, mark, i, j + 1, row, col);
            }
        }
    }

    /**
     * @author zepto
     * @Description leetCode 加油站
     * @date 2018/10/24
     * @return int
     **/
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int i = 0; boolean b = true;
        while (b && i < gas.length) {
            if (i == gas.length - 1) b = false;
            int j = i;
            int gasNum = 0;
            while (gasNum >= 0){
                gasNum += gas[j] - cost[j];
                if (j == cost.length - 1){
                    j = 0;
                }else j++;
                if(gasNum >= 0 && j == i) return j;
            }
            i = j;
        }
        return -1;
    }

    /**
     * @author zepto
     * @Description leetCode 139 单词拆分
     * @date 2018/10/24
     * @return boolean
     **/
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s==null || s.length()==0)
            return true;
        boolean[] res = new boolean[s.length()+1];
        res[0] = true;
        for(int i=0;i<s.length();i++)
        {
            for (int j = 0; j <= i; j++) {
                if (res[j] && wordDict.contains(s.substring(j, i + 1))){
                    res[i+1] = true;
                    break;
                }
            }
        }
        return res[s.length()];
    }

    /**
     * @author zepto
     * @Description 连续子列最大乘积
     * @date 2018/10/25
     * @return int
     **/
    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        int res = nums[0];
        if (nums[0] > 0){
            max[0] = nums[0];
            min[0] = 1;
        }else {
            max[0] = 1;
            min[0] = nums[0];
        }
        for (int i = 1; i < nums.length; i++) {
            max[i] = Math.max(max[i-1]*nums[i], Math.max(max[i-1]*nums[i], nums[i]));
            min[i] = Math.min(max[i-1]*nums[i], Math.min(max[i-1]*nums[i], nums[i]));
            res = Math.max(res, max[i]);
        }
        return res;
    }

    /**
     * @author zepto
     * @Description 162. 寻找峰值
     * 两端收敛至负无穷，因此二分查找，判断中间点是否大于右侧，大于右侧，则左侧有峰值
     * @date 2018/10/29
     * @return int
     **/
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        int mid = (left+right)/2;
        while (left<=right){
            if (left == mid) return nums[left] > nums[right] ? left : right;
            if (nums[mid] < nums[mid+1]){
                left = mid;
            }else if (nums[mid] > nums[mid+1]){
                right = mid;
            }
            mid = (left+right)/2;
        }
        return 0;
    }

    public static String largestNumber(int[] nums) {
        String[] arr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        Comparator comparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                String temp1 = s1 + s2;
                String temp2 = s2 + s1;
                for (int i = 0; i < temp1.length(); i++) {
                    if (temp1.charAt(i) < temp2.charAt(i)){
                        return 1;
                    }else if (temp1.charAt(i) > temp2.charAt(i)){
                        return -1;
                    }
                }
                return 0;
            }
        };
        Arrays.sort(arr, comparator);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /** 
     * @author zepto
     * @Description 209. 长度最小的子数组
     * @date 2018/10/30
     * @return int
     **/
    public int minSubArrayLen(int s, int[] nums) {
        int size = Integer.MAX_VALUE;
        int start = 0, end = 0;
        while (start < nums.length){
            int sum = 0;
            for (int i = start; i < nums.length; i++) {
                sum += nums[i];
                if (sum >= s){
                    size = Math.min(size, i-start);
                    break;
                }
            }
            start++;
        }
        return size;
    }

    /**
     * @author zepto
     * @Description 213. 打家劫舍 II
     * @date 2018/10/31
     * @return int
     **/
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 1; i < nums.length-1; i++) {
            dp[i+1] = Math.max(dp[i], dp[i-1]+nums[i]);
        }
        int a = dp[nums.length];
        dp[1] = nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        int b = dp[nums.length];
        return Math.max(a,b);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3Helper(k, n, res, new ArrayList<>(), 1, 0);
        return res;
    }

    public void combinationSum3Helper(int k, int n, List<List<Integer>> res, List<Integer> list, int count, int sum) {
        if (list.size() == k){
            if (sum == n) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        if (count == 10) return;
        combinationSum3Helper(k, n , res, list, count+1, sum);
        list.add(count);
        combinationSum3Helper(k, n, res, list, count+1, sum+count);
        list.remove(list.size()-1);
    }
}

