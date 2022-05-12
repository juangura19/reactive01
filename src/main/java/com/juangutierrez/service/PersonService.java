package com.juangutierrez.service;

import com.juangutierrez.document.Person;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface PersonService {

    Single<Person> save(Person person);

    Single<Person> update(Person person);

    Flowable<Person> findAll();

    Maybe<Person> getId(String id);

    Completable delete(String id);
}
