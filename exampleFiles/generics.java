package com.wix.detox.espresso;

public class GenericClass<T, T1, T2> {
	public static void setSynchronization(
			HashMap<T2, T> options,
			String key,
			GenericClass<T, T1, T2> instance
	) {
			if (options.containsKey(key)) {
					instance.setSynchronization(options, this);
			}
	}
}

