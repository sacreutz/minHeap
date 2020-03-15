import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Integer> inputData = new ArrayList<Integer>();
    public static Heap<Integer> heapCopy;
    public static BufferedWriter output;

    public static void printHeap(String description, Heap<Integer> heap) {

        System.out.println(description);
        System.out.println("[" + heap.size() + "]");

        for (int i = 0; i < heap.size(); ++i) {
            System.out.print(" " + heap.getElement(i) + " ");

        }
        System.out.println("\n");
    }

    public static StringBuilder printHeap2(Heap<Integer> heap)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(heap.getElement(0));
        for (int i = 1; i < heap.size()/2; ++i) {

              sb.append(" ").append( Integer.toString(heap.getElement(2 * i)))
               .append(" ").append(Integer.toString(heap.getElement(2 * i + 1))).append(" ");
          //  System.out.println();
        }
        int size = heap.size();
//       if (sb.length() % 2 != 0){
//           sb.append(heap.getElement(size)).append(" ");
//       }

        return sb;
    }


    public static boolean areHeapElementsOrdered(Heap<Integer> heap, int i, int j) {
        return i >= heap.size() || j >= heap.size() || heap.getElement(i) <= heap.getElement(j);

    }

    public static String heapElement(Heap<Integer> heap, int i) {
        return i < heap.size() ? Integer.toString(heap.getElement(i)) : "none";
    }


    public static boolean isHeapUtil(int element) {
        if((2*element + 1) > (heapCopy.size() -1)  && (2*element + 2) > (heapCopy.size() -1)) {
                return true;
        }

        if ((2*element + 2) > (heapCopy.size() -1)) {
            return element >= heapCopy.getElement(2*element + 1);
        }
        else {
            if (element >= heapCopy.getElement(2*element + 1) && element >= heapCopy.getElement(2*element + 2)) {
                return isHeapUtil(heapCopy.getElement(2*element+1)) && isHeapUtil(heapCopy.getElement(2*element+2));
            } else {
                return false;
            }

        }
    }

    public static boolean checkMinHeap(Heap<Integer> A, int i)
    {
        // if i is a leaf node, return true as every leaf node is a heap
        if (2*i + 2 > A.size()) {
            return true;
        }

        // if i is an internal node

        // recursively check if left child is heap
        boolean left = A.getElement(i) <= A.getElement(2*i + 1) && checkMinHeap(A, 2*i + 1);

        // recursively check if right child is heap (to avoid array out
        // of bound, we first check if right child exists or not)
        boolean right = (2*i + 2 == A.size()) ||
                (A.getElement(i) <= A.getElement(2*i + 2) && checkMinHeap(A, 2*i + 2));

        // return true if both left and right child are heap
        return left && right;
    }

    public static void checkHeap(Heap<Integer> heap)
    {
        int index = 0;

        if (checkMinHeap(heap, index)) {
            System.out.println("heap");
        } else {
            System.out.println("not a heap");
        }

//        if (isHeapUtil(heap.getElement(0))) {
//            System.out.println("is a heap");
//        } else {
//            System.out.println("is not a heap");
//        }


//        for (int i = 0; i < heap.size(); ++i) {
//            if (!(areHeapElementsOrdered(heap, i, i*2+1) && areHeapElementsOrdered(heap, i, i*2+2))) {
//                printHeap("Corrupted", heap);
//                System.out.println("Error: heap violation at index " + i);
//                System.out.println(", heap[" + i + "] = " + heapElement(heap, i));
//                System.out.println(", heap[" + (i * 2 + 1) + "] = " + heapElement(heap, i * 2 + 1));
//                System.out.println(", heap[" + (i * 2 + 2) + "] = " + heapElement(heap, i * 2 + 2));
//                System.out.println("\n");
//            }
//
//        }
    }

    public static void insertOne(Heap<Integer> heap, int element) {
        System.out.println("Insert " + element + "\n");
        heap.insert(element);
       // heap.minHeapify(element);
        heapCopy = heap;
        checkHeap(heap);

    }

    public void deleteOne(Heap<Integer> heap) {
        System.out.println("Delete " + heap.getElement(0) + "\n");
        heap.deleteMin();
        heapCopy = heap;
        checkHeap(heap);
    }

    public static void testData(ArrayList<Integer> inputData, int j) throws IOException {
        Heap<Integer> heap = new Heap<Integer>(inputData.size());
        String fileName =  "out" + (j + 1) + ".txt";
        File outputFile = new File(fileName);
        BufferedWriter output2 = new BufferedWriter(new FileWriter(outputFile));

        for (int i = 0; i < inputData.size(); i++) {
            heap.insert(inputData.get(i));
            System.out.println(inputData.get(i) + " key");

            heap.minHeapify(i);
            heapCopy = heap;


            checkHeap(heapCopy);
        }


        StringBuilder sb2 = new StringBuilder();

        sb2.append(printHeap2(heap));


        output2.write(String.valueOf(sb2));
        output2.newLine();


        StringBuilder sb3 = new StringBuilder();
       // insertOne(heap, 31);
       // printHeap("Heap after insert 31", heap);
        output2.write("Heap after insert 31");
       // Heap.maxsize++;
      //  Heap.size++;
        heap.insert(31);
        heap.minHeapify(heap.size());
        output2.newLine();
        sb3.append(printHeap2(heap));
        output2.write(String.valueOf(sb3));
        output2.newLine();

       // insertOne(heap, 14);
        //printHeap("Heap after insert 14", heap);
        StringBuilder sb5 = new StringBuilder();
        heap.insert(14);
        output2.write("Heap after insert 14");
        output2.newLine();
        sb5.append(printHeap2(heap));
        output2.write(String.valueOf(sb5));
        output2.newLine();

        while (heap.size() > 0) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("delete min");
            sb4.append("\n");
            heap.deleteMin();
            sb4.append(printHeap2(heap));
            output2.write(String.valueOf(sb4));
            output2.newLine();
            printHeap("Heap after deleteMin", heap);
        }

        output2.close();
    }

    public static void readAndWriteData(File inputFile, int i) throws IOException {
        Scanner sc = new Scanner (inputFile);
        while (sc.hasNext()) {
            inputData.add(Integer.parseInt(sc.next()));
        }

        String fileName =  i + ".txt";
      //  File outputFile = new File(fileName);
       // output = new BufferedWriter(new FileWriter(outputFile));
        System.out.println(inputData.toString() + "inputData");
        testData(inputData, i);


    }



    public static void main(String[] args) throws IOException {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        System.out.println("Current relative path is: " + s);
        File folder = new File(s + "\\src\\Inputfiles");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
           // String fileName = Integer.toString(i) + ".txt";
            inputData.clear();
            readAndWriteData(listOfFiles[i], i);
        }

    }

}
