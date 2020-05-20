/**
* une classe qui hérite de la classe Exception
* sert a gérer les exceptions du poids négative 
* elle a comme attribue "p" un poids
*/

public class PoidNegativeException extends Exception{
	int p;
	/**
	*un constructeur par défaut 
	*/
	public PoidNegativeException(){
		System.out.println("le poids est négative");
		}
	/**
	*un constructeur avec argument "i" pour initialisé le champs p 
	*/
	public PoidNegativeException(int i){
	p=i;

	}

	/**
	* afficher un message d'erreur en cas d'un poids négative
	*/
	public void message(){
	System.out.println("le poids est negative!!! \n");

	}






























}
