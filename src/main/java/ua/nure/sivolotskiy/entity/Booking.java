package ua.nure.sivolotskiy.entity;

public class Booking extends Entity{
    private Trip trip;
    private Entity user;
    private String vehicle_specification;

public Booking(){
}
public Booking(Long id){
    setId(id);
}
    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Entity getUser() {
        return user;
    }

    public void setUser(Entity user) {
        this.user = user;
    }

    public String getVehicle_specification() {
        return vehicle_specification;
    }

    public void setVehicle_specification(String vehicle_specification) {
        this.vehicle_specification = vehicle_specification;
    }
}
