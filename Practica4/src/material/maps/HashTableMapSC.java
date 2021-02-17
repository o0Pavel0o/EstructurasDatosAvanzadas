package material.maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;


/**
 * Separate chaining table implementation of hash tables. Note that all
 * "matching" is based on the equals method.
 *
 * @author A. Duarte, J. Vélez, J. Sánchez-Oro
 */
/**
 * @author Pavel
 *
 */
public class HashTableMapSC<K, V> implements Map<K, V> {

    private class HashEntry<T, U> implements Entry<T, U> {
    	
    	private T key;
    	private U value;
    	
        public HashEntry(T key, U value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
        public U getValue() {
            return value;
        }

        @Override
        public T getKey() {
           return key;
        }

        public U setValue(U val) {
        	U aux = value;
            value = val;
            return aux;
        }

        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			result = prime * result + ((value == null) ? 0 : value.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			HashEntry other = (HashEntry) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (key == null) {
				if (other.key != null)
					return false;
			} else if (!key.equals(other.key))
				return false;
			if (value == null) {
				if (other.value != null)
					return false;
			} else if (!value.equals(other.value))
				return false;
			return true;
		}    
		
		private HashTableMapSC getEnclosingInstance() {
			return HashTableMapSC.this;
		}


        /**
         * Entry visualization.
         */
		@Override
		public String toString() {
			return "HashEntry [key=" + key + ", value=" + value + "]";
		}
		
    }

    private class HashTableMapIterator<T, U> implements Iterator<Entry<T, U>> {
    	private int p, pLista;
        private List<HashEntry<T, U>> map[];
        
        //Ejercicio 2.2
        public HashTableMapIterator(ArrayList<HashEntry<T, U>>[] map, int num) {
        	 this.map = map;
             this.pLista = 0;
             
             if(num == 0) {
                 this.p = map.length;
             }else {
                 this.p = num;
             }
        }

        private void goToNextElement() {
        	int n = map.length;
            
            while ((this.p < n) && (this.map[p].size()==0)){
                this.p++;
            }
        }

        @Override
        public boolean hasNext() {
            return (this.map.length!=0);
        }

        @Override
        public Entry<T, U> next() {
        	HashEntry<T,U> entry = null;
            if (hasNext()) {
               
         	   if((map[p] != null)&& (!map[p].isEmpty())) {
         		   entry =  map[p].get(pLista); 
         		 	pLista++;
 
                  }else if(pLista==map[p].size() || map[p].isEmpty() || map[p] == null ) {
                 	 
                 	 goToNextElement();
                  }
                  
      
            } else {
                throw new IllegalStateException("The map has not more elements");
            }
			 return entry;
     	
  	
     }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapKeyIterator<T, U> implements Iterator<T> {
    	public HashTableMapIterator<T, U> iterator;
    	
        public HashTableMapKeyIterator(HashTableMapIterator<T, U> it) {
        	this.iterator = it;
        }

        @Override
        public T next() {
        	return (T) iterator.next().getValue();
        }

        @Override
        public boolean hasNext() {
        	return iterator.hasNext();
        }

        @Override
        public void remove() {
            // NO HAY QUE IMPLEMENTARLO
            throw new UnsupportedOperationException("Not implemented.");
        }
    }

    private class HashTableMapValueIterator<T, U> implements Iterator<U> {
    	public HashTableMapIterator<T, U> iterator;
    	
        public HashTableMapValueIterator(HashTableMapIterator<T, U> it) {
           this.iterator = it;
        }

        @Override
        public U next() {
        	return iterator.next().getValue();
        }

        @Override
        public boolean hasNext() {
        	 return iterator.hasNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not implemented.");
        }
    }
    
    protected int n = 0; // number of entries in the dictionary
    protected int prime, capacity; // prime factor and capacity of bucket array
    protected long scale, shift; // the shift and scaling factors
    protected ArrayList<HashEntry<K, V>>[] map;// bucket array

    /**
     * Creates a hash table
     */
    public HashTableMapSC() {
    	 this(109345121, 1000);
    }

    /**
     * Creates a hash table.
     *
     * @param cap initial capacity
     */
    public HashTableMapSC(int cap) {
    	this(109345121, cap);
    }

    /**
     * Creates a hash table with the given prime factor and capacity.
     *
     * @param p prime number
     * @param cap initial capacity
     */
    public HashTableMapSC(int p, int cap) {
    	this.n = 0;
        this.prime = p;
        this.capacity = cap;
        this.map = (ArrayList<HashEntry<K,V>>[]) new ArrayList[capacity];
        Random rand = new Random();        
        this.scale = rand.nextInt(prime - 1)+1;
        this.shift = rand.nextInt(prime);
        for(int i =0;i<cap;i++){
        	this.map[i]=new ArrayList<HashEntry<K,V>>();
        }
    }

