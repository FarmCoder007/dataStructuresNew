package com.my.reader.datastructure.linked_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.my.reader.datastructure.R;

/**
 * 链表相关算法
 */
public class LinkedListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linked_list);
    }

    /**
     * 链表算法题：给你一个链表，每K个节点一组进行翻转，请返回翻转后的链表。k是一个正整数，它的值小于或者等于链表的长度。如果节点总数不是k的整数倍那么
     * 请将最后剩余的节点保持原有顺序
     * 说明：1你的算法只能使用常数的额外空间
     * 2 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
     * 示例：  给定这个链接：1->2->3->4->5
     * 当k=2时，应当返回：2->1->4->3->5
     * 当k=3时，应当返回：3->2->1->4->5
     * <p>
     * --------------------------------------------
     * 解题思路考查2个知识点：1 对链表翻转算法是否熟悉
     * 2 对递归算法的理解是否清晰
     */
    /**
     * 给定一个链表     每K个进行一组翻转
     *
     * @param head 给定的链表
     * @param k
     * @return
     */
    private ListNode flipLinkedList(ListNode head, int k) {
        // 当链表的长度小于K时  直接返回原链接即可
        if (head.length < k) {
            return head;
        }
        // 定义一个新链表 拼接翻转后的部分
        ListNode dummy = new ListNode(0);
        //
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        while (end != null) {
            // 让end 遍历到需要翻转的最后一个元素位置
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
                // 只要end 遍历到null直接跳出循环
                if (end == null) {
                    break;
                }
                // 定义plast指向翻转元素后面的第一个元素
                ListNode next = end.next;
                // 定义start指向翻转元素的第一个位置
                ListNode start = pre.next;
                // 最后一个翻转元素先断链
                end.next = null;
                // 然后通过翻转方法reverse();后接在pre后面
                pre.next = reverse(start);
                start.next = next;
                pre = start;
                end = pre;
            }
        }
        return dummy.next;
    }

    /**
     * 链表逆转方法
     *
     * @return
     */
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 前一个节点
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode p = cur.next;
            cur.next = pre;
            pre = cur;
            cur = p;
        }
        return pre;
    }

    /**
     * 链表类
     */
    public class ListNode {
        public int length;
        ListNode next;

        ListNode(int length) {
            this.length = length;
        }
    }
}
