import java.util.Map;
import java.util.HashMap;

public class LRUCache<K, V> {
  private int capacity;
  private Node<K, V> head;
  private Node<K, V> tail;
  private Map<K, Node<K, V>> map;

  public LRUCache(int capacity) {
    this.capacity = capacity;
    this.map = new HashMap<>(capacity);
  }

  public V get(K key) {
    if (map.containsKey(key)) {
      Node<K, V> n = map.get(key);
      remove(n);
      setHead(n);
      return n.value;
    }
    return null;
  }

  public void insert(K key, V value) {
    if (map.containsKey(key)) {
      Node<K, V> old = map.get(key);
      old.value = value;
      remove(old);
      setHead(old);
    } else {
      Node<K, V> n = new Node<>(key, value);
      map.put(key, n);
      setHead(n);
    }
    if (map.size() > capacity) {
      map.remove(tail.key);
      remove(tail);
    }
  }

  private void remove(Node<K, V> node) {
    if (node.prev != null) {
      node.prev.next = node.next;
    } else {
      head = head.next;
    }
    if (node.next != null) {
      node.next.prev = node.prev;
    } else {
      tail = node.prev;
    }
  }

  private void setHead(Node<K, V> node) {
    if (head == null) {
      head = node;
    } else {
      head.prev = node;
      node.next = head;
      head = node;
    }
    if(tail == null) {
      tail = head;
    }
  }

  private class Node<K, V> {
    K key;
    V value;
    Node<K, V> prev;
    Node<K, V> next;
    
    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public static void main(String[] args) {
    LRUCache<String, String> cache = new LRUCache<>(2);
    cache.insert("F005E4N", "Aegon Targaryen");
    cache.insert("F00742U", "Aenys Targaryen");
    cache.insert("F00432R", "Maegor Targaryen");
    cache.insert("F0018P8", "Jaehaerys Targaryen");
    System.out.println("" + cache.get("F005E4N"));
    System.out.println("" + cache.get("F00742U"));
    System.out.println("" + cache.get("F00432R"));
    System.out.println("" + cache.get("F0018P8"));
  }
}
