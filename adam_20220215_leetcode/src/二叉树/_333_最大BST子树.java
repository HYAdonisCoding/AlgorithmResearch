package 二叉树;

/** https://leetcode-cn.com/problems/largest-bst-subtree/
 * @auther adam
 * @date 2022/2/16
 * @apiNote 二叉树
 */
public class _333_最大BST子树 {
    /** 后续遍历判断 successor */
    public int largestBSTSubtree(TreeNode root) {

        return root == null ? 0 : getInfo(root).size;
    }
    private Info getInfo(TreeNode node) {
        return null;
    }
    /** 最大BST子树的信息 */
    private static  class Info {
        /** 根节点 */
        public TreeNode root;
        /** 节点数量 */
        public int size = 1;
        /** 最大值 */
        public int max;
        /** 最小值 */
        public int min;
    }

/************-----------------------------------*/
    private boolean isBST(TreeNode node) {
        return false;
    }
    private int nodesCount(TreeNode node) {
        return 0;
    }
    public int largestBSTSubtree1(TreeNode root) {
        if (root == null) {
            return  0;
        }
        if (isBST(root)) {
            return nodesCount(root);
        }
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }
}
