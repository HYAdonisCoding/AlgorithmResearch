package 链表;

// https://leetcode-cn.com/problems/remove-linked-list-elements/
public class _203_移除链表元素 {
	/*
	 * 输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5] 示例 2：
	 * 
	 * 输入：head = [], val = 1 输出：[] 示例 3：
	 * 
	 * 输入：head = [7,7,7,7], val = 7 输出：[]
	 * 
	 * 来源：力扣（LeetCode）
	 * 链接：https://leetcode-cn.com/problems/remove-linked-list-elements
	 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
	 */
	public static ListNode removeElements(ListNode head, int val) {
		if (head == null) {
			return head;
		}
		ListNode node = head;
		while (node != null) {
			if (node.val == val) {
				node = (node.next == null || node.next.next == null) ? null : node.next.next;

			} else {
				node = node.next;
				System.out.println(node);
			}

		}
		return head;

//		if (head == null) {
//			return null;
//		}
//		head.next = removeElements(head.next, val);
//		if (head.val == val) {
//			return head.next;
//		} else {
//			return head;
//		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * https://leetcode-cn.com/problems/remove-dumplicates-from-sorted-list/
		 * 
		 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
		 */
		int[] data = { 2, 6, 3, 4, 5, 6 };
		ListNode head = new ListNode(1);
		ListNode tmpListNode = head;
		for (int i = 0; i < data.length; i++) {

			tmpListNode.next = new ListNode(data[i]);
			tmpListNode = tmpListNode.next;
			System.out.println(tmpListNode);
		}
		System.out.println("-------------");
		ListNode tmp = head;
		System.out.println(head);
		removeElements(head, 6);
		System.out.println("-------------");
		System.out.println(head);
	}

}
