package items;

public abstract class Item {
    protected String name;
    protected String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // ENCAPSULAMIENTO
    public String getName() { return name; }
    public String getDescription() { return description; }

    // ABSTRACCIÓN: Método que deben implementar las subclases
    public abstract void use(entities.Character character);
}
