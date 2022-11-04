package pl.edu.pw.ee;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import static java.lang.Math.pow;

public class Huffman {
    private ArrayList<Character> chars;
    private ArrayList<Node> tree;
    private ArrayList<Page> dictionary;
    private int[] counter;
    private Node root;

    public int huffman(String pathToRootDir, boolean compress) {
        File f = new File(pathToRootDir);
        if(compress){
            chars = new ArrayList<>();
            dictionary = new ArrayList<>();
            tree = new ArrayList<>();
            readFileToCompress(pathToRootDir+"plik.txt");
            createTree();
            createDictionaryOnBytes(tree.get(0), (byte) 0, (char) 0);
            showDictionary();
            printDictionaryToFile(pathToRootDir+"dictionary.txt");
            printCodedTextToFile(pathToRootDir+"output.txt");
            return numberOfBytes();
        }else{
            dictionary = new ArrayList<>();
            readDictionaryFromFile(pathToRootDir+"dictionary.txt");
            showDictionary();
            root = new Node(0,'-');
            root = createTreeFromDictionary(root);
            return writeDecodedText(pathToRootDir+"output.txt",pathToRootDir+"decoded.txt");
        }
    }

    private void readFileToCompress(String filename){
        counter = new int[381];
        try {
            File file = new File(filename);
            BufferedReader bfr = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));
            int c = 0;
            while((c = bfr.read()) != -1){
                chars.add((char)c);
                if (c > 380)
                    counter[(int) ' ']++;
                else
                    counter[(int) c]++;
            }
            bfr.close();
        } catch (Exception e) {
        }
        putDataToTree();
    }

    private void putDataToTree(){
        int n = counter.length;
        for(int i=0;i<n;i++)
            if(counter[i] != 0)
                tree.add(new Node(counter[i],(char)i));
    }

    private void createTree(){
        while (tree.size() > 1) {
            Node leftNode = removeMin();
            Node rightNode = removeMin();
            Node nNode = new Node(leftNode.getFrequency() + rightNode.getFrequency(), '@');
            nNode.setChildren(leftNode, rightNode);
            tree.add(nNode);
        }
    }

    private Node removeMin() {
        int n = tree.size();
        int min = 0;
        for (int i = 0; i < n; i++)
            if (tree.get(i).compareTo(tree.get(min)) <= 0)
                min = i;
        return tree.remove(min);
    }

    private void createDictionaryOnBytes(Node root, byte length,char b){
        if (root.isLeaf()) {
            dictionary.add(new Page(root.getSign(), length,b));
            return;
        }
        int liczba = (int) pow(2,length);
        createDictionaryOnBytes(root.getLeftChild(), (byte) (length+1), b);
        createDictionaryOnBytes(root.getRightChild(), (byte) (length+1), (char) (b+liczba));
    }

    private void showDictionary(){
        for(Page p : dictionary)
            System.out.println(p);
    }

    private void printDictionaryToFile(String filename) {
        try {
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(filename), Charset.forName("UTF-8"), true));
            PrintWriter pw = new PrintWriter(filename);
            pw.close();
            for (Page p : dictionary) {
                bfw.write((char) p.getSign());
                bfw.write((char) p.getCode());
                bfw.write((byte) p.getLength());
            }
            bfw.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void printCodedTextToFile(String filename){
        try {
            PrintWriter pw = new PrintWriter(filename);
            pw.close();
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(filename), Charset.forName("UTF-8"), true));
            for (char c : chars) {
                for (Page p : dictionary) {
                    if (c == p.getSign())
                        bfw.append((char) p.getCode());
                }
            }
            bfw.close();
        }catch (Exception e){e.getMessage();}
    }


    private int numberOfBytes(){
        int result = 0;
        for(Page p:dictionary){
            int n = counter[p.getSign()];
            int bytes = p.getLength();
            result += n*bytes;
        }
        return result;
    }

    private class Page {
        private char sign;
        private byte length;
        private char code;

        public Page(char sign, byte length, char code) {
            this.sign = sign;
            this.length = length;
            this.code = code;
        }

        public char getSign() {
            return sign;
        }
        public byte getLength(){
            return length;
        }
        public char getCode() {
            return code;
        }
    }

    private void readDictionaryFromFile (String filename){
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(new File(filename), Charset.forName("UTF-8")));
            int a;
            int i=1;
            char c = 0;
            char code = 0;
            byte len = 0;
            while((a = bfr.read()) != -1) {
                if(i==1)
                    c = (char) a;
                if(i==2)
                    code = (char) a;
                if(i==3) {
                    len = (byte) a;
                    dictionary.add(new Page((char) c, len, (char) code));
                    i=0;
                }i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node createTreeFromDictionary(Node root){
        int lengthOfDictionary = dictionary.size();
        for(int i=0;i<lengthOfDictionary;i++){
            int j=i;
            Node tmp = root;
            int length = dictionary.get(i).getLength();
            char i1 = dictionary.get(i).getCode();
            for(int l=0;l<length;l++){
                char b = (char)(i1>>l);
                //byte b = (byte)(dictionary.get(i).getCode()<<l);
                if(b%2 == 0){
                    if (tmp.getLeftChild() == null)
                        if (l != length-1) {
                            tmp.setLeftChild(new Node(0, '@'));
                            tmp = tmp.getLeftChild();
                        } else {
                            tmp.setLeftChild(new Node(0, dictionary.get(i).getSign() ));
                        }
                    else {
                        tmp = tmp.getLeftChild();
                    }
                }else {
                    if (tmp.getRightChild() == null)
                        if (l != length-1 ) {
                            tmp.setRightChild(new Node(0, '@'));
                            tmp = tmp.getRightChild();
                        } else {
                            tmp.setRightChild(new Node(0, dictionary.get(i).getSign() ));
                        }
                    else {
                        tmp = tmp.getRightChild();
                    }
                }
            }
        }
        return root;
    }

    private int writeDecodedText(String filenameToRead,String filenameToWrite) {
        int numberOfSigns =0;
        try {
            PrintWriter pw = new PrintWriter(filenameToWrite);
            pw.close();
            File file = new File(filenameToRead);
            BufferedReader bfr = new BufferedReader(new FileReader(file, Charset.forName("UTF-8")));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File(filenameToWrite), Charset.forName("UTF-8"), true));
            Node node = root;
            int a;
            while ((a = bfr.read()) != -1) {
                char c = (char) a;
                for (int j = 0; j < 15; j++) {
                    boolean isLeft = ((a >> j) % 2 == 0) ? true : false;
                    if (isLeft)
                        node = node.getLeftChild();
                    else {
                        node = node.getRightChild();
                    }
                    if (node.isLeaf()) {
                        bfw.write((char) node.getSign());
                        node = root;
                        numberOfSigns++;
                        break;
                    }
                }
            }
            bfr.close();
            bfw.close();
        }catch (Exception e){e.getMessage();}
        return numberOfSigns;
    }
}
