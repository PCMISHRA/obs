package com.xyz.obs.booking.consumer;

import com.xyz.obs.booking.bean.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Consumer;
@Getter
@AllArgsConstructor
public class CriteriaConsumer <T>implements Consumer<SearchCriteria>{
    private Predicate predicate;
    private Root<T> root;
    private CriteriaBuilder criteriaBuilder;

    @Override
    public void accept(SearchCriteria searchCriteria) {
        //One eq implemetation

        predicate =
                criteriaBuilder.and(predicate,criteriaBuilder.equal(root.get(searchCriteria.getKey()),searchCriteria.getValue()));

    }

    @Override
    public Consumer<SearchCriteria> andThen(Consumer<? super SearchCriteria> after) {
        return Consumer.super.andThen(after);
    }
}