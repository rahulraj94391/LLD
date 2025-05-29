package com.org.design.Structural.Proxy;

class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public void create(String client, EmployeeDo obj) throws Exception {
        System.out.println("created new row in the Employee table");
    }

    @Override
    public void delete(String client, int employeeId) throws Exception {
        System.out.println("deleted row with employeeId: " + employeeId);
    }

    @Override
    public EmployeeDo get(String client, int employeeId) throws Exception {
        System.out.println("fetching data from DB");
        return new EmployeeDo();
    }
}

