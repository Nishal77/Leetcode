class Solution {

    // Merge two sorted lists (standard helper)
    private ListNode merge(ListNode a, ListNode b) {
        if (a == null) return b;
        if (b == null) return a;

        if (a.val < b.val) {
            a.next = merge(a.next, b);
            return a;
        } else {
            b.next = merge(a, b.next);
            return b;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        // Why this approach:
        // Pairwise merging (Divide & Conquer) reduces the problem size by half each round.
        // This turns a slow O(k*n) process into O(n log k), which is the optimal solution.
        // It scales well when k is large and fits naturally because merging sorted lists
        // behaves exactly like the merge step of merge sort.

        if (lists == null || lists.length == 0) return null;

        return divideAndConquer(lists, 0, lists.length - 1);
    }

    private ListNode divideAndConquer(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];

        int mid = left + (right - left) / 2;
        ListNode l1 = divideAndConquer(lists, left, mid);
        ListNode l2 = divideAndConquer(lists, mid + 1, right);

        return merge(l1, l2);
    }
}
