package ua.nure.sivolotskiy.entity;

public class Vehicle extends Entity {
    private Long sits;
    private Type type;
    private Trunk_Size trunk_size;
    private String car_class;
    private Condition condition;

    public Vehicle() {

    }

    public Vehicle(Long id) {
        setId(id);
    }

    public Long getSits() {
        return sits;
    }

    public void setSits(Long sits) {
        this.sits = sits;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Trunk_Size getTrunk_size() {
        return trunk_size;
    }

    public void setTrunk_size(Trunk_Size trunk_size) {
        this.trunk_size = trunk_size;
    }

    public String getCar_class() {
        return car_class;
    }

    public void setCar_class(String car_class) {
        this.car_class = car_class;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
