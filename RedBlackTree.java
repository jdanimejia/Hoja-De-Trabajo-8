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
public class RedBlackTree implements WordSet
{  
   Node root; 
   
   static final int BLACK = 1;
   static final int RED = 0;
   private static final int NEGATIVE_RED = -1;
   private static final int DOUBLE_BLACK = 2;

   public RedBlackTree()
   {  
      root = null;
   }
   
  
   public void add(Word wordObject) 
   {  
      Node newNode = new Node();
      newNode.data = wordObject;
      newNode.left = null;
      newNode.right = null;
      if (root == null) {
          root = newNode; 
      }
      else { 
          root.addNode(newNode); 
      }
      fixAfterAdd(newNode);
   }

   
   public boolean find(Comparable obj)
   {
      Node current = root;
      while (current != null)
      {
         int d = current.data.compareTo(obj);
         if (d == 0) 
             return true;
         else if (d > 0)
             current = current.left;
         else 
             current = current.right;
      }
      return false;
   }
   
   /**
      Obtiene un Word del árbol
      @param word es el objeto que se va a obtener del árbol
      @return el valor, si se encuentra en el árbol. Si no esta en el árbol devuelve null
   */
   
   @Override
   public Word get(Word word){
       Node current = root;
      while (current != null)
      {
         int d = current.data.compareTo(word);
         if (d == 0) 
             return (Word) current.data;
         else if (d > 0)
             current = current.left;
         else 
             current = current.right;
      }
      return null;
   }

   /**
      Un nodo tiene hijos a la izquierda o derecha. En el 
      RedBlack tree pueden ser negros o rojos. 1 es para negro y 0
      es para rojo. Temporalmente se puede poner 2 o -1
   */
   static class Node
   {  
      public Comparable data;
      public Node left;
      public Node right;
      public Node parent;
      public int color;
      
      /**
         Constructor
      */
      public Node() {}
      
      /**
         Le da set al hijo de la izquierda
         @param child es el hijo de la izquierda
      */
      public void setLeftChild(Node child)
      {
         left = child;
         if (child != null) { child.parent = this; }
      }
      
      /**
         Set al hijo de la derecha
         @param child es el hijo de la derecha
      */
      public void setRightChild(Node child)
      {
         right = child;
         if (child != null) { child.parent = this; }
      }
      
      /**
       * Reemplaza un nodo
       * Actualiza elos padres y los nodos de reemplazo cuando se reemplaza
       * @param replacement es que va a reemplazar
       */
      public void replaceWith(Node replacement)
      {
          if (parent == null) return;
          if (this == parent.left) parent.setLeftChild(replacement);
          else parent.setRightChild(replacement);
      }
      
      /**
         Agrega un nodo
         @param newNode el nodo a ingresar
      */
      public void addNode(Node newNode)
      {  
         int comp = newNode.data.compareTo(data);
         if (comp < 0)
         {  
            if (left == null) 
            {
               left = newNode;
               left.parent = this;
            }
            else { left.addNode(newNode); }
         }
         else if (comp > 0)
         {  
            if (right == null) 
            {
               right = newNode;
               right.parent = this;
            }
            else { right.addNode(newNode); }
         }
      }
   }

   /**
    * Esto se hace cada vez que se agrega un nodo, de esta manera siempre
    * se va a tener un árbol balanceado
      Cuando se agrega un nodo hace que el árbol se tipo RedBlackTree
      @param newNode El nodo que se agregó
   */
   private void fixAfterAdd(Node newNode)
   {
      if (newNode.parent == null) 
      { 
         newNode.color = BLACK; 
      }
      else
      {
         newNode.color = RED;
         if (newNode.parent.color == RED) { fixDoubleRed(newNode); }
      }
   }

