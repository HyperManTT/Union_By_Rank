import java.util.*;
import java.io.*;

public class unionByRank
{
    private static class node
    {
        private int rank;
        private int value;
        private int parent;
    }
    
    public static void main(String[] args)
    {
        unionByRank r = new unionByRank();
        node nodeList[] = new node[12];
        for(int i = 1; i < nodeList.length; i++)
        {
            node n = new node();
            n.rank = 0;
            n.value = i;
            n.parent = 0;
            nodeList[i] = n;
        }
        
        r.union(nodeList, 1, 2);
        r.union(nodeList, 3, 4);
        r.union(nodeList, 5, 6);
        r.union(nodeList, 7, 8);
        r.union(nodeList, 1, 3);
        r.union(nodeList, 8, 9);
        r.union(nodeList, 6, 8);
        //System.out.println(r.find(nodeList, 5));
        r.union(nodeList, 4, 8);
        r.union(nodeList, 5, 10);
        r.union(nodeList, 3, 11);
        //System.out.println(r.find(nodeList, 1));
        
        for(int i = 1; i < nodeList.length; i++)
        {
            System.out.print(nodeList[i].parent + " ");
        }
    }
    
    private int find(node nodeList[], int n)
    {
        ArrayList<Integer> nodesPassedThrough = new ArrayList<Integer>();
        int currentIndex = n;
        while(nodeList[currentIndex].parent != 0)
        {
            nodesPassedThrough.add(currentIndex); //Store the nodes that was traversed to do path compression afterwards
            currentIndex = nodeList[currentIndex].parent;
        }
        
        //Path compression
        for(Integer i: nodesPassedThrough)
        {
            nodeList[i].parent = currentIndex;
        }
        return currentIndex;
    }
    
    private void union(node nodeList[], int num1, int num2)
    {
        //Find the root of the two numbers
        int root1 = find(nodeList, num1);
        int root2 = find(nodeList, num2);
        if (root1 != root2)
        {
            if(nodeList[root1].rank != nodeList[root2].rank)
            {
                //If the rank of root1 is less than the rank of root2, let root1 point to root2
                if(nodeList[root1].rank < nodeList[root2].rank)
                {
                    nodeList[root1].parent = root2;
                }
                else
                {
                    nodeList[root2].parent = root1;
                }
            }
            else
            {
                //If ranks are equal, let the first point to the second and increment rank of second.
                nodeList[root1].parent = root2;
                nodeList[root2].rank += 1;
            }
        }
    }
}