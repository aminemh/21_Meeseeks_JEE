package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.DayOff;
import entities.Establishment;
import entities.LeaveType;
import entities.Resource;
import interfaces.DayOffServieLocal;
import interfaces.DayOffServieRemote;
import interfaces.LeaveTypeServiceLocal;
/**
 * Session Bean implementation class DayOffServie
 */
@Stateless
@LocalBean
public class DayOffServie implements DayOffServieRemote, DayOffServieLocal {
	
	@PersistenceContext(unitName = "21meeseeks-ejb")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public DayOffServie() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public int addDayOff(DayOff d) {
		int id=d.getLeaveType().getIdLeaveType();
		LeaveType lt=em.find(LeaveType.class, id);
		d.setLeaveType(lt);
		System.out.println(d);
		em.persist(d);
		
		return d.getIdLeave();
		
	}

	@Override
	public DayOff findDayOff(int id) {
		DayOff d=em.find(DayOff.class, id);
		return d;
	}

	@Override
	public Boolean deleteDayOff(int id) {
		DayOff d=em.find(DayOff.class, id);
		em.remove(d);
		return true;
	}

	@Override
	public void updateDayOff(DayOff d) {
		DayOff dayOff=em.find(DayOff.class, d.getIdLeave());
		dayOff=d;
		em.merge(dayOff);
		
	}

	@Override
	public List<DayOff> getAllDayOff() {
		TypedQuery<DayOff> q = em.createQuery("SELECT d FROM DayOff d", DayOff.class);
		List<DayOff> listDayOff = q.getResultList();
		return listDayOff;
		
	}

}