   /**
      Move a charge from two children of a parent
      @param parent a node with two children, or null (in which case nothing is done)
   */
   private void bubbleUp(Node parent)
   {
      if (parent == null) { return; }
      parent.color++;
      parent.left.color--;
      parent.right.color--;
           
      Node child = parent.left;
      if (child.color == NEGATIVE_RED) { fixNegativeRed(child); return; }
      else if (child.color == RED)
      {
         if (child.left != null && child.left.color == RED) 
         { 
            fixDoubleRed(child.left); 
            return; 
         }
         if (child.right != null && child.right.color == RED) 
         { 
            fixDoubleRed(child.right); return; 
         }
      }
   
      child = parent.right;
      if (child.color == NEGATIVE_RED) { fixNegativeRed(child); return; }
      else if (child.color == RED)
      {
         if (child.left != null && child.left.color == RED) 
         { 
            fixDoubleRed(child.left); 
            return; 
         }
         if (child.right != null && child.right.color == RED) 
         { 
            fixDoubleRed(child.right); 
            return; 
         }
      }
          
      if (parent.color == DOUBLE_BLACK) 
      { 
         if (parent.parent == null) { parent.color = BLACK; }
         else { bubbleUp(parent.parent); }
      }
   }
   
   
   /**
      Corrige la violación de dos nodos rojos seguidos
      @param child El hijo rojo con un nodo padre rojo
   */
   private void fixDoubleRed(Node child)
   {
      Node parent = child.parent;      
      Node grandParent = parent.parent;
      if (grandParent == null) { parent.color = BLACK; return; }
      Node n1, n2, n3, t1, t2, t3, t4;
      if (parent == grandParent.left)
      {
         n3 = grandParent; t4 = grandParent.right;
         if (child == parent.left)
         {
            n1 = child; n2 = parent;
            t1 = child.left; t2 = child.right; t3 = parent.right;
         }
         else
         {
            n1 = parent; n2 = child;
            t1 = parent.left; t2 = child.left; t3 = child.right; 
         }
      }
      else
      {
         n1 = grandParent; t1 = grandParent.left;
         if (child == parent.left)
         {
            n2 = child; n3 = parent;
            t2 = child.left; t3 = child.right; t4 = parent.right;
         }
         else
         {
            n2 = parent; n3 = child;
            t2 = parent.left; t3 = child.left; t4 = child.right; 
         }         
      }
      
      if (grandParent == root)
      {
         root = n2;
         n2.parent = null;
      }
      else
      {
         grandParent.replaceWith(n2);
      }
      
      n1.setLeftChild(t1);
      n1.setRightChild(t2);
      n2.setLeftChild(n1);
      n2.setRightChild(n3);
      n3.setLeftChild(t3);
      n3.setRightChild(t4);
      n2.color = grandParent.color - 1; 
      n1.color = BLACK;
      n3.color = BLACK;

      if (n2 == root)
      {
         root.color = BLACK;
      }
      else if (n2.color == RED && n2.parent.color == RED)
      {
         fixDoubleRed(n2);
      }
   }
   
   /**
      Corrige la violación "negative Red"
      @param negRed the negative red node
   */
   private void fixNegativeRed(Node negRed)
   {    
      Node n1, n2, n3, n4, t1, t2, t3, child;
      Node parent = negRed.parent;
      if (parent.left == negRed)
      {
         n1 = negRed.left;
         n2 = negRed;
         n3 = negRed.right;
         n4 = parent;
         t1 = n3.left;
         t2 = n3.right;
         t3 = n4.right;
         n1.color = RED;
         n2.color = BLACK;
         n4.color = BLACK;
         n2.setRightChild(t1);
         Comparable temp = n4.data; n4.data = n3.data; n3.data = temp;
         n3.setLeftChild(t2);
         n3.setRightChild(t3);
         n4.setRightChild(n3);
         child = n1;
      }
      else
      {
         n4 = negRed.right;
         n3 = negRed;
         n2 = negRed.left;
         n1 = parent;
         t3 = n2.right;
         t2 = n2.left;
         t1 = n1.left;
         n4.color = RED;
         n3.color = BLACK;
         n1.color = BLACK;
         n3.setLeftChild(t3);
         Comparable temp = n1.data; n1.data = n2.data; n2.data = temp;
         n2.setRightChild(t2);
         n2.setLeftChild(t1);
         n1.setLeftChild(n2);
         child = n4;
      }
           
      if (child.left != null && child.left.color == RED) 
      { 
         fixDoubleRed(child.left); 
         return; 
      }
      if (child.right != null && child.right.color == RED) 
      { 
         fixDoubleRed(child.right);  
      }
   }

   
}