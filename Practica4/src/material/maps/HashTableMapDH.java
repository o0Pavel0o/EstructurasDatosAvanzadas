package material.maps;
/**
 * @author Pavel
 *
 */
public class HashTableMapDH<K, V> extends AbstractHashTableMap<K, V> {

    public HashTableMapDH(int size) {
    	super(size);
    }

    public HashTableMapDH() {
    	super();
    }

    public HashTableMapDH(int p, int cap) {
    	super(p,cap);
    }

    @Override
    protected int offset(K key, int i) {
    	int d = 7 - (hashValue (key) % 7);
        return d*i;
    }

}
