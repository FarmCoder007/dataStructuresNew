package com.my.reader.datastructure.linked_list;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.my.reader.datastructure.R;

/**
 * 翻转链表
 */
public class ReverseLinkedActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "ReverseLinkedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_linked);
        initView();
    }

    private void initView() {
        findViewById(R.id.tv_one).setOnClickListener(this);
        findViewById(R.id.tv_two).setOnClickListener(this);
        findViewById(R.id.tv_three).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                // 链表翻转
                reverseList(buildNode(arr));
                break;
            case R.id.tv_two:
                // 计算两个链表之和
                Node addResult = addTwoNumbers(buildNode(arr), buildNode(arr2));
                showNode(addResult);
                break;
            case R.id.tv_three:
//                Node swapNode = swapPairs(buildNode(arr));
                Node swapNode = swapPairsTwo(buildNode(arr));
                showNode(swapNode);
                break;
            default:
                break;
        }
    }

    /**
     * 定义节点
     */
    class Node {
        // 本节点的数据
        int data;
        // 下一个节点的地址
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * 定义数组   用来创建链表
     */
    int[] arr = {2, 5, 3, 4, 1, 0};
    int[] arr2 = {2, 1, 2, 3, 0, 7};

    /**
     * 构建链表
     *
     * @param array 输入int数组
     * @return 返回链表
     */
    private Node buildNode(int[] array) {
        Node head = new Node(array[0]);
        // currNode节点作为变量存在 首先为第一个节点
        Node currNode = head;
        //构建链表
        for (int i = 1; i < array.length; i++) {
            // 给该节点 2 的下一个 指向5
            currNode.next = new Node(array[i]);
            // currNode作为新节点
            currNode = currNode.next;
        }
        return head;
    }

    /**
     * 输出链表
     *
     * @param currNode
     */
    public void showNode(Node currNode) {
        while (currNode != null) {
            Log.e(TAG, "翻转前reverseList:p " + currNode.data);
            currNode = currNode.next;
        }
    }

    /**
     * 翻转指定列表
     */
    public void reverseList(Node header) {
        //header 为第一个节点  作为链表头 并且该节点的数据为数组的第一个元素
        Node currNode = header;
        // 输出链表
        showNode(currNode);
        //链表反转时，需要用到上一个节点preNode 和下一个节点nextNode。
        //当前节点currNode重新指向currNode的上一个节点之前，需要先记录下currNode的下一个节点。因为currNode.next改变后，
        //你已经无法通过currNode.next获得下一个节点。
        Node preNode = null, nextNode = null;
        currNode = header;

        // 取头节点开始遍历
        while (currNode.next != null) {     //开始反转
            nextNode = currNode.next;//记录下一个节点
            currNode.next = preNode; //currNode.next反转 指向前一个节点
            preNode = currNode;      //更新 preNode
            currNode = nextNode;     //更新currNode
            /**
             *  原链表数据 ： 2, 5, 3, 4, 1, 0   翻转一次   preNode 变成 翻转后的表头  转后链表 2      未转链表 53410
             *                                   翻转2次                                        52              3410
             *                                                                                  352              410
             *                                                                                  4352              10
             *                                                                                  14352               0
             */
        }
        // currentnext  为null跳出循环  则只翻转 currNode 即可                                 currNode为0  剩一位0  没有next  将
        currNode.next = preNode;
        //输出反转链表
        Log.e(TAG, "翻转后:");
        showNode(currNode);
    }

    /**
     * 翻转一次链表
     */
    private void turnNodeOnce(Node head) {
        Node preNode, nextNode = null;
        preNode = null;
        Node currNode = head;
        // 每一次翻转  都是把当前的next存下来原因上边说了    然后不断的把当前的next指向前一个节点 进行翻转
        if (currNode.next != null) {
            nextNode = currNode.next;//记录下一个节点
            currNode.next = preNode; //currNode.next反转 指向前一个节点
            preNode = currNode;      //preNode currNode  一起向后移一步   更新 preNode
            currNode = nextNode;     //更新currNode
        }
        Log.e(TAG, "开始第一次翻转\n" + "nextNode.data:" + nextNode.data + "" + "nextNode.next()" + nextNode.next + "\n" + "---pre:" + preNode.data + "--pre.next：" +
                preNode.next + "\n" + "--currNode.data:" + currNode.data + "---currNode.next:" + currNode.next);
        Log.e(TAG, "开始第一次翻转\n");
        showNode(head);
    }


    /**
     * 面试题  2 两数相加 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public Node addTwoNumbers(Node node1, Node node2) {
        // 待返回的链表头
        Node dummyNode = new Node(0);
        // 定义 p  为 node1 的表头   q 为 node2的表头    curr 为 待返回的表头
        Node p = node1, q = node2, curr = dummyNode;
        // 表示  两数相加 是否向前进位
        int carry = 0;
        while (p != null || q != null) {
            // 遍历  这2 个链表      循环 会执行到链表最长的那个
            // x 为 p 节点的值  如果 p 节点到达了node1的末尾  将其设置为0
            int x = p == null ? 0 : p.data;
            // y 为 q 节点的值  如果 q 节点到达了node2的末尾  将其设置为0
            int y = q == null ? 0 : q.data;
            // 计算当前 两节点  加 上个节点 进位上来和
            int sum = x + y + carry;
            // 计算下一次需要的进位
            carry = sum / 10;

            // 生成    两数和链表的  下一个节点
            curr.next = new Node(sum % 10);
            // 进行下一次循环
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        // 处理超位数
        if (carry > 0) {
            curr.next = new Node(carry);
        }
        // 返回记录的链表头
        return dummyNode.next;
    }

    /**
     * LeetCode 24 两两交互链表中的节点 ： 给定一个链表，两两交换其中的相邻节点，并返回交换后的链表
     * 不能只说单纯的改变节点内部的值，而是需要实际的进行节点交换   示例 给定 1->2->3->4   返回  2->1->4->3
     * 方法一 递归   时间复杂度 O(N)   空间复杂度 O(N)
     */
    private Node swapPairs(Node head) {
        // 传入 2 5 3 4 1 0
        if ((head == null) || head.next == null) {
            // 递归退出条件
            return head;
        }
        Log.e(TAG, "----swapPairs---------输入的节点：" + head.data);
        // 定义前后两个节点
        Node firstNode = head;   // 5
        Node secondNode = head.next; // 2
        /**
         * 交换   不断递归   直到条件退出     从退出条件分析   最后一次递归
         *  Node firstNode = head;   // 1
         *  Node secondNode = head.next; // 0
         *  firstNode.next = swapPairs(secondNode.next);    firstNode.next = null // 1->null 因为0是最后一个节点  远是输入的第一个节点  指向  第二个 节点后的 交换后的2个节点
         *  // 输入的节点 与后节点 翻转
         *  secondNode.next = firstNode;  即 0->1
         *   返回 翻转后的节点 作为链表头
         *   最后一次递归 返回  0
         */
        // 永远是输入的第一个节点  指向  第二个 节点后的 交换后的2个节点
        firstNode.next = swapPairs(secondNode.next);
        // 后节点 指向前节点
        secondNode.next = firstNode;
        // 新的表头就是  secondNode
        Log.e(TAG, "-------swapPairs------新的表头：" + secondNode.data);
        return secondNode;
    }

    /**
     *  方法二
     * @param head
     * @return
     */
    public Node swapPairsTwo(Node head) {
        Node dummy = new Node(-1);
        dummy.next = head;
        Node prevNode = dummy;
        while (head != null && head.next != null) {
            Node firstNode = head;
            Node secondNode = head.next;
            // 交换
            prevNode.next = secondNode;
            firstNode.next = secondNode.next;
            secondNode.next = firstNode;

            // 重置 prevNode  和head
            prevNode = firstNode;
            head = firstNode.next;
        }
        return dummy.next;
    }
}
