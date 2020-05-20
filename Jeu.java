
/**
 *  Classe principale du jeu "Zork". <p>
 *
 *  Zork est un jeu d'aventure très rudimentaire avec une interface en mode
 *  texte: les joueurs peuvent juste se deplacer parmi les differentes pieces.
 *  Ce jeu necessite vraiment d'etre enrichi pour devenir interessant!</p> <p>
 *
 *  Pour jouer a ce jeu, creer une instance de cette classe et appeler sa
 *  methode "jouer". </p> <p>
 *
 *  Cette classe cree et initialise des instances de toutes les autres classes:
 *  elle cree toutes les pieces, cree l'analyseur syntaxique et demarre le jeu.
 *  Elle se charge aussi d'executer les commandes que lui renvoie l'analyseur
 *  syntaxique.</p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.1
 * @since      March 2000
 */
import java.util.*;;
public class Jeu {
	private AnalyseurSyntaxique analyseurSyntaxique;
	private Piece pieceCourante;
	private Joueur un_joueur;
	private   String di="null";
	private ArrayList<Piece> tel;
	private ArrayList<Piece> telf;
	private Piece barbacane;
	private Personnage sorciere;
	private ArrayList<ObjetZork> l;
	private ArrayList<Arme> arme;
	private Piece teleportation;
	private Personnage FontomeBlanc;
	private Personnage FontomeNoir;
	private Personnage fee;
	private int dep=0;
	private boolean depnull=true;
	private Piece tourPrison;
	private ObjetZork ClePrison;
	private ObjetZork baguetteMagique;
	private Personnage Monstre;
	/**
	 *  Cree le jeu et initialise la carte du jeu (i.e. les pièces).
	 */
	public Jeu() {
		l=new ArrayList<ObjetZork>();
		arme=new ArrayList<Arme>();
		un_joueur =  new Joueur("hocine",60,5,l);
		creerPieces();
		analyseurSyntaxique = new AnalyseurSyntaxique();
		
	}


