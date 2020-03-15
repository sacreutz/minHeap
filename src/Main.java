import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<Integer> inputData = new ArrayList<Integer>();

    public static void printHeap(String description, Heap<Integer> heap) {
        System.out.println(description);
        System.out.println("[" + heap.size() + "]");

        for (int i = 0; i < heap.size(); ++i) {
            System.out.print(" " + heap.getElement(i) + " ");

        }
        System.out.println("\n");
    }

    public static boolean areHeapElementsOrdered(Heap<Integer> heap, int i, int j) {
        return i >= heap.size() || j >= heap.size() || heap.getElement(i) <= heap.getElement(j);

    }

    public static String heapElement(Heap<Integer> heap, int i) {
        return i < heap.size() ? Integer.toString(heap.getElement(i)) : "none";
    }

    public static void checkHeap(Heap<Integer> heap)
    {
        for (int i = 0; i < heap.size(); ++i) {
            if (!(areHeapElementsOrdered(heap, i, i*2+1) && areHeapElementsOrdered(heap, i, i*2+2))) {
                printHeap("Corrupted", heap);
                System.out.println("Error: heap violation at index " + i);
                System.out.println(", heap[" + i + "] = " + heapElement(heap, i));
                System.out.println(", heap[" + (i * 2 + 1) + "] = " + heapElement(heap, i * 2 + 1));
                System.out.println(", heap[" + (i * 2 + 2) + "] = " + heapElement(heap, i * 2 + 2));
                System.out.println("\n");
            }

        }
    }

    public static void insertOne(Heap<Integer> heap, int element) {
        System.out.println("Insert " + element + "\n");
        heap.insert(element);
        checkHeap(heap);
      //  printHeap("Heap after insert " + Integer.toString(element), heap);
    }

    public void deleteOne(Heap<Integer> heap) {
        System.out.println("Delete " + heap.getElement(0) + "\n");
        heap.deleteMin();
        checkHeap(heap);
    }

    public static void testData(ArrayList<Integer> inputData)
    {
        Heap<Integer> heap = new Heap<Integer>(inputData.size());
        for (int i = 0; i < inputData.size(); i++) {
            heap.insert(inputData.get(i));
            System.out.println(inputData.get(i) + "key");

            heap.minHeapify(i);
            printHeap("test ", heap);
            //checkHeap(heap);
        }

        insertOne(heap, 31);
        printHeap("Heap after insert 31", heap);

        insertOne(heap, 14);
        printHeap("Heap after insert 14", heap);

        while (heap.size() > 0) {
            heap.deleteMin();
            printHeap("Heap after deleteMin", heap);
        }
    }

    public static void readAndWriteData(File inputFile, int i) throws FileNotFoundException {
        Scanner sc = new Scanner (inputFile);
        while (sc.hasNext()) {
            inputData.add(Integer.parseInt(sc.next()));
        }

        System.out.println(inputData.toString() + "inputData");
        testData(inputData);


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
