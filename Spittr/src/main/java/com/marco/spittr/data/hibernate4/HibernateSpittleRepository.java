package com.marco.spittr.data.hibernate4;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.marco.spittr.Spittle;
import com.marco.spittr.data.SpittleRepository;

@Repository
public class HibernateSpittleRepository implements SpittleRepository {

	private SessionFactory sesssionFactory;
			
	public HibernateSpittleRepository(SessionFactory sesssionFactory) {
		this.sesssionFactory = sesssionFactory;
	}

	private Session currentSession() {
		return sesssionFactory.getCurrentSession();
	}
	
	@Override
	public long count() {		
		return findAll().size();
	}

	@Override
	public List<Spittle> findRecent() {		
		return findRecent(10);
	}

	@Override
	public List<Spittle> findRecent(int count) {		
		return (List<Spittle>) spittleCriteria().setMaxResults(count).list();
	}

	@Override
	public Spittle findOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spittle save(Spittle spittle) {
		Serializable id = currentSession().save(spittle);
		return new Spittle((Long)id, spittle.getMessage(), spittle.getTime(), spittle.getLatitude(), spittle.getLongitude());
	}

	@Override
	public List<Spittle> findBySpitterId(long spitterId) {
		return (List<Spittle>)spittleCriteria().add(Restrictions.eq("spitter.id", spitterId)).list();
	}

	@Override
	public void delete(long id) {
		currentSession().delete(findOne(id));
		
	}

	public List<Spittle> findAll() {
		return (List<Spittle>) spittleCriteria().list(); 
	}
	
	private Criteria spittleCriteria() {
		return currentSession().createCriteria(Spittle.class).addOrder(Order.desc("postedTime"));
	}

	@Override
	public List<Spittle> findSpittles(long max, long count) {
		// TODO Auto-generated method stub
		return null;
	}

}