	/**
	 *  Cree toutes les pieces et relie leurs sorties les unes aux autres.
	 */
	public void creerPieces() {
		/*declaration des pieces comme variables locales */
		Piece donjon;
		Piece breteche;
		Piece tourDeGuet;
		Piece PontLevis;
		Piece chapelle;
		
		/*declarations des armes et des objets comme vriables locales*/
		Arme trident,haches,fléau,marteau;
 		Arme fleche;
		Personnage princisse;
		ObjetZork miroir, tapis_volant,lampe,balai_magique;

		// creation des pieces
		donjon= new Piece("le donjon: le centre du chateau");
		breteche = new Piece("une des breteche du chateau");
		tourDeGuet = new Piece("la tour de guet");
		PontLevis= new Piece("le pont levis a l'entree du chateau");
		barbacane= new Piece("la barbacane");
		chapelle= new Piece("la chapelle");
		tourPrison= new Piece("la tour de prison");

		/****initialiser la teleportation*****/

		tel=new ArrayList<Piece>();
		telf=new ArrayList<Piece>();
		tel.add(breteche);
		tel.add(donjon);
		tel.add(tourDeGuet);
		tel.add(barbacane);
		tel.add(chapelle);
		tel.add(tourPrison);

		/*initialisation des pieces de deplacement pour la fee */
		telf.add(breteche);
		telf.add(donjon);
		telf.add(tourDeGuet);
		telf.add(barbacane);
		telf.add(tourPrison);
		/*creation de la piece teleportation*/
		teleportation=new Piece("la piece teleportation");
	       // creation des objets 
		try{
			/******** les armes ********/
		trident=new Arme(10,"trident","une sorte de fourche à trois dents",14);
		haches=new Arme(20,"Haches","une arme tranchante",15);
		fléau=new Arme(10,"Fléau","une arme articulée",10);
		marteau=new Arme(60,"Marteau","un marteau pour le combat approché",50);
		fleche=new Arme(5,"fleche","une arme simple",2);
			/******** objets ********/
		ClePrison=new ObjetZork("clef","le seul moyen pour libérer la princesse ",4);
		baguetteMagique=new ObjetZork("baguette_magique","pour la sorciere",7);
		
		miroir=new ObjetZork("Miroir_magique ","capable de révéler par l’image des vérités invisibles ");
		tapis_volant=new ObjetZork("Tpis_volant "," moyen de transport ",7);
		lampe=new ObjetZork("Lampe_merveilleuse","la lampe du pouvoir magique");
		balai_magique=new ObjetZork("balai_magique","un balai de sorcières capable de voler",10);
			/******** les personnages ********/
		sorciere= new Personnage(20,10,"sorciere","La méchante sorcière de l'Ouest",true);
		princisse= new Personnage(400,0,"princesse","la princesse  emprisonié",false);
		fee=new Personnage(200,0,"Ailisa","la feé noble",telf,false);
		FontomeBlanc=new Personnage(50,10,"Ralph" ," le fontome blanc ",tel,true);
		FontomeNoir=new Personnage(60,40,"Picsou","le fontome noir ",tel,true);
		Monstre=new Personnage(110,25,"monstre","L'horible monstre avec des dents aiguës",true);
		

			/******l'ajout des objets ******/
		
		sorciere.ajouter(baguetteMagique);
		fee.ajouter(ClePrison);
		donjon.ajouter(miroir);
		tourPrison.ajouter(tapis_volant);
		chapelle.ajouter(lampe);
		tourDeGuet.ajouter(balai_magique);
			/********* l'ajout des personnages*********/
		tourPrison.ajouterP(fee);
		barbacane.ajouterP(Monstre);
		breteche.ajouterP(FontomeBlanc);
		chapelle.ajouterP(sorciere);
		tourPrison.ajouterP(princisse);
			/************l'ajout des armes***********/
		donjon.ajouterA(trident);
		breteche.ajouterA(haches);
		tourDeGuet.ajouterA(fléau);
		barbacane.ajouterA(marteau);	
		un_joueur.ajouterA(fleche);

		} catch(PoidNegativeException e){
			e.message();
		}
		// initialise les sorties des pieces
		donjon.setSorties(breteche, null,PontLevis , tourDeGuet);
		breteche.setSorties(teleportation,barbacane ,donjon, chapelle );
		
		tourDeGuet.setSorties(chapelle,donjon, null, null);
		PontLevis.setSorties(donjon, null, null, null);
		barbacane.setSorties(null,tourPrison, null, breteche);
		chapelle.setSorties(null, breteche, tourDeGuet, null);
		tourPrison.setSorties(null, null,null,barbacane );
	
		// le jeu commence en dehors du chateau 
		pieceCourante = PontLevis;

	}


	/**
	 *  Pour lancer le jeu. Boucle jusqu'a la fin du jeu.
	 */
	public void jouer() {
		afficherMsgBienvennue();

		// Entree dans la boucle principale du jeu. Cette boucle lit
		// et execute les commandes entrees par l'utilisateur, jusqu'a
		// ce que la commande choisie soit la commande "quitter"
		boolean termine = false;
		while (!termine) {
			Commande commande = analyseurSyntaxique.getCommande();
			termine = traiterCommande(commande);
		}
		System.out.println("Merci d'avoir jouer.  Au revoir.");
	}


	/**
	 *  Affiche le message d'accueil pour le joueur.
	 */
	public void afficherMsgBienvennue() {
		System.out.println();
		System.out.println("Bienvennue dans Le château prison!");
		
		System.out.println("vous etes a l'entré du chateau ");
		System.out.println();
		System.out.println("votre mission est de lébirer la princesse de sa prison");
		System.out.println("alors il faut avoir la clef de la tour prison ");
		System.out.println();
		System.out.println("vous devez vaincre la sorciere et récuperer la baguette magique ");
		System.out.println("et la remettra a la fée qui va vous donner la clef ");
		System.out.println();
		System.out.println("Attention au fontomes ils peuvent vous attaquer pandant votre déplacement ");
		System.out.println("Mais attention aussi au monstre qui garde la porte de la tour prison ");
		System.out.println();
		System.out.println("allez -y- la princesse compte sur vous  ");
		System.out.println("Bon courage ");
		System.out.println();
		System.out.println("Tapez 'aide' si vous avez besoin d'aide.");
		System.out.println();
		plan();
		System.out.println(pieceCourante.descriptionLongue());
		
	}


