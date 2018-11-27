package AlgorithmDemo;

import java.util.*;
import java.util.LinkedList;

public class BinaryTree {

    /**
     * @author zepto
     * @Description git分支测试
     * @date 2018/11/27
     * @return void
     **/
    public static void main(String[] args) {
       TreeNode root = new TreeNode(1);
       TreeNode node2 = new TreeNode(2);
       TreeNode node3 = new TreeNode(3);
       TreeNode node4 = new TreeNode(4);
       TreeNode node5 = new TreeNode(5);
       root.left = node2;
       root.right = node3;
       node2.left = node4;
       node2.right = node5;
       System.out.println(totalPath(root));
    }

    /**
     * @author zepto
     * @Description 查找两个节点和为定值的节点对
     * @date 2018/9/16
     * @return void
     **/
    public static void findPair(TreeNode head, int target, List<List<Integer>> lists){
        if (head == null) return;
        List<Integer> list = new ArrayList<>();
        list.add(head.val);
        findNode(head, target-head.val, list, lists);
        findPair(head.left, target, lists);
        findPair(head.right, target, lists);
    }

    public static void findNode(TreeNode head, int target, List<Integer> list, List<List<Integer>> lists){
        if (head == null) return ;
        if (head.left != null && head.left.val == target){
            if (list.size() != 2) {
                list.add(head.left.val);
            }
            lists.add(new ArrayList<>(list));
        } else if (head.right != null && head.right.val == target){
            if (list.size() != 2) {
                list.add(head.right.val);
            }
            lists.add(new ArrayList<>(list));
        }
        findNode(head.left, target, list, lists);
        findNode(head.right, target, list, lists);
    }

    /**
     * @author zepto
     * @Description 查找路径和为目标值的路径
     * @date 2018/9/16
     * @return void
     **/
    public static void findPath(TreeNode head, int target, List<List<Integer>> lists, List<Integer> list){
        if (head == null) return;
        list.add(head.val);
        target -= head.val;
        if (target == 0){
            lists.add(new ArrayList<>(list));
        }
        findPath(head.left, target, lists, list);
        findPath(head.right, target, lists, list);
        list.remove(list.size()-1);
    }

