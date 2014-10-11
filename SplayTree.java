/**
 *
 * Universidad del Valle de Guatemala
 * @author  Diego Juarez    13280
 *          Daniel Mejia    13271
 *          Jose Sagastume  13217
 * Algoritmos y estructura de datos
 * Seccion: 30
 *          
 */

class BinaryNode
{
    BinaryNode(Comparable theKey) {
        key = theKey;
        left = right = null;
    }

    Comparable key;      
    BinaryNode left;    
    BinaryNode right;        
}

public class SplayTree implements WordSet{
    private BinaryNode root;

    public SplayTree() {
        root = null;
    }

    @Override
    public void add(Word wordObject) {
        BinaryNode n;
        int c;
        if (root == null) {
            root = new BinaryNode(wordObject);
            return;
        }
        splay(wordObject);
        if ((c = wordObject.compareTo( (Word) root.key)) == 0) {      
            return;
        }
        n = new BinaryNode(wordObject);
        if (c < 0) {
            n.left = root.left;
            n.right = root;
            root.left = null;
        } else {
            n.right = root.right;
            n.left = root;
            root.right = null;
        }
        root = n;
    }

    @Override
    public Word get(Word word) {
        if (root == null) return null;
        splay(word);
        if(root.key.compareTo(word) != 0) return null;
        return  (Word) root.key;
    }
    
    private static BinaryNode header = new BinaryNode(null); 
    

    private void splay(Comparable key) {
        BinaryNode l, r, t, y;
        l = r = header;
        t = root;
        header.left = header.right = null;
        for (;;) {
            if (key.compareTo(t.key) < 0) {
                if (t.left == null) break;
                if (key.compareTo(t.left.key) < 0) {
                    y = t.left;                    
                    t.left = y.right;
                    y.right = t;
                    t = y;
                    if (t.left == null) break;
                }
                r.left = t;                                
                r = t;
                t = t.left;
            } else if (key.compareTo(t.key) > 0) {
                if (t.right == null) break;
                if (key.compareTo(t.right.key) > 0) {
                    y = t.right;                           
                    t.right = y.left;
                    y.left = t;
                    t = y;
                    if (t.right == null) break;
                }
                l.right = t;                                
                l = t;
                t = t.right;
            } else {
                break;
            }
        }
        l.right = t.left;    
        r.left = t.right;
        t.left = header.right;
        t.right = header.left;
        root = t;
    }
}