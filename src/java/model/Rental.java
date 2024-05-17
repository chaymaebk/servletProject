package model;

import java.util.Date;
/**
 * User.java This is a model class represents a User entity
 *
 * @author Ramesh Fadatare
 *
 */
public class Rental {

    protected int id;
    protected int item;
    protected int user;
    protected Date start;
    protected Date end;
    protected int somme;

    public Rental() {
    }

    public Rental(int item, int user, Date start, Date end) {
        super();
        this.item = item;
        this.user = user;
        this.start = start;
        this.end = end;
    }

    public Rental(int id, int item, int user, Date start, Date end, int somme) {
        super();
        this.id = id;
        this.item = item;
        this.user = user;
        this.start = start;
        this.end = end;
        this.somme = somme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public Date getStart() {
        return (Date) start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public Date getEnd() {
        return (Date) end;
    }

    public int getSomme() {
        return somme;
    }
}
