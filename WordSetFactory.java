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

class WordSetFactory {
	
	public static WordSet generateSet(int tipo)
	{
	    if (tipo == 1)
                return new SimpleSet();
            else if (tipo == 2)
                return RedBlackTree();
            else if (tipo == 3)
                return SplayTree();
            else if (tipo == 4)
                return Hashtable();
            else if (tipo == 5)
                return TreeMap();
	}
	
	
}