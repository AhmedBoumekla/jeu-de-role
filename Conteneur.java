/**
* une interface pour la classe abstraite ArrayListConteneur
* dont laquelle on déclare toutes les méthodes définies dans cette
* classe  abstraite
*/

public interface  Conteneur{

	public ObjetZork[] getTabobjets();
	public void ajouter(ObjetZork oz);
	public  abstract boolean ajoutPossible(ObjetZork oz);
	public boolean contient(ObjetZork oz);
	public int contientCombien_de(ObjetZork oz);
	public int getNbobjets();
	public boolean retirer(ObjetZork oz);
	
	public  abstract boolean ajoutPossibleA(Arme ar);
	public boolean retirerA(Arme ar);
	public void ajouterA(Arme ar);
}
