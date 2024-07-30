package ru.ram;

import java.util.Collection;
import java.util.Comparator;

public interface CarList {
/*    Ниже приведены условия задачи из практики, написать кастомный
    ArrayList, в котором нужно реализовать следующие методы
    После каждого пункта будет написан метод,
    который реализует этот пункт
    В качестве объекта использую класс Car
 */
//    - add(int index, E element)
    void add(int index, Car car);

//    - addAll(Collection<? extends E> c)
    void addAll (Collection<? extends Car> cars);

//    - clear()
    void clear();

//    - get(int index)
    Car get(int index);

//    - isEmpty()
    boolean isEmpty();

//    - remove(int index)
    boolean remove(int index);

//    - remove(Object o)
    boolean removeCar(Car car);

//    - sort(Comparator<? super E> c)
// Реализация метода sort() при помощи алгоритма merge sort
// (по условию задачи)
    void sort(Comparator<? super Car> cars);

}