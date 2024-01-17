package org.vetsandshelters.animal.infraestructure;

import java.util.List;

import org.vetsandshelters.animal.domain.Animal;
import org.vetsandshelters.animal.domain.AnimalCollection;
import org.vetsandshelters.animal.domain.AnimalCriteria;
import org.vetsandshelters.animal.domain.AnimalRepository;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Priority(5)
@ApplicationScoped
public class AnimalRepositoryPostgreSQL implements AnimalRepository {
    @Inject
    EntityManager em;
    // @Inject
    // EntityManagerFactory emf;

    @Override
    public Animal getById(Integer id) {
        // // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getById'");
        Animal animal = em.find(Animal.class, id);
        if (animal == null) {
            throw new IllegalStateException("Animal not found");
        }
        return animal;
    }

    @Override
    public AnimalCollection getBy(AnimalCriteria criteria) {

        // TODO: See how can we create dynamic queries)
        List<Animal> animalList = em.createQuery("FROM Animal", Animal.class).getResultList();

        return new AnimalCollection(animalList.toArray(new Animal[animalList.size()]));
    }

    @Override
    @Transactional
    public int store(Animal animal) {
        // // TODO Auto-generated method stub
        // System.out.println(animal.getBreed().getId());
        // Breed breed = em.find(Breed.class, animal.getBreed().getId());
        // if (breed == null) {
        // throw new IllegalStateException("Breed not found");
        // }
        // Sex sex = em.find(Sex.class, animal.getSex().getId());
        // if (sex == null) {
        // throw new IllegalStateException("Sex not found");
        // }
        // ProcedenceType procedenceType = em.find(ProcedenceType.class,
        // animal.getProcedenceType().getId());
        // if (procedenceType == null) {
        // throw new IllegalStateException("Origin type not found");
        // }
        // animal = new Animal(
        // null,
        // animal.getName(),
        // animal.getColor(),
        // sex,
        // breed,
        // procedenceType);

        // throw new UnsupportedOperationException("Unimplemented method 'store'");
        em.persist(animal);
        return animal.getId();
    }

