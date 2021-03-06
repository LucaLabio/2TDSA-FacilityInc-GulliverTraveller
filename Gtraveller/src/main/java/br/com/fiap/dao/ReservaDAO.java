package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Reserva;
import br.com.fiap.util.EntityManagerFacade;

public class ReservaDAO {
	
	private EntityManager manager = new EntityManagerFacade().getEntityManager();

	public void save(Reserva reserva) {
		manager.getTransaction().begin();
		manager.persist(reserva);
		manager.getTransaction().commit();
		
		manager.clear();
	}

	public List<Reserva> getAll() {
		String jpql = "SELECT s FROM Reserva s";
		TypedQuery<Reserva> createQuery = manager.createQuery(jpql, Reserva.class);
		return createQuery.getResultList();
	}

	public Reserva findById(Long id) {
		return manager.find(Reserva.class, id);
	}

	public void update(Reserva reserva) {
		manager.getTransaction().begin();
		manager.merge(reserva);
		manager.flush();
		manager.getTransaction().commit();
	}

}
