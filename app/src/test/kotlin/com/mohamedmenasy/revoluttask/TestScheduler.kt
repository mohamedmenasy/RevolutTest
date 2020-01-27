package com.mohamedmenasy.revoluttask

import com.mohamedmenasy.core.executor.ExecutionScheduler
import com.mohamedmenasy.revoluttask.TestScheduler.Function.*
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.mock

internal class TestScheduler(
    private val scheduler: ExecutionScheduler = mock(
        ExecutionScheduler::class
    )
) : ExecutionScheduler {

  override fun ui(): Scheduler {
    scheduler.ui()
    return Schedulers.trampoline()
  }

  override fun highPriority(): Scheduler {
    scheduler.highPriority()
    return Schedulers.trampoline()
  }

  override fun lowPriority(): Scheduler {
    scheduler.lowPriority()
    return Schedulers.trampoline()
  }

  override fun <T> highPrioritySingle(): (Single<T>) -> Single<T> {
    scheduler.highPrioritySingle<T>()
    return { upstream: Single<T> ->
      upstream.subscribeOn(highPriority())
          .observeOn(ui())
    }
  }

  override fun <T> lowPrioritySingle(): (Single<T>) -> Single<T> {
    scheduler.lowPrioritySingle<T>()
    return { upstream: Single<T> ->
      upstream.subscribeOn(lowPriority())
          .observeOn(ui())
    }
  }

  override fun <T> highPriorityObservable(): (Observable<T>) -> Observable<T> {
    scheduler.highPriorityObservable<T>()
    return { upstream: Observable<T> ->
      upstream.subscribeOn(highPriority())
          .observeOn(ui())
    }
  }

  override fun <T> lowPriorityObservable(): (Observable<T>) -> Observable<T> {
    scheduler.lowPriorityObservable<T>()
    return { upstream: Observable<T> ->
      upstream.subscribeOn(lowPriority())
          .observeOn(ui())
    }
  }

  override fun <T> highPriorityFlowable(): (Flowable<T>) -> Flowable<T> {
    scheduler.highPriorityFlowable<T>()
    return { upstream: Flowable<T> ->
      upstream.subscribeOn(highPriority())
          .observeOn(ui())
    }
  }

  override fun <T> lowPriorityFlowable(): (Flowable<T>) -> Flowable<T> {
    scheduler.lowPriorityFlowable<T>()
    return { upstream: Flowable<T> ->
      upstream.subscribeOn(lowPriority())
          .observeOn(ui())
    }
  }

  override fun highPriorityCompletable(): (Completable) -> Completable {
    scheduler.highPriorityCompletable()
    return { upstream: Completable ->
      upstream.subscribeOn(highPriority())
          .observeOn(ui())
    }
  }

  override fun lowPriorityCompletable(): (Completable) -> Completable {
    scheduler.lowPriorityCompletable()
    return { upstream: Completable ->
      upstream.subscribeOn(lowPriority())
          .observeOn(ui())
    }
  }

  internal infix fun verify(function: Function) {
    when (function) {
      is UI -> verify(scheduler).ui()
      is HighPriority -> verify(scheduler).highPriority()
      is LowPriority -> verify(scheduler).lowPriority()

      is HighPrioritySingle -> verify(scheduler).highPrioritySingle<Any>()
      is LowPrioritySingle -> verify(scheduler).lowPrioritySingle<Any>()

      is HighPriorityObservable -> verify(scheduler).highPriorityObservable<Any>()
      is LowPriorityObservable -> verify(scheduler).lowPriorityObservable<Any>()

      is HighPriorityFlowable -> verify(scheduler).highPriorityFlowable<Any>()
      is LowPriorityFlowable -> verify(scheduler).lowPriorityFlowable<Any>()

      is HighPriorityCompletable -> verify(scheduler).highPriorityCompletable()
      is LowPriorityCompletable -> verify(scheduler).lowPriorityCompletable()
    }
  }

  internal sealed class Function {
    object UI : Function()
    object HighPriority : Function()
    object LowPriority : Function()

    object HighPrioritySingle : Function()
    object LowPrioritySingle : Function()

    object HighPriorityObservable : Function()
    object LowPriorityObservable : Function()

    object HighPriorityFlowable : Function()
    object LowPriorityFlowable : Function()

    object HighPriorityCompletable : Function()
    object LowPriorityCompletable : Function()
  }
}
