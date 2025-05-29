package com.org.design.Structural.Proxy;


/**
 * <p>1. Logging: Logging has different levels, like INFO, DEBUG, WARN, etc.</p>
 *
 * <p>2. Caching proxy: CDNs are essentially massive caching proxies distributed globally. When you request
 * content (e.g., an image, video, or script) from a website, the request might first go to a CDN server
 * geographically closer to you. If the CDN has a cached copy, it serves it immediately, reducing latency
 * and server load for the origin server.</p>
 *
 * <p>3. Security checks: API Gateways/Middlewares: In a microservices architecture, an API Gateway often
 * acts as a proxy, intercepting all requests before they reach the actual services. It can perform logging,
 * authentication, rate limiting, and other cross-cutting concerns without modifying the individual services.</p>
 */
public class ProxyRunner {
    public static void main(String[] args) {
        try {
            EmployeeDao empTableDao = new EmployeeDaoProxy();
            empTableDao.create("USER", new EmployeeDo());
            System.out.println("Operation successful");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

