package 二叉搜索树;

/*	https://leetcode-cn.com/problems/validate-binary-search-tree/
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * */
public class _98_验证二叉搜索树 {
	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}

		return isValid(root);
	}

	private boolean isValid(TreeNode node) {
		if (node == null) {
			return true;
		}
		if (node.left != null) {
			if (node.left.val < node.val) {
				return isValid(node.left);
			}
		}

		if (node.right != null && node.right.val < node.val)

		{
			return isValid(node.right);
		}
		return false;
	}
}
