# Concurrency Notes (Java)

## Intent

This folder is a small playground for core Java concurrency concepts: locking, fairness, thread coordination, and the
Executor framework.

## Concept map (topic -> example)

- **Reentrant locking / explicit locks** -> `ReentrantExample.java`
- **Fair vs unfair locks (starvation vs throughput)** -> `FairnessExample.java`
- **Try-lock + timeouts** -> `ConcurrencyRunner.java`
- **Thread communication (wait/notify)** -> `ThreadCommunication.java`
- **Executor framework / futures / latches** -> `ExecutorFrameworkRunner.java`

---

## `ConcurrencyRunner.java` (tryLock + critical section)

### What it demonstrates
- Protecting shared state (`balance`) with `ReentrantLock`
- `tryLock(timeout, unit)` to avoid blocking forever
- Always `unlock()` in `finally` when you successfully acquired the lock

### Notes / gotchas
- If `tryLock(...)` returns `false`, **don’t call `unlock()`**.
- The sample prints `Remaining balance = amount` (that should logically be the new balance).
  (Not a concurrency bug, just a logging bug.)
- `Thread.sleep(...)` inside the critical section is for demo only; it reduces throughput.

---

## `ReentrantExample.java` (reentrancy / hold count)

### What it demonstrates
- **Reentrancy**: the same thread can acquire the same lock multiple times.
- `outerMethod()` acquires the lock and calls `innerMethod()` which acquires it again.
- Requires the same number of `unlock()` calls as `lock()` calls.

### Key idea
- Internally, `ReentrantLock` tracks an owner thread + a **hold count**.

---

## `FairnessExample.java` (fair lock)

### What it demonstrates
- `new ReentrantLock(true)` enables a *fair* (roughly FIFO) policy.

### Why fairness matters
- **Fair lock**: reduces starvation risk, can reduce throughput.
- **Unfair lock (default)**: higher throughput, but a thread can repeatedly “barge” in front of others.

---

## `ThreadCommunication.java` (producer/consumer with wait/notifyAll)

### What it demonstrates
- Coordinating threads using intrinsic monitor (`synchronized`) + `wait()`/`notifyAll()`.
- Using a condition flag (`hasData`) checked in a `while` loop.

### Correct patterns shown here
- Always call `wait()` inside a `while` loop (spurious wakeups are real).
- `wait()`/`notifyAll()` are called on the same monitor (`this`) because methods are `synchronized`.

### Notes / extensions
- This is a *single-slot buffer*.
- For production code, prefer `BlockingQueue` for producer/consumer.

---

## `ExecutorFrameworkRunner.java` (executors / invokeAll / CountDownLatch)

### What it demonstrates
- `ExecutorService` with `newFixedThreadPool(...)`
- Submitting work (`submit`) and waiting for completion (`awaitTermination`)
- `invokeAll(tasks, timeout, unit)` to run a collection of tasks with a time limit
- `CountDownLatch` as a “wait until N things finish” primitive

### Notes / gotchas
- Always `shutdown()` the executor.
- A timed `invokeAll` can return Futures that are cancelled; calling `future.get()` may throw.
  (Handle `CancellationException` if you enable timeouts.)

---

## Quick API cheat sheet

### `synchronized` / monitor methods
- `synchronized` method/block: acquires intrinsic monitor on that object
- `wait()`: releases the monitor and suspends the thread until notified
- `notify()`: wakes one waiter
- `notifyAll()`: wakes all waiters (commonly safer for multi-condition scenarios)

### `Lock` / `ReentrantLock`
- `lock()` / `unlock()`
- `tryLock()` / `tryLock(timeout, unit)`
- `new ReentrantLock(true)` -> fair lock

### `ExecutorService`
- `submit(Runnable/Callable)` -> `Future`
- `invokeAll(Collection<Callable>, timeout, unit)` -> `List<Future>`
- `shutdown()` / `awaitTermination(...)`

### `CountDownLatch`
- `new CountDownLatch(n)`
- Worker calls `countDown()`
- Waiting thread calls `await()`

---

## Common pitfalls (things to watch for in interviews + real code)

- **Forgetting `unlock()`** → deadlocks (fix: `try/finally`).
- **Calling `unlock()` without owning the lock** → `IllegalMonitorStateException`.
- **Using `if` instead of `while` around `wait()`** → broken by spurious wakeups.
- **Ignoring interrupts** → threads can’t be stopped cleanly.
- **Using fairness everywhere** → can hurt throughput; enable only when needed.

