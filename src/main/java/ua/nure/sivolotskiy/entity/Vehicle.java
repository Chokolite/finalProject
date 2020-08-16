package ua.nure.sivolotskiy.entity;

public class Vehicle extends Entity {
    private Long sits;
    private Type type;
    private TrunkSize trunkSize;
    private String carClass;
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

    public TrunkSize getTrunkSize() {
        return trunkSize;
    }

    public void setTrunkSize(TrunkSize trunkSize) {
        this.trunkSize = trunkSize;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
