/**
 *  un objet dans un jeu d'aventure
 *  Cette classe fait partie du logiciel Zork,
 *  
 *  Un "ObjetZork" est un  element essentiel dans ce jeu qui permet de le ceer
 *  
 *  il est compose de quatres attribues "nom" , "description","poids"
 *   et un boolean "estTransportable" qui permet de savoir si l'objet est transportable ou pas
 * @author     chabani hocine
 * @author     hamrouni lounis
 * @version    1.0
 * @since      octobre 2015
*/

public class ObjetZork{
	private String nom;
	private String description;
	private int poids;
	private boolean estTransportable;
	
	/**
	*cette fonction permet de creer un objet initialise avec 2 arguments
	* 
	*@ param nom le nom de l'objet
	*@ param desc la description de l'objet
	*@ requires nom!=null;
	*@ requires desc!=null;
	*/
	public ObjetZork(String nom,String desc){
	if(nom==null){
				throw new NullPointerException(
					"Le nom  doit être non null.");
			}
	if(desc==null){
				throw new NullPointerException(
					"La description  doit être non null.");
			}
	
	this.nom=nom;
	description=desc;
	estTransportable=false;
	poids=Integer.MAX_VALUE;
	
	}
	
	/**
	*cette fonction permet de creer un objet initialise avec 3 arguments
	* 
	*@ param nom le nom de l'objet
	*@ param desc la description de l'objet
	*@ param p le poids de l'objet
	*@ requires n!=null;
	*@ requires d!=null;
	*@ requires p!=null;
	*/
	
	public ObjetZork(String n,String d,int p)throws PoidNegativeException{
		if(n==null){
				throw new NullPointerException(
					"Le nom  doit être non null.");
			}
		if(d==null){
				throw new NullPointerException(
					"La description  doit être non null.");
			}
		nom=n;
		description=d;
		//
		estTransportable=true;
		if(p<0){
			throw new PoidNegativeException(p);
		}
		else{
		poids=p;
		}
	}
	
	/**
	*@return  le nom d'un objet
	*
	*/
	
	public String get_nom(){
	
	return nom;
	
	}
	
	/**
	*@return la description  d'un objet
	*/
	public String get_description(){
	
	return description;
	}
	
	/**
	*@retourn le poids d'un objet
	*/
	public int get_poids(){
	
	return poids;
	
	}
	
	/**
	* permet de savoir si l'objet est transportable ou pas 
	* @return true c'est l'objet est transportable 
	* false si non
	*/
	
	public boolean get_boolean(){
	
	return estTransportable;
	
	}
	
	/**
	*cette fonction permet de recuperer toutes les information d'un objet:
	* son nom , sa description, son poids et aussi on va connaitre si 
	* il est tronsportable ou pas 
	*
	*@ retourne les informatoin de l'objet sous forme d'une chaine de caractere   
	*/
	public String get_Information(){
	
	String S;
	S=nom+": "+description+" ";
	
		if(estTransportable==true){
		
		S=S+"transportable "+" poids : "+poids+" kg";
		}
		else{
		
		S=S+" pas tronsportable";
		
		}
	return S;
	}
	
	/**
	*pour tester si un objet est du type objetzork
	* @ param o un object 
	*@ requires o!=null; 
	*/
	
	
	public boolean equals(Object o){
	if(o==null){
				throw new NullPointerException(
					"L'objet a comparer doit être non null.");
			}
	if(!(o instanceof ObjetZork)){
	
		return false;
	
		}
		ObjetZork oz =(ObjetZork) o ;
	 if((nom.equals(oz.nom))&&(description.equals(oz.description))&&(poids==oz.poids)&&(estTransportable == (oz.estTransportable)))
		{
		return true ;
		}
		
	
	return false ;
	
	}
	
	
	

}
