package com.marco.spittr.data.hibernate4;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.marco.spittr.Spitter;
import com.marco.spittr.data.SpitterRepository;

@Repository
public class HibernateSpitterRepository implements SpitterRepository {

	private SessionFactory sessionFactory;
	
	@Inject
	public HibernateSpitterRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory; //<co id="co_InjectSessionFactory"/>
	}
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();//<co id="co_RetrieveCurrentSession"/>
	}
	
	
	@Override
	public long count() {
		return findAll().size();
	}
	

	@Override
	public Spitter save(Spitter spitter) {
		Serializable id = currentSession().save(spitter);							
		return new Spitter((Long)id, spitter.getUserName(), spitter.getPassword(), spitter.getFirstName(), spitter.getLastName(), spitter.getEmail());
	}

	@Override
	public Spitter findOne(long id) {
		return (Spitter)currentSession().get(Spitter.class, id);
	}

	@Override
	public Spitter findByUserName(String userName) {		
		return (Spitter) currentSession().createCriteria(Spitter.class).add(Restrictions.eq("username", userName)).list().get(0);
	}

	@Override
	public List<Spitter> findAll() {
		return (List<Spitter>)currentSession().createCriteria(Spitter.class).list();
	}

}
