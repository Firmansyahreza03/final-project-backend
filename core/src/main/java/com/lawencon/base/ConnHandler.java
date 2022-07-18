package com.lawencon.base;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import com.lawencon.util.BeanUtil;

/**
 * Handler that manage connection manually
 * 
 * @author Agung Damas
 */
public class ConnHandler {
	private static ThreadLocal<EntityManager> manager = new InheritableThreadLocal<>();

	/**
	 * initialize manager
	 */
	private static void init() {
		clear();
		EntityManagerFactory emf = (EntityManagerFactory) BeanUtil.getBean("entityManagerFactory");
		EntityManager em = emf.createEntityManager();

		manager.set(em);
	}

	/**
	 * opening transaction, automatically initialize if no available manager
	 */
	public static void begin() {
		if (manager.get() != null && manager.get().isOpen()) {
			manager.get().getTransaction().begin();
			System.out.println("Transaction started.");
		} else {
			init();
			manager.get().getTransaction().begin();
			System.out.println("Transaction started with initialization.");
		}
	}

	/**
	 * rollback transaction and close manager
	 */
	public static void rollback() {
		if (manager.get() != null && manager.get().isOpen() && manager.get().getTransaction().isActive()) {
			manager.get().getTransaction().rollback();
			System.out.println("Transaction rolled back.");
		}
	}

	/**
	 * commit transaction and close manager
	 */
	public static void commit() {
		if (manager.get() != null && manager.get().isOpen() && manager.get().getTransaction().isActive()) {
			manager.get().getTransaction().commit();
			System.out.println("Transaction commited.");
		}
	}

	/**
	 * get current manager, automatically initialize if no available manager
	 * 
	 * @return EntityManager
	 */
	public static EntityManager getManager() {
		if (manager.get() != null && manager.get().isOpen()) {
			return manager.get();
		} else {
			System.out.println("Get manager with initialization.");
			init();
			return manager.get();
		}
	}

	/**
	 * close and clear manager
	 */
	public static void clear() {
		if (manager.get() != null && manager.get().isOpen()) {
			manager.get().close();
		}
		manager.set(null);
		System.out.println("Cleared existing manager.");
	}
}
