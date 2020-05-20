import java.util.*;
/* un personnage dans le jeu de zork
  un des elements du jeu  
  cetains personnages peuvent se déplacer dans toutes les pieces , d'autres non
  certains personnages peuvent attaquer le joueur et d'autres non
  alors y a des amies et des enmies
	cette classe est composé de neuf attribues "vie" , "xp" le nombre de points a deduire en cas d'attaque,
	"nom" , "description" ,"objs" une liste d'objet zork, "move" un boolean pour le deplacement, "pieceCour" la piece courante 
	pour le personnage, boolean "attaque" pour savoir si le personnage peut attaquer ou pas
**/
public   class Personnage {
	
	private int vie;
	private int xp;
	private String nom;
	private String description;
	private ArrayList<ObjetZork> objs;
	private ArrayList<Piece> mesPieces;
	private boolean move;
	private Piece pieceCour;
	private boolean attaque;
	public Personnage(String n){
		if(n==null){
			
			throw new NullPointerException(
					"Le nom  doit être non null.");}
		vie=100;
		nom=n;
		
		mesPieces=new ArrayList<Piece>();
		objs=new ArrayList<ObjetZork>();
		move=false;
		pieceCour=null;
		attaque =false;
	
	}
	/**
	* permet de creer un personnage initialisé avec 5 arguments
	* @ requires v >=0
	* @ requires x >=0
	* @ requires n!=null
	* @ requires d!=null
	* @ requires att!=null
	*/
	public Personnage(int v,int x,String n,String d,boolean att){
		if(v==0){
			throw new IllegalArgumentException(
					"la vie ne doit pas etre null ");

		}
		if(n==null){
			
			throw new NullPointerException(
					"Le nom  doit être non null.");
			
		}
		if(d==null){
			throw new NullPointerException(
					"La description  doit être non null.");
			}
		
		vie=v;
		xp=x;
		nom=n;
		description =d;
		mesPieces=new ArrayList<Piece>();
		objs=new ArrayList<ObjetZork>();
		move=false;
		pieceCour=null;
		attaque=att;
		
	}
	/**
	* permet de creer un personnage initialisé avec 6 arguments
	* @ requires v >=0
	* @ requires x >=0
	* @ requires n!=null
	* @ requires d!=null
	* @ requires p!=null
	* @ requires att!=null
	*/
	public Personnage(int v,int x,String n,String d,ArrayList<Piece> p,boolean att){
		if(v==0){
			throw new IllegalArgumentException(
					"la vie ne doit pas etre null ");

		}
		if(n==null){
			
			throw new NullPointerException(
					"Le nom  doit être non null.");
			
		}
		if(d==null){
			throw new NullPointerException(
					"La description  doit être non null.");
			}
		if(p==null){
			throw new NullPointerException(
					"La liste des pieces  doit être non null.");

		}
		vie=v;
		xp=x;
		nom=n;
		description =d;
		mesPieces=new ArrayList<Piece>(p);
		objs=new ArrayList<ObjetZork>();
		move=true;
		for(Piece pi:p){
		mesPieces.add(pi);
		pieceCour=p.get(0);
		attaque=att;
		}
	}
	/**
	* @return la vie du personnage
	*/
	public int getVie(){
	return vie;
	}
	/**
	* @return le xp du personnage
	*/
	public int getXp(){

	return xp;

	}
	/**
	* reinitialie la vie du personnage a v
	* @param v la nouvelle valeur de vie
	* @requires v>=0
	* @ ensures vie=v
	*/
	public void setVie(int v){

	 vie=v;

	}
	/**
	* reinitialie le xp du personnage a x
	* @param x la nouvelle valeur de xp
	* @requires x>0
	* @ensures xp=x
	*/
	public void setXp(int x){

	 xp=x;

	}
	/**
	*
	* @ return le nom du personnage	
	*/
	public String getNom(){
		return nom;
	}
	/**
	* @ return la description du personnage 
	*/
	public String getDesc(){
		return description;
	}
	/**
	*
	* affiche le nom du personnage ; sa description et la valeur de sa vie
	*/
	public void AfficherP(){
		System.out.println ("personnage: "+ getNom()+""+ getDesc()+ " a une vie :  "+getVie()+"\n");

		}

	/**
	 * ajoute l'objet zork oz a la liste des objets de la classe 
	 *@param oz l'objet zork a ajouter 
	 *@requires oz!=null;
	 * requires objs.size()!=0
	 *@ensures objs.size()==(\old(objs.size())+1);
	 */
	public void ajouter(ObjetZork oz){
			if(oz==null){
				throw new NullPointerException(
					"L'objet a ajouté doit être non null.");
			}
			objs.add(oz);
		}
	/** retirer l'objet zork oz 
	 * @ param oz l'objet a retirer
	 * @ return vrai si l'objet a ete retire et faux si non
	 * @ requires oz!=null;
	 * @ requires objets.size!=0;
	 * @ ensures objs.size()==(\old(objs.size())-1);
	 */
	public boolean retirer(ObjetZork o){
			if(o==null){
				throw new NullPointerException(
					"L'objet a retiré doit être non null.");
			}
			return objs.remove(o);
		}

	/**
	* attaquer un jouer donner en parametre
	*@ param j un joueur a attaquer
	*@ requires j!=null
	*@ ensures j.getVie()=(\old(j.getVie)-xp)
	*/
	public void attaquer(Joueur j){
		if(j==null){
				throw new NullPointerException(
					"Le joueur  a attaqué doit être non null.");
			}
		if(j.est_vivant()){
			
			j.setV(j.getV()-xp);
		
			System.out.println("joueur: "+j.getNom()+" est attaqué par "+getNom()+" " +getDesc());
			if(j.est_vivant()){
				System.out.println("il lui reste que :" +j.getV()+" point de vie ");
			}

			else{	System.out.println("le joeur  "+j.getNom()+" est  mort");}
			}
		else{
			System.out.println("le joeur  "+j.getNom()+" est deja mort");
			}
	}
	/**
	* @return true si vie > 0 ; false si non 
 	*/
	public boolean est_vivant(){
		if(!(vie <=0))
		return true;
		return false;

		}
	/**
	* @ return une piece au hasard parmi la liste des pieces du personnage
	*/
	public Piece maPiece(){
		
		Random rand = new Random(); 
		int i = rand.nextInt(mesPieces.size());
		return mesPieces.get(i);
		}
		
	/** 
	 * @ retourn la liste d'objets du personnage
	 * 
	 */
	public ArrayList<ObjetZork> getobjs(){
		return objs;
	}
	/**
	* @ return la piece courante pour le personnage
	*/
	public Piece getpieceCour(){
		return pieceCour;
		}
	/**
	* réinitialse la piece courante du personnage
	*/
	public void setPieceCour(Piece p){
		if(p==null){
				throw new NullPointerException(
					"La piece doit être non null.");
			}
		pieceCour=p;
		}
	/**
	*@ return true si le personnage peut attaquer; false si non
	*/
	public boolean puetAttaquer(){	
			return attaque;
		}
	
	

}
