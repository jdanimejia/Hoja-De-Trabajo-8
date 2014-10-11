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


class Word implements Comparable<Word> {
	private String word;
	private String type;
	
	public Word(String word, String type)
	{
		this.word=word;
		this.type=type;
	}
	
	public Word()
	{
		this.word= "";
		this.type="";
	}
	
	public int compareTo(Word o)
	{
		return this.word.compareTo(o.getWord());
	}
	
	public boolean equals(Object obj)
	{
		return (obj instanceof Word && getWord().equals(((Word)obj).getWord()));
	}
	
	public void setWord(String word)
	{
		this.word=word;
	}
	public void setType(String type)
	{
		this.type=type;
	}
	public String getWord()
	{
		return word;
	}
	
	public String getType()
	{
		return type;
	}
	
}