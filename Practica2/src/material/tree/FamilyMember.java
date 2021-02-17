package material.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Pavel
 *
 */

public class FamilyMember {
		
	private String id;
	private String name;
	private String surname;
	String gender;
	private int age;
	private static List<LinkedTree<FamilyMember>> bosque = new ArrayList<>(); 
	
	public FamilyMember(String id, String name, String surname, String gender, int age) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.gender = gender;
		this.age = age;
	}


	/**
	 * @return el id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id el id a establecer
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return el name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name el name a establecer
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return el surname
	 */
	public String getSurname() {
		return surname;
	}


	/**
	 * @param surname el surname a establecer
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}


	/**
	 * @return el gender
	 */
	public String getGender() {
		return gender;
	}


	/**
	 * @param gender el gender a establecer
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	 * @return el age
	 */
	public int getAge() {
		return age;
	}


	/**
	 * @param age el age a establecer
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	
	public static Position<FamilyMember> pertenece(LinkedTree<FamilyMember> arbol, String id){      
        FamilyMember miembro; 
        Position<FamilyMember> p;
        Iterator<Position<FamilyMember>> it = arbol.iterator();
        while (it.hasNext()){
            p = it.next();
            miembro = p.getElement();
            if(miembro.getId().equalsIgnoreCase(id)){
                return p;
            }
        }
        return null;
    }
    
    public static FamilyMember damePersona(ArrayList<FamilyMember> lista, String id) {

        for ( FamilyMember miembro: lista){
            if (miembro.getId().equalsIgnoreCase(id)){
                return miembro;
            }
        }
        return null;
    }
    
    public static Position<FamilyMember> damePosicion(LinkedTree<FamilyMember> tree, String name)throws IllegalStateException{
        Iterator<Position<FamilyMember>> it = tree.iterator();
        while(it.hasNext()){
            Position<FamilyMember> p = it.next();
            if(p.getElement().getName().equalsIgnoreCase(name)){
                return p;
            }
        }
        throw new IllegalStateException ("Error");
    }
    
    public static LinkedTree<FamilyMember> dameFamilia(String name) throws IllegalStateException{
        for (LinkedTree<FamilyMember> familia : bosque) {
            Iterator<Position<FamilyMember>> it = familia.iterator();
            while (it.hasNext()) {
                if (it.next().getElement().getName().equalsIgnoreCase(name)) {
                    return familia;
                }
            }
        }
        throw new IllegalStateException("Error");
    }

}
