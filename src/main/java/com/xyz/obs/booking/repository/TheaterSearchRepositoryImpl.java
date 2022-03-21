package com.xyz.obs.booking.repository;

import com.xyz.obs.booking.consumer.CriteriaConsumer;
import com.xyz.obs.booking.entity.TheaterView;
import com.xyz.obs.booking.bean.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository
public class TheaterSearchRepositoryImpl implements TheaterSearchRepository {
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<TheaterView> search(List<SearchCriteria> criterias) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TheaterView> criteriaQuery = criteriaBuilder.createQuery(TheaterView.class);
        Root<TheaterView> root = criteriaQuery.from(TheaterView.class);
        Predicate predicate =criteriaBuilder.conjunction();
        CriteriaConsumer<TheaterView> consumer = new CriteriaConsumer<>(predicate,root,criteriaBuilder);
        criterias.forEach(consumer);
        predicate = consumer.getPredicate();
        criteriaQuery.where(predicate);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
