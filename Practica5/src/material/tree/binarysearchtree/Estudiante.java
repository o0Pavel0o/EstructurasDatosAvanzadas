package material.tree.binarysearchtree;

import java.util.ArrayList;
import java.util.Comparator;

public class Estudiante {
    String name; // name and surname
    int age; // age of the student
    int record; // record number in the university
    double mark; // average qualification in the university

    public Estudiante(String name, int age, int record, double mark) {
        this.name = name;
        this.age = age;
        this.record = record;
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "name = " + name + ", age = " + age + ", record = " + record + ", mark = " + mark + '}';
    }
    
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		if (age != other.age)
			return false;
		if (Double.doubleToLongBits(mark) != Double.doubleToLongBits(other.mark))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (record != other.record)
			return false;
		return true;
	}

	public static class CompararNombre implements Comparator<Estudiante> {

        @Override
        public int compare(Estudiante alumno1, Estudiante alumno2) {
            return alumno1.name.compareTo(alumno2.getName());
        }

    }

    public static class CompararEdad implements Comparator<Estudiante> {

        @Override
        public int compare(Estudiante alumno1, Estudiante alumno2) {
            if(alumno1.getAge() == alumno2.getAge()) {
                return 0;
            }else if (alumno1.getAge() > alumno2.getAge()) {
                return 1;
            }else {
                return -1;
            }
        }

    }

    public static class CompararNota implements Comparator<Estudiante> {

        @Override
        public int compare(Estudiante alumno1, Estudiante alumno2) {
            if(alumno1.getMark() == alumno2.getMark()) {
                return 0;
            }else if (alumno1.getMark() > alumno2.getMark()) {
                return 1;
            }else {
                return -1;
            }
        }

    }
     
    public static void main (String args[]){
        
        Estudiante e1 = new Estudiante("Bartolo", 18, 429, 5.23);
        Estudiante e2 = new Estudiante("Eustaquio", 20, 126, 6.38);
        Estudiante e3 = new Estudiante("Dinio", 25, 201, 8.66);
        Estudiante e4 = new Estudiante("Isidoro", 33, 158, 9.98);
        Estudiante e5 = new Estudiante("Benancio", 35, 609, 7.85);
        Estudiante e6 = new Estudiante("Marcelino", 24, 101, 6.32);
        
        ArrayList<Estudiante> lista = new ArrayList<Estudiante>();
        lista.add(e1);
        lista.add(e2);
        lista.add(e3);
        lista.add(e4);
        lista.add(e5);
        lista.add(e6);
        
        //LinkedBinarySearchTree comparar por edad
        LinkedBinarySearchTree<Estudiante> l = new LinkedBinarySearchTree<>(new CompararNombre());
        lista.forEach((e) -> {
            l.insert(e);
        });

        LinkedBinarySearchTree<Estudiante> l2 = new LinkedBinarySearchTree<>(new CompararEdad());
        lista.forEach((e) -> {
            l2.insert(e);
        });

        LinkedBinarySearchTree<Estudiante> l3 = new LinkedBinarySearchTree<>(new CompararNota());
        lista.forEach((e) -> {
            l3.insert(e);
        });
        System.out.println("/************LinkedBinarySearchTree*************/");
        System.out.println(l.last().getElement().getName());
        System.out.println(l2.last().getElement().getName());
        System.out.println(l3.last().getElement().getName());

        //AVLTree comparar por edad
        AVLTree<Estudiante> a = new AVLTree<>(new CompararNombre());
        lista.forEach((e) -> {
            a.insert(e);
        });

        AVLTree<Estudiante> a2 = new AVLTree<>(new CompararEdad());
        lista.forEach((e) -> {
            a2.insert(e);
        });

        AVLTree<Estudiante> a3 = new AVLTree<>(new CompararNota());
        lista.forEach((e) -> {
            a3.insert(e);
        });
        
        System.out.println("/*******************AVLTree*********************/");
        System.out.println(a.last().getElement().getName());
        System.out.println(a2.last().getElement().getName());
        System.out.println(a3.last().getElement().getName());

        //RBTree comparar por edad
        RBTree<Estudiante> r = new RBTree<>(new CompararNombre());
        lista.forEach((e) -> {
            r.insert(e);
        });

        RBTree<Estudiante> r2 = new RBTree<>(new CompararEdad());
        lista.forEach((e) -> {
            r2.insert(e);
        });

        RBTree<Estudiante> r3 = new RBTree<>(new CompararNota());
        lista.forEach((e) -> {
            r3.insert(e);
        });

        System.out.println("/*******************RBTree*********************/");
        System.out.println(r.last().getElement().getName());
        System.out.println(r2.last().getElement().getName());
        System.out.println(r3.last().getElement().getName());

    }

}