	/**
	 *  Execute la commande specifiee. Si cette commande termine le jeu, la valeur
	 *  true est renvoyee, sinon false est renvoyee
	 *
	 * @param  commande  La commande a executer
	 * @return           true si cette commande termine le jeu ; false sinon.
	 */
	public boolean traiterCommande(Commande commande) {
		
		if (commande.estInconnue()) {
			System.out.println("Je ne comprends pas ce que vous voulez...");
			return false;
		}

		String motCommande = commande.getMotCommande();
		if (motCommande.equals("aide")) {
			afficherAide();
			}
		 else if (motCommande.equals("aller")) {
			
			deplacerVersAutrePiece(commande);
			dep++;
			teleporter(commande);
			deplacer_P(FontomeBlanc);
			deplacer_P(FontomeNoir);
			attaque();
			deplacer_P(fee);
			//plan();
			return si_gagner();
		} 
		else if(motCommande.equals("affichage")){
				pieceCourante.affiche();
				
				}
		else if(motCommande.equals("retour")){
					Retour(di);
				dep++;
				deplacer_P(FontomeBlanc);
				deplacer_P(FontomeNoir);
				deplacer_P(fee);
				attaque();
				return si_gagner();
				}
		else if(motCommande.equals("chargement")){
					mes_objets();
				}
		else if(motCommande.equals("s'armer")){
					s_armer(commande);
				}
		else if(motCommande.equals("prendre")){
					prendre(commande);
				}
		else if(motCommande.equals("decharger")){
					decharger(commande);
					
				}
		else if(motCommande.equals("information")){
					information(commande);
				}
		else if(motCommande.equals("donner")){
					donner(commande);
				
				}
		else if(motCommande.equals("desarmer")){
					de_sarmer(commande);
				}
		else if(motCommande.equals("plan")){
					plan();
				}
		else if(motCommande.equals("attaquer")){
					c_moi_attaque(commande);
				}
		else if (motCommande.equals("quitter")) {
			if (commande.aSecondMot()){
				System.out.println("Quitter quoi ?");
			} else {
				return true;
			}
		}
		return false;
	}


	// implementations des commandes utilisateur:

	/**
	 *  Affichage de l'aide. Affiche notament la liste des commandes utilisables.
	 */
	public void afficherAide() {
		System.out.println("Vous etes perdu. Vous etes seul dans le chateau prison ");
		System.out.println("vous pouriez lébirer la princisse de sa prison");
		System.out.println();
		System.out.println("voila le plan du chateau\n");
		plan();
		System.out.println("Les commandes reconnues sont:");
		analyseurSyntaxique.afficherToutesLesCommandes();
	}


	/**
	 *  Tente d'aller dans la direction specifiee par la commande. Si la piece
	 *  courante possède une sortie dans cette direction, la piece correspondant a
	 *  cette sortie devient la piece courante, dans les autres cas affiche un
	 *  message d'erreur.
	 *
	 * @param  commande  Commande dont le second mot specifie la direction a suivre
	 */
	public void deplacerVersAutrePiece(Commande commande) {
		if (!commande.aSecondMot()) {
			// si la commande ne contient pas de second mot, nous ne
			// savons pas ou aller..
			System.out.println("Aller où ?");
			return;
		}

		String direction = commande.getSecondMot();
		 di=commande.getSecondMot();

		// Tentative d'aller dans la direction indiquee.
		Piece pieceSuivante = pieceCourante.pieceSuivante(direction);

		if (pieceSuivante == null) {
			System.out.println("Il n'y a pas de porte dans cette direction!");
			depnull=false;
		}
		
		 else {
				if(pieceSuivante.equals(tourPrison)  ){
					if(!(Monstre.est_vivant())){//barbacane.getPersonnages().contains(Monstre)){
						if(! un_joueur.getobj().contains(ClePrison)){
							System.out.println("vous devez avoir la clef pour rentrer a la tour de prison");
						}		
						else{
							pieceCourante = pieceSuivante;
						}
					}
					else{
					System.out.println("ahh je suis le monstre "+Monstre.getDesc());
					System.out.println("je vais pas vous laisser passer a la tour de prison\n");
					}
				}
			
				else{
				pieceCourante = pieceSuivante;
				}
			
			if(!(pieceCourante.equals(teleportation))){
			System.out.println(pieceCourante.descriptionLongue());
			depnull=true;
			}
			else{System.out.println("attention vous venez de vous téléporté vous vous ne pouvez pas routonrés  \n");
			}
			
		}
		
		
	}
	
	
	/**
	* ajoute  l'objet donner par la commande c de la liste d'objets du joueur
	* @param  commande  La commande a executer
	* @ requires c!=null;
	*/

