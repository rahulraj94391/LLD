package com.org.design.Structural.Decorator;

public class PizzaCustomization {
    public static void runPizzaCustomization() {

        // Let's get a base Margherita pizza with additional toppings on it.
        BasePizza margheritaPizza = new Margherita();
        margheritaPizza = new Mushroom(margheritaPizza);
        margheritaPizza = new ExtraCheese(margheritaPizza);

        System.out.println(margheritaPizza.cost());
        System.out.println(margheritaPizza.getDescription());



        BasePizza farmhousePizza = new FarmHouse();
        farmhousePizza = new ExtraCheese(farmhousePizza);
        farmhousePizza = new ExtraCheese(farmhousePizza);

        System.out.println(farmhousePizza.cost());
        System.out.println(farmhousePizza.getDescription());
    }
}


// Mushroom is-a BasePizza, also Mushroom has-a BasePizza.
class Mushroom extends ToppingDecorator {
    public Mushroom(BasePizza basePizza) {
        super(basePizza);
    }

    @Override
    public String getDescription() {
        return basePizza.getDescription() + ", Mushroom";
    }

    @Override
    public float cost() {
        return basePizza.cost() + 15;
    }
}

// ExtraCheese is-a BasePizza, also ExtraCheese has-a BasePizza.
class ExtraCheese extends ToppingDecorator {
    public ExtraCheese(BasePizza basePizza) {
        super(basePizza);
    }

    @Override
    public String getDescription() {
        return basePizza.getDescription() + ", Extra_Cheese";
    }

    @Override
    public float cost() {
        return basePizza.cost() + 10;
    }
}


// ToppingDecorator is-a BasePizza, also ToppingDecorator has-a BasePizza.
abstract class ToppingDecorator implements BasePizza {
    protected BasePizza basePizza;

    public ToppingDecorator(BasePizza basePizza) {
        this.basePizza = basePizza;
    }
}


// Margherita is a Pizza
class Margherita implements BasePizza {
    @Override
    public float cost() {
        return 100;
    }

    public String getDescription() {
        return "Margherita";
    }
}

// VegDelight is a Pizza
class VegDelight implements BasePizza {
    @Override
    public float cost() {
        return 120;
    }

    public String getDescription() {
        return "VegDelight";
    }
}

// FarmHouse is a Pizza
class FarmHouse implements BasePizza {
    @Override
    public float cost() {
        return 200;
    }

    public String getDescription() {
        return "FarmHouse";
    }
}


interface BasePizza {
    String getDescription();

    float cost();
}