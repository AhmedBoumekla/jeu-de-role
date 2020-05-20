/**
 *  un joueur dans un jeu d'aventure
 *  Cette classe fait partie du logiciel Zork,
 *  elle hérite de ArrayListConteneur
 *  Un "joueur" fais partie d'un des element essentiel du jeu 
 *  
 *  prmet de savoir qui est entrain de joueur 
 *
 *  il est compose de six attribues "nom" , "objets_joueur","poidsTotal","poidsMax","piece_joueur"
 *   cette classe hérite de la clase ArrayListConteneur
*/
import java.util.ArrayList;
public class Joueur extends ArrayListConteneur{

	private String nom ;
	private int capitaleV;
	private int poidsTotal;
	private int poidsMax;
	private int objetsMax;
	
	private Piece piece_joueur;
	
	/**
	*cette fonction permet de creer un joueur initialise avec un seul argument
	* 
	*@ param nom le nom du joueur
	*@ requires nom!=null;
	*/
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
		poidsMax=120;
		objetsMax=5;
		capitaleV=100;
		Armes=new ArrayList<Arme>();
		
	}
	
	/**
	*cette fonction permet de creer un joueur initialise avec quatres arguments
	*@ param nom le nom du joueur
	*@ param le poids maximal
	*@ param nombre maximale d'objets
	*@ param arr une liste d'objets Zork
	*@ requires nom!=null;
	*@ requires poidsMax!=0;
	*@ requires objetsMax!=0;
	*@ requires arr!=null
	*/

	public  Joueur(String nom,int poidsMax,int objetsMax,ArrayList<ObjetZork> arr ) {
		
		super(arr);
		if(poidsMax==0){
			throw new IllegalArgumentException(
					"la vie ne doit pas etre null ");

		}
		if(nom==null){
			
			throw new NullPointerException(
					"Le nom  doit être non null.");
			
		}
		if(objetsMax==0){
			throw new IllegalArgumentException(
					"le nombre maximal d'objets  ne doit pas etre null ");

		}
		
		this.nom = nom;
		this.poidsMax= poidsMax;
		this.objetsMax=objetsMax;
		capitaleV=150;
		Armes=new ArrayList<Arme>();
		
	}
	
	

	 /**
	* retour l'objet qui se trouve a la position i dnas la liste des objets du joueur
	*@ param la position ir 
	*@ requires i!=0;
	*/

	/**
	* @param oz l'objet zork que on veut ajouter
	* @return true si on peut ajouter oz au joueur ; false si non
	* @ requires oz!=null
	*/
	@Override
	public boolean ajoutPossible(ObjetZork oz){
		if(oz==null){
				throw new NullPointerException(
					"L'objet a ajouté doit être non null.");
			}
		int poids=oz.get_poids();
		int max=1;
		for(int i=0;i<getTabobjets().length;i++){
			poids+=getTabobjets()[i].get_poids();
			//max++;
			//objetsMax++;
			}
		for(int j=0;j<getArmes().size();j++){
			poids+=getArmes().get(j).get_poids();
			}

		if(poids<poidsMax) {
				if(getTabobjets().length +getArmes().size()+1 <= objetsMax){
					return true;
				}
			
			else{
			System.out.println("l'ajout est impossible; nombre d'objets dépassé");
			return false;
			}
		}
		else{
			System.out.println("l'ajout est impossible a cause du poids");
			return false;
		}
	}
	/**
	* @param ar l'arme que on veut ajouter
	* @return true si on peut ajouter ar au joueur ; false si non
	* @ requires ar!=null
	*/
	
	@Override
	public  boolean ajoutPossibleA(Arme ar){
		if(ar==null){
				throw new NullPointerException(
					"L'arme a ajouté doit être non null.");
			}
		int poids=ar.get_poids();
		int max=1;
		for(int i=0;i<Armes.size();i++){
			poids+=Armes.get(i).get_poids();
			max++;
			}
		for(int k=0;k<getTabobjets().length;k++){
			poids+=getTabobjets()[k].get_poids();
			
			}
		if(poids<poidsMax ){	
				if(getTabobjets().length+getArmes().size()+1 <= objetsMax){
						
					return true;}
				else{
				System.out.println("vous ne pouvez pas prendre l'arme "+ar.get_nom()+" nombre d'objets dépassé");
						return false;}
			}
		else{
			System.out.println("vous ne pouvez pas prendre l'arme "+ar.get_nom()+" a cause de votre poids");
				return false;
			}
		

	}

	/**
	* affiche tous les objets du joueur
	*
	*/
	public void afficher_j(){
	String s= " " ;
	for (int i=0;i<getTabobjets().length;i++){
		
		s=s+" " + getTabobjets()[i].get_nom();
		}
		
	System.out.println(s);

	}
	/**
	* ajoute un objet zork a la liste des objets du joueur
	* 
	*@ param oz l'objet a ajouter 
	*@ requires oz!=null;
	*@ requires objets.size()!=0;
	*@ ensures objets.size()==(\old(objets.size())+1);
	*/
	@ Override
 	public void ajouter(ObjetZork oz){
		if(oz==null){
				throw new NullPointerException(
					"L'objet a ajouté doit être non null.");
			}
		if(oz.get_boolean()){
			if(ajoutPossible(oz)){
				poidsTotal+= oz.get_poids();
				//objetsMax ++;
				objets.add(oz);
				//objetsMax ++;
				}
		

			else{
				System.out.println("l'ajout est impossible a cause de son poids");
			}
			
		}
		else{
			System.out.println("l'objet"+" "+ oz.get_nom()+ " n'est pas transportable");
		}
		
	}
	/**
	* ajoute une arme  a la liste des armes du joueur
	* 
	*@ param a l'arme a ajouter 
	*@ requires  a!=null;
	*@ requires Armes.size()!=0
	*@ ensures Armes.size()==(\old(Armes.size())+1);
	*/
	@Override
	public void ajouterA(Arme a){
		
		if(a==null){
				throw new NullPointerException(
					"L'arme a ajouté doit être non null.");
			}
		//if(poidsTotal+ a.get_poids()<=poidsMax &&(getTabobjets().length+getArmes().size())<=objetsMax){
		if(ajoutPossibleA( a)){
		poidsTotal+= a.get_poids();
		//objetsMax ++;
		Armes.add(a);
		
		}
		

		else{
			System.out.println("l'ajout est impossible a cause de son poids");
			}
			
	}
		
		

	/**
	* retire un objet de la liste des objets du joueur
	* @ param oz l'objet a retirer
	* @ return true si l'objet a été retiré ; false si non
	* @ requires oz!=null
	* @ requires objets.size()!=0
	* @ ensures objets.size()==(\old(objets.size())-1);
	*/
	
	@Override
	
	 public boolean retirer(ObjetZork oz){
		if(oz==null){
				throw new NullPointerException(
					"L'objet a retirer doit être non null.");
			}
	 if(objets.contains(oz)){
	 	poidsTotal-= oz.get_poids();
		//objetsMax --;
	        return super.retirer(oz);
	 	}
	 	return false;
	
	 }

	/**
	* retire une arme de la liste des armes du joueur
	* @ param a l'arme a retirer
	* @ return true si l'arme " a "  a été retirée ; false si non
	* @ requires a!=null
	* @ requires Armes.size()!=0
	* @ ensures Armes.size()==(\old(Armes.size())-1);
	*/
	@Override
	public boolean retirerA(Arme a){
		if(a==null){
				throw new NullPointerException(
					"L'arme a retirer doit être non null.");
			}
	 if(Armes.contains(a)){
	 	poidsTotal-= a.get_poids();
		//objetsMax --;
	        return super.retirerA(a);
	 	}
	 	return false;
	
	 }
	/**
	* teste si le joueur est encore vivant 
	* @ return true si le joueur est encore vivant ; false si non
	*/
	public boolean est_vivant(){
		if(!(capitaleV <= 0 ))
		return true;
		return false;
	}
	/**
	* @ return capitalev d'une instance de la classe joueur
	* @ insures 
	*/
	public int getV(){
		return capitaleV;
	}

	/**
	* @ return le nom du joueur
	*/
	public String getNom(){
		return nom;
	}

	/**
	* renitilalise la vie du joueur a une nouvelle valeur v
	* @ param v la nouvelle vie du joueur
	* @ requires v!=null
	*/

	void setV(int v){
			capitaleV=v;

	}
	/**
	* attaque un personnage avec une arme ; les deux sont des parametres de la méthode
	* @param p le personnage a attaquer
	* @param a , l'arme avec laquelle le joueur attaque
	* @requires p!=null
	* @ requires a!=null
	*/

	public void J_attque(Personnage p,Arme a){
		if(p==null){
				throw new NullPointerException(
					"Le personnage  a attaqué doit être non null.");
			}
		if(a==null){
				throw new NullPointerException(
					"l'arme avec laquelle vous attaquer doit être non null.");
			}
		
		if(p.puetAttaquer() ){
			if(p.est_vivant()){
					p.setVie(p.getVie()-a.getP());
				 	System.out.println("vous avez attaqué "+p.getNom()+" avec "+a.get_nom());
					if(p.est_vivant()){
			
						System.out.println(p.getNom()+" lui reste "+p.getVie() +"points");}
					else{
						System.out.println(p.getNom()+" est  mort");
						p.setVie(0);
					}
			}
			else{
				
				System.out.println(p.getNom()+" est deja mort");
				}
		}
		
		else{
			System.out.println("vous ne pouvez pas attaqué " +p.getNom()+" malgré qu'elle est la ");	
			}
		
	}

	


}