	public void prendre(Commande c){
		int n=pieceCourante.get_objets().size();
		boolean trouve=true;
		
		 if(pieceCourante.get_objets().size()==0){System.out.println("il y a rien a prendre la piece est vide");}
		else if(!c.aSecondMot()){System.out.println("vous voulez prendre quoi");}
			
		else{
			
			for (int i=0;i<pieceCourante.get_objets().size();i++){
				if(c.getSecondMot().equals(pieceCourante.get_obj(i).get_nom()) && trouve){
					if(un_joueur.ajoutPossible(pieceCourante.get_obj(i))){
					un_joueur.ajouter(pieceCourante.get_obj(i));
					if(pieceCourante.get_obj(i).get_boolean()){
					System.out.println("l'objet "+ pieceCourante.get_obj(i).get_nom()+" a ete pris");
					pieceCourante.retirer(pieceCourante.get_obj(i));
					trouve=false;
					}}
				}
				else {
				n=n-1;
				}	
			}	
			if(n==0){
				System.out.println("cet objet n'existe pas\n");
				}
		}
			
			
	}

	/**
	* enleve l'objet donner par la commande c de la liste d'objets de la piece courantes
	* @param  commande c La commande a executer
	*@author     chabani hocine
	*@ requires n!=null;
	*/
	public void decharger(Commande c){
		boolean t=true;
		int m=un_joueur.getTabobjets().length;;
		
		
		 if(un_joueur.getTabobjets().length==0){System.out.println("le joueur n'as pas d'objet a decharger");}
		else if(!c.aSecondMot()){System.out.println("vous voulez decharger quoi");}
			
		else{ 
			for (int i=0;i<un_joueur.getTabobjets().length;i++){
				if(c.getSecondMot().equals(un_joueur.getTabobjets()[i].get_nom()) && t){
				
				pieceCourante.ajouter(un_joueur.getTabobjets()[i]);
			
			System.out.println("l'objet "+ un_joueur.getTabobjets()[i].get_nom()+" a ete bien decharger ");
			
			un_joueur.retirer(un_joueur.getTabobjets()[i]);
			t=false;
				}
				else {m=m-1;}
					}
				
			if(m==0){System.out.println("le joueur ne pocéde pas cet objet ");}
		}
			
			
			}
	/**
	* transformer la direction dans le sens inverse pour faire le retour et cela tout dépond de la direction initiale
	* @param directionRetour la direction a la quelle en cherche l'inverse pour faire le retour
	**@author     chabani hocine
	*@ requires directionRetour!=null;
	*/
	
