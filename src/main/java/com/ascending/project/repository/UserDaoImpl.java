package com.ascending.project.repository;

import com.ascending.project.domain.Bill;
import com.ascending.project.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        String hql = "From User";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<User> query=s.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public User findByIdEager(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        String hql = "FROM User u where u.id = :userId";
        TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql).setParameter( "usersId",id);
        return query.getSingleResult();
    }
}
