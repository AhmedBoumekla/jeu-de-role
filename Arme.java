/**
 une classe qui hérite d'objetZork
 alors une arme c'est un objet avec lequel le joueur peut attaquer ses enmies
 elle a comme attribue en plus que la classe ObjetZork : "deuireP" est c'es le nombre de points
 a déduire de la vie d'un personnage attaqué
*/
public class Arme extends ObjetZork{

	private int deduireP;


	/**
	* permet de creer une ainstance d'arme initialisé avec 4 arguments
	* @param d le nombre de puis a déduire 
	* @param n le nom de l'arme
	* @param dd le description de l'arme
	* @param pp le poids de l'arme
	* @requires d >=0
	* @requires  nn!=null
	* @requires  dd!=null
	* @requires  pp  >0
	*/


	public Arme(int d,String nn,String dd,int pp)throws PoidNegativeException{
		
		super(nn,dd,pp);
		 deduireP=d;
		}
	
	/**
	* @return deduireP le nombre de points a déduire ; de la classe Arme
	*/
	public int getP(){

	return deduireP;
	}




}
