package ru.itis.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.itis.config.HibernateConfig;
import ru.itis.models.City;
import ru.itis.repository.CrudRepository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class CityRepository implements CrudRepository<City, Long> {
    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    @Override
    public Optional<City> findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();

            Query<City> query = session.createQuery("select city from City city where city.id = :city_id", City.class);
            query.setParameter("city_id", id);

            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalStateException(e);
        } finally {
            if(session.getTransaction().isActive()) session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public List<City> findAll() {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();

            Query<City> query = session.createQuery("select city from City city", City.class);
            List<City> result = query.list();

            return result;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalStateException(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public City save(City item) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();

            session.persist(item);
            return item;
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalStateException(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();

            findById(id).ifPresent(session::delete);
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new IllegalStateException(e);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
