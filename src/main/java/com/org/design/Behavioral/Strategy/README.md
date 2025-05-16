# Intent:

Strategy is a behavioral design pattern that lets you define a family of algorithms, put each of them into a separate
class, and make their objects interchangeable.

# Problem:

Routing logic was tightly coupled with the main navigator class, making it hard to extend and maintain.

# Solution:

Apply the Strategy Pattern to encapsulate different route-building algorithms into separate classes. This allows easy
addition of new strategies (e.g., car, walking, cycling) without modifying the core navigator logic, promoting clean
code, better teamwork, and easier maintenance.

# TL;DR (Strategy Pattern via Google Maps Analogy):

Think of Google Maps — you can choose different route options: Car, Walking, Cycling, or Public Transport.

Instead of writing one giant class that handles all these routing modes, the Strategy Pattern lets you define each mode
as a separate "strategy" (e.g., CarRouteStrategy, WalkingRouteStrategy, etc.).

This keeps the main logic clean and flexible — Google Maps just plugs in the right strategy based on your choice,
without needing to rewrite the entire routing system.