    @Override
    public int update(Animal animal) {
        /**
         * TODO: Is throwing the following exception:
         * org.jboss.resteasy.spi.UnhandledException:
         * org.hibernate.PersistentObjectException: detached entity passed to persist:
         * org.vetsandshelters.animal.domain.Animal
         * at
         * org.jboss.resteasy.core.ExceptionHandler.handleApplicationException(ExceptionHandler.java:107)
         * at
         * org.jboss.resteasy.core.ExceptionHandler.handleException(ExceptionHandler.java:344)
         * at
         * org.jboss.resteasy.core.SynchronousDispatcher.writeException(SynchronousDispatcher.java:205)
         * at
         * org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:452)
         * at
         * org.jboss.resteasy.core.SynchronousDispatcher.lambda$invoke$4(SynchronousDispatcher.java:240)
         * at
         * org.jboss.resteasy.core.SynchronousDispatcher.lambda$preprocess$0(SynchronousDispatcher.java:154)
         * at
         * org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:321)
         * at
         * org.jboss.resteasy.core.SynchronousDispatcher.preprocess(SynchronousDispatcher.java:157)
         * at
         * org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:229)
         * at
         * io.quarkus.resteasy.runtime.standalone.RequestDispatcher.service(RequestDispatcher.java:82)
         * at
         * io.quarkus.resteasy.runtime.standalone.VertxRequestHandler.dispatch(VertxRequestHandler.java:147)
         * at
         * io.quarkus.resteasy.runtime.standalone.VertxRequestHandler$1.run(VertxRequestHandler.java:93)
         * at
         * io.quarkus.vertx.core.runtime.VertxCoreRecorder$14.runWith(VertxCoreRecorder.java:582)
         * at
         * org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2513)
         * at
         * org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1538)
         * at org.jboss.threads.DelegatingRunnable.run(DelegatingRunnable.java:29)
         * at
         * org.jboss.threads.ThreadLocalResettingRunnable.run(ThreadLocalResettingRunnable.java:29)
         * at
         * io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
         * at java.base/java.lang.Thread.run(Thread.java:833)
         * Caused by: org.hibernate.PersistentObjectException: detached entity passed to
         * persist: org.vetsandshelters.animal.domain.Animal
         * at
         * org.hibernate.event.internal.DefaultPersistEventListener.persist(DefaultPersistEventListener.java:88)
         * at
         * org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:77)
         * at
         * org.hibernate.event.internal.DefaultPersistEventListener.onPersist(DefaultPersistEventListener.java:54)
         * at
         * org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:127)
         * at org.hibernate.internal.SessionImpl.firePersist(SessionImpl.java:755)
         * at org.hibernate.internal.SessionImpl.persist(SessionImpl.java:739)
         * at
         * io.quarkus.hibernate.orm.runtime.session.TransactionScopedSession.persist(TransactionScopedSession.java:145)
         * at
         * org.hibernate.engine.spi.SessionLazyDelegator.persist(SessionLazyDelegator.java:275)
         * at
         * org.hibernate.Session_OpdLahisOZ9nWRPXMsEFQmQU03A_Synthetic_ClientProxy.persist(Unknown
         * Source)
         * at
         * org.vetsandshelters.animal.infraestructure.AnimalRepositoryPostgreSQL.store(AnimalRepositoryPostgreSQL.java:71)
         * at
         * org.vetsandshelters.animal.infraestructure.AnimalRepositoryPostgreSQL_Subclass.store$$superforward(Unknown
         * Source)
         * at
         * org.vetsandshelters.animal.infraestructure.AnimalRepositoryPostgreSQL_Subclass$$function$$1.apply(Unknown
         * Source)
         * at
         * io.quarkus.arc.impl.AroundInvokeInvocationContext.proceed(AroundInvokeInvocationContext.java:73)
         * at
         * io.quarkus.arc.impl.AroundInvokeInvocationContext.proceed(AroundInvokeInvocationContext.java:62)
         * at
         * io.quarkus.narayana.jta.runtime.interceptor.TransactionalInterceptorBase.invokeInOurTx(TransactionalInterceptorBase.java:136)
         * at
         * io.quarkus.narayana.jta.runtime.interceptor.TransactionalInterceptorBase.invokeInOurTx(TransactionalInterceptorBase.java:107)
         * at
         * io.quarkus.narayana.jta.runtime.interceptor.TransactionalInterceptorRequired.doIntercept(TransactionalInterceptorRequired.java:38)
         * at
         * io.quarkus.narayana.jta.runtime.interceptor.TransactionalInterceptorBase.intercept(TransactionalInterceptorBase.java:61)
         * at
         * io.quarkus.narayana.jta.runtime.interceptor.TransactionalInterceptorRequired.intercept(TransactionalInterceptorRequired.java:32)
         * at
         * io.quarkus.narayana.jta.runtime.interceptor.TransactionalInterceptorRequired_Bean.intercept(Unknown
         * Source)
         * at
         * io.quarkus.arc.impl.InterceptorInvocation.invoke(InterceptorInvocation.java:42)
         * at
         * io.quarkus.arc.impl.AroundInvokeInvocationContext.perform(AroundInvokeInvocationContext.java:30)
         * at
         * io.quarkus.arc.impl.InvocationContexts.performAroundInvoke(InvocationContexts.java:27)
         * at
         * org.vetsandshelters.animal.infraestructure.AnimalRepositoryPostgreSQL_Subclass.store(Unknown
         * Source)
         * at
         * org.vetsandshelters.animal.infraestructure.AnimalRepositoryPostgreSQL_ClientProxy.store(Unknown
         * Source)
         * at
         * org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalCommandHandler.handle(UpdateAnimalCommandHandler.java:30)
         * at
         * org.vetsandshelters.animal.application.updateAnimal.UpdateAnimalCommandHandler_ClientProxy.handle(Unknown
         * Source)
         * at
         * org.vetsandshelters.animal.infraestructure.Controller.UpdateAnimalController.updateAnimal(UpdateAnimalController.java:34)
         * at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native
         * Method)
         * at
         * java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
         * at
         * java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
         * at java.base/java.lang.reflect.Method.invoke(Method.java:568)
         * at
         * org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:154)
         * at
         * org.jboss.resteasy.core.MethodInjectorImpl.invoke(MethodInjectorImpl.java:118)
         * at
         * org.jboss.resteasy.core.ResourceMethodInvoker.internalInvokeOnTarget(ResourceMethodInvoker.java:560)
         * at
         * org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTargetAfterFilter(ResourceMethodInvoker.java:452)
         * at
         * org.jboss.resteasy.core.ResourceMethodInvoker.lambda$invokeOnTarget$2(ResourceMethodInvoker.java:413)
         * at
         * org.jboss.resteasy.core.interception.jaxrs.PreMatchContainerRequestContext.filter(PreMatchContainerRequestContext.java:321)
         * at
         * org.jboss.resteasy.core.ResourceMethodInvoker.invokeOnTarget(ResourceMethodInvoker.java:415)
         * at
         * org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:378)
         * at
         * org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:356)
         * at
         * org.jboss.resteasy.core.ResourceMethodInvoker.invoke(ResourceMethodInvoker.java:70)
         * at
         * org.jboss.resteasy.core.SynchronousDispatcher.invoke(SynchronousDispatcher.java:429)
         * ... 15 more
         */
        em.merge(animal);
        return animal.getId();
    }

}