	public void Retour(String directionRetour){
		
		if(directionRetour.equals("null")){
			System.out.println("vous pouvez pas retourner vous vous n'etes pas encore déplacé");
		}
		else if(directionRetour.equals("nord")){

			directionRetour="sud";
		}
		else if(directionRetour.equals("sud")){
			directionRetour="nord";
                                    }

		else if(directionRetour.equals("est")){
			directionRetour="ouest";
		}
		else if(directionRetour.equals("ouest")){
			directionRetour="est";
			}

		Commande comande=new Commande("aller",directionRetour,null);
		deplacerVersAutrePiece(comande);

		}
	/**
	* affiche les objets et les armes du joueur
	*
	*@author     hamrouni lounis
	*/
	public void mes_objets(){
		//System.out.println("*******************************************************************************");
		System.out.println("*************************les objets************************");
		//System.out.println("*******************************************************************************");
		
		if(un_joueur.objets.isEmpty() ){
		System.out.println(" le joueur n'as pas d'objet   ");
		} 
		else{
		un_joueur.afficher_j();
			}
		System.out.println("*************************les armes************************");
		if(un_joueur.getArmes().isEmpty() ){
		System.out.println("le joueur n'as pas d'armes ");
		} 
		else{
		//un_joueur.afficher_j();
			for(int i=0; i<un_joueur.getArmes().size();i++){
		System.out.println(un_joueur.getArmes().get(i).get_nom());
				}
			}


		System.out.println("************************************************************\n");
		
	}
	/**
	* affiches toutes les information soit de la piece courantes ,soit du joueur 
	* et ça selon la commander donner en param
	* @param  commande  La commande a executer
	* @author     chabani hocine
	* @requires c!=null
	*/
	public void information(Commande c){
		
		if(!c.aSecondMot()){System.out.println("informaton de qui???");}

		else if(c.getSecondMot().equals("piece")){

			if(pieceCourante.get_objets().size()==0){
			
				System.out.println("dans la piece  y a aucun objet!!\n ");
					}
			else{
				for (int i=0;i<pieceCourante.get_objets().size();i++){

					System.out.println(pieceCourante.get_obj(i).get_Information()+"\n");

				}
			}
			 if(pieceCourante.getArmes().size()==0){
			
				System.out.println("dans la piece  y a aucune arme!!\n");
					}
			else{
				for (int j=0;j<pieceCourante.getArmes().size();j++){

					System.out.println(pieceCourante.getArmes().get(j).get_Information()+"\n");

				}
			}

			if(pieceCourante.getPersonnages().size()==0){
			
				System.out.println("y a aucune personne dans cette piece!");
					}
				else{
					for (int k=0;k<pieceCourante.getPersonnages().size();k++){

						pieceCourante.getPersonnages().get(k).AfficherP();
						}
				}	 
			}
		else if(c.getSecondMot().equals("joueur")){
				if(un_joueur.getTabobjets().length==0){
					System.out.println("votre joueur n'as pas d'objet avec lui  donc n'y a pas d'information\n");
					}
				else{
					for (int i=0;i<un_joueur.getTabobjets().length;i++){	

						System.out.println(un_joueur.getTabobjets()[i].get_Information()+"\n");
					}
				}
				if(un_joueur.getArmes().size()==0){
					System.out.println("votre joueur n'as aucune arme\n");
					}
				else {
					
					for (int i=0;i<un_joueur.getArmes().size();i++){	

						System.out.println(un_joueur.getArmes().get(i).get_Information()+"\n");
					
					}
				}
			}
			else{
				System.out.println("vous chercher des information qui n'existent pas");
				}
			}
  

