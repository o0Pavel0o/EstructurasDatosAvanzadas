package material.tree;
import java.io.FileNotFoundException;
import material.tree.FamilyMember;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;
import java.io.File;

/**
 * @author Pavel
 *
 */

public class GameOfThrones {
	private static List<LinkedTree<FamilyMember>> bosque = new ArrayList<>(); 
    private static ArrayList<FamilyMember> listaPersonas = new ArrayList<>(); 
	 
	public void loadFile(String pathToFile) {
		FamilyMember nueva;
        ArrayList<String> listaCabezaFamilia = new ArrayList<>();
        ArrayList<String> listaParentesco = new ArrayList<>(); 
        Integer num = 0;
        File file = new File(pathToFile);
        Scanner data = null;
		try {
			data = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

        while(data.hasNext()) { 
            String linea = data.nextLine(); 
            String[] separa = linea.split(" "); 
            if (linea.contains("=")){ //si son todas las personas
                nueva  = new FamilyMember(separa[0], separa[2], separa[3], separa[4], Integer.valueOf(separa[5]));
                listaPersonas.add(nueva);
            }else if (linea.equals("---------------------------------------------------------------------------------------")){
                String numFamilias = data.nextLine();
                num = Integer.valueOf(numFamilias);
            }else if(separa.length == 1){ //si son los padres de cada familia
                String id = separa[0];
                listaCabezaFamilia.add(id);
            }else if (separa.length == 3){ //si son asignaciones padre -> hijo
                String lineaRelacion = linea;
                listaParentesco.add(lineaRelacion);
            }
        }
        data.close();

        for (int i=0; i<num; i++ ){
            String id = listaCabezaFamilia.get(i);
            LinkedTree<FamilyMember> arbolFamilia = new LinkedTree<>();
            FamilyMember miembroRaiz = FamilyMember.damePersona(listaPersonas, id);
            arbolFamilia.addRoot(miembroRaiz); 
            bosque.add(i, arbolFamilia); //array de arboles
        }

        listaParentesco.stream().map((x) -> x.split(" ")).forEachOrdered((explode) -> {
            bosque.forEach((arbol) -> {
                Position<FamilyMember> e = FamilyMember.pertenece(arbol, explode[0]);
                if (e != null) {
                    arbol.add(FamilyMember.damePersona(listaPersonas,explode[2]), e); 
                }
            });
        });
    }

	
	 public LinkedTree<FamilyMember> getFamily(String surname){

	        LinkedTree<FamilyMember> tree = null;

	        for (Iterator<LinkedTree<FamilyMember>> it = bosque.iterator(); it.hasNext();) {
	            LinkedTree<FamilyMember> arbol = it.next();
	            if (arbol.root().getElement().getSurname().equalsIgnoreCase(surname)){
	                tree = arbol;
	                return tree;
	            }
	        }
			return tree;
	    }
    
    public String findHeir(String surname){
        LinkedTree<FamilyMember> arbol = getFamily(surname);
        Position<FamilyMember> root = arbol.root();
        Position<FamilyMember> sol = null;
        for (Position<FamilyMember> miembro : arbol.children(root)){
            if(miembro.getElement().gender.equalsIgnoreCase("(M)")){
                if(sol == null){
                    sol = miembro;
                }else if (sol.getElement().gender.equalsIgnoreCase("(F)") || sol.getElement().getAge() < miembro.getElement().getAge()){
                    sol = miembro;
                }
            }else if(miembro.getElement().gender.equalsIgnoreCase("(F)")){
                if(sol == null){
                    sol = miembro;
                }else if (sol.getElement().gender.equalsIgnoreCase("(F)") && sol.getElement().getAge() < miembro.getElement().getAge()){
                    sol = miembro;
                }
            }
        }
        return sol.getElement().getName();
    }
	
    public static void changeFamily(String memberName, String newParent) throws InvalidPositionException { 
        LinkedTree<FamilyMember> arbolDestino = FamilyMember.dameFamilia(memberName);
        LinkedTree<FamilyMember> arbolPadre = FamilyMember.dameFamilia(newParent);
        Position<FamilyMember> pDestino = FamilyMember.damePosicion(arbolDestino, memberName);
        Position<FamilyMember> pPadre = FamilyMember.damePosicion(arbolPadre, newParent);
        arbolDestino.moveSubtree(pDestino, pPadre);
    }
    
    
    public boolean areFamily(String name1, String name2){
        LinkedTree<FamilyMember> arbol1 = FamilyMember.dameFamilia(name1);
        LinkedTree<FamilyMember> arbol2 = FamilyMember.dameFamilia(name2);

        if(arbol1.root().getElement().getSurname().equalsIgnoreCase(arbol2.root().getElement().getSurname())){
            return true;
        }else{
            return false;
        }
    }
    

    /***************************************************************************/
	public static void main(String[] args) throws NoSuchFieldException, InvalidPositionException {
		
		GameOfThrones got = new GameOfThrones();
		got.loadFile("C:\\Users\\Pablo\\Documents\\EDA\\Practica2\\src\\Practica2.txt");
        System.out.println(got.findHeir("Lannister"));
        System.out.println("\n");
        System.out.println(got.getFamily("Lannister"));
        		
	}
	/****************************************************************************/
	
}