    /**
     * @author zepto
     * @Description 前序非递归遍历
     * @date 2018/9/16
     * @return
     **/
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.empty()){
            while (current != null){
                stack.push(current);
                list.add(current.val);
                current = current.left;
            }
            if (!stack.empty()){
                current = stack.pop();
                current = current.right;
            }
        }
        return list;
    }

    /**
     * @author zepto
     * @Description leetCode 95. 不同的二叉搜索树 II
     * @date 2018/10/16
     * @return java.util.List<TreeNode>
     **/
    public List<TreeNode> generateTrees(int n) {
        return generateTreesHelper(1, n);
    }

    public List<TreeNode> generateTreesHelper(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if (end < start){
            list.add(null);
            return list;
        }
        for (int i = start; i <= end; i++) {
            for (TreeNode left : generateTreesHelper(start, i - 1)){
                for (TreeNode right : generateTreesHelper(i + 1, end)){
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    list.add(root);
                }
            }
        }
        return list;
    }

    /**
     * @author zepto
     * @Description 搜索二叉树的个数
     * @date 2018/10/16
     * @return int
     **/
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    /**
     * @author zepto
     * @Description leetCode 101. 对称二叉树
     * @date 2018/10/17
     * @return boolean
     **/
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode left = root.left;
        TreeNode right = root.right;
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offerFirst(left);
        list.offerLast(right);
        while (!list.isEmpty()){
            TreeNode head = list.pollFirst();
            TreeNode tail = list.pollLast();
            if (head.val != tail.val) return false;
            if ((head.left == null && tail.right != null) || (head.left != null && tail.right == null)) return false;
            if (head.left != null){
                list.offerFirst(head.left);
                list.offerLast(tail.right);
            }
            if ((head.right == null && tail.left != null) || (head.right != null && tail.left == null)) return false;
            if (head.right != null){
                list.offerFirst(head.right);
                list.offerLast(tail.left);
            }
        }
        return true;
    }

    public static boolean isSymmetricRecursive(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isSymmetricRecursive(left.left, right.right) && isSymmetricRecursive(left.right, right.left);
    }

    /**
     * @author zepto
     * @Description 102. 二叉树的层次遍历
     * @date 2018/10/17
     * @return java.util.List<java.util.List<java.lang.Integer>>
     **/
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tem = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                tem.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(tem);
        }
        return res;
    }

    /**
     * @author zepto
     * @Description 从前序与中序遍历序列构造二叉树
     * @date 2018/10/17
     * @return AlgorithmDemo.TreeNode
     **/
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;
        TreeNode res = new TreeNode(preorder[0]);
        if (preorder.length == 1) return res;
        int index = 0;
        for ( ; index < inorder.length; index++){
            if (inorder[index] == preorder[0]){
                break;
            }
        }
        int[] left_preorder = new int[index];
        int[] right_preorder = new int[inorder.length-index-1];
        int[] left_inorder = new int[index];
        int[] right_inorder = new int[inorder.length-index-1];
        for (int i = 0; i < index; i++) {
            left_inorder[i] = inorder[i];
            left_preorder[i] = preorder[i+1];
        }
        res.left = buildTree(left_preorder, left_inorder);
        for (int i = 0; i < inorder.length-index-1; i++) {
            right_inorder[i] = inorder[i+index+1];
            right_preorder[i] = preorder[i+index+1];
        }
        res.right = buildTree(right_preorder, right_inorder);
        return res;
    }

    /**
     * @author zepto
     * @Description 从后序序与中序遍历序列构造二叉树
     * @date 2018/10/17
     * @return AlgorithmDemo.TreeNode
     **/
    public TreeNode buildTreePostOrder(int[] inorder, int[] postorder) {
        if (postorder.length == 0 || inorder.length == 0) return null;
        TreeNode res = new TreeNode(postorder[postorder.length-1]);
        if (postorder.length == 1) return res;
        int index = 0;
        for ( ; index < inorder.length; index++){
            if (inorder[index] == postorder[postorder.length-1]){
                break;
            }
        }
        int[] left_postorder = new int[index];
        int[] right_postorder = new int[inorder.length-index-1];
        int[] left_inorder = new int[index];
        int[] right_inorder = new int[inorder.length-index-1];
        for (int i = 0; i < index; i++) {
            left_inorder[i] = inorder[i];
            left_postorder[i] = postorder[i];
        }
        res.left = buildTreePostOrder(left_inorder, left_postorder);
        for (int i = 0; i < inorder.length-index-1; i++) {
            right_inorder[i] = inorder[i+index+1];
            right_postorder[i] = postorder[i+index];
        }
        res.right = buildTreePostOrder(right_inorder, right_postorder);
        return res;
    }

    /** 
     * @author zepto
     * @Description 108. 将有序数组转换为二叉搜索树
     * @date 2018/10/17
     * @return AlgorithmDemo.TreeNode
     **/
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start+end)/2;
        TreeNode cur = new TreeNode(nums[mid]);
        cur.left = sortedArrayToBST(nums, start, mid - 1);
        cur.right = sortedArrayToBST(nums, mid + 1, end);
        return cur;
    }

    /**
     * @author zepto
     * @Description 将有序链表转化为二叉搜索树
     * @date 2018/10/17
     * @return AlgorithmDemo.TreeNode
     **/
    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode preNode = head;
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            preNode = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = slow.next;
        preNode.next = null;
        TreeNode res = new TreeNode(slow.val);
        if (head != slow) {
            res.left = sortedListToBST(head);
        }
        res.right = sortedListToBST(fast);
        return res;
    }

    /**
     * @author zepto
     * @Description 判断二叉树是否为平衡树（子树高度差不大于1）
     * @date 2018/10/18
     * @return boolean
     **/
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        if (Math.abs(depth(root.left) - depth(root.right)) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * @author zepto
     * @Description 树的高度
     * @date 2018/10/18
     * @return int
     **/
    public int depth(TreeNode root) {
        int res = 0;
        if (root == null) return res;
        return 1 + Math.max(depth(root.left), depth(root.right));
    }

    /**
     * @author zepto
     * @Description 树的最小深度
     * @date 2018/10/18
     * @return int
     **/
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int leftMinDepth = 0, rightMinDepth = 0;
        if (root.left == null) {
            leftMinDepth = Integer.MAX_VALUE;
        }else leftMinDepth = minDepth(root.left);
        if (root.right == null) {
            rightMinDepth = Integer.MAX_VALUE;
        }else rightMinDepth = minDepth(root.right);
        return Math.min(leftMinDepth, rightMinDepth) + 1;
    }

    /**
     * @author zepto
     * @Description 路径和
     * @date 2018/10/18
     * @return boolean
     **/
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null){
            return sum == 0 ? true : false;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        pathSumHelper(root, sum, res, new ArrayList<>());
        return res;
    }

    public void pathSumHelper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null){
            if (sum == root.val) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        if (root.left != null) {
            pathSumHelper(root.left, sum - root.val, res, list);
            list.remove(list.size() - 1);
        }
        if (root.right != null) {
            pathSumHelper(root.right, sum - root.val, res, list);
            list.remove(list.size() - 1);
        }
    }

    /**
     * @author zepto
     * @Description 114. 二叉树展开为链表
     * @date 2018/10/18
     * @return void
     **/
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left != null){
            flatten(root.left);
        }
        if (root.right != null){
            flatten(root.right);
        }
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null){
            root = root.right;
        }
        root.right = temp;
    }

    /**
     * @author zepto
     * @Description 114. 二叉树展开为链表 迭代
     * @date 2018/10/18
     * @return void
     **/
    public void flattenIteration(TreeNode root){
        TreeNode cur = root;
        while (cur != null){
            if (cur.left != null){
                TreeNode temp = cur.left;
                while (temp.right != null){
                    temp = temp.right;
                }
                temp.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    public int sumNumbers(TreeNode root) {
        List<String> res = new ArrayList<>();
        sumNumbersHelper(root, new String(), res);
        int sum = 0;
        for (String s : res) {
            sum += Integer.parseInt(s);
        }
        return sum;
    }

    public void sumNumbersHelper(TreeNode root, String string, List<String> res) {
        if (root == null) return;
        string = string + String.valueOf(root.val);
        if (root.left == null && root.right == null){
            res.add(string);
            return;
        }
        if (root.left != null){
            sumNumbersHelper(root.left, string, res);
        }
        if (root.right != null){
            sumNumbersHelper(root.right, string, res);
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> tem = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                tem.add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(tem.get(tem.size()-1));
        }
        return res;
    }

    public void rightSideViewHelper(TreeNode root, List<Integer> res) {
        if(root == null) return;
        TreeNode cur = root, pre = root, left = root;
        while (cur.right != null){
            res.add(cur.val);
            pre = cur;
            cur = cur.right;
            left = cur.left;
        }
        res.add(cur.val);
        if (left != null) rightSideViewHelper(left, res);
        if (pre.left == null) return;
        if(pre == cur) {
            rightSideViewHelper(pre.left, res);
        }else {
            if (pre.left.right == null){
                rightSideViewHelper(pre.left.left, res);
            }else rightSideViewHelper(pre.left.right, res);
        }
    }

    public static List<List<Integer>> totalPath(TreeNode root){
        List<List<Integer>> res = new ArrayList<>();
        totalPathHelper(root, res, new ArrayList<>());
        return res;
    }

    public static void totalPathHelper(TreeNode root, List<List<Integer>> res, List<Integer> list){
        if (root == null) return;
        list.add(root.val);
        if (root.left == null && root.right == null){
            res.add(new ArrayList<>(list));
            list.remove(list.size() - 1);
            return;
        }
        totalPathHelper(root.left, res, list);
        totalPathHelper(root.right, res, list);
        list.remove(list.size() - 1);
    }
}
class TreeNode{
    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val) {
        this.val = val;
    }
}
