import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
/**
 *  Une piece dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Une "Piece" represente un des lieux dans lesquels se deroule l'action du
 *  jeu. Elle est reliee a au plus quatre autres "Piece" par des sorties. Les
 *  sorties sont etiquettees "nord", "est", "sud", "ouest". Pour chaque
 *  direction, la "Piece" possede une reference sur la piece voisine ou null
 *  s'il n'y a pas de sortie dans cette direction.</p>
 *  cette classe hérite de la classe ArrayListConteneur
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      August 2000
 */

public class Piece extends ArrayListConteneur {
	private String description;
	private ArrayList<Personnage> personnages;
	private int nbobjets;
	private int maxobjets;
	// memorise les sorties de cette piece.
	private Map sorties;



	/**
	 *  Initialise une piece decrite par la chaine de caracteres specifiee.
	 *  Initialement, cette piece ne possede aucune sortie. La description fournie
	 *  est une courte phrase comme "la bibliotheque" ou "la salle de TP".
	 *
	 * @param  description  Description de la piece.
	 * @requires description!=null;
	 * ensures get nbobjets()=0;
	 */
	public Piece(String description) {
		super();
		if(description==null){
			throw new NullPointerException(
					"la description ne doit pas être null.");

			}
		
		this.description = description;
		sorties = new HashMap();
		personnages=new ArrayList<Personnage>(); 
	}
	
	
	
	/**
	 *  Definie les sorties de cette piece. A chaque direction correspond ou bien
	 *  une piece ou bien la valeur null signifiant qu'il n'y a pas de sortie dans
	 *  cette direction.
	 *
	 * @param  nord   La sortie nord
	 * @param  est    La sortie est
	 * @param  sud    La sortie sud
	 * @param  ouest  La sortie ouest 
	 */
	public void setSorties(Piece nord, Piece est, Piece sud, Piece ouest) {
		if (nord != null) {
			sorties.put("nord", nord);
		}
		if (est != null) {
			sorties.put("est", est);
		}
		if (sud != null) {
			sorties.put("sud", sud);
		}
		if (ouest != null) {
			sorties.put("ouest", ouest);
		}
	}


	/**
	 *  Renvoie la description de cette piece (i.e. la description specifiee lors
	 *  de la creation de cette instance).
	 *
	 * @return    Description de cette piece
	 * 
	 */
	public String descriptionCourte() {
		return description;
	}


	/**
	 *  Renvoie une description de cette piece mentionant ses sorties et
	 *  directement formatee pour affichage, de la forme: <pre>
	 *  Vous etes dans la bibliotheque.
	 *  Sorties: nord ouest</pre> Cette description utilise la chaine de caracteres
	 *  renvoyee par la methode descriptionSorties pour decrire les sorties de
	 *  cette piece.
	 *
	 * @return    Description affichable de cette piece
	 * 
	 */
	public String descriptionLongue() {
		return "Vous etes dans " + description + ".\n" + descriptionSorties();
	}


	/**
	 *  Renvoie une description des sorties de cette piece, de la forme: <pre>
	 *  Sorties: nord ouest</pre> Cette description est utilisee dans la
	 *  description longue d'une piece.
	 *
	 * @return    Une description des sorties de cette piece.
	 
	 */
	public String descriptionSorties() {
		String resulString = "Sorties:";
		Set keys = sorties.keySet();
		for (Iterator iter = keys.iterator(); iter.hasNext(); ) {
			resulString += " " + iter.next();
		}
		return resulString;
	}


	/**
	 *  Renvoie la piece atteinte lorsque l'on se deplace a partir de cette piece
	 *  dans la direction specifiee. Si cette piece ne possede aucune sortie dans cette direction,
	 *  renvoie null.
	 *
	 * @param  direction  La direction dans laquelle on souhaite se deplacer
	 * @return            Piece atteinte lorsque l'on se deplace dans la direction
	 *      specifiee ou null.
	 * @requires direction!=null;
	 */
	public Piece pieceSuivante(String direction) {
		return (Piece) sorties.get(direction);
	}
	
	
	/** 
	 * @ affiche les noms des objets de  la piece
	 * 
	 */
	 public void affiche(){
		String s=" ";
	if(objets.isEmpty()){System.out.println("il y a aucun objets dans cette piece" );}
		for(int i=0;i<getTabobjets().length;i++){
			s= s+ " "+getTabobjets()[i].get_nom();
			
			
			}
	System.out.println(s );

		}

	/** 
	 * @ retourn la liste d'objets de la piece
	 * 
	 */
	public ArrayList get_objets(){
		return objets;

			}
	/** 
	 * @ retourn l'objet de la piece se trouvenat a la position i
	 * @requires i>=0
	 */
	public ObjetZork get_obj(int i){

	return getTabobjets()[i];

	}
	/**
	* ajoute un personnage a liste des personnages de la piece
	* @ param p le personnage a ajouter
	* @ requires p!=null
	* @ requires personnage.size()!=0
	* @ ensures personnages.size()==(\old(personnages.size())+1);
	*/
	
	public void ajouterP(Personnage p){
		if(p==null){
			throw new NullPointerException(
					"Le personnage a  ajouté doit être non null.");
			}
		personnages.add(p);
		}

	/**
	* retire un personnage de la liste des personnages de la piece
	* @ param p le personnage a retirer
	* @ return true si le personnage est retiré; false si non
	* @ requires p!=null
	* @ requires personnage.size()!=0
	* @ ensures personnages.size()==(\old(personnages.size())-1);
	*/
	public boolean retirerP(Personnage p){
		if(p==null){
			throw new NullPointerException(
					"Le personnage a  retirer doit être non null.");
			}
		return personnages.remove(p);
		}
	
	/**
	* @ return true puisque on peut toujours ajouter des objets a la piece
	*/
	 public boolean ajoutPossible(ObjetZork oz){
		return true;
		}
	/**
	* @ return true puisque on peut toujours ajouter des armes a la piece
	*/
	public  boolean ajoutPossibleA(Arme ar){
		return true;	
	}


	/**
	* @return la liste des personnages de la piece
	*
	*/
	public ArrayList<Personnage> getPersonnages(){
		return personnages;

	}

}

