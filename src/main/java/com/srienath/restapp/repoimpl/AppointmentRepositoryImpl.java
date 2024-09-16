package com.srienath.restapp.repoimpl;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.srienath.restapp.model.Appointment;
import com.srienath.restapp.repo.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {
	@PersistenceContext
    private EntityManager entityManager;

    @Override
    public Appointment findById(int id) {
        return entityManager.find(Appointment.class, id);
    }

    @Override
    public List<Appointment> findAll() {
        return entityManager.createQuery("from Appointment", Appointment.class).getResultList();
    }

    @Override
    public void save(Appointment appointment) {
        entityManager.persist(appointment);
    }

    @Override
    public void update(Appointment appointment) {
        entityManager.merge(appointment);
    }

    @Override
    public void deleteById(int id) {
        Appointment appointment = findById(id);
        if (appointment != null) {
            entityManager.remove(appointment);
        }
    }
}
