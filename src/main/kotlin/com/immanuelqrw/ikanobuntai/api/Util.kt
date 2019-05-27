package com.immanuelqrw.ikanobuntai.api

// ! Add to Nucleus-Util package

/**
 * Returns a new read-only set either of single given element, if it is not null, or empty set if the element is null. The returned set is serializable (JVM).
 */
public fun <T : Any> setOfNotNull(element: T?): Set<T> = if (element != null) setOf(element) else emptySet()

/**
 * Returns a new read-only set only of those given elements, that are not null.  The returned set is serializable (JVM).
 */
public fun <T : Any> setOfNotNull(vararg elements: T?): Set<T> = elements.filterNotNull().toSet()