    /**
     * Hash function applying MAD method to default hash code.
     *
     * @param key Key
     * @return
     */
    protected int hashValue(K key) {
    	return (int) ((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
    }

    /**
     * Returns the number of entries in the hash table.
     *
     * @return the size
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Returns whether or not the table is empty.
     *
     * @return true if the size is 0
     */
    @Override
    public boolean isEmpty() {
       return (n==0);
    }

    /**
     * Returns the value associated with a key.
     *
     * @param key
     * @return value
     */
    @Override
    public V get(K key) throws IllegalStateException {
    	HashEntry<K,V> entry = findEntry(key); 
        if (entry == null) {
            return null; 
        }
        return entry.getValue(); 
    }
    
    protected HashEntry<K,V> findEntry(K key) throws IllegalStateException {
        checkKey(key);
        int i = hashValue(key);
        ArrayList<HashEntry<K, V>> it = map[i];

        if (it.size()!=0){
            for(HashEntry<K,V> entry : it){
            	if (key.equals(entry.getKey())){
            		return entry;
            	}
            }
        }
        return null;
    }

    /**
     * Put a key-value pair in the map, replacing previous one if it exists.
     *
     * @param key
     * @param value
     * @return value
     */
    @Override
    public V put(K key, V value) throws IllegalStateException {
    	HashEntry<K,V> entry = findEntry(key);
    	if (entry!=null){
    		return entry.setValue(value);
    	}
    	if (n> capacity*0.75){
    		rehash(capacity*2);
    	}
    	int i = hashValue(key);
    	ArrayList<HashEntry<K,V>> lista;
    	entry = new HashEntry<K,V>(key,value);
    	lista = map[i];
		lista.add(entry);
		n++;
		return null;
    }

    /**
     * Removes the key-value pair with a specified key.
     *
     * @param key
     * @return
     */
    @Override
    public V remove(K key) throws IllegalStateException {
    	HashEntry<K,V> entry = findEntry(key);
    	if(entry==null){
    		return null;
    	}
    	int i = hashValue(key);
    	n--;
    	V valor = entry.getValue();
    	map[i].remove(entry);
    	return valor;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
    	return new HashTableMapIterator<K, V>(this.map, this.n);
    }

    /**
     * Returns an iterable object containing all of the keys.
     *
     * @return
     */
    @Override
    public Iterable<K> keys() {
    	Iterator<Entry<K,V>> it = iterator();
        ArrayList<K> lista = new ArrayList<K>();
        while(it.hasNext()){
     	   lista.add(it.next().getKey());
        }
        return lista;
    }

    /**
     * Returns an iterable object containing all of the values.
     *
     * @return
     */
    @Override
    public Iterable<V> values() {
    	 Iterator<Entry<K,V>> it = this.iterator();
         ArrayList<V> lista = new ArrayList<V>();
         while(it.hasNext()){
      	   lista.add(it.next().getValue());
         }
         return lista;
    }

    /**
     * Returns an iterable object containing all of the entries.
     *
     * @return
     */
    @Override
    public Iterable<Entry<K, V>> entries() {
    	 Iterator<Entry<K,V>> it = iterator();
         ArrayList<Entry<K,V>> lista = new ArrayList<Entry<K,V>>();
         while(it.hasNext()){
      	   lista.add(it.next());
         }
         return lista;
    }

    /**
     * Determines whether a key is valid.
     *
     * @param k Key
     */
    protected void checkKey(K k) {
    	if (k == null) {
            throw new IllegalStateException("Clave invalida.");
        }    
    }

    /**
     * Increase/reduce the size of the hash table and rehashes all the entries.
     */
    protected void rehash(int newCap) {
    	 ArrayList<HashEntry<K, V>>[] old = map;
         // new bucket is twice as big
         map = (ArrayList<HashEntry<K, V>>[]) new ArrayList[newCap];
         java.util.Random rand = new java.util.Random();
         // new hash scaling factor
         scale = rand.nextInt(prime - 1) + 1;
         // new hash shifting factor
         shift = rand.nextInt(prime);     

         for (ArrayList<HashEntry<K, V>> e : old) { 
             if ((e != null)) { // a valid entry       
                 for (int i=0;i<e.size();i++){
                     int j = hashValue(e.get(i).getKey());
                     if (map[j]==null){
                         map[j] = new ArrayList<>();
                         map[j].add(e.get(i));
                     }else map[j].add(e.get(i));
                 }
             }          
           
         }
  }
}
