package ru.itis.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.itis.config.HibernateConfig;
import ru.itis.models.Country;
import ru.itis.repository.CrudRepository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

public class CountryRepository implements CrudRepository<Country, Long> {
    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    @Override
    public Optional<Country> findById(Long id) {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();

            Query<Country> query = session.createQuery("select country from Country country where country.id = :country_id", Country.class);
            query.setParameter("country_id", id);

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
    public List<Country> findAll() {
        Session session = sessionFactory.openSession();
        try {
            session.getTransaction().begin();

            Query<Country> query = session.createQuery("select country from Country country", Country.class);
            List<Country> result = query.list();

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
    public Country save(Country item) {
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
