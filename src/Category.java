class Category {
    private final String name;
    private final String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public String toString(){
        return "Name: " + name + "\nDescription: " + description;
    }

    public boolean equals(Object obj){
        if(obj instanceof Category other){
            return name.equals(other.name) && description.equals(other.description);
        } else {
            return false;
        }
    }

    public void print(){
        System.out.println(this);
    }

    public void printProducts(){
        System.out.println("Products in " + name + ":");
        for(Product product : Product.getProducts()){
            if(product.getCategory().equals(name)){
                product.print();
            }
        }
    }
}
