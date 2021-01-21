package immutable;

// 类声明为 final 表示无法创建Person类的子类
public final class Person {
    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.address = address;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
