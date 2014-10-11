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

import java.util.*;
        
public class TreeMapSet implements WordSet{
   
    private TreeMap base;
        public TreeMapSet()
        {
                base = new TreeMap();
        }
       
        public Word get(Word word)
        {
            return (Word)base.get(word.getWord());
        }
       
       public void add(Word wordObject)
        {
          if (!base.containsKey(wordObject.getWord()))
            base.put(wordObject.getWord(),wordObject);
        }
}