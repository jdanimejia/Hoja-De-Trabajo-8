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

class HashTable implements WordSet{
        private Hashtable base;
        private int key;
        
        public HashTable(){
            base = new Hashtable();
            key = 0;
        }
        
        public Word get(Word word){
            Word wordActual;
            for(int x=0; x<base.size(); x++){
                wordActual = (Word) base.get(x);
                if(wordActual.getWord().equals(word.getWord())){
                    return wordActual;
                }
            }
            return null;
        }
        
        public void add(Word wordObject){
            base.put(key, wordObject);
            key++;
        }
}