	/**
	* teste si le joueur a gagné selon quelque conditions definies dans la fonction
	* et affiches ce qui correspond selon son etat par rapport au conditions
	*@author     hamrouni lounis	
	*/
	public boolean si_gagner(){
	if(pieceCourante.descriptionCourte().equals("la tour de prison")){ 
			System.out.println("vous avez libérer la princisse");
			System.out.println("Bravo vous avez gagné ");
		return true;

		}
		else if(!(un_joueur.est_vivant())){
					System.out.println("le jouer est mort ");
					System.out.println("Dommage vous etes pérdu!!! ");
				return true ;}
		return false;
	}
	/**
	* ajout un objet donner dans le premier mot de la  commande au objets  
	* d'un personnage donner dans le 2emme mot de la commande
	* @param c : la commande a exécuter
	* @requires c!=null
	*/
	public void donner(Commande c){
		boolean t=true ;
		boolean t1=true;
		int n=pieceCourante.getPersonnages().size();
		int n2=un_joueur.getTabobjets().length;
		if(un_joueur.getTabobjets().length==0){
			System.out.println("vous n'avez pas d'objet a donner");
			}
		else if(!c.aSecondMot()){
			System.out.println("donner quoi \n");
			}
		else if(!c.aThirdMot()){
			System.out.println("donner "+c.getSecondMot()+" a qui ? \n");
				}
		
		else{	
			
			for(int j=0;j<un_joueur.getTabobjets().length;j++){
				if(un_joueur.getTabobjets()[j].get_nom().equals(c.getSecondMot()) && t1){
					for(int i=0;i<pieceCourante.getPersonnages().size();i++){
						if(pieceCourante.getPersonnages().get(i).getNom().equals(c.getThirdMot())&& t){
							pieceCourante.getPersonnages().get(i).ajouter(un_joueur.getTabobjets()[j]);
							un_joueur.retirer(un_joueur.getTabobjets()[j]);
							System.out.println("vous avez donné " +c.getSecondMot()+" a "+c.getThirdMot());
							t=false;
							t1=false;
						}
						else{
							n--;
						}
					}	
				}
				else{
					n2--;
				}
			}
			if(n==0){
				System.out.println(c.getThirdMot()+" n'est pas dans cette piece \n");
			}

			if(n2==0){
				System.out.println("vous n'avez pas l'objet "+c.getSecondMot());
				}
		}
		
		if(fee.getobjs().contains(baguetteMagique)){
			fee.retirer(ClePrison);
			pieceCourante.ajouter(ClePrison);
		}

	}
	public void deplacer_P(Personnage p){
			if(depnull){
				if(dep%2==0){
				Piece prec=p.getpieceCour();
				Piece tmp= p.maPiece();
				prec.retirerP(p);
				tmp.ajouterP(p);
				p.setPieceCour(tmp);
			System.out.println(p.getNom()+" "+p.getDesc()+" s'est déplacer!!-_-vers la piece "+tmp.descriptionCourte());
				}
				else{
					System.out.println(p.getNom()+" "+p.getDesc()+" le personnage ne s'est deplacé  pas ");
					}
			}
		}
	/**
	* teleporte le joueur vers une piece chosie au harsard
	* @param c la commande a exécuter pour faire la téléportation 
	* requires c!=null
	*/
	public void teleporter(Commande c){
		
		Random rand = new Random(); 
		int i = rand.nextInt(tel.size());
		
		if(pieceCourante.descriptionCourte().equals("la piece teleportation")){ 
			pieceCourante=tel.get(i);
			System.out.println("teleporté vers "+pieceCourante.descriptionCourte());
			di="null";
		}

	}
	/**
	* attaquer le joueur par tous les personnages qui sont dans la piece courante  
	*/
	public void attaque(){
		for(int i=0;i<pieceCourante.getPersonnages().size();i++){
			if(pieceCourante.getPersonnages().get(i).puetAttaquer() && 
						pieceCourante.getPersonnages().get(i).est_vivant()){
				pieceCourante.getPersonnages().get(i).attaquer(un_joueur);
				}
			}

	}
	/**
	* attaquer un personnage donné par le 3eme mot de la commande par une arme
	* donné par le 2eme mot de la commande
	* @param c la commande a éxécuter
	* requires c!=null
	*/
	public void c_moi_attaque(Commande c){
		int n= un_joueur.getArmes().size();
		int m=pieceCourante.getPersonnages().size();
		boolean trouve =true;
		if(n==0){
			System.out.println("vous n'avez pas d'arme pour attaquer ");
			}
		else if(!c.aSecondMot()){
			System.out.println("attaquer avec quoi et qui! ");
			}
		else if(!c.aThirdMot()){
			System.out.println("attaquer avec "+c.getSecondMot()+" qui ?");
			}
		
		else{	
			for(int j=0;j<un_joueur.getArmes().size();j++){
				if(un_joueur.getArmes().get(j).get_nom().equals(c.getSecondMot())){
					
					for(int i=0;i<pieceCourante.getPersonnages().size();i++){
						if(pieceCourante.getPersonnages().get(i).getNom().equals(c.getThirdMot())&& trouve){
						un_joueur.J_attque(pieceCourante.getPersonnages().get(i),un_joueur.getArmes().get(j));
						
						trouve=false;
							if(!(pieceCourante.getPersonnages().get(i).est_vivant())){
								for(int y=0;y<pieceCourante.getPersonnages().get(i).getobjs().size();y++){
							pieceCourante.ajouter(pieceCourante.getPersonnages().get(i).getobjs().get(y));
				pieceCourante.getPersonnages().get(i).retirer(pieceCourante.getPersonnages().get(i).getobjs().get(i));}
								//pieceCourante.retirerP(pieceCourante.getPersonnages().get(i));	
								
						}
						else{
							
										}
							}
							
					}
				}
				else{
					n--;
					}
		
				
			}
			for(int k=0;k<pieceCourante.getPersonnages().size();k++){
						if(pieceCourante.getPersonnages().get(k).getNom().equals(c.getThirdMot())){
						}
						else{
							
							m--;
						}
					}
			if(n==0){
				System.out.println("vous ne possedez pas l'arme "+c.getSecondMot()+"\n");
				}
			if(m==0){
				System.out.println(c.getThirdMot()+
							" n'est pas dans cette piece vous ne puvez pas l'attaquer\n");
				}
	
		
		
	}

}
	/**
	* ajout une arme de la piece a la liste des armes du joueur
	* @param c la commande a éxécuter pour s'armer 
	* @requires c!=null
	*/
	public void s_armer(Commande c){
		
		int n=pieceCourante.getArmes().size()+1;
	
		 if(pieceCourante.getArmes().size()==0){
				System.out.println("il y a pas d'arme dans cette piece \n");
				}
		else if(!c.aSecondMot()){
				System.out.println("vous voulez vous munir de quoi ???\n");
			}
			
		else{
			
			for (int i=0;i<pieceCourante.getArmes().size();i++){
				if(c.getSecondMot().equals(pieceCourante.getArmes().get(i).get_nom())){
					if(un_joueur.ajoutPossibleA(pieceCourante.getArmes().get(i))){
				un_joueur.ajouterA(pieceCourante.getArmes().get(i));
			
				pieceCourante.retirerA(pieceCourante.getArmes().get(i));
				System.out.println("vous avez pris l'arme "+c.getSecondMot());
				
				break;}
				}
				else{
					n--;
				};	
			}
			if(n==1){
			System.out.println("l'arme "+c.getSecondMot()+" n'est pas de cette piece\n");
			}
		}
		
	}
	/**
	* retire une arme de la liste des armes du joueur et l'ajouter 
	* a la liste des armes de la piece
	* @param c la commande a éxécuter pour se désarmer
	* @ requires c!=null
	*/
	public void de_sarmer(Commande c){
		int m=un_joueur.getArmes().size();
		 if(un_joueur.getArmes().size()==0){
			System.out.println("le joueur n'as pas d'armes");
			}
		else if(!c.aSecondMot()){
			System.out.println("vous voulez desarmer quoi");
			}
			
		else{ 
			for (int i=0;i<un_joueur.getArmes().size();i++){
				if(c.getSecondMot().equals(un_joueur.getArmes().get(i).get_nom())){
					pieceCourante.ajouterA(un_joueur.getArmes().get(i));
					
					System.out.println("vous avez desarmer "+un_joueur.getArmes().get(i).get_nom());
					un_joueur.retirerA(un_joueur.getArmes().get(i));
				}
				else {
					m=m-1;
				}
			}
				
			if(m==0){
				System.out.println("le joueur ne pocéde pas cette arme ");
				}
			}
			
			
	}
	public void plan(){
	
	System.out.println("			        ");
	System.out.println("	                LE PLAN \n    ");
	System.out.println("  		    *****************			        ");
	System.out.println(" 		    *               *				");
	System.out.println(" 		    * teleportation *				");
	System.out.println(" 		    *               *				");
	System.out.println("   	            *	            *	                        ");
	System.out.println(" ********************------    -----******************************** ");
	System.out.println(" *    	   	    |		    |		  |	           * ");
	System.out.println(" *    	   	    |		    |		  |	           * ");
	System.out.println(" *   chapelle 	   	breteche      barbacane     tour de prison *");
	System.out.println(" *    	   						           *");
	System.out.println(" *    	   	    |		    |		  |	           *");
	System.out.println(" *    	   	    |		    |		  |	           *");
	System.out.println(" *-------      -------------	----********************************");
	System.out.println(" *    	   	    |		    *");
	System.out.println(" *    	   	    |		    *");
	System.out.println(" *   tour de guet       le dondjon *");
	System.out.println(" *    	   			    *");
	System.out.println(" *    	   	    |		    *");
	System.out.println(" *    	   	    |		    *");
	System.out.println(" ***********************---    ---***");
	System.out.println("              N        *          *");
	System.out.println("              ^        *          *");
	System.out.println("          O <   > E    *   pont   *");
	System.out.println("                       *          *");
	System.out.println("              S        *   levis  *");
	System.out.println("                       *----   ---*");
	System.out.println("   ");
	
	
	
	
	
	}

}
