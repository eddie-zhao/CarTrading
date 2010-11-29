package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {
	public static <T> List<T> newArrayList() {
		return new ArrayList<T>();
	}
	public static <T> List<T> newArrayList(int size) {
		return new ArrayList<T>(size);
	}
	public static <K, V> Map<K, V> newHashMap() {
		return new HashMap<K, V>();
	}
	public static <T> Set<T> newHashSet() {
		return new HashSet<T>();
	}
	public static <T> Set<T> newHashSet(int initialCapacity) {
		return new HashSet<T>(initialCapacity);
	}